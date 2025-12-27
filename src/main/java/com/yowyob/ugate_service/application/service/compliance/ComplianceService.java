package com.yowyob.ugate_service.application.service.compliance;


import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.FeedbackRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.BatchComplianceResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ComplianceResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.OfficialProfileResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.*;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.AvisRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ComplianceDetailsRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatMemberRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComplianceService {

    private final ReactiveRedisTemplate<String, Object> redisTemplate;

    // Repositories Locaux (Sources de vérité)
    private final SyndicatMemberRepository memberRepository;
    private final SyndicatRepository syndicatRepository;
    private final AvisRepository avisRepository;
    private final ComplianceDetailsRepository complianceDetailsRepository; // <--- AJOUTÉ

    // Gateway vers TraMaSys (Auth)
    private final UserGatewayPort userGatewayPort;

    private static final Duration CACHE_TTL = Duration.ofMinutes(15);


    public Mono<ComplianceResponse> checkCompliance(UUID driverId) {
        String cacheKey = "compliance:" + driverId;

        // 1. Check Cache Redis
        return redisTemplate.opsForValue().get(cacheKey)
                .cast(ComplianceResponse.class)
                .switchIfEmpty(
                        // 2. Cache Miss -> Vérification complète en Base de Données
                        verifyDriverStatusLocally(driverId)
                                .flatMap(response -> redisTemplate.opsForValue()
                                        .set(cacheKey, response, CACHE_TTL)
                                        .thenReturn(response))
                );
    }

    private Mono<ComplianceResponse> verifyDriverStatusLocally(UUID driverId) {

        // 1. On récupère TOUTES les adhésions du chauffeur (renvoie un Flux)
        return memberRepository.findAllByUserId(driverId)
                // 2. Pour chaque adhésion, on récupère les détails du syndicat associé
                .flatMap(member ->
                        syndicatRepository.findById(member.syndicatId())
                                .map(syndicat -> buildPartialResponse(member, syndicat))
                )
                // 3. On rassemble toutes les "décisions partielles" dans une liste
                .collectList()
                .map(partialResults -> {
                    // Si la liste est vide, le chauffeur n'est membre d'aucun syndicat
                    if (partialResults.isEmpty()) {
                        return buildBannedResponse(driverId, "NOT_A_MEMBER");
                    }

                    // RÈGLE MÉTIER : On cherche si au moins UNE des adhésions est valide (statut AUTHORIZED)
                    boolean isAuthorized = partialResults.stream()
                            .anyMatch(res -> "AUTHORIZED".equals(res.globalStatus()));

                    if (isAuthorized) {
                        // Si on trouve une adhésion valide, on retourne ce statut.
                        return partialResults.stream()
                                .filter(res -> "AUTHORIZED".equals(res.globalStatus()))
                                .findFirst().get();
                    } else {
                        // Sinon (toutes les adhésions sont suspendues ou dans des syndicats non approuvés),
                        // on retourne le premier statut "RESTRICTED" trouvé.
                        return partialResults.get(0);
                    }
                })
                // Sécurité : si le flux est vide du début à la fin
                .defaultIfEmpty(buildBannedResponse(driverId, "NOT_A_MEMBER"));
    }

    private ComplianceResponse buildPartialResponse(SyndicatMember member, Syndicat syndicat) {
        boolean isMembershipActive = Boolean.TRUE.equals(member.isActive());
        boolean isSyndicatApproved = Boolean.TRUE.equals(syndicat.isApproved());

        String globalStatus = (isMembershipActive && isSyndicatApproved) ? "AUTHORIZED" : "RESTRICTED";

        List<String> restrictions = new java.util.ArrayList<>();
        if (!isMembershipActive) restrictions.add("MEMBERSHIP_SUSPENDED");
        if (!isSyndicatApproved) restrictions.add("SYNDICATE_NOT_APPROVED");

        return new ComplianceResponse(
                member.userId().toString(),
                Instant.now(),
                globalStatus,
                new ComplianceResponse.ComplianceDetails(isSyndicatApproved, isSyndicatApproved, isMembershipActive, true),
                restrictions
        );
    }

    private ComplianceResponse buildResponseFromEntities(UUID driverId, SyndicatMember member, Syndicat syndicat, ComplianceDetails details) {
        boolean isMembershipActive = Boolean.TRUE.equals(member.isActive());
        boolean isSyndicatApproved = Boolean.TRUE.equals(syndicat.isApproved());

        // Vérification du dossier administratif
        // Le permis est considéré valide si le numéro existe ET que le dossier a été marqué "isVerified" par un admin
        boolean isLicenseValid = details.licenseNumber() != null && !details.licenseNumber().isBlank() && Boolean.TRUE.equals(details.isVerified());

        // TODO: Ajouter table Assurance plus tard. Pour l'instant on considère OK si le dossier est vérifié.
        boolean isInsuranceValid = Boolean.TRUE.equals(details.isVerified());

        // Règle métier globale
        boolean isAuthorized = isMembershipActive && isSyndicatApproved && isLicenseValid;
        String globalStatus = isAuthorized ? "AUTHORIZED" : "RESTRICTED";

        List<String> restrictions = new ArrayList<>();
        if (!isMembershipActive) restrictions.add("MEMBERSHIP_SUSPENDED");
        if (!isSyndicatApproved) restrictions.add("SYNDICATE_NOT_APPROVED");
        if (!isLicenseValid) restrictions.add("DOCUMENTS_NOT_VERIFIED");

        return new ComplianceResponse(
                driverId.toString(),
                Instant.now(),
                globalStatus,
                new ComplianceResponse.ComplianceDetails(
                        isLicenseValid,
                        isInsuranceValid,
                        isMembershipActive,
                        true // Medical check (Mock pour l'instant)
                ),
                restrictions
        );
    }

    private ComplianceResponse buildBannedResponse(UUID driverId, String reason) {
        return new ComplianceResponse(
                driverId.toString(),
                Instant.now(),
                "BANNED",
                new ComplianceResponse.ComplianceDetails(false, false, false, false),
                List.of(reason)
        );
    }


    public Mono<OfficialProfileResponse> getOfficialProfile(UUID driverId) {
        // 1. Récupérer l'identité de base
        Mono<User> userMono = userGatewayPort.findById(driverId);

        // 2. Récupérer les détails officiels (ou un objet vide si inexistant)
        Mono<ComplianceDetails> detailsMono = complianceDetailsRepository.findById(driverId)
                .defaultIfEmpty(ComplianceDetails.createEmpty(driverId));

        return Mono.zip(userMono, detailsMono)
                .map(tuple -> {
                    User user = tuple.getT1();
                    ComplianceDetails details = tuple.getT2();

                    // Logique de priorité pour la photo
                    String officialPhotoUrl = (details.profilePhotoUrl() != null && !details.profilePhotoUrl().isBlank())
                            ? details.profilePhotoUrl()
                            : user.profilUrl();


                    return new OfficialProfileResponse(
                            user.id().toString(),
                            user.firstName(),
                            user.lastName(),
                            officialPhotoUrl,
                            details.cvUrl(),
                            details.cniNumber(),
                            details.cniRectoUrl(),
                            details.cniVersoUrl(),
                            details.licenseNumber(),
                            details.licenseRectoUrl(),
                            details.licenseVersoUrl(),
                            details.isVerified()
                    );
                });
    }


    public Mono<BatchComplianceResponse> checkComplianceBatch(List<UUID> driverIds) {

        return Flux.fromIterable(driverIds)
                // Pour chaque ID, on appelle la logique de check unitaire
                .flatMap(driverId -> checkCompliance(driverId)
                        .map(response -> new java.util.AbstractMap.SimpleEntry<>(driverId, response))
                )
                .collect(Collectors.toMap(java.util.Map.Entry::getKey, java.util.Map.Entry::getValue))
                .map(BatchComplianceResponse::new);
    }


    public Mono<Void> processFeedback(FeedbackRequest request) {
        log.info("Enregistrement Feedback local pour chauffeur {}", request.syndicatDriverId());

        try {
            UUID driverId = UUID.fromString(request.syndicatDriverId());

            // On récupère l'ID du syndicat du chauffeur pour lier l'avis correctement
            return memberRepository.findByUserId(driverId)
                    .map(SyndicatMember::syndicatId)
                    .defaultIfEmpty(null) // Si pas membre, syndicatId reste null
                    .flatMap(syndicatId -> {
                        if (syndicatId == null) {
                            log.warn("Feedback reçu pour un chauffeur sans syndicat : {}", driverId);
                        }

                        Avis avis = new Avis(
                                UUID.randomUUID(),
                                null, // User ID noteur (non fourni)
                                null,
                                syndicatId,
                                request.comment(),
                                request.rating(),
                                Instant.now()
                        );
                        return avisRepository.save(avis);
                    })
                    .then();

        } catch (IllegalArgumentException e) {
            return Mono.error(new RuntimeException("ID Chauffeur invalide"));
        }
    }

    public Mono<Void> invalidateCache(UUID driverId) {
        String cacheKey = "compliance:" + driverId;
        log.info("Invalidation manuelle du cache pour le chauffeur : {}", driverId);
        return redisTemplate.opsForValue().delete(cacheKey).then();
    }
}