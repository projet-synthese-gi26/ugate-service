package com.yowyob.ugate_service.domain.ports.out.syndicate;

import java.util.UUID;

import reactor.core.publisher.Mono;

public interface ReactionPersistencePort {
  Mono<Void> saveReaction(UUID publicationId, String reactionType, UUID userId);
}
