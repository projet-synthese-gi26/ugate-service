package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Publication;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PublicationRepository extends R2dbcRepository<Publication, UUID> {
    Flux<Publication> findByBranchId(UUID branchId);
    @Query("""
        SELECT COUNT(p.id) 
        FROM publications p 
        INNER JOIN branches b ON p.branch_id = b.id 
        WHERE b.syndicat_id = :syndicatId
    """)
    Mono<Long> countBySyndicatId(UUID syndicatId);

    Mono<Long> countByBranchId(UUID branchId);
}
