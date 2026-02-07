package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.enumeration.RoleTypeEnum;

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

        Boolean isActive,
        UUID userBranchId,
        String userBranchName,
        RoleTypeEnum userRole
) {}