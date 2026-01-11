package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("branch")
public record Branch(
        @Id
        UUID id, // FK vers Agency.id selon le schéma

        @Column("syndicat_id")
        UUID syndicatId, // FK vers Syndicat

        String name,
        String location,
        String contact,

        @CreatedDate
        @Column("created_at")
        Instant createdAt,

        @LastModifiedDate
        @Column("updated_at")
        Instant updatedAt
) {
    // Exemple de wither pour mise à jour simple
    public Branch withContactInfo(String name, String location, String contact) {
        return new Branch(
                this.id, this.syndicatId, name, location, contact,
                this.createdAt, null // null déclenchera la mise à jour de @LastModifiedDate
        );
    }
}