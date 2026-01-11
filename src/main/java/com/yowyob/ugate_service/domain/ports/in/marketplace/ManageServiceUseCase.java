package com.yowyob.ugate_service.domain.ports.in.marketplace;


import java.util.UUID;
import com.yowyob.ugate_service.domain.model.SyndicatService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageServiceUseCase {
    Mono<SyndicatService> createService(SyndicatService service);
    Mono<SyndicatService> updateService(SyndicatService service);
    Mono<Void> deleteService(UUID id);
    // Flux<ServiceOffering> getSyndicatServices(UUID syndicatId);
    Flux<SyndicatService> getAllActiveServices();
    Mono<SyndicatService> getServiceDetails(UUID id);
}