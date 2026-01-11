package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;



public record OfficialProfileResponse(
        String id,
        String firstName,
        String lastName,
        String photoUrl,
        // Détails de conformité
        String cvUrl,
        String cniNumber,
        String cniRectoUrl,
        String cniVersoUrl,
        String licenseNumber,
        String licenseRectoUrl,
        String licenseVersoUrl,
        Boolean isVerified
) {}
