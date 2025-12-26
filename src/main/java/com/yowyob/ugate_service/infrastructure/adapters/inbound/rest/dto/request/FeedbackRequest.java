package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;



import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.List;

public record FeedbackRequest(

        @NotBlank(message = "L'ID du trajet Ride & Go est obligatoire")
        String rideGoTripId,

        @NotBlank(message = "L'ID du chauffeur est obligatoire")
        String syndicatDriverId,

        @NotNull(message = "La date de l'incident est obligatoire")
        Instant incidentDate,

        @NotNull(message = "La note est obligatoire")
        @Min(value = 1, message = "La note doit être comprise entre 1 et 5")
        @Max(value = 5, message = "La note doit être comprise entre 1 et 5")
        Integer rating,

        @NotBlank(message = "La sévérité est obligatoire (LOW, MEDIUM, HIGH, CRITICAL)")
        String severity,

        // Liste optionnelle de catégories (ex: ["DANGEROUS_DRIVING"])
        List<String> categories,

        String comment,

        @NotBlank(message = "Le hash du passager est obligatoire pour l'anonymat")
        String passengerHash
) {}
