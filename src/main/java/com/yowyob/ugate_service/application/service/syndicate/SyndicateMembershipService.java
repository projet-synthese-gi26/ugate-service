package com.yowyob.ugate_service.application.service.syndicate;


import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
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
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyndicateMembershipService {

    private final MembershipRequestRepository requestRepository;
    private final SyndicatMemberRepository memberRepository;
    private final UserGatewayPort userGateway;
    private final NotificationPort notificationPort;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final SyndicatRepository syndicatRepository;


    /**
     * Un utilisateur demande à rejoindre un syndicat via une branche.
     */
    @Transactional
    public Mono<MembershipRequest> requestToJoin(UUID syndicatId, UUID branchId, String motivation) {
        return getCurrentUserId()
                .flatMap(userId ->

                        requestRepository.findLastRequest(userId, syndicatId)
                                .flatMap(lastRequest -> {
                                    if (lastRequest.status() == MembershipRequest.MembershipStatus.PENDING) {
                                        return Mono.error(new IllegalStateException("Vous avez déjà une demande en attente."));
                                    }
                                    if (lastRequest.status() == MembershipRequest.MembershipStatus.APPROVED) {
                                        return Mono.error(new IllegalStateException("Vous êtes déjà membre de ce syndicat."));
                                    }

                                    return createNewRequest(userId, syndicatId, branchId, motivation);
                                })
                                .switchIfEmpty(createNewRequest(userId, syndicatId, branchId, motivation))
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
     * Un admin de syndicat approuve ou rejette une demande.
     */
    @Transactional
    public Mono<Void> processRequest(UUID requestId, boolean approve, String rejectionReason) {
        // TODO: Vérifier que l'utilisateur courant a les droits d'admin sur le syndicat

        return requestRepository.findById(requestId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Demande introuvable")))
                .flatMap(request -> {
                    if (request.status() != MembershipRequest.MembershipStatus.PENDING) {
                        return Mono.error(new IllegalStateException("Cette demande a déjà été traitée."));
                    }

                    if (approve) {
                        // 1. Mettre à jour la demande en APPROUVÉ
                        MembershipRequest approvedRequest = new MembershipRequest(
                                request.id(), request.userId(), request.syndicatId(), request.branchId(),
                                MembershipRequest.MembershipStatus.APPROVED, request.motivation(),
                                null, request.createdAt(), Instant.now()
                        );

                        // 2. Créer l'entrée dans la table des membres
                        SyndicatMember newMember = new SyndicatMember(
                                request.syndicatId(), request.userId(), Instant.now(), true, RoleTypeEnum.CLIENT
                        );

                        // On combine les deux sauvegardes dans une transaction
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


    @Transactional
    public Mono<Void> addMemberByAdmin(UUID syndicateId, String email, String firstName, String lastName) {
        return syndicatRepository.findById(syndicateId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Syndicat introuvable")))
                .flatMap(syndicate ->

                        userGateway.registerUser(email, firstName, lastName, "00000000")
                                .flatMap(extUser -> {
                                    UUID userId = extUser.id();


                                    User localUser = new User(userId, firstName + " "+ lastName, null, email);
                                    Profile localProfile = new Profile(
                                            UUID.randomUUID(), userId, null, firstName, lastName,
                                            null, null, null, null, Instant.now(), Instant.now()
                                    );


                                    SyndicatMember member = SyndicatMember.create(syndicateId, userId, RoleTypeEnum.CLIENT);

                                    return userRepository.save(localUser)
                                            .then(profileRepository.save(localProfile))
                                            .then(memberRepository.save(member))
                                            .then(notificationPort.sendSyndicateInvitation(email, syndicate.name(), firstName));
                                })
                );
    }

    private Mono<UUID> getCurrentUserId() {
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> UUID.fromString(((Jwt) ctx.getAuthentication().getPrincipal()).getSubject()));
    }
}