package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;

@Table("reaction")
public record Reaction(
        @Id
        Long id,

        @Column("publication_id")
        Long publicationId, // FK -> Publication

        @Column("user_id")
        Long userId,        // FK -> User

        String type,        // "LIKE", "LOVE", etc.

        @CreatedDate
        @Column("reacted_at")
        Instant reactedAt
) {}