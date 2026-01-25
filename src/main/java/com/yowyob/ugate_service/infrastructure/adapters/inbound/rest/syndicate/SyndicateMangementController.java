package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.syndicate;


import com.yowyob.ugate_service.application.service.syndicate.MembershipService;
import com.yowyob.ugate_service.application.service.syndicate.SyndicateMembershipService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.MembershipApprovalRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.UpdateMemberRoleRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.BasicResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateFullStatsResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.MembershipRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/admin/syndicates")
@RequiredArgsConstructor
@Tag(name = "Syndicat Admin Management", description = "Gestion de l'état d'un Syndicat ( l'approuver, le désactiver, etc)")
public class SyndicateMangementController {
    private final SyndicateMembershipService membershipService;
    @Operation(
            summary = "Ajouter un membre manuellement (Admin)",
            description = "Permet à un administrateur d'ajouter directement un utilisateur dans une branche spécifique du syndicat via son email.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Membre ajouté et invitation envoyée"),
            @ApiResponse(responseCode = "404", description = "Syndicat ou Branche introuvable"),
            @ApiResponse(responseCode = "400", description = "Données manquantes")
    })
    // Nouvelle route incluant le syndicat et la branche
    @PostMapping("/{syndicatId}/branches/{branchId}/members/add")
    public Mono<ResponseEntity<BasicResponse>> addMember(
            @Parameter(description = "ID du syndicat", required = true)
            @PathVariable UUID syndicatId,

            @Parameter(description = "ID de la branche cible", required = true)
            @PathVariable UUID branchId,

            @RequestBody AddMemberRequest request) {

        log.info("Admin ajoute membre {} dans la branche {}", request.email(), branchId);

        return membershipService.addMemberByAdmin(
                syndicatId,
                branchId,
                request.email(),
                request.firstName(),
                request.lastName()
        ).thenReturn(ResponseEntity.ok(new BasicResponse("Membre ajouté et invité avec succès")));
    }

    @Operation(summary = "Lister toutes les demandes d'un syndicat", description = "Retourne toutes les demandes en attente pour toutes les branches du syndicat.", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{syndicatId}/requests")
    public Flux<MembershipRequest> getSyndicateRequests(@PathVariable UUID syndicatId) {
        return membershipService.getRequestsBySyndicate(syndicatId);
    }

    @Operation(summary = "Lister les demandes d'une branche", description = "Retourne les demandes en attente pour une branche spécifique.", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{syndicatId}/branches/{branchId}/requests")
    public Flux<MembershipRequest> getBranchRequests(
            @PathVariable UUID syndicatId, // Gardé pour la cohérence de l'URL, même si non utilisé directement
            @PathVariable UUID branchId) {
        return membershipService.getRequestsByBranch(branchId);
    }

    @Operation(summary = "Obtenir les détails d'une demande", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/requests/{requestId}")
    public Mono<MembershipRequest> getRequestDetails(@PathVariable UUID requestId) {
        return membershipService.getRequestDetails(requestId);
    }

    @Operation(summary = "Traiter une demande (Approuver/Rejeter)", description = "Permet à un admin de valider ou refuser une adhésion.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Traitement effectué"),
            @ApiResponse(responseCode = "404", description = "Demande introuvable")
    })
    @PostMapping("/requests/{requestId}/process")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> processRequest(
            @PathVariable UUID requestId,
            @Valid @RequestBody MembershipApprovalRequest request) {
        return membershipService.processRequest(requestId, request.approve(), request.rejectionReason());
    }

    @Operation(
            summary = "Changer le rôle d'un membre",
            description = "Permet de modifier le rôle d'un membre du syndicat (ex: passer en MODERATOR).",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PatchMapping("/{syndicatId}/members/{userId}/role")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> updateMemberRole(
            @PathVariable UUID syndicatId,
            @PathVariable UUID userId,
            @Valid @RequestBody UpdateMemberRoleRequest request) {
        return membershipService.updateMemberRole(syndicatId, userId, request.newRole());
    }

    @Operation(
            summary = "Obtenir les statistiques du syndicat",
            description = "Renvoie les compteurs globaux (Membres, Branches, Demandes, Publications) pour le tableau de bord.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/{syndicatId}/stats")
    public Mono<SyndicateFullStatsResponse> getSyndicateStats(@PathVariable UUID syndicatId) {
        return membershipService.getSyndicateStats(syndicatId);
    }

    public record AddMemberRequest(String email, String firstName, String lastName) {}
}
