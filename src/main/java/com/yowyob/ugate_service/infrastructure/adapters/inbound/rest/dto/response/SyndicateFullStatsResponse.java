package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;



public record SyndicateFullStatsResponse(
        long totalMembers,
        long totalBranches,
        long pendingRequests,
        long activeServices,
        long totalPublications
) {}
