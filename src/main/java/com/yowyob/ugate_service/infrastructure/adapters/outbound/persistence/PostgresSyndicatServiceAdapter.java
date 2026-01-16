package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yowyob.ugate_service.domain.model.SyndicatService;
import com.yowyob.ugate_service.domain.ports.out.marketplace.ServiceOfferingRepositoryPort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.ServiceEntity;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PostgresSyndicatServiceAdapter implements ServiceOfferingRepositoryPort {

    private final ServiceRepository syndicatServiceRepository;

    @Override
    public Mono<SyndicatService> findServiceById(UUID id) {
        return syndicatServiceRepository.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public Flux<SyndicatService> findAllActiveServices() {
        return syndicatServiceRepository.findAll()
                .map(this::mapToDomain);
    }

    @Override
    @Transactional
    public Mono<SyndicatService> save(SyndicatService service) {
        ServiceEntity entity = new ServiceEntity(
            service.id(),
            service.title(),
            service.description(),
            service.price(),
            service.features(),
            service.isActive(),
            true
        );
        return syndicatServiceRepository.save(entity)
               .map(this::mapToDomain);
    }

    @Override
    public Mono<SyndicatService> updateService(SyndicatService service) {
        return syndicatServiceRepository.findById(service.id())
        .switchIfEmpty(Mono.error(new RuntimeException("Service not found")))
        .map(entity -> {

            Optional.ofNullable(service.title()).ifPresent(entity::setTitle);
            Optional.ofNullable(service.description()).ifPresent(entity::setDescription);
            Optional.ofNullable(service.price()).ifPresent(entity::setPrice);
            Optional.ofNullable(service.features()).ifPresent(entity::setFeatures);
            Optional.ofNullable(service.isActive()).ifPresent(entity::setIsActive);
            entity.setNew(false);
        
            return entity;
        })
        
        .flatMap(syndicatServiceRepository::save)
        .map(this::mapToDomain);
    }

    @Override
    public Mono<Void> deleteService(UUID id) {
        syndicatServiceRepository.findById(id)
        .switchIfEmpty(Mono.error(new RuntimeException("Service not found")));


        return syndicatServiceRepository.deleteById(id);
    }

    // --- MAPPER ---
    private SyndicatService mapToDomain(ServiceEntity row) {
        List<String> featuresList = row.getFeatures() != null 
                ? row.getFeatures() 
                : List.of();

        return new SyndicatService(
            row.getId(),
            row.getTitle(),
            row.getDescription(),
            row.getPrice(),
            featuresList,
            row.getIsActive()
        );
    }
}