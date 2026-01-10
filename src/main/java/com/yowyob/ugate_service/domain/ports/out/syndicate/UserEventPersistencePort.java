package com.yowyob.ugate_service.domain.ports.out.syndicate;

import com.yowyob.ugate_service.domain.model.UserEventModel;
import reactor.core.publisher.Mono;

public interface UserEventPersistencePort {
    Mono<Void> save(UserEventModel userEventModel);
}
