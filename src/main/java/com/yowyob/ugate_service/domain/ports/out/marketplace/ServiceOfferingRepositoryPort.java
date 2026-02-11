package com.yowyob.ugate_service.domain.ports.out.marketplace;

import com.yowyob.ugate_service.domain.model.SyndicatService;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface ServiceOfferingRepositoryPort {
    Mono<SyndicatService> save(SyndicatService serviceOffering);
    Mono<SyndicatService> findServiceById(UUID id);
    Mono<SyndicatService> updateService(SyndicatService serviceOffering);
    Mono<Void> deleteService(UUID id);
    Flux<SyndicatService> findAllActiveServices();


    Flux<SyndicatService> findBySyndicatId(UUID syndicatId);
}
