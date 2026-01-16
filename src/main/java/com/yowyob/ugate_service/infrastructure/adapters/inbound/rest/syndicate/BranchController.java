package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.syndicate;

import com.yowyob.ugate_service.application.service.syndicate.BranchManagementService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreateBranchRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.UpdateBranchRequest;
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
}