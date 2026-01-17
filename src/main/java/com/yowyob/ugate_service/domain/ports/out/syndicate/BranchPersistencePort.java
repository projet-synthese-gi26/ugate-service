package com.yowyob.ugate_service.domain.ports.out.syndicate;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Branch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BranchPersistencePort {
    Mono<Branch> save(Branch branch);
    Mono<Branch> findById(UUID id);
    Flux<Branch> findBySyndicatId(UUID syndicatId);
}