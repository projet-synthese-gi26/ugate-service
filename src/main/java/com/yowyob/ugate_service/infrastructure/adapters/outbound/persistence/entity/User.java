package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table("users")
public record User(
        @Id UUID id,

        String name,

        @Column("phone_number") String phoneNumber,

        @Column("email_address") String email,
        @Transient boolean isNewRecord) implements Persistable<UUID> {
    public User(UUID id, String name, String phoneNumber, String email) {
        this(id, name, phoneNumber, email, true);
    }

    @PersistenceCreator
    public static User create(UUID id, String name, String phoneNumber, String email) {
        return new User(id, name, phoneNumber, email, false);
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
