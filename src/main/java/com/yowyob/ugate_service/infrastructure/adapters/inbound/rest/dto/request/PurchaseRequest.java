package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import java.util.UUID;

public record PurchaseRequest(UUID itemId,
                              ItemType itemType, // PRODUCT | SERVICE
                              String buyerInfo) {
}
