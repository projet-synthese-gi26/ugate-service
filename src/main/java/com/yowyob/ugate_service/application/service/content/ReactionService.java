package com.yowyob.ugate_service.application.service.content;

import java.util.UUID;

import com.yowyob.ugate_service.domain.ports.out.syndicate.ReactionPersistencePort;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class ReactionService {
  // Une methode pour ajouter une reaction à une publication et changer nlikes
  // dans le publication service en appelant la methode prévu
  private final PublicationService publicationService;
  private final ReactionPersistencePort reactionPersistencePort;

  public Mono<Void> addReactionToPublication(UUID publicationId, UUID userId, String reactionType) {
    return reactionPersistencePort.saveReaction(publicationId, reactionType, userId)
        .then(publicationService.incrementLikes(publicationId));
  }
}
