package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

public record WebhookStatusChangeRequest(
        java.util.UUID driverId,
        String newStatus, // BANNED, SUSPENDED, ACTIVE
        String reason
) {}
