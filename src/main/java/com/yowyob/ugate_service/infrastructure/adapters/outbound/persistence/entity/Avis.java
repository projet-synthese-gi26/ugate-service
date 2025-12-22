package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("avis")
public record Avis(
        @Id
        Long id,

        @Column("user_id")
        Long userId,      // FK User

        @Column("product_id")
        Long productId,   // FK Product

        @Column("syndicat_id")
        Long syndicatId,  // FK Syndicat

        String comment,

        Integer number,   // Note / Rating

        @CreatedDate
        @Column("created_at")
        Instant createdAt
) {
    // Les avis sont souvent immuables fonctionnellement (on modifie rarement un avis),
    // donc pas de méthode "with" nécessaire immédiatement.
}