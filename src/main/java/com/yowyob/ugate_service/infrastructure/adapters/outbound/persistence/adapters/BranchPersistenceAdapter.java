package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.ports.out.syndicate.BranchPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Branch;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component; // Très important !
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BranchPersistenceAdapter implements BranchPersistencePort {

    private final BranchRepository branchRepository;

    @Override
    public Mono<Branch> save(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public Mono<Branch> findById(UUID id) {
        return branchRepository.findById(id);
    }

    @Override
    public Flux<Branch> findBySyndicatId(UUID syndicatId) {
        return branchRepository.findBySyndicatId(syndicatId);
    }
}