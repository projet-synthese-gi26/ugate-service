package com.yowyob.ugate_service.domain.ports.out.syndicate;

import com.yowyob.ugate_service.domain.model.UserEventModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface UserEventPersistencePort {
    Mono<Void> save(UserEventModel userEventModel);
    Mono<Long> countByEventId(UUID eventId);
    Flux<UserEventModel> findByEventId(UUID eventId);
    Mono<Void> delete(UUID userId, UUID eventId);
}
