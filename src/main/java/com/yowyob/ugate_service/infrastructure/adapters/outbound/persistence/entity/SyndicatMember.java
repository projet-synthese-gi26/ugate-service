package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Table("syndicat_members")
public record SyndicatMember(

        @Column("syndicat_id")
        UUID syndicatId, // FK -> Syndicat

        @Column("user_id")
        UUID userId,     // FK -> AppUser

        @CreatedDate     // Rempli automatiquement si l'auditing est activé
        @Column("joined_at")
        Instant joinedAt,

        @Column("is_active")
        Boolean isActive
) {
    // Constructeur utilitaire pour une adhésion par défaut
    public static SyndicatMember create(UUID syndicatId, UUID userId) {
        return new SyndicatMember(syndicatId, userId, null, true);
    }
}