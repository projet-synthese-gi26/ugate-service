package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.enumeration.ReactionTypeEnum;
import com.yowyob.ugate_service.domain.ports.out.syndicate.ReactionPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Reaction;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ReactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@AllArgsConstructor
public class ReactionPersistenceAdapter implements ReactionPersistencePort {

    private final ReactionRepository reactionRepository;

    @Override
    public Mono<Void> saveReaction(UUID publicationId, ReactionTypeEnum reactionType, UUID userId) {
        Reaction reaction = new Reaction(publicationId, userId, reactionType);
        return reactionRepository.insertReaction(
                reaction.publicationId(),
                reaction.userId(),
                reaction.type().name(),
                reaction.reactedAt()).then();
    }
}
