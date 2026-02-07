package com.yowyob.ugate_service.application.service.content;

import java.util.UUID;

import com.yowyob.ugate_service.domain.enumeration.ReactionTypeEnum;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.ReactionPersistencePort;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.Loggers;
import reactor.util.Logger;

@AllArgsConstructor
public class ReactionService {

  private static final Logger log = Loggers.getLogger(ReactionService.class);

  // Une methode pour ajouter une reaction à une publication et changer nlikes
  // dans le publication service en appelant la methode prévu
  private final PublicationService publicationService;
  private final ReactionPersistencePort reactionPersistencePort;
  private final PublicationPersistencePort publicationPersistencePort;
  private final UserGatewayPort userGatewayPort;
  private final NotificationPort notificationPort;

  public Mono<Void> addReactionToPublication(UUID publicationId, UUID userId, ReactionTypeEnum reactionType) {
    log.info("addReactionToPublication called for publicationId: {}, userId: {}", publicationId, userId);
    return reactionPersistencePort.saveReaction(publicationId, reactionType, userId)
        .doOnSuccess(v -> log.info("Reaction saved for publicationId: {}", publicationId))
        .then(publicationService.incrementLikes(publicationId)
            .doOnSuccess(v -> log.info("Likes incremented for publicationId: {}", publicationId))
            .doOnError(e -> log.error("Error incrementing likes for publicationId: {}", publicationId, e)))
        .then(Mono.defer(() -> {
            log.info("Mono.defer block entered for publicationId: {}", publicationId);
            return Mono.zip(
                publicationPersistencePort.findById(publicationId)
                    .doOnNext(p -> log.info("Found publication model: {}", p.getId()))
                    .doOnError(e -> log.error("Error finding publication model for id: {}", publicationId, e)),
                userGatewayPort.findById(userId)
                    .doOnNext(u -> log.info("Found reactor user info: {}", u.id()))
                    .doOnError(e -> log.error("Error finding reactor user info for id: {}", userId, e))
            );
        }))
        .flatMap(tuple -> {
            log.info("flatMap after Mono.zip entered for publicationId: {}", publicationId);
            Tuple2<com.yowyob.ugate_service.domain.model.PublicationModel, com.yowyob.ugate_service.domain.model.ExternalUserInfo> typedTuple = tuple;
            var publication = typedTuple.getT1();
            var reactorInfo = typedTuple.getT2();

            return userGatewayPort.findById(publication.getAuthorId())
                .doOnNext(pubAuthorInfo -> log.info("Found publication author info: {}", pubAuthorInfo.id()))
                .doOnError(e -> log.error("Error finding publication author info for id: {}", publication.getAuthorId(), e))
                .flatMap(publicationAuthorInfo ->
                        notificationPort.sendPublicationReactAlert(
                                publicationAuthorInfo.email(),
                                publication.getContent(), // Using content as title for now
                                reactorInfo.firstName()
                        ).doOnSuccess(v -> log.info("Publication reaction alert sent for publicationId: {}", publicationId))
                        .doOnError(e -> log.error("Error sending notification for publicationId: {}", publicationId, e))
                );
        })
        .then() // Ensure the Mono<Void> return type
        .doOnSuccess(v -> log.info("addReactionToPublication completed successfully for publicationId: {}", publicationId))
        .doOnError(e -> log.error("addReactionToPublication encountered an error for publicationId: {}", publicationId, e));
  }
}
