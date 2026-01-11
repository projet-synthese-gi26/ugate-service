package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;


@Table("profiles")
public record Profile (
        @Id UUID id,
        @Column("user_id")
        UUID userId ,
        @Column("profile_image_url")
        String profilImageUrl,

        @Column("first_name")
        String firstName,
        @Column("last_name")
        String lastName,
        @Column("birth_date")
        Instant birth_date,
        String nationality,
        String gender,
        String language,
        @CreatedDate
        @Column("created_at")
        Instant createdAt,
        @LastModifiedDate
        @Column("updated_at")
        Instant updatedAt

)
{}
