package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("syndicat")
public record Syndicat(
        @Id
        Long id,

        @Column("organization_id")
        Long organizationId, // FK vers Organization

        @Column("is_approved")
        Boolean isApproved,

        String name,
        String description,
        String domain,
        String type,

        // URLs
        @Column("charte_url")
        String charteUrl,

        @Column("status_url")
        String statusUrl,

        @Column("members_list_url")
        String membersListUrl,

        @Column("commitment_certificate_url")
        String commitmentCertificateUrl,

        // Audit
        @CreatedDate
        @Column("created_at")
        Instant createdAt,

        @LastModifiedDate
        @Column("updated_at")
        Instant updatedAt
) {
    // Méthode "Wither" pour mettre à jour lors d'un UPDATE
    public Syndicat withStatus(Boolean isApproved, String charteUrl, String statusUrl) {
        // On garde l'ID et les dates, on change le reste
        return new Syndicat(
                this.id, this.organizationId, isApproved, this.name, this.description,
                this.domain, this.type, charteUrl, statusUrl, this.membersListUrl,
                this.commitmentCertificateUrl, this.createdAt, this.updatedAt
        );
    }
}
