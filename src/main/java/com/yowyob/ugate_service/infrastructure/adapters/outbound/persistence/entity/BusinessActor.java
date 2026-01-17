package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator; // Import Important
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("business_actors")
public record BusinessActor(
        @Id
        UUID id,

        String name,

        @Column("phone_number")
        String phoneNumber,

        @Column("email_address")
        String emailAddress,

        @Transient
        @org.springframework.data.annotation.ReadOnlyProperty
        boolean isNewRecord

) implements Persistable<UUID> {

    @PersistenceCreator
    public BusinessActor(UUID id, String name, String phoneNumber, String emailAddress) {
        this(id, name, phoneNumber, emailAddress, false);
    }

    public static BusinessActor createNew(UUID userId, String name, String phone, String email) {
        return new BusinessActor(userId, name, phone, email, true);
    }

    public static BusinessActor existing(UUID userId, String name, String phone, String email) {
        return new BusinessActor(userId, name, phone, email, false);
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