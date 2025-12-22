package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Table("reaction")
public record Reaction(
        @Id
        UUID id,

        @Column("publication_id")
        Long publicationId, // FK -> Publication

        @Column("user_id")
        UUID userId,        // FK -> User

        String type,        // "LIKE", "LOVE", etc.

        @CreatedDate
        @Column("reacted_at")
        Instant reactedAt
) {}