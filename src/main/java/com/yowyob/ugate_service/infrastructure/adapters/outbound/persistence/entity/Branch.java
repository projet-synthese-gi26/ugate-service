package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import java.util.UUID;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceCreator; // Import
import org.springframework.data.annotation.Transient;         // Import
import org.springframework.data.domain.Persistable;           // Import
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;

@Table("branches")
public record Branch(
        @Id
        UUID id,

        @Column("syndicat_id")
        UUID syndicatId,

        String name,
        String location,
        String contact,

        @Column("banner_url")
        String bannerUrl,

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
    public Branch(UUID id, UUID syndicatId, String name, String location, String contact, String bannerUrl, Instant createdAt, Instant updatedAt) {
        this(id, syndicatId, name, location, contact, bannerUrl, createdAt, updatedAt, false);
    }


    public static Branch createNew(UUID id, UUID syndicatId, String name, String location, String contact, String bannerUrl) {
        return new Branch(
                id,
                syndicatId,
                name,
                location,
                contact,
                bannerUrl,
                Instant.now(),
                Instant.now(),
                true
        );
    }

    public Branch withInfo(String name, String location, String contact, String bannerUrl) {
        return new Branch(
                this.id, this.syndicatId, name, location, contact, bannerUrl,
                this.createdAt, null,
                false
        );
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