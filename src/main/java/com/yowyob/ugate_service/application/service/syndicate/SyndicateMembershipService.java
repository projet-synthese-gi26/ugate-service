package com.yowyob.ugate_service.application.service.syndicate;

import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateFullStatsResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.MembershipRequest;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Profile;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.SyndicatMember;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.User;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.enumeration.RoleTypeEnum;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyndicateMembershipService {

    private final MembershipRequestRepository requestRepository;
    private final SyndicatMemberRepository memberRepository;
    private final SyndicatRepository syndicatRepository;
    private final BranchRepository branchRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    // Pour les stats (si besoin de compter les publications)
    private final PublicationRepository publicationRepository;

    private final UserGatewayPort userGateway;
    private final NotificationPort notificationPort;

    /**
     * Un utilisateur demande à rejoindre un syndicat via une branche spécifique.
     */
    @Transactional
    public Mono<MembershipRequest> requestToJoin(UUID syndicatId, UUID branchId, String motivation) {
        return getCurrentUserId()
                .flatMap(userId ->
                        memberRepository.existsBySyndicatIdAndUserId(syndicatId, userId)
                                .flatMap(isMember -> {
                                    if (Boolean.TRUE.equals(isMember)) {
                                        return Mono.error(new IllegalStateException("Vous êtes déjà membre de ce syndicat."));
                                    }

                                    return requestRepository.findLastRequest(userId, syndicatId)
                                            .flatMap(lastRequest -> {
                                                if (lastRequest.status() == MembershipRequest.MembershipStatus.PENDING) {
                                                    return Mono.error(new IllegalStateException("Une demande est déjà en cours de traitement."));
                                                }
                                                return createNewRequest(userId, syndicatId, branchId, motivation);
                                            })
                                            .switchIfEmpty(createNewRequest(userId, syndicatId, branchId, motivation));
                                })
                );
    }

    private Mono<MembershipRequest> createNewRequest(UUID userId, UUID syndicatId, UUID branchId, String motivation) {
        MembershipRequest newRequest = new MembershipRequest(
                null, userId, syndicatId, branchId, MembershipRequest.MembershipStatus.PENDING,
                motivation, null, Instant.now(), Instant.now()
        );
        return requestRepository.save(newRequest);
    }

    /**
     * Un admin traite (approuve ou rejette) une demande.
     */
    @Transactional
    public Mono<Void> processRequest(UUID requestId, boolean approve, String rejectionReason) {
        return requestRepository.findById(requestId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Demande introuvable")))
                .flatMap(request -> {
                    if (request.status() != MembershipRequest.MembershipStatus.PENDING) {
                        return Mono.error(new IllegalStateException("Cette demande a déjà été traitée."));
                    }

                    if (approve) {
                        MembershipRequest approvedRequest = new MembershipRequest(
                                request.id(), request.userId(), request.syndicatId(), request.branchId(),
                                MembershipRequest.MembershipStatus.APPROVED, request.motivation(),
                                null, request.createdAt(), Instant.now()
                        );

                        SyndicatMember newMember = SyndicatMember.createLocal(
                                request.syndicatId(),
                                request.branchId(),
                                request.userId(),
                                RoleTypeEnum.CLIENT
                        );

                        return requestRepository.save(approvedRequest)
                                .then(memberRepository.save(newMember))
                                .then();
                    } else {
                        // Rejet
                        if (rejectionReason == null || rejectionReason.isBlank()) {
                            return Mono.error(new IllegalArgumentException("Le motif de rejet est obligatoire."));
                        }
                        MembershipRequest rejectedRequest = new MembershipRequest(
                                request.id(), request.userId(), request.syndicatId(), request.branchId(),
                                MembershipRequest.MembershipStatus.REJECTED, request.motivation(),
                                rejectionReason, request.createdAt(), Instant.now()
                        );
                        return requestRepository.save(rejectedRequest).then();
                    }
                });
    }

    // --- LECTURE DES DEMANDES ---

    /**
     * Récupérer toutes les demandes en attente pour un syndicat (toutes branches).
     */
    public Flux<MembershipRequest> getRequestsBySyndicate(UUID syndicatId) {
        return requestRepository.findBySyndicatIdAndStatus(syndicatId, MembershipRequest.MembershipStatus.PENDING, null);
    }

    /**
     * Récupérer les demandes en attente pour une branche spécifique.
     */
    public Flux<MembershipRequest> getRequestsByBranch(UUID branchId) {
        return requestRepository.findByBranchIdAndStatus(branchId, MembershipRequest.MembershipStatus.PENDING);
    }

    /**
     * Détails d'une demande spécifique.
     */
    public Mono<MembershipRequest> getRequestDetails(UUID requestId) {
        return requestRepository.findById(requestId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Demande non trouvée")));
    }

    // --- GESTION DES RÔLES ---

    /**
     * Modifier le rôle d'un membre dans une branche spécifique.
     * Protection : Impossible de modifier le rôle du créateur du syndicat.
     */
    @Transactional
    public Mono<Void> updateMemberRole(UUID syndicatId, UUID targetUserId, RoleTypeEnum newRole) {
        return memberRepository.findBySyndicatIdAndUserId(syndicatId, targetUserId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Membre introuvable dans ce syndicat")))
                .flatMap(member ->
                        syndicatRepository.findById(syndicatId)
                                .flatMap(syndicat -> {
                                    if (syndicat.creatorId().equals(targetUserId)) {
                                        return Mono.error(new IllegalStateException("Impossible de modifier le rôle du créateur du syndicat."));
                                    }
                                    SyndicatMember updatedMember = member.withRole(newRole);
                                    return memberRepository.save(updatedMember);
                                })
                )
                .then();
    }


    public Mono<SyndicateFullStatsResponse> getSyndicateStats(UUID syndicatId) {
        return Mono.zip(
                memberRepository.countBySyndicatIdAndIsActiveTrue(syndicatId)
                        .defaultIfEmpty(0L),
                branchRepository.countBySyndicatId(syndicatId)
                        .defaultIfEmpty(0L),
                requestRepository.countBySyndicatIdAndStatus(syndicatId, MembershipRequest.MembershipStatus.PENDING)
                        .defaultIfEmpty(0L),

                // 4. Services (Actuellement 0 car le lien Service <-> Syndicat n'est pas encore défini dans l'entité ServiceEntity)
                Mono.just(0L),


                publicationRepository.countBySyndicatId(syndicatId)
                        .defaultIfEmpty(0L)
        ).map(tuple -> new SyndicateFullStatsResponse(
                tuple.getT1(), // totalMembers
                tuple.getT2(), // totalBranches
                tuple.getT3(), // pendingRequests
                tuple.getT4(), // activeServices
                tuple.getT5()  // totalPublications
        ));
    }



    /**
     * Ajouter manuellement un membre (par email) dans une branche spécifique.
     */
    @Transactional
    public Mono<Void> addMemberByAdmin(UUID syndicatId, UUID branchId, String email, String firstName, String lastName) {
        return syndicatRepository.findById(syndicatId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Syndicat introuvable")))
                .zipWith(branchRepository.findById(branchId)
                        .switchIfEmpty(Mono.error(new ResourceNotFoundException("Branche introuvable"))))
                .flatMap(tuple -> {
                    var syndicat = tuple.getT1();
                    return userRepository.findByEmail(email)
                            .flatMap(existingUser -> Mono.just(existingUser.getId()))
                            .switchIfEmpty(
                                    userGateway.registerUser(email, firstName, lastName, "00000000")
                                            .flatMap(extUser -> {
                                                User localUser = new User(
                                                        extUser.id(),
                                                        firstName + " " + lastName,
                                                        null,
                                                        email
                                                );
                                                Profile localProfile = Profile.createNew(extUser.id(), firstName, lastName);

                                                return userRepository.save(localUser)
                                                        .then(profileRepository.save(localProfile))
                                                        .thenReturn(extUser.id());
                                            })
                            )
                            .flatMap(userId -> {
                                return memberRepository.existsBySyndicatIdAndUserId(syndicatId, userId)
                                        .flatMap(isMember -> {
                                            if (isMember) return Mono.error(new IllegalStateException("Déjà membre"));

                                            SyndicatMember member = SyndicatMember.createLocal(syndicatId, branchId, userId, RoleTypeEnum.CLIENT);
                                            return memberRepository.save(member)
                                                    .then(
                                                            notificationPort.sendSyndicateInvitation(email, syndicat.name(), firstName)
                                                                    .onErrorResume(e -> {
                                                                        log.error("⚠️ ÉCHEC NOTIFICATION : Le membre {} a été ajouté mais l'invitation n'a pas pu être envoyée. Erreur: {}", email, e.getMessage());
                                                                        return Mono.empty();
                                                                    })
                                                    );
                                        });
                            });
                })
                .then();
    }

    private Mono<UUID> getCurrentUserId() {
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> UUID.fromString(((Jwt) ctx.getAuthentication().getPrincipal()).getSubject()));
    }
}