package com.yowyob.ugate_service.domain.ports.out.marketplace;

import com.yowyob.ugate_service.domain.model.Purchase;
import reactor.core.publisher.Mono;

public interface PurchaseRepositoryPort {
    Mono<Purchase> save(Purchase purchase);
}
