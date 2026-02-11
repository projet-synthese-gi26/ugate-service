package com.yowyob.ugate_service.domain.model;

import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.ItemType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record Purchase(
        UUID id,
        UUID syndicatId,
        UUID itemId,
        ItemType itemType,
        String buyerInfo,
        BigDecimal amount,
        Instant createdAt
) {}