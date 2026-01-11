package com.yowyob.ugate_service.application.service.marketplace;
import java.util.UUID;

import com.yowyob.ugate_service.domain.exception.ServiceNotFoundException;
import com.yowyob.ugate_service.domain.model.SyndicatService;
import com.yowyob.ugate_service.domain.ports.in.marketplace.ManageServiceUseCase;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

import com.yowyob.ugate_service.domain.ports.out.marketplace.ServiceOfferingRepositoryPort;



@Service
@RequiredArgsConstructor
public class ServiceOfferingService implements ManageServiceUseCase {

    public final ServiceOfferingRepositoryPort serviceOfferingRepositoryPort;

    @Override
    public Mono<SyndicatService> createService(SyndicatService service) {
        SyndicatService serviceToCreate = new SyndicatService(
            service.id() != null ? service.id() : UUID.randomUUID(),
            service.title(),
            service.description(),
            service.price(),
            service.features(),
            service.isActive()
        );

        return serviceOfferingRepositoryPort.save(serviceToCreate);
    }

    @Override
    public Mono<SyndicatService> updateService(SyndicatService service) {
        return serviceOfferingRepositoryPort.findServiceById(service.id())
            .flatMap(existing -> {
                SyndicatService serviceToUpdate = new SyndicatService(
                    existing.id(),
                    service.title() != null ? service.title() : existing.title(),
                    service.description() != null ? service.description() : existing.description(),
                    service.price() != null ? service.price() : existing.price(),
                    service.features() != null ? service.features() : existing.features(),
                    service.isActive() != null ? service.isActive() : existing.isActive()
                );
                return serviceOfferingRepositoryPort.updateService(serviceToUpdate);
            });
    }

    @Override
    public Mono<Void> deleteService(UUID id) {
        return serviceOfferingRepositoryPort.findServiceById(id)
            .switchIfEmpty(Mono.error(new ServiceNotFoundException("Service not found")))
            .flatMap( serviceOffering -> serviceOfferingRepositoryPort.deleteService(serviceOffering.id()));
        
    }

    @Override
    public Flux<SyndicatService> getAllActiveServices() {
        return serviceOfferingRepositoryPort.findAllActiveServices();
    }

    @Override
    public Mono<SyndicatService> getServiceDetails(UUID id) {
        return serviceOfferingRepositoryPort.findServiceById(id)
            .switchIfEmpty(Mono.error(new ServiceNotFoundException("Service not found")));
    }
}
