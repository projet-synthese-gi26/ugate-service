package com.yowyob.ugate_service.domain.ports.out.syndicate;

import java.util.UUID;

import com.yowyob.ugate_service.domain.model.PublicationModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PublicationPersistencePort {

  Mono<PublicationModel> save(PublicationModel publicationModel);

  Flux<PublicationModel> findByBranchId(UUID branchId);

  Mono<Void> incrementLikes(UUID publicationId);
}
