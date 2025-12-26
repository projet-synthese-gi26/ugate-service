package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

public record BatchComplianceRequest(
        java.util.List<java.util.UUID> driverIds
) {}
