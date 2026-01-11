package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
    UUID id,
    UUID syndicatId,
    String name,
    String description,
    BigDecimal price,
    String sku,
    String category,
    Integer stock,
    String imageUrl,
    Boolean isActive
) {

}
