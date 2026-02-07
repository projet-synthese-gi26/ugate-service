package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.syndicate;

import com.yowyob.ugate_service.application.service.syndicate.BranchManagementService;
import com.yowyob.ugate_service.application.service.syndicate.SyndicateMembershipService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreateBranchRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.UpdateBranchRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.BranchMembersStatsResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.BranchResponse;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "Branches", description = "Gestion des branches (agences) des syndicats")
public class BranchController {

    private final BranchManagementService branchService;
    private final SyndicateMembershipService membershipService;

    @Operation(
            summary = "Créer une branche pour un syndicat",
            description = "Permet de créer une branche avec une bannière optionnelle.",
            security = @SecurityRequirement(name = "bearerAuth"),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = CreateBranchRequest.class)
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Branche créée"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping(
            value = "/syndicates/{syndicatId}/branches",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BranchResponse> createBranch(
            @Parameter(description = "ID du syndicat", required = true)
            @PathVariable UUID syndicatId,
            @Parameter(hidden = true)
            @Valid @ModelAttribute CreateBranchRequest request) {

        return branchService.createBranch(syndicatId, request);
    }

    @Operation(summary = "Lister les branches d'un syndicat", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/syndicates/{syndicatId}/branches")
    public Flux<BranchResponse> getBranchesBySyndicate(@PathVariable UUID syndicatId) {
        return branchService.getSyndicateBranches(syndicatId);
    }

    @Operation(summary = "Mettre à jour une branche", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping("/branches/{branchId}")
    public Mono<BranchResponse> updateBranch(
            @PathVariable UUID branchId,
            @RequestBody UpdateBranchRequest request) {
        return branchService.updateBranch(branchId, request);
    }


    @Operation(summary = "Obtenir les détails d'une branche", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Détails trouvés"),
            @ApiResponse(responseCode = "404", description = "Branche introuvable")
    })
    @GetMapping("/branches/{branchId}")
    public Mono<BranchResponse> getBranchDetails(
            @Parameter(description = "ID de la branche", required = true)
            @PathVariable UUID branchId) {
        return branchService.getBranchDetails(branchId);
    }


    @Operation(
            summary = "Lister les membres d'une branche",
            description = "Retourne le nombre total et la liste des membres (y compris l'admin s'il est affecté à cette branche).",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste récupérée")
    })
    @GetMapping("/branches/{branchId}/members")
    public Mono<BranchMembersStatsResponse> getBranchMembers(
            @Parameter(description = "ID de la branche", required = true)
            @PathVariable UUID branchId) {
        return membershipService.getBranchMembers(branchId);
    }
}