package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Table("organizations")
public record Organization(
        @Id UUID id,
        @Column("business_actor_id") UUID businessActorId,
        String code,

        @Column("short_name") String shortName,
        @Column("long_name") String longName,
        @Column("email") String email,
        @Column("is_active") Boolean isActive,

        @Transient boolean isNewRecord
) implements Persistable<UUID> {

    @PersistenceCreator
    public Organization(UUID id, UUID businessActorId, String code, String shortName,
                        String longName, String email, Boolean isActive) {
        this(id, businessActorId, code, shortName, longName, email, isActive, false);
    }

    public Organization(UUID id, UUID businessActorId, String code, String email, String name) {
        this(id, businessActorId, code, name, name, email, true, true);
    }

    public static Organization createNew(UUID id, UUID businessActorId, String name, String email) {
        return new Organization(
                id,
                businessActorId,
                name.toUpperCase().replaceAll("\\s+", "_"),
                name,
                name,
                email,
                true,
                true // isNewRecord = true pour forcer l'INSERT
        );
    }

    @Override public UUID getId() { return id; }
    @Override @Transient public boolean isNew() { return isNewRecord; }
}
