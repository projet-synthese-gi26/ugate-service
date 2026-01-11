package com.yowyob.ugate_service.domain.ports.in.content;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface LeaveEventUseCase {
    Mono<Void> leaveEvent(UUID userId, UUID eventId);
}
