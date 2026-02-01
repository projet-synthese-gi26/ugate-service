package com.yowyob.ugate_service.domain.ports.out.syndicate;

import com.yowyob.ugate_service.domain.model.EventModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

public interface EventPersistencePort {
  Mono<EventModel> save(EventModel event);

  Flux<EventModel> findByBranchId(UUID branchId);

  Flux<EventModel> findAllPaginated(int page, int size);
}
