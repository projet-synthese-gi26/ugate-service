package com.yowyob.ugate_service.domain.ports.out.syndicate;

import java.util.UUID;

import com.yowyob.ugate_service.domain.enumeration.ReactionTypeEnum;

import reactor.core.publisher.Mono;

public interface ReactionPersistencePort {
  Mono<Void> saveReaction(UUID publicationId, ReactionTypeEnum reactionType, UUID userId);
}
