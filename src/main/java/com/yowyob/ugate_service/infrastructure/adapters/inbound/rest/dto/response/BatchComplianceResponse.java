package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

public record BatchComplianceResponse(
        java.util.Map<java.util.UUID, ComplianceResponse> results
) {}