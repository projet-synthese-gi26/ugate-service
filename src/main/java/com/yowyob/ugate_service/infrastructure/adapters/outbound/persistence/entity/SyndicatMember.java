package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;


import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.enumeration.RoleTypeEnum;
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

        @Column("branch_id")
        UUID branchId,

        @CreatedDate     // Rempli automatiquement si l'auditing est activé
        @Column("joined_at")
        Instant joinedAt,

        @Column("is_active")
        Boolean isActive,

        @Column("role") RoleTypeEnum role   // MEMBER, MODERATOR, ADMIN
) {

    public static SyndicatMember createLocal(UUID syndicatId, UUID branchId, UUID userId, RoleTypeEnum role) {
        if (branchId == null) {
            throw new IllegalArgumentException("Un membre local doit avoir une branche (branchId ne peut pas être null)");
        }
        return new SyndicatMember(syndicatId, userId, branchId, Instant.now(), true, role);
    }


    public static SyndicatMember createGlobalAdmin(UUID syndicatId, UUID userId) {
        return new SyndicatMember(syndicatId, userId, null, Instant.now(), true, RoleTypeEnum.ADMIN);
    }

    public SyndicatMember withRole(RoleTypeEnum newRole) {
        return new SyndicatMember(syndicatId, userId, branchId, joinedAt, isActive, newRole);
    }

    public boolean isGlobalAdmin() {
        return RoleTypeEnum.ADMIN.equals(this.role) && this.branchId == null;
    }



    public boolean isStatusActive() {
        return Boolean.TRUE.equals(isActive);
    }

    public boolean isStatusDisabled() {
        return !isStatusActive();
    }

    public boolean isAdmin() {
        return RoleTypeEnum.ADMIN.equals(this.role);
    }

    public boolean isModerator() {
        return RoleTypeEnum.MODERATOR.equals(this.role);
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
    public boolean hasRole(RoleTypeEnum targetRole) {
        return this.role == targetRole;
    }
}