package com.yowyob.ugate_service.domain.ports.out.syndicate;

import com.yowyob.ugate_service.domain.model.EventModel;

import reactor.core.publisher.Mono;

public interface EventPersistencePort {
  public Mono<EventModel> save(EventModel event);
}
