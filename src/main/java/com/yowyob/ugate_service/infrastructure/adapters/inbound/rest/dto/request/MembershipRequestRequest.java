package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import java.util.UUID;

public record MembershipRequestRequest(
        UUID branchId,
        String motivation
) {}
