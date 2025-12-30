package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.superadmin;

import com.yowyob.ugate_service.application.service.syndicate.SyndicatManagementService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.StatsResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatMemberRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/super-admin/syndicates")
@RequiredArgsConstructor
@Tag(name = "SuperAdmin Management", description = "Gestion de l'état d'un Syndicat ( l'approuver, le désactiver, etc)")
public class SyndicateSuperAdminController {

    private final SyndicatManagementService syndicateService;
    private final SyndicatRepository syndicatRepository;
    private final SyndicatMemberRepository memberRepository;



    @Operation(
            summary = "Approuver un syndicat",
            description = "Valide officiellement le syndicat après vérification des documents.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PatchMapping("/{id}/approve")
    public Mono<SyndicateResponse> approveSyndicate(@PathVariable UUID id) {
        log.info("Requête d'approbation pour le syndicat: {}", id);
        return syndicateService.approve(id);
    }

    @Operation(summary = "Désapprouver un syndicat", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping("/{id}/disapprove")
    public Mono<SyndicateResponse> disapproveSyndicate(@PathVariable UUID id) {
        log.info("Requête de désapprobation pour le syndicat: {}", id);
        return syndicateService.disapprove(id);
    }

    @Operation(
            summary = "Activer un syndicat",
            description = "Rend le syndicat visible et opérationnel sur la plateforme.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PatchMapping("/{id}/activate")
    public Mono<SyndicateResponse> activateSyndicate(@PathVariable UUID id) {
        log.info("Requête d'activation pour le syndicat: {}", id);
        return syndicateService.activate(id);
    }

    @Operation(summary = "Désactiver un syndicat", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping("/{id}/deactivate")
    public Mono<SyndicateResponse> deactivateSyndicate(@PathVariable UUID id) {
        log.info("Requête de désactivation pour le syndicat: {}", id);
        return syndicateService.deactivate(id);
    }


    @GetMapping("/dashboard")
    @Operation(summary = "Obtenir les statistiques globales")
    public Mono<StatsResponse> getGlobalStats() {
        return Mono.zip(
                syndicatRepository.count(),
                syndicatRepository.countByIsActiveTrue(),
                syndicatRepository.countByIsApprovedFalse(),
                memberRepository.count(),
                memberRepository.countByIsActiveTrue()
        ).map(tuple -> new StatsResponse(
                tuple.getT1(),
                tuple.getT2(),
                tuple.getT3(),
                tuple.getT4(),
                tuple.getT5(),
                0.0
        ));
    }


}
