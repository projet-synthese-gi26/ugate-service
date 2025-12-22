package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Branch;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface BranchRepository extends R2dbcRepository<Branch, UUID> {
    Flux<Branch> findBySyndicatId(UUID syndicatId);
}