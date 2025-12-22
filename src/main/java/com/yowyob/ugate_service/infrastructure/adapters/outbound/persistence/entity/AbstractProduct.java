package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;

@Table("abstract_product")
public record AbstractProduct(
        @Id
        Long id,

        @Column("syndicat_id")
        Long syndicatId, // FK -> Syndicat

        String name,
        String description,

        @CreatedDate
        @Column("created_at")
        Instant createdAt
) {}
