package com.yowyob.ugate_service.domain.ports.in.content;

import reactor.core.publisher.Mono;
import java.util.UUID;

public interface JoinEventUseCase {
    Mono<Void> joinEvent(UUID userId, UUID eventId);
}
