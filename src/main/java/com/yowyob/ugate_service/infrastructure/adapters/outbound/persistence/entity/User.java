package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table("app_user")
public record User(
        @Id UUID id,

        @Column("profil_url") String profilUrl,
        @Column("first_name") String firstName,
        @Column("last_name") String lastName,
        String email,
        @Column("password_hash") String passwordHash,
        @Column("date_of_birth") LocalDate dateOfBirth,

        //TODO la structure de cette table est à revoir
        String phoneNumber

) {}
