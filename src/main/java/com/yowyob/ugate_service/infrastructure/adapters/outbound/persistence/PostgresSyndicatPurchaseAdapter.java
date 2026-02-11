package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence;

import com.yowyob.ugate_service.domain.model.Purchase;
import com.yowyob.ugate_service.domain.ports.out.marketplace.PurchaseRepositoryPort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.PurchaseEntity;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PurchaseRepository;
import com.yowyob.ugate_service.infrastructure.mappers.PurchaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class PostgresSyndicatPurchaseAdapter implements PurchaseRepositoryPort {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;

    @Override
    public Mono<Purchase> save(Purchase purchase) {
        PurchaseEntity entity = new PurchaseEntity(null, purchase.syndicatId(), purchase.itemId(), String.valueOf(purchase.itemType()), purchase.buyerInfo(), purchase.amount(), Instant.now());
        return purchaseRepository.save(entity)
                .map(purchaseMapper::mapToDomain);
    }
}
