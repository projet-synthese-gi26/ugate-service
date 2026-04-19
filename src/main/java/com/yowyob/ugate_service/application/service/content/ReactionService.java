package com.yowyob.ugate_service.application.service.content;

import com.yowyob.ugate_service.domain.enumeration.ReactionTypeEnum;
import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.model.PublicationModel;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.ReactionPersistencePort;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.Loggers;
import reactor.util.Logger;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.UUID;

@AllArgsConstructor
public class ReactionService {

    private static final Logger log = Loggers.getLogger(ReactionService.class);
    private static final Duration NOTIFICATION_TIMEOUT = Duration.ofSeconds(3);

    // Flux métier principal
    private final PublicationService publicationService;
    private final ReactionPersistencePort reactionPersistencePort;
    private final PublicationPersistencePort publicationPersistencePort;
    private final UserGatewayPort userGatewayPort;
    private final NotificationPort notificationPort;

    /**
     * Flux principal :
     * 1) save reaction
     * 2) increment likes
     * 3) notification BEST-EFFORT (ne bloque jamais)
     */
    public Mono<Void> addReactionToPublication(UUID publicationId, UUID userId, ReactionTypeEnum reactionType) {
        log.info("addReactionToPublication called for publicationId: {}, userId: {}", publicationId, userId);

        return reactionPersistencePort.saveReaction(publicationId, reactionType, userId)
                .doOnSuccess(v -> log.info("✅ Reaction saved for publicationId: {}", publicationId))
                .then(publicationService.incrementLikes(publicationId)
                        .doOnSuccess(v -> log.info("✅ Likes incremented for publicationId: {}", publicationId))
                        .doOnError(e -> log.error("❌ Error incrementing likes for publicationId: {}", publicationId, e))
                )
                // Notification totalement non bloquante
                .then(sendReactionNotificationBestEffort(publicationId, userId))
                .then()
                .doOnSuccess(v -> log.info("🎉 addReactionToPublication completed successfully for publicationId: {}", publicationId))
                .doOnError(e -> log.error("❌ addReactionToPublication encountered an error for publicationId: {}", publicationId, e));
    }

    /**
     * Notification BEST-EFFORT :
     * - Si publication/user gateway/notification tombe => log + ignore
     * - Timeout court pour éviter un blocage
     */
    private Mono<Void> sendReactionNotificationBestEffort(UUID publicationId, UUID reactorUserId) {

        Mono<PublicationModel> publicationMono = publicationPersistencePort.findById(publicationId)
                .doOnNext(p -> log.debug("✅ Found publication model: {}", p.getId()))
                .doOnError(e -> log.warn("⚠️ Error finding publication model for id: {} : {}", publicationId, e.getMessage()))
                .onErrorResume(e -> Mono.empty());

        Mono<ExternalUserInfo> reactorMono = userGatewayPort.findById(reactorUserId)
                .doOnNext(u -> log.debug("✅ Found reactor user info: {}", u.id()))
                .doOnError(e -> log.warn("⚠️ Error finding reactor user info for id: {} : {}", reactorUserId, e.getMessage()))
                .onErrorResume(e -> Mono.empty());

        return Mono.zip(publicationMono, reactorMono)
                .flatMap(tuple -> {
                    Tuple2<PublicationModel, ExternalUserInfo> typedTuple = tuple;
                    PublicationModel publication = typedTuple.getT1();
                    ExternalUserInfo reactorInfo = typedTuple.getT2();

                    return userGatewayPort.findById(publication.getAuthorId())
                            .doOnNext(pubAuthorInfo -> log.debug("✅ Found publication author info: {}", pubAuthorInfo.id()))
                            .doOnError(e -> log.warn(
                                    "⚠️ Error finding publication author info for id: {} : {}",
                                    publication.getAuthorId(),
                                    e.getMessage()
                            ))
                            .onErrorResume(e -> Mono.empty())
                            .flatMap(publicationAuthorInfo -> {
                                String authorEmail = publicationAuthorInfo.email();
                                if (authorEmail == null || authorEmail.isBlank()) {
                                    log.warn("⚠️ Notification réaction ignorée: email auteur vide (publicationId={})", publicationId);
                                    return Mono.empty();
                                }

                                String reactorFirstName = safeText(reactorInfo.firstName(), "Un utilisateur");
                                String title = safeText(publication.getContent(), "Nouvelle réaction");

                                return notificationPort.sendPublicationReactAlert(
                                                authorEmail,
                                                title,
                                                reactorFirstName
                                        )
                                        .doOnSuccess(v -> log.debug("✅ Publication reaction alert sent for publicationId: {}", publicationId))
                                        .doOnError(e -> log.warn(
                                                "⚠️ Error sending reaction notification (ignored) for publicationId: {} : {}",
                                                publicationId,
                                                e.getMessage()
                                        ))
                                        .onErrorResume(e -> Mono.empty());
                            });
                })
                // Si publicationMono ou reactorMono est vide => zip ne produit rien => on ne notifie pas
                .timeout(NOTIFICATION_TIMEOUT)
                .doOnError(e -> log.warn("⚠️ Reaction notification timeout/failed (ignored) for publicationId: {} : {}", publicationId, e.getMessage()))
                .onErrorResume(e -> Mono.empty())
                .then();
    }

    private String safeText(String value, String fallback) {
        return (value == null || value.isBlank()) ? fallback : value;
    }
}