package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.syndicate;

import com.yowyob.ugate_service.application.service.auth.UserManagementService;
import com.yowyob.ugate_service.application.service.syndicate.SyndicateMembershipService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.MembershipApprovalRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.MembershipRequestRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.UpdateFullProfileRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.UpdateMemberRoleRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.MemberResponse;
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
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/syndicates")
@RequiredArgsConstructor
@Tag(name = "Gestion Syndicat & Membres", description = "Endpoints pour gérer les adhésions, les rôles et visualiser les statistiques.")
public class SyndicateMembershipController {

    private final SyndicateMembershipService membershipService;
    private final UserManagementService userManagementService;


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


    @Operation(summary = "Mise à jour complète du profil de l'utilisateur connecté", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/user")
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<MemberResponse> updateMyProfile(
            @AuthenticationPrincipal Jwt jwt,
            @RequestPart(value = "firstName", required = false) String firstName,
            @RequestPart(value = "lastName", required = false) String lastName,
            @RequestPart(value = "phoneNumber", required = false) String phoneNumber,
            @RequestPart(value = "nationality", required = false) String nationality,
            @RequestPart(value = "gender", required = false) String gender,
            @RequestPart(value = "language", required = false) String language,
            @RequestPart(value = "birthDate", required = false) String birthDateStr, // Format ISO
            @RequestPart(value = "image", required = false) FilePart image
    ) {
        UUID userId = UUID.fromString(jwt.getSubject());

        Instant birthDate = (birthDateStr != null) ? Instant.parse(birthDateStr) : null;

        UpdateFullProfileRequest request = new UpdateFullProfileRequest(
                firstName, lastName, phoneNumber, nationality, gender, language, birthDate, image
        );

        return userManagementService.updateFullProfile(userId, request);
    }


}