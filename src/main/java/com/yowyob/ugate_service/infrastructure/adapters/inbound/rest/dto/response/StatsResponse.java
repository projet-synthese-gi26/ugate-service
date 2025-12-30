package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

public record StatsResponse(
        long totalSyndicats,
        long activeSyndicats,
        long pendingSyndicats,
        long totalMembers,
        long activeMembers,
        double totalRevenue

) {}