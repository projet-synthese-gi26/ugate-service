package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;

@Table("image")
public record Image(
        @Id
        Long id,

        String url,

        @Column("alt_text")
        String altText,

        @CreatedDate
        @Column("uploaded_at")
        Instant uploadedAt
) {}