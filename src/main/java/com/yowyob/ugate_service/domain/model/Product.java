package com.yowyob.ugate_service.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record Product(
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
) {}