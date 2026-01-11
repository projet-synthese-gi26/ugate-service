package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.compliance;


import com.yowyob.ugate_service.application.service.compliance.ComplianceService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.BatchComplianceRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.FeedbackRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.WebhookStatusChangeRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.BatchComplianceResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ComplianceResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.OfficialProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/compliance")
@RequiredArgsConstructor
@Tag(name = "Conformité (Middleware)", description = "API  pour la vérification temps réel des chauffeurs, la récupération du Jumeau Numérique et la gestion de la réputation.")
public class ComplianceController {

    private final ComplianceService complianceService;


    @Operation(
            summary = "Vérifier l'éligibilité d'un chauffeur (Check-In)",
            description = "Appelé par les services comme Ride & Go avant la mise en ligne. Vérifie le statut syndical, les suspensions et la validité du dossier. Utilise un cache Redis pour la haute performance.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vérification effectuée (Voir le champ 'globalStatus' dans la réponse)",
                    content = @Content(schema = @Schema(implementation = ComplianceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Format de l'ID invalide"),
            @ApiResponse(responseCode = "401", description = "Non authentifié")
    })
    @GetMapping("/check/{driverId}")
    public Mono<ResponseEntity<ComplianceResponse>> checkEligibility(
            @Parameter(description = "UUID du chauffeur (User ID)", required = true)
            @PathVariable UUID driverId
    ) {
        return complianceService.checkCompliance(driverId)
                .map(ResponseEntity::ok);
    }


    @Operation(
            summary = "Récupérer le profil officiel (Jumeau Numérique)",
            description = "Récupère les données certifiées par le syndicat (Nom légal, Photo officielle, Véhicule déclaré) pour affichage client.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profil trouvé",
                    content = @Content(schema = @Schema(implementation = OfficialProfileResponse.class))),
            @ApiResponse(responseCode = "404", description = "Chauffeur introuvable ou non syndiqué")
    })
    @GetMapping("/details/{driverId}")
    public Mono<ResponseEntity<OfficialProfileResponse>> getOfficialProfile(
            @Parameter(description = "UUID du chauffeur", required = true)
            @PathVariable UUID driverId
    ) {
        return complianceService.getOfficialProfile(driverId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Soumettre un avis ou un signalement",
            description = "Permet à Ride & Go de transmettre un feedback (Note, Commentaire, Incident grave) vers le dossier syndical du chauffeur. Traitement asynchrone.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Feedback accepté pour traitement"),
            @ApiResponse(responseCode = "400", description = "Données invalides (ex: Note > 5, ID manquant)")
    })
    @PostMapping("/feedback")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> submitFeedback(
            @Parameter(description = "Détails de l'avis ou de l'incident", required = true)
            @Valid @RequestBody FeedbackRequest request
    ) {
        log.info("Réception signalement (RideID: {})", request.rideGoTripId());
        return complianceService.processFeedback(request);
    }


    @PostMapping("/check/batch")
    @Operation(summary = "Vérifier l'éligibilité d'un lot de chauffeurs")
    public Mono<BatchComplianceResponse> checkEligibilityBatch(@RequestBody BatchComplianceRequest request) {
        return complianceService.checkComplianceBatch(request.driverIds());
    }

    @PostMapping("/syndicats/status-change")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Notification de changement de statut (Bannissement)")
    public Mono<Void> onDriverStatusChange(@RequestBody WebhookStatusChangeRequest request) {
        return complianceService.invalidateCache(request.driverId());
    }

}