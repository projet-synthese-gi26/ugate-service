package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;

@Table("publication")
public record Publication(
        @Id
        Long id,

        @Column("branch_id")
        Long branchId,    // FK -> Branch

        @Column("author_id")
        Long authorId,    // FK -> User

        String content,
        String status,    // Ex: "PUBLISHED", "DRAFT"

        @Column("n_likes")
        Long nLikes,      // Compteur de likes

        @CreatedDate
        @Column("created_at")
        Instant createdAt
) {}
