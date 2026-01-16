package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.ProductEntity;

import reactor.core.publisher.Flux;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import java.util.UUID;

public interface ProductRepository extends R2dbcRepository<ProductEntity, UUID> {
    Flux<ProductEntity> findBySyndicatId(UUID syndicatId);

}
