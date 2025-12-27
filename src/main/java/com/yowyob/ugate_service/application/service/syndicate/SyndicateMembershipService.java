package com.yowyob.ugate_service.application.service.syndicate;



import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.MembershipRequest;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.SyndicatMember;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.enumeration.MemberRole;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.MembershipRequestRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    /**
     * Un utilisateur demande à rejoindre un syndicat via une branche.
     */
    @Transactional
    public Mono<MembershipRequest> requestToJoin(UUID syndicatId, UUID branchId, String motivation) {
        return getCurrentUserId()
                .flatMap(userId ->
                        // Vérifier s'il n'y a pas déjà une demande en attente
                        requestRepository.findLastRequest(userId, syndicatId)
                                .flatMap(lastRequest -> {
                                    if (lastRequest.status() == MembershipRequest.MembershipStatus.PENDING) {
                                        return Mono.error(new IllegalStateException("Vous avez déjà une demande en attente."));
                                    }
                                    if (lastRequest.status() == MembershipRequest.MembershipStatus.APPROVED) {
                                        return Mono.error(new IllegalStateException("Vous êtes déjà membre de ce syndicat."));
                                    }
                                    // Si REJECTED, on continue et on autorise une nouvelle demande
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
                                request.syndicatId(), request.userId(), Instant.now(), true, MemberRole.MEMBER
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

    private Mono<UUID> getCurrentUserId() {
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> UUID.fromString(((Jwt) ctx.getAuthentication().getPrincipal()).getSubject()));
    }
}