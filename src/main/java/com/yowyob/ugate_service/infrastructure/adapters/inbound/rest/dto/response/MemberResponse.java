package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.enumeration.RoleTypeEnum;
import java.time.Instant;
import java.util.UUID;

public record MemberResponse(
        UUID userId,
        String firstName,
        String lastName,
        String email,
        String profileImageUrl,
        RoleTypeEnum role,
        UUID branchId,
        Instant joinedAt
) {}