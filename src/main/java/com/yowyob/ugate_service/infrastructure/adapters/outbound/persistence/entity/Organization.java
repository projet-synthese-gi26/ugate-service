package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Table("organizations")
public record Organization(
        @Id
        UUID id,

        @Column("business_actor_id")
        UUID businessActorId,

        @Column("logo_id")
        UUID logoId,

        String code,
        String service,

        @Column("is_individual_business")
        Boolean isIndividualBusiness,

        String email,

        @Column("short_name")
        String shortName,

        @Column("long_name")
        String longName,

        String description,

        @Column("logo_uri")
        String logoUri,

        @Column("website_url")
        String websiteUrl,

        @Column("social_network") // Stocké souvent en JSON String pour les liens multiples
        String socialNetwork,

        @Column("business_registration_number")
        String businessRegistrationNumber,

        @Column("tax_number")
        String taxNumber,

        @Column("capital_share")
        BigDecimal capitalShare,

        @Column("ceo_name")
        String ceoName,

        @Column("year_founded")
        Integer yearFounded,

        String keywords,

        @Column("number_of_employees")
        Integer numberOfEmployees,

        @Column("legal_form")
        String legalForm,

        @Column("is_active")
        Boolean isActive,

        String status,

        @CreatedDate
        @Column("created_at")
        Instant createdAt,

        @LastModifiedDate
        @Column("updated_at")
        Instant updatedAt,

        @Column("deleted_at")
        Instant deletedAt
) implements Persistable<UUID> {

    public boolean isDeleted() {
        return deletedAt != null;
    }

    public Organization(UUID id, UUID businessActorId, String code, String email,
                        String shortName, String longName, String legalForm) {
        this(
                id,
                businessActorId,
                null,           // logoId
                code,
                "SYNDICAT",      // service par défaut
                false,          // isIndividualBusiness
                email,
                shortName,
                longName,
                null,           // description
                null,           // logoUri
                null,           // websiteUrl
                null,           // socialNetwork
                null,           // businessRegistrationNumber
                null,           // taxNumber
                null,           // capitalShare
                null,           // ceoName
                null,           // yearFounded
                null,           // keywords
                null,           // numberOfEmployees
                legalForm,
                true,           // isActive
                "PENDING",      // status initial
                null,           // createdAt (géré par @CreatedDate)
                null,           // updatedAt (géré par @LastModifiedDate)
                null            // deletedAt
        );
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    @Transient // Ne doit pas être persisté
    public boolean isNew() {
        // Une organisation est considérée comme nouvelle si elle n'a pas encore de date de création
        return createdAt == null;
    }

}
