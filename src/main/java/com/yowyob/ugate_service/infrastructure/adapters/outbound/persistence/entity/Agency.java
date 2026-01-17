package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("agencies")
public record Agency(
        @Id
        UUID id,

        @Column("organization_id")
        UUID organizationId,

        String name,

        @CreatedDate
        @Column("created_at")
        Instant createdAt,

        @LastModifiedDate
        @Column("updated_at")
        Instant updatedAt,

        @Transient
        boolean isNewRecord
) implements Persistable<UUID> {

    @PersistenceCreator
    public Agency(UUID id, UUID organizationId, String name, Instant createdAt, Instant updatedAt) {
        this(id, organizationId, name, createdAt, updatedAt, false);
    }


    public static Agency createNew(UUID id, UUID organizationId, String name) {
        return new Agency(id, organizationId, name, Instant.now(), Instant.now(), true);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    @Transient
    public boolean isNew() {
        return isNewRecord;
    }
}