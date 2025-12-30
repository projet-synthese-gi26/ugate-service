package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.superadmin;

import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.StatsResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatMemberRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/super-admin/analytics")
@RequiredArgsConstructor
@Tag(name = "SuperAdmin Analytics", description = "Statistiques globales de la plateforme")
public class SuperAdminAnalyticsController {

    private final SyndicatRepository syndicatRepository;
    private final SyndicatMemberRepository memberRepository;

    @Operation(summary = "Obtenir les KPIs globaux", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/dashboard")
    public Mono<StatsResponse> getDashboardStats() {
        return Mono.zip(
                syndicatRepository.count(),                 // T1: Total Syndicats
                syndicatRepository.countByIsApprovedTrue(), // T2: Approuvés
                syndicatRepository.countByIsApprovedFalse(),// T3: En attente
                memberRepository.count(),                   // T4: Total Membres
                memberRepository.countByIsActiveTrue()      // T5: Membres Actifs
        ).map(tuple -> new StatsResponse(
                tuple.getT1(),
                tuple.getT2(),
                tuple.getT3(),
                tuple.getT4(),
                tuple.getT5(),
                45230.00 // Mock revenu pour l'instant
        ));
    }
}