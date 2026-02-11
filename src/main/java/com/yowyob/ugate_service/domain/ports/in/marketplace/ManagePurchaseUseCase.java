package com.yowyob.ugate_service.domain.ports.in.marketplace;

import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.ItemType;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ManagePurchaseUseCase {
    Mono<Void> processPurchase(UUID itemId, ItemType itemType, String buyerInfo);
}
