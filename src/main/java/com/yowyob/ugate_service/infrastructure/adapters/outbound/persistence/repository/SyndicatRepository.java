package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Syndicat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.UUID;

public interface SyndicatRepository extends R2dbcRepository<Syndicat, UUID> {
    // Trouver les syndicats créés par un admin
    Flux<Syndicat> findByCreatorId(UUID creatorId);

    Flux<Syndicat> findAllBy(Pageable pageable);

    @Query("""
        SELECT s.* 
        FROM syndicats s 
        INNER JOIN syndicat_members sm ON s.id = sm.syndicat_id 
        WHERE sm.user_id = :userId
    """)
    Flux<Syndicat> findAllByMemberUserId(UUID userId);

    // Filtrer par domaine ou nom
    Flux<Syndicat> findByNameContainingIgnoreCase(String name);

    Mono<Long> countByIsActiveTrue();

    Mono<Long> countByIsApprovedFalse();

    Mono<Long> countByIsApprovedTrue();
}
