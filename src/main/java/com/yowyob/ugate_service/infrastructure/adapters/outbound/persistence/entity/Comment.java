package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;

@Table("comment")
public record Comment(
        @Id
        Long id,

        @Column("author_id")
        Long authorId,       // FK -> User

        @Column("publication_id")
        Long publicationId,  // FK -> Publication

        @Column("parent_id")
        Long parentId,       // FK -> Comment (Nullable : null si commentaire racine)

        @Column("image_id")
        Long imageId,        // FK -> Image (Nullable)

        String content,

        @CreatedDate
        @Column("created_at")
        Instant createdAt
) {}
