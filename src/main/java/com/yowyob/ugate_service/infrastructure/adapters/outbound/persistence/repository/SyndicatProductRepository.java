package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.SyndicatProductEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SyndicatProductRepository extends R2dbcRepository<SyndicatProductEntity, UUID> {
    
    @Query("""
        SELECT p.id, p.organization_id, p.name, p.description, p.product_urls, 
               p.is_active, p.standard_price, e.sku, e.category, e.stock 
        FROM abstract_products p 
        INNER JOIN syndicat_products e ON p.id = e.product_id 
        WHERE p.id = :id
    """)
    Mono<SyndicatProductRow> findFullById(UUID id);

    @Query("""
        SELECT p.id, p.organization_id, p.name, p.description, p.product_urls, 
               p.is_active, p.standard_price, e.sku, e.category, e.stock 
        FROM abstract_products p 
        INNER JOIN syndicat_products e ON p.id = e.product_id 
        WHERE p.organization_id = :syndicatId
    """)
    Flux<SyndicatProductRow> findFullBySyndicatId(UUID syndicatId);
}