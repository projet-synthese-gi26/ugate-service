package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("syndicats")
public record Syndicat(
        @Id
        UUID id,

        @Column("organization_id")
        UUID organizationId, // FK vers Organization

        @Column("creator_id")
        UUID creatorId, // FK vers Le createur

        @Column("is_approved")
        Boolean isApproved,

        String name,
        String description,
        String domain,
        String type,

        // URLs
        @Column("charte_url")
        String charteUrl,

        @Column("logo_url")
        String logoUrl,

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
)implements Persistable<UUID> {


    // Constructeur pour créer un Syndicat
    public Syndicat(UUID id, UUID creatorId, String name, String description,
                    String domain, String logoUrl, String statusUrl) {
        this(id, null, creatorId, false, name, description, domain, "STANDARD",
                null, logoUrl, statusUrl, null, null, null, null);
    }


    // Méthode "Wither" pour mettre à jour lors d'un UPDATE
    public Syndicat withStatus(Boolean isApproved, String charteUrl,String logoUrl,  String statusUrl) {
        // On garde l'ID et les dates, on change le reste
        return new Syndicat(
                this.id, this.organizationId, this.creatorId, isApproved, this.name, this.description,
                this.domain, this.type, charteUrl, logoUrl, statusUrl, this.membersListUrl,
                this.commitmentCertificateUrl, this.createdAt, this.updatedAt
        );
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    @Transient
    public boolean isNew() {
        return createdAt == null || updatedAt == null;
    }
}
