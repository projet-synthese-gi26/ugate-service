package com.yowyob.ugate_service.domain.ports.out.syndicate;

import com.yowyob.ugate_service.domain.model.PublicationModel;
import reactor.core.publisher.Mono;

public interface PublicationPersistencePort {

  Mono<PublicationModel> save(PublicationModel publicationModel);
}

