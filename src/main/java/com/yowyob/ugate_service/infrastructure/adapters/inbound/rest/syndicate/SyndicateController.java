package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.syndicate;


import com.yowyob.ugate_service.application.service.syndicate.SyndicatManagementService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.UpdateSyndicateFullRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.PaginatedResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateDetailsResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/syndicates")
@RequiredArgsConstructor
@Tag(name = "Syndicats", description = "Gestion du cycle de vie des syndicats (Création, Recherche, Profil)")
public class SyndicateController {

    private final SyndicatManagementService syndicateService;

    @Operation(
            summary = "Créer un nouveau syndicat",
            description = "Permet à un utilisateur authentifié de soumettre une demande de création de syndicat. Nécessite l'upload du logo et des statuts.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Demande de création enregistrée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SyndicateResponse.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides ou fichiers manquants"),
            @ApiResponse(responseCode = "401", description = "Utilisateur non authentifié"),
            @ApiResponse(responseCode = "415", description = "Type de média non supporté (attendu: multipart/form-data)")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<SyndicateResponse> createSyndicate(
            @Parameter(description = "Nom officiel du syndicat", required = true)
            @RequestPart("name") String name,

            @Parameter(description = "Description complète et objectifs", required = true)
            @RequestPart("description") String description,

            @Parameter(description = "Domaine d'activité (ex: Transport, Commerce)", required = true)
            @RequestPart("domain") String domain,

            @Parameter(description = "Fichier image du logo (PNG/JPG)", required = true)
            @RequestPart("logo") FilePart logo,

            @Parameter(description = "Document officiel des statuts (PDF)", required = true)
            @RequestPart("document") FilePart document
    ) {
        log.debug("Réception demande création syndicat: {}", name);
        return syndicateService.createSyndicate(name, description, domain, logo, document);
    }



    @Operation(summary = "Lister les syndicats", description = "Récupère une liste paginée des syndicats enregistrés.", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public Mono<PaginatedResponse<SyndicateResponse>> getAllSyndicates(
            @Parameter(description = "Numéro de la page (0..N)")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Nombre d'éléments par page")
            @RequestParam(defaultValue = "10") int size
    ) {
        return syndicateService.getAllSyndicates(page, size);
    }



    @Operation(
            summary = "Lister les syndicats d'un utilisateur",
            description = "Récupère la liste de tous les syndicats dont l'utilisateur spécifié est membre.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste récupérée (peut être vide)")
    })
    @GetMapping("/user/{userId}")
    public Flux<SyndicateResponse> getUserSyndicates(
            @Parameter(description = "UUID de l'utilisateur", required = true)
            @PathVariable UUID userId
    ) {
        return syndicateService.getUserSyndicates(userId);
    }


    @Operation(summary = "Mes syndicats", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/mine")
    public Flux<SyndicateResponse> getMySyndicates() {
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> {
                    Jwt jwt = (Jwt) ctx.getAuthentication().getPrincipal();
                    return UUID.fromString(jwt.getSubject());
                })
                .flatMapMany(currentUserId -> syndicateService.getUserSyndicates(currentUserId));
    }


    @Operation(
            summary = "Mise à jour complète d'un syndicat (Créateur uniquement)",
            description = "Permet de modifier nom, description, domaine et fichiers. Les champs non envoyés restent inchangés.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<SyndicateResponse> updateSyndicate(
            @Parameter(description = "ID du syndicat") @PathVariable UUID id,

            @RequestPart(value = "name", required = false) String name,
            @RequestPart(value = "description", required = false) String description,
            @RequestPart(value = "domain", required = false) String domain,
            @RequestPart(value = "logo", required = false) FilePart logo,
            @RequestPart(value = "charte", required = false) FilePart charte,
            @RequestPart(value = "statusDoc", required = false) FilePart statusDoc
    ) {
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> UUID.fromString(((Jwt) ctx.getAuthentication().getPrincipal()).getSubject()))
                .flatMap(requesterId -> {
                    UpdateSyndicateFullRequest request = new UpdateSyndicateFullRequest(
                            name, description, domain, logo, charte, statusDoc
                    );
                    return syndicateService.updateSyndicateFull(id, requesterId, request);
                });
    }


    @Operation(
            summary = "Obtenir les détails complets d'un syndicat",
            description = "Retourne les informations du syndicat, de l'organisation liée, du créateur, les documents et les statistiques.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Détails récupérés avec succès"),
            @ApiResponse(responseCode = "404", description = "Syndicat introuvable")
    })
    @GetMapping("/{id}/details")
    public Mono<SyndicateDetailsResponse> getSyndicateDetails(
            @Parameter(description = "UUID du syndicat") @PathVariable UUID id
    ) {
        return syndicateService.getSyndicateDetails(id);
    }


}