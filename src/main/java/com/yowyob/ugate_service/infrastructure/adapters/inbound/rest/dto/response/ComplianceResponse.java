package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;



import java.time.Instant;
import java.util.List;

public record ComplianceResponse(
        String syndicatDriverId,
        Instant verificationTimestamp,
        String globalStatus, // AUTHORIZED, RESTRICTED, BANNED
        ComplianceDetails details,
        List<String> restrictions
) {
    public record ComplianceDetails(
            boolean licenseValid,
            boolean insuranceValid,
            boolean membershipCurrent,
            boolean medicalCheck
    ) {}
}