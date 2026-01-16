package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;


public record UpdateBranchRequest(
        String name,
        String location,
        String contact
) {}