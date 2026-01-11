package com.yowyob.ugate_service.domain.ports.in.content;

import reactor.core.publisher.Mono;
import java.util.UUID;

public interface CastVoteUseCase {
    Mono<Void> castVote(UUID userId, UUID publicationVoteId, String choiceLabel);
}
