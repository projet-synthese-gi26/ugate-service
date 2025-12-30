package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.syndicate;


import com.yowyob.ugate_service.application.service.syndicate.MembershipService;
import com.yowyob.ugate_service.application.service.syndicate.SyndicateMembershipService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.BasicResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/admin/syndicates")
@RequiredArgsConstructor
@Tag(name = "Syndicat Admin Management", description = "Gestion de l'état d'un Syndicat ( l'approuver, le désactiver, etc)")
public class SyndicateMangementController {
    private final SyndicateMembershipService membershipService;
    @PostMapping("/admin/add-member")
    @Operation(summary = "Ajouter un membre manuellement (Admin)")
    public Mono<ResponseEntity<BasicResponse>> addMember(
            @PathVariable UUID syndicatId,
            @RequestBody AddMemberRequest request) {

        return membershipService.addMemberByAdmin(
                syndicatId,
                request.email(),
                request.firstName(),
                request.lastName()
        ).thenReturn(ResponseEntity.ok(new BasicResponse("Membre invité avec succès")));
    }

    public record AddMemberRequest(String email, String firstName, String lastName) {}
}
