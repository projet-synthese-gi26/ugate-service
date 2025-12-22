package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Table("comment")
public record Comment(
        @Id
        UUID id,

        @Column("author_id")
        UUID authorId,       // FK -> User

        @Column("publication_id")
        UUID publicationId,  // FK -> Publication

        @Column("parent_id")
        UUID parentId,       // FK -> Comment (Nullable : null si commentaire racine)

        @Column("image_id")
        UUID imageId,        // FK -> Image (Nullable)

        String content,

        @CreatedDate
        @Column("created_at")
        Instant createdAt
) {}
