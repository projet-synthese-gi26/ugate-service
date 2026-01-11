package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import java.math.BigDecimal;
import java.util.UUID;

// Ce record sert uniquement à la lecture (JOIN) dans la couche Infra
public record SyndicatProductRow(
    UUID id,
    UUID organizationId,
    String name,
    String description,
    String productUrls,
    Boolean isActive,
    BigDecimal standardPrice,
    String sku,
    String category,
    Integer stock
) {}