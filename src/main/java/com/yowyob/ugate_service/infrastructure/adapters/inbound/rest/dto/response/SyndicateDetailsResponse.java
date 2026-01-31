package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import java.time.Instant;
import java.util.UUID;

public record SyndicateDetailsResponse(
        // Infos de base
        UUID id,
        String name,
        String description,
        String domain,
        String type,
        Boolean isApproved,
        Boolean isActive,

        // Documents et URLs
        SyndicatDocuments documents,

        // Organisation liée
        OrganizationInfo organization,

        // Créateur
        CreatorInfo creator,

        // Statistiques en temps réel
        SyndicatStats stats,

        // Audit
        Instant createdAt,
        Instant updatedAt
) {

    public record SyndicatDocuments(
            String logoUrl,
            String charteUrl,
            String statusUrl,
            String membersListUrl,
            String commitmentCertificateUrl
    ) {}

    public record OrganizationInfo(
            String legalForm,
            String socialNetwork, // JSON ou String brut
            String keywords,
            String email,
            String shortName,
            String longName
    ) {}

    public record CreatorInfo(
            UUID id,
            String fullName,
            String email
    ) {}

    public record SyndicatStats(
            Long totalMembers,
            Long totalBranches
    ) {}
}