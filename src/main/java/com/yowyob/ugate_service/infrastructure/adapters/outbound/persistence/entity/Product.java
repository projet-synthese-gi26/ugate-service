package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Table("product")
public record Product(
        // Cet ID doit correspondre à celui de abstract_product correspondant
        @Id
        Long id,

        @Column("organization_id")
        Long organizationId, // FK -> Organization

        // Certains champs semblent redondants avec AbstractProduct (name, description)
        // Je les inclus car ils sont dans le schéma spécifique de Product
        String name,
        String description,

        @Column("is_active")
        Boolean isActive,

        @Column("standard_price")
        BigDecimal standardPrice, // Utilisez BigDecimal pour les prix !

        @Column("departure_location")
        String departureLocation,

        @Column("arrival_location")
        String arrivalLocation,

        @Column("start_date")
        LocalDate startDate,

        @Column("start_time")
        LocalTime startTime,

        @Column("end_date")
        LocalDate endDate,

        @Column("end_time")
        LocalTime endTime,

        @Column("baggage_info")
        String baggageInfo,

        @Column("is_negotiable")
        Boolean isNegotiable,

        @Column("payment_method")
        String paymentMethod,

        String title,
        String status,

        // URLs : Souvent stocké en JSON ou tableau de texte dans Postgres
        // Ici mappé en String (JSON brut) pour simplifier
        @Column("product_urls")
        String productUrls,

        @Column("regular_amount")
        BigDecimal regularAmount,

        @Column("discount_percentage")
        Double discountPercentage, // Pourcentage peut être Double ou BigDecimal

        @Column("discounted_amount")
        BigDecimal discountedAmount,

        // Metadata : JSONB dans Postgres
        String metadata,

        @CreatedDate
        @Column("created_at")
        Instant createdAt,

        @LastModifiedDate
        @Column("updated_at")
        Instant updatedAt
) {}