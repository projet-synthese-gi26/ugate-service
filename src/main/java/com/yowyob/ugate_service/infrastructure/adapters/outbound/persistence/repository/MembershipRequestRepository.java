package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.MembershipRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface MembershipRequestRepository extends R2dbcRepository<MembershipRequest, UUID> {

    @Query("SELECT * FROM membership_request WHERE user_id = :userId AND syndicat_id = :syndicatId ORDER BY created_at DESC LIMIT 1")
    Mono<MembershipRequest> findLastRequest(UUID userId, UUID syndicatId);


    Flux<MembershipRequest> findBySyndicatIdAndStatus(UUID syndicatId, MembershipRequest.MembershipStatus status, Pageable pageable);


    Flux<MembershipRequest> findBySyndicatIdAndStatus(UUID syndicatId, MembershipRequest.MembershipStatus status);


    Flux<MembershipRequest> findByBranchIdAndStatus(UUID branchId, MembershipRequest.MembershipStatus status);


    Mono<Long> countBySyndicatIdAndStatus(UUID syndicatId, MembershipRequest.MembershipStatus status);
}
