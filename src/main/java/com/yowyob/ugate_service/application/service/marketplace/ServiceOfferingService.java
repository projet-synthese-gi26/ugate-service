package com.yowyob.ugate_service.application.service.marketplace;
import java.util.UUID;

import com.yowyob.ugate_service.domain.exception.ServiceNotFoundException;
import com.yowyob.ugate_service.domain.model.Product;
import com.yowyob.ugate_service.domain.model.SyndicatService;
import com.yowyob.ugate_service.domain.ports.in.marketplace.ManageServiceUseCase;

import com.yowyob.ugate_service.domain.ports.out.payment.PaymentGatewayPort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

import com.yowyob.ugate_service.domain.ports.out.marketplace.ServiceOfferingRepositoryPort;



@Service
@RequiredArgsConstructor
public class ServiceOfferingService implements ManageServiceUseCase {

    public final ServiceOfferingRepositoryPort serviceOfferingRepositoryPort;
    public final SyndicatRepository syndicatRepository;
    public final PaymentGatewayPort paymentGatewayPort;

    @Override
    public Mono<SyndicatService> createService(SyndicatService service) {
        SyndicatService serviceToCreate = new SyndicatService(
            UUID.randomUUID(),
            service.syndicatId(),
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
                    existing.syndicatId(),
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

    public Flux<SyndicatService> getSyndicatServices(UUID syndicatId) {
        return syndicatRepository.findById(syndicatId)
                .flatMapMany(syndicat -> {
                    // On appelle le Payment-Service pour vérifier si le solde > 0
                    return paymentGatewayPort.canOperate(syndicat.walletId())
                            .flatMapMany(hasPoints -> {
                                if (Boolean.TRUE.equals(hasPoints)) {
                                    return serviceOfferingRepositoryPort.findBySyndicatId(syndicatId);
                                }
                                // Si pas de points, on renvoie un Flux vide (services cachés)
                                return Flux.empty();
                            });
                });
    }
}
