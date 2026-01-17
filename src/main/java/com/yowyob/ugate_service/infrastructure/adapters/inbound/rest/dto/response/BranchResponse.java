package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import java.time.Instant;
import java.util.UUID;

public record BranchResponse(
        UUID id,
        UUID syndicatId,
        String name,
        String location,
        String contact,
        String bannerUrl,
        Instant createdAt,
        Instant updatedAt
) {}