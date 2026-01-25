package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.yowyob.ugate_service.domain.enumeration.ReactionTypeEnum;

import java.time.Instant;
import java.util.UUID;

@Table("reactions")
public record Reaction(
        @Id UUID id,

        @Column("publication_id") UUID publicationId, // FK -> Publication

        @Column("user_id") UUID userId, // FK -> User

        ReactionTypeEnum type, // "LIKE", "LOVE", etc.

        @CreatedDate @Column("reacted_at") Instant reactedAt) {
    public Reaction(UUID publicationId, UUID userId, ReactionTypeEnum type) {
        this(null, publicationId, userId, type, Instant.now());
    }
}