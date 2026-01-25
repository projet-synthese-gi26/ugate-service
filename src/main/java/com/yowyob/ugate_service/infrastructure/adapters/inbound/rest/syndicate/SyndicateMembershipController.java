package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.syndicate;

import com.yowyob.ugate_service.application.service.syndicate.SyndicateMembershipService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.MembershipApprovalRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.MembershipRequestRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.UpdateMemberRoleRequest;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/syndicates")
@RequiredArgsConstructor
@Tag(name = "Gestion Syndicat & Membres", description = "Endpoints pour gérer les adhésions, les rôles et visualiser les statistiques.")
public class SyndicateMembershipController {

    private final SyndicateMembershipService membershipService;


    @Operation(summary = "Demander à rejoindre un syndicat", description = "Crée une demande d'adhésion pour une branche spécifique.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Demande créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides ou demande déjà existante")
    })
    @PostMapping("/{syndicatId}/memberships/request")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MembershipRequest> requestToJoin(
            @PathVariable UUID syndicatId,
            @Valid @RequestBody MembershipRequestRequest request) {
        return membershipService.requestToJoin(syndicatId, request.branchId(), request.motivation());
    }


}