package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import java.time.Instant;
import java.util.UUID;

public record SyndicateResponse(
        UUID id,
        String name,
        String description,
        String domain,
        Boolean isApproved,
        String logoUrl,
        String statusUrl,
        UUID creatorId,
        Instant createdAt,

        Boolean isActive
) {}