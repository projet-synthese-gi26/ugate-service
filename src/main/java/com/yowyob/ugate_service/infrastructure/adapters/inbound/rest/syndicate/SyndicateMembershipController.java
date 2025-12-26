package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.syndicate;


import com.yowyob.ugate_service.application.service.syndicate.SyndicateMembershipService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.MembershipApprovalRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.MembershipRequestRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/syndicates/{syndicatId}/memberships")
@RequiredArgsConstructor
@Tag(name = "Adhésions", description = "Gestion des demandes et statuts des membres")
public class SyndicateMembershipController {

    private final SyndicateMembershipService membershipService;

    @PostMapping("/request")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Demander à rejoindre un syndicat")
    public Mono<?> requestToJoin(@PathVariable UUID syndicatId, @Valid @RequestBody MembershipRequestRequest request) {
        return membershipService.requestToJoin(syndicatId, request.branchId(), request.motivation());
    }

    @PostMapping("/requests/{requestId}/process")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Traiter une demande d'adhésion (Admin)")
    public Mono<Void> processRequest(@PathVariable UUID syndicatId, @PathVariable UUID requestId, @Valid @RequestBody MembershipApprovalRequest request) {
        // La validation de l'admin se fera dans le service
        return membershipService.processRequest(requestId, request.approve(), request.rejectionReason());
    }
}