package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.SyndicatServiceEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SyndicatServiceRepository extends R2dbcRepository<SyndicatServiceEntity, UUID> {

    @Query("""
        SELECT s.id, s.title, s.description, s.is_active, 
               e.price, e.features 
        FROM services s 
        INNER JOIN syndicat_services e ON s.id = e.service_id 
        WHERE s.id = :id
    """)
    Mono<SyndicatServiceRow> findFullById(UUID id);

    @Query("""
        SELECT s.id, s.title, s.description, s.is_active, 
               e.price, e.features 
        FROM services s 
        INNER JOIN syndicat_services e ON s.id = e.service_id 
        WHERE s.is_active = true
    """)
    Flux<SyndicatServiceRow> findAllActiveFull();
} 