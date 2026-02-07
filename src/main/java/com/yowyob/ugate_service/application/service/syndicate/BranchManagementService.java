package com.yowyob.ugate_service.application.service.syndicate;

import com.yowyob.ugate_service.domain.ports.out.media.FileStoragePort; // Import du port Média
import com.yowyob.ugate_service.domain.ports.out.syndicate.BranchPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreateBranchRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.UpdateBranchRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.BranchResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Agency;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Branch;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.AgencyRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatRepository;
import com.yowyob.ugate_service.infrastructure.mappers.syndicate.BranchMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class BranchManagementService {

    private final BranchPersistencePort branchPersistencePort;
    private final FileStoragePort fileStoragePort;
    private final BranchMapper branchMapper;
    private final AgencyRepository agencyRepository;
    private final SyndicatRepository syndicatRepository;





    @Transactional
    public Mono<BranchResponse> createBranch(UUID syndicatId, CreateBranchRequest request) {
        return syndicatRepository.findById(syndicatId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Syndicat introuvable : " + syndicatId)))
                .flatMap(syndicat -> {
                    Mono<String> bannerUrlMono;
                    if (request.getBanner() != null && !request.getBanner().filename().isBlank()) {
                        bannerUrlMono = fileStoragePort.uploadFile(request.getBanner(), "branches");
                    } else {
                        bannerUrlMono = Mono.justOrEmpty(null);
                    }

                    return bannerUrlMono.flatMap(bannerUrl -> {
                        UUID sharedId = UUID.randomUUID();


                        Agency newAgency = Agency.createNew(
                                sharedId,
                                syndicat.organizationId(),
                                request.getName()
                        );

                        // 4. Création de la Branch
                        Branch newBranch = Branch.createNew(
                                sharedId,
                                syndicatId,
                                request.getName(),
                                request.getLocation(),
                                request.getContact(),
                                bannerUrl
                        );

                        return agencyRepository.save(newAgency)
                                .then(branchPersistencePort.save(newBranch));
                    });
                })
                .map(branchMapper::toResponse)
                .doOnSuccess(b -> log.info("Branche créée avec succès : {}", b.id()));
    }

    public Flux<BranchResponse> getSyndicateBranches(UUID syndicatId) {
        return branchPersistencePort.findBySyndicatId(syndicatId)
                .map(branchMapper::toResponse);
    }

    public Mono<BranchResponse> getBranchDetails(UUID branchId) {
        return branchPersistencePort.findById(branchId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Branche introuvable : " + branchId)))
                .map(branchMapper::toResponse);
    }

    @Transactional
    public Mono<BranchResponse> updateBranch(UUID branchId, UpdateBranchRequest request) {

        return branchPersistencePort.findById(branchId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Branche introuvable : " + branchId)))
                .flatMap(existingBranch -> {
                    String newName = request.name() != null ? request.name() : existingBranch.name();
                    String newLocation = request.location() != null ? request.location() : existingBranch.location();
                    String newContact = request.contact() != null ? request.contact() : existingBranch.contact();

                    Branch updatedBranch = existingBranch.withInfo(newName, newLocation, newContact, existingBranch.bannerUrl());
                    return branchPersistencePort.save(updatedBranch);
                })
                .map(branchMapper::toResponse);
    }
}