package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.enumeration.MemberRole;
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
        Boolean isActive,

        @Column("role") MemberRole role   // MEMBER, MODERATOR, ADMIN
) {
    // Constructeur utilitaire pour une adhésion par défaut
    public static SyndicatMember create(UUID syndicatId, UUID userId, MemberRole role) {
        return new SyndicatMember(syndicatId, userId, null, true, role);
    }

    public boolean isStatusActive() {
        return Boolean.TRUE.equals(isActive);
    }

    public boolean isStatusDisabled() {
        return !isStatusActive();
    }

    public boolean isAdmin() {
        return MemberRole.ADMIN.equals(this.role);
    }

    public boolean isModerator() {
        return MemberRole.MODERATOR.equals(this.role);
    }

    /**
     * Vérifie si l'utilisateur possède un privilège de gestion (Admin ou Modérateur)
     */
    public boolean hasManagementPrivileges() {
        return isAdmin() || isModerator();
    }

    /**
     * Version générique pour vérifier n'importe quel rôle
     */
    public boolean hasRole(MemberRole targetRole) {
        return this.role == targetRole;
    }
}