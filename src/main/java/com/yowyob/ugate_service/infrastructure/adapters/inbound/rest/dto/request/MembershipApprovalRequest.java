package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

public record MembershipApprovalRequest(
        boolean approve,
        String rejectionReason
) {}