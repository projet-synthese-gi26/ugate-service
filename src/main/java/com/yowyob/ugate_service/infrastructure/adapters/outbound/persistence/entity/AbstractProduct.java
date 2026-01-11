package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Table("abstract_products")
public record AbstractProduct(
        @Id
        UUID id,

        @Column("syndicat_id")
        UUID syndicatId, // FK -> Syndicat

        String name,
        String description,

        @CreatedDate
        @Column("created_at")
        Instant createdAt
) {}
