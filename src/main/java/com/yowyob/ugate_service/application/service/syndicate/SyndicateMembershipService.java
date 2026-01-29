package com.yowyob.ugate_service.application.service.syndicate;

import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.AddUserResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.MemberResponse;
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
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Instant;
import java.util.List;
import java.util.Map;
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
                                .then(memberRepository.insertMember(
                                        request.syndicatId(),
                                        request.userId(),
                                        request.branchId(),
                                        true,
                                        RoleTypeEnum.CLIENT.name()
                                ));
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



    
    //TODO LES Méthodes qui suivent sont à refactoriser
    @Transactional
    public Mono<AddUserResponse> addMemberByAdmin(UUID syndicatId, UUID branchId,
                                                  String email, String firstName, String lastName) {
        log.info("🔵 Début ajout membre - Syndicat: {}, Branche: {}, Email: {}",
                syndicatId, branchId, email);

        return syndicatRepository.findById(syndicatId)
                .doOnNext(s -> log.debug("✅ Syndicat trouvé: {}", s.name()))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Syndicat introuvable")))
                .zipWith(branchRepository.findById(branchId)
                        .doOnNext(b -> log.debug("✅ Branche trouvée: {}", branchId))
                        .switchIfEmpty(Mono.error(new ResourceNotFoundException("Branche introuvable"))))
                .flatMap(tuple -> {
                    var syndicat = tuple.getT1();
                    log.debug("📋 Vérification existence utilisateur: {}", email);

                    // Mono<Tuple2<User, Boolean>> où Boolean indique si c'est un nouvel user
                    Mono<Tuple2<User, Boolean>> userMono = userRepository.findByEmail(email)
                            .doOnNext(u -> log.info("✅ Utilisateur existant trouvé: {} (ID: {})",
                                    email, u.getId()))
                            .map(user -> Tuples.of(user, false)) // Utilisateur existant
                            .switchIfEmpty(Mono.defer(() -> {
                                log.info("🆕 Utilisateur inexistant, création externe + locale pour: {}", email);

                                return userGateway.registerUser(email, firstName, lastName, "00000000")
                                        .doOnNext(extUser -> log.info(
                                                "✅ Utilisateur créé dans système externe - ID: {}, Email: {}",
                                                extUser.id(), email))
                                        .doOnError(e -> log.error(
                                                "❌ ERREUR création utilisateur externe pour {}: {}",
                                                email, e.getMessage(), e))
                                        .flatMap(extUser -> {
                                            User localUser = new User(
                                                    extUser.id(),
                                                    firstName + " " + lastName,
                                                    null,
                                                    email
                                            );
                                            Profile localProfile = Profile.createNew(
                                                    extUser.id(), firstName, lastName);

                                            log.debug("💾 Sauvegarde utilisateur local + profil - ID: {}", extUser.id());

                                            return userRepository.save(localUser)
                                                    .doOnSuccess(u -> log.info("✅ User local sauvegardé: {} - {}",
                                                            u.getId(), u.email()))
                                                    .doOnError(e -> log.error(
                                                            "❌ ERREUR sauvegarde user local (ID: {}): {} - " +
                                                                    "ATTENTION: User externe créé mais pas en local!",
                                                            extUser.id(), e.getMessage(), e))
                                                    .zipWith(profileRepository.save(localProfile)
                                                            .doOnSuccess(p -> log.debug("✅ Profil sauvegardé: {}", p.userId()))
                                                            .doOnError(e -> log.error(
                                                                    "❌ ERREUR sauvegarde profil (ID: {}): {} - " +
                                                                            "ATTENTION: User externe créé, user local sauvegardé mais pas le profil!",
                                                                    extUser.id(), e.getMessage(), e)))
                                                    .map(userAndProfile -> Tuples.of(userAndProfile.getT1(), true)) // Nouvel utilisateur
                                                    .onErrorResume(e -> {
                                                        log.error(
                                                                "🔴 ROLLBACK NÉCESSAIRE - Échec sauvegarde locale " +
                                                                        "mais user externe créé (ID: {}, Email: {}). " +
                                                                        "Action requise: Nettoyer user externe ou réessayer sync.",
                                                                extUser.id(), email, e);

                                                        return Mono.error(new IllegalStateException(
                                                                "Échec synchronisation user local/externe pour " + email, e));
                                                    });
                                        });
                            }));

                    return userMono.flatMap(userTuple -> {
                        User user = userTuple.getT1();
                        boolean isNewUser = userTuple.getT2();
                        UUID userId = user.getId();

                        log.debug("🔍 Vérification si déjà membre - User: {}, Syndicat: {}",
                                userId, syndicatId);

                        return memberRepository.existsBySyndicatIdAndUserId(syndicatId, userId)
                                .doOnNext(exists -> log.debug("Résultat vérification membre: {}", exists))
                                .flatMap(isMember -> {
                                    if (isMember) {
                                        log.warn("⚠️ Utilisateur {} déjà membre du syndicat {}",
                                                userId, syndicatId);
                                        return Mono.error(new IllegalStateException("Déjà membre"));
                                    }

                                    log.info("➕ Insertion nouveau membre - User: {}, Syndicat: {}, Branche: {}",
                                            userId, syndicatId, branchId);

                                    SyndicatMember member = SyndicatMember.createLocal(
                                            syndicatId, branchId, userId, RoleTypeEnum.CLIENT);

                                    return memberRepository.insertMember(
                                                    syndicatId,
                                                    userId,
                                                    branchId,
                                                    true,
                                                    RoleTypeEnum.CLIENT.name()
                                            )
                                            .doOnSuccess(v -> log.info(
                                                    "✅ Membre inséré avec succès - User: {}, Syndicat: {}",
                                                    userId, syndicatId))
                                            .doOnError(e -> log.error(
                                                    "❌ ERREUR insertion membre - User: {}, Syndicat: {}: {}",
                                                    userId, syndicatId, e.getMessage(), e))
                                            .thenReturn(Tuples.of(user, member, syndicat.name(), isNewUser));
                                });
                    });
                })
                // Construction de la réponse avec notification APRÈS le commit
                .flatMap(resultTuple -> {
                    User user = resultTuple.getT1();
                    SyndicatMember member = resultTuple.getT2();
                    String syndicatName = resultTuple.getT3();
                    boolean isNewUser = resultTuple.getT4();

                    log.info("📧 Tentative envoi notification invitation - Email: {}", email);

                    // Créer l'objet de données pour la réponse
                    Map<String, Object> responseData = Map.of(
                            "user", user,
                            "member", member,
                            "isNewUser", isNewUser
                    );

                    return notificationPort.sendSyndicateInvitation(email, syndicatName, firstName)
                            .doOnSuccess(v -> log.info("✅ Notification envoyée avec succès à {}", email))
                            .doOnError(e -> log.error(
                                    "⚠️ ÉCHEC NOTIFICATION (membre ajouté mais email non envoyé) - " +
                                            "Email: {}, Syndicat: {}, Erreur: {}",
                                    email, syndicatName, e.getMessage()))
                            .onErrorResume(e -> {
                                log.warn("♻️ Erreur notification ignorée (membre déjà en DB)");
                                return Mono.empty();
                            })
                            .thenReturn(new AddUserResponse(
                                    "Membre ajouté avec succès" + (isNewUser ? " (nouvel utilisateur créé)" : ""),
                                    responseData
                            ));
                })
                .doOnSuccess(response -> log.info(
                        "🎉 Ajout membre terminé avec succès - Email: {}, Syndicat: {}, Data: {}",
                        email, syndicatId, response.data()))
                .doOnError(e -> log.error(
                        "❌ ÉCHEC GLOBAL ajout membre - Email: {}, Syndicat: {}, Erreur: {}",
                        email, syndicatId, e.getMessage()));
    }

    public Flux<MemberResponse> getSyndicateMembers(UUID syndicatId) {
        log.info("🔍 Récupération membres du syndicat: {}", syndicatId);

        return memberRepository.findBySyndicatId(syndicatId)
                .doOnNext(member -> log.debug("👤 Membre trouvé - UserID: {}, Role: {}, BranchID: {}",
                        member.userId(), member.role(), member.branchId()))
                .flatMap(member -> {
                    log.debug("🔎 Recherche infos utilisateur - ID: {}", member.userId());

                    return userGateway.findById(member.userId())
                            .doOnNext(extUser -> log.debug(
                                    "✅ User trouvé dans gateway externe: {} {} ({})",
                                    extUser.firstName(), extUser.lastName(), extUser.email()))
                            .doOnError(e -> log.warn(
                                    "⚠️ Erreur gateway externe pour user {}: {}, tentative fallback local",
                                    member.userId(), e.getMessage()))
                            .onErrorResume(e -> {
                                // ✅ Fallback aussi sur erreur, pas seulement sur empty
                                log.debug("♻️ Fallback vers repository local pour user {}", member.userId());
                                return Mono.empty();
                            })
                            .switchIfEmpty(Mono.defer(() -> {
                                log.debug("🔄 Gateway vide, recherche user {} dans repository local",
                                        member.userId());

                                return userRepository.findById(member.userId())
                                        .doOnNext(localUser -> log.debug(
                                                "✅ User trouvé en local: {} ({})",
                                                localUser.name(), localUser.email()))
                                        .map(localUser -> {
                                            // ✅ Parsing robuste avec limit
                                            String[] parts = localUser.name().split(" ", 2);
                                            String firstName = parts[0];
                                            String lastName = parts.length > 1 ? parts[1] : "";

                                            log.debug("📝 Parsing nom local: '{}' → Prénom: '{}', Nom: '{}'",
                                                    localUser.name(), firstName, lastName);

                                            return new ExternalUserInfo(
                                                    localUser.id(),
                                                    firstName,
                                                    lastName,
                                                    localUser.email(),
                                                    localUser.phoneNumber(),
                                                    List.of(),
                                                    List.of()
                                            );
                                        })
                                        .switchIfEmpty(Mono.defer(() -> {
                                            log.error(
                                                    "❌ DONNÉE INCOHÉRENTE - User {} (membre du syndicat {}) " +
                                                            "introuvable ni dans gateway ni dans repo local!",
                                                    member.userId(), syndicatId);
                                            return Mono.empty();
                                        }));
                            }))
                            .map(userInfo -> {
                                log.debug("✅ MemberResponse créé pour user: {} {} ({})",
                                        userInfo.firstName(), userInfo.lastName(), userInfo.email());

                                return new MemberResponse(
                                        userInfo.id(),
                                        userInfo.firstName(),
                                        userInfo.lastName(),
                                        userInfo.email(),
                                        null, // Avatar URL - TODO: récupérer depuis ProfileRepository si nécessaire
                                        member.role(),
                                        member.branchId(),
                                        member.joinedAt()
                                );
                            });
                })
                .doOnComplete(() -> log.info("✅ Récupération membres terminée pour syndicat {}",
                        syndicatId))
                .doOnError(e -> log.error(
                        "❌ Erreur récupération membres syndicat {}: {}",
                        syndicatId, e.getMessage(), e));
    }

    private Mono<UUID> getCurrentUserId() {
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> UUID.fromString(((Jwt) ctx.getAuthentication().getPrincipal()).getSubject()));
    }
}