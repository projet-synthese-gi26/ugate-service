package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Table("images")
public record Image(
        @Id
        UUID id,

        String url,

        @Column("alt_text")
        String altText,

        @CreatedDate
        @Column("uploaded_at")
        Instant uploadedAt
) {
    public Image(String url, String altText, Instant uploadedAt) {
        this(null, url, altText, uploadedAt);
    }
}