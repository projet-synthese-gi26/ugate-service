package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Table("publication")
public record Publication(
        @Id
        UUID id,

        @Column("branch_id")
        UUID branchId,    // FK -> Branch

        @Column("author_id")
        UUID authorId,    // FK -> User

        String content,
        String status,    // Ex: "PUBLISHED", "DRAFT"

        @Column("n_likes")
        Long nLikes,      // Compteur de likes

        @CreatedDate
        @Column("created_at")
        Instant createdAt
) {}
