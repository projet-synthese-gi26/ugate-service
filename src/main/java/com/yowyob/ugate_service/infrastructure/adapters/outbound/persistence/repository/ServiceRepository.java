package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import java.util.UUID;


import org.springframework.data.r2dbc.repository.R2dbcRepository;


import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.ServiceEntity;
import reactor.core.publisher.Mono;


public interface ServiceRepository extends R2dbcRepository<ServiceEntity, UUID> {

    Mono<Long> countByIsActiveTrue();
} 