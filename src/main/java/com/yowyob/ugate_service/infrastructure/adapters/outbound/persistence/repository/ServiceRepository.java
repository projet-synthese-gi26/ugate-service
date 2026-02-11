package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import java.util.UUID;


import com.yowyob.ugate_service.domain.model.SyndicatService;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.ServiceEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ServiceRepository extends R2dbcRepository<ServiceEntity, UUID> {

    Mono<Long> countByIsActiveTrue();

    Flux<ServiceEntity> findBySyndicatId(UUID syndicatId);
}