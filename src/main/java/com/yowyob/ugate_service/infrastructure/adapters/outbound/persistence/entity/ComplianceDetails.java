package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("compliance_details")
public record ComplianceDetails(
        @Id
        @Column("user_id")
        UUID userId,

        // --- Profil ---
        @Column("profile_photo_url") String profilePhotoUrl,
        @Column("cv_url") String cvUrl,

        // --- CNI ---
        @Column("cni_number") String cniNumber,
        @Column("cni_recto_url") String cniRectoUrl,
        @Column("cni_verso_url") String cniVersoUrl,

        // --- Permis ---
        @Column("license_number") String licenseNumber,
        @Column("license_recto_url") String licenseRectoUrl,
        @Column("license_verso_url") String licenseVersoUrl,

        // --- Meta ---
        @Column("is_verified") Boolean isVerified,

        @LastModifiedDate
        @Column("updated_at") Instant updatedAt,

        // Champ technique pour gérer l'INSERT manuel
        @Transient boolean isNewRecord

) implements Persistable<UUID> {


    @PersistenceCreator
    public ComplianceDetails(UUID userId, String profilePhotoUrl, String cvUrl, String cniNumber,
                             String cniRectoUrl, String cniVersoUrl, String licenseNumber,
                             String licenseRectoUrl, String licenseVersoUrl, Boolean isVerified, Instant updatedAt) {
        this(userId, profilePhotoUrl, cvUrl, cniNumber, cniRectoUrl, cniVersoUrl,
                licenseNumber, licenseRectoUrl, licenseVersoUrl, isVerified, updatedAt, false);
    }

    /**
     * Constructeur utilitaire pour créer un NOUVEL objet (pour un INSERT).
     */
    public static ComplianceDetails createNew(UUID userId) {
        return new ComplianceDetails(
                userId, null, null, null, null, null, null, null, null, false, null, true
        );
    }





    // Constructeur vide pour permettre l'initialisation par défaut
    public static ComplianceDetails createEmpty(UUID userId) {
        return new ComplianceDetails(
                userId, null, null, null, null, null, null, null, null, false, null, true
        );
    }

    @Override
    public UUID getId() {
        return userId;
    }

    @Override
    @Transient
    public boolean isNew() {
        // Si true -> Spring fait un INSERT
        // Si false -> Spring fait un UPDATE
        return isNewRecord;
    }

    // --- Méthodes "Wither" pour mettre à jour partiellement ---

    public ComplianceDetails withProfilePhoto(String url) {
        return new ComplianceDetails(
                this.userId, url, this.cvUrl, this.cniNumber, this.cniRectoUrl, this.cniVersoUrl,
                this.licenseNumber, this.licenseRectoUrl, this.licenseVersoUrl, this.isVerified, Instant.now(), false
        );
    }

    public ComplianceDetails withCni(String number, String rectoUrl, String versoUrl) {
        return new ComplianceDetails(
                this.userId, this.profilePhotoUrl, this.cvUrl, number, rectoUrl, versoUrl,
                this.licenseNumber, this.licenseRectoUrl, this.licenseVersoUrl, this.isVerified, Instant.now(), false
        );
    }

    public ComplianceDetails withLicense(String number, String rectoUrl, String versoUrl) {
        return new ComplianceDetails(
                this.userId, this.profilePhotoUrl, this.cvUrl, this.cniNumber, this.cniRectoUrl, this.cniVersoUrl,
                number, rectoUrl, versoUrl, this.isVerified, Instant.now(), false
        );
    }
}