package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import java.util.List;

public record BranchMembersStatsResponse(
        long totalMembers,
        List<MemberResponse> members
) {}