package com.yowyob.ugate_service.application.service.compliance;


import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.FeedbackRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.BatchComplianceResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ComplianceResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.OfficialProfileResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.*;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.*;
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

    private final ReactiveRedisTemplate<String, ComplianceResponse> redisTemplate;

    private final SyndicatMemberRepository memberRepository;
    private final SyndicatRepository syndicatRepository;
    private final AvisRepository avisRepository;
    private final ComplianceDetailsRepository complianceDetailsRepository;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final UserGatewayPort userGatewayPort;

    private static final Duration CACHE_TTL = Duration.ofMinutes(15);

    public Mono<ComplianceResponse> checkCompliance(UUID driverId) {
        String cacheKey = buildComplianceCacheKey(driverId);

        return readComplianceCacheBestEffort(cacheKey)
                .switchIfEmpty(
                        verifyDriverStatusLocally(driverId)
                                .flatMap(response ->
                                        writeComplianceCacheBestEffort(cacheKey, response)
                                                .thenReturn(response)
                                )
                );
    }

    private Mono<ComplianceResponse> readComplianceCacheBestEffort(String cacheKey) {
        return redisTemplate.opsForValue()
                .get(cacheKey)
                .doOnSuccess(response -> {
                    if (response != null) {
                        log.debug("✅ Compliance trouvée en cache [{}]", cacheKey);
                    }
                })
                .doOnError(e -> log.warn(
                        "⚠️ Redis indisponible pendant lecture compliance [{}] : {}",
                        cacheKey,
                        e.getMessage()
                ))
                .onErrorResume(e -> Mono.empty());
    }

    private Mono<Void> writeComplianceCacheBestEffort(String cacheKey, ComplianceResponse response) {
        return redisTemplate.opsForValue()
                .set(cacheKey, response, CACHE_TTL)
                .doOnSuccess(saved ->
                        log.debug("✅ Compliance mise en cache [{}] : {}", cacheKey, saved)
                )
                .doOnError(e -> log.warn(
                        "⚠️ Impossible d'écrire la compliance en cache [{}] : {}",
                        cacheKey,
                        e.getMessage()
                ))
                .onErrorResume(e -> Mono.empty())
                .then();
    }

    private Mono<Void> deleteComplianceCacheBestEffort(String cacheKey) {
        return redisTemplate.opsForValue()
                .delete(cacheKey)
                .doOnSuccess(deleted ->
                        log.debug("✅ Cache compliance invalidé [{}] : {}", cacheKey, deleted)
                )
                .doOnError(e -> log.warn(
                        "⚠️ Impossible d'invalider le cache compliance [{}] : {}",
                        cacheKey,
                        e.getMessage()
                ))
                .onErrorResume(e -> Mono.empty())
                .then();
    }

    private String buildComplianceCacheKey(UUID driverId) {
        return "v2_compliance:" + driverId;
    }

    private Mono<ComplianceResponse> verifyDriverStatusLocally(UUID driverId) {
        return memberRepository.findAllByUserId(driverId)
                .flatMap(member ->
                        syndicatRepository.findById(member.syndicatId())
                                .map(syndicat -> buildPartialResponse(member, syndicat))
                )
                .collectList()
                .map(partialResults -> {
                    if (partialResults.isEmpty()) {
                        return buildBannedResponse(driverId, "NOT_A_MEMBER");
                    }

                    boolean isAuthorized = partialResults.stream()
                            .anyMatch(res -> "AUTHORIZED".equals(res.globalStatus()));

                    if (isAuthorized) {
                        return partialResults.stream()
                                .filter(res -> "AUTHORIZED".equals(res.globalStatus()))
                                .findFirst()
                                .orElse(buildBannedResponse(driverId, "NOT_A_MEMBER"));
                    } else {
                        return partialResults.get(0);
                    }
                })
                .defaultIfEmpty(buildBannedResponse(driverId, "NOT_A_MEMBER"));
    }

    private ComplianceResponse buildPartialResponse(SyndicatMember member, Syndicat syndicat) {
        boolean isMembershipActive = Boolean.TRUE.equals(member.isActive());
        boolean isSyndicatApproved = Boolean.TRUE.equals(syndicat.isApproved());

        String globalStatus = (isMembershipActive && isSyndicatApproved) ? "AUTHORIZED" : "RESTRICTED";

        List<String> restrictions = new ArrayList<>();
        if (!isMembershipActive) {
            restrictions.add("MEMBERSHIP_SUSPENDED");
        }
        if (!isSyndicatApproved) {
            restrictions.add("SYNDICATE_NOT_APPROVED");
        }

        return new ComplianceResponse(
                member.userId().toString(),
                Instant.now(),
                globalStatus,
                new ComplianceResponse.ComplianceDetails(
                        isSyndicatApproved,
                        isSyndicatApproved,
                        isMembershipActive,
                        true
                ),
                restrictions
        );
    }

    private ComplianceResponse buildResponseFromEntities(
            UUID driverId,
            SyndicatMember member,
            Syndicat syndicat,
            ComplianceDetails details
    ) {
        boolean isMembershipActive = Boolean.TRUE.equals(member.isActive());
        boolean isSyndicatApproved = Boolean.TRUE.equals(syndicat.isApproved());

        boolean isLicenseValid = details.licenseNumber() != null
                && !details.licenseNumber().isBlank()
                && Boolean.TRUE.equals(details.isVerified());

        // TODO: Ajouter table Assurance plus tard. Pour l'instant on considère OK si le dossier est vérifié.
        boolean isInsuranceValid = Boolean.TRUE.equals(details.isVerified());

        // Règle métier globale
        boolean isAuthorized = isMembershipActive && isSyndicatApproved && isLicenseValid;
        String globalStatus = isAuthorized ? "AUTHORIZED" : "RESTRICTED";

        List<String> restrictions = new ArrayList<>();
        if (!isMembershipActive) {
            restrictions.add("MEMBERSHIP_SUSPENDED");
        }
        if (!isSyndicatApproved) {
            restrictions.add("SYNDICATE_NOT_APPROVED");
        }
        if (!isLicenseValid) {
            restrictions.add("DOCUMENTS_NOT_VERIFIED");
        }

        return new ComplianceResponse(
                driverId.toString(),
                Instant.now(),
                globalStatus,
                new ComplianceResponse.ComplianceDetails(
                        isLicenseValid,
                        isInsuranceValid,
                        isMembershipActive,
                        true
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
        Mono<ExternalUserInfo> userMono = userGatewayPort.findById(driverId)
                .doOnError(e -> log.warn(
                        "⚠️ Échec récupération utilisateur depuis le portail externe pour {} : {}",
                        driverId,
                        e.getMessage()
                ))
                .onErrorResume(e -> Mono.empty())
                .switchIfEmpty(
                        userRepository.findById(driverId)
                                .map(localUser -> {
                                    String fullName = localUser.name() != null
                                            ? localUser.name()
                                            : "Utilisateur Inconnu";

                                    String[] parts = fullName.split(" ", 2);
                                    String firstName = parts.length > 0 ? parts[0] : "";
                                    String lastName = parts.length > 1 ? parts[1] : "";

                                    return new ExternalUserInfo(
                                            localUser.id(),
                                            firstName,
                                            lastName,
                                            localUser.email(),
                                            localUser.phoneNumber(),
                                            List.of(),
                                            List.of()
                                    );
                                })
                )
                .defaultIfEmpty(new ExternalUserInfo(
                        driverId,
                        "N/A",
                        "N/A",
                        "",
                        "",
                        List.of(),
                        List.of()
                ));

        Mono<Profile> profileMono = profileRepository.findByUserId(driverId)
                .defaultIfEmpty(new Profile(
                        UUID.randomUUID(),
                        driverId,
                        null,
                        "",
                        "",
                        null,
                        null,
                        null,
                        null,
                        Instant.now(),
                        Instant.now()
                ));

        Mono<ComplianceDetails> detailsMono = complianceDetailsRepository.findById(driverId)
                .defaultIfEmpty(ComplianceDetails.createEmpty(driverId));

        return Mono.zip(userMono, detailsMono, profileMono)
                .map(tuple -> {
                    ExternalUserInfo user = tuple.getT1();
                    ComplianceDetails details = tuple.getT2();
                    Profile profile = tuple.getT3();

                    String officialPhotoUrl = (details.profilePhotoUrl() != null && !details.profilePhotoUrl().isBlank())
                            ? details.profilePhotoUrl()
                            : (profile.profilImageUrl() != null ? profile.profilImageUrl() : "");

                    String finalFirstName = (profile.firstName() != null && !profile.firstName().isBlank())
                            ? profile.firstName()
                            : user.firstName();

                    String finalLastName = (profile.lastName() != null && !profile.lastName().isBlank())
                            ? profile.lastName()
                            : user.lastName();

                    return new OfficialProfileResponse(
                            user.id().toString(),
                            finalFirstName != null ? finalFirstName : "N/A",
                            finalLastName != null ? finalLastName : "N/A",
                            officialPhotoUrl,
                            details.cvUrl(),
                            details.cniNumber(),
                            details.cniRectoUrl(),
                            details.cniVersoUrl(),
                            details.licenseNumber(),
                            details.licenseRectoUrl(),
                            details.licenseVersoUrl(),
                            Boolean.TRUE.equals(details.isVerified())
                    );
                });
    }

    public Mono<BatchComplianceResponse> checkComplianceBatch(List<UUID> driverIds) {
        return Flux.fromIterable(driverIds)
                .flatMap(driverId ->
                        checkCompliance(driverId)
                                .map(response -> new java.util.AbstractMap.SimpleEntry<>(driverId, response))
                )
                .collect(Collectors.toMap(
                        java.util.Map.Entry::getKey,
                        java.util.Map.Entry::getValue
                ))
                .map(BatchComplianceResponse::new);
    }

    public Mono<Void> processFeedback(FeedbackRequest request) {
        log.info("Enregistrement Feedback local pour chauffeur {}", request.syndicatDriverId());

        try {
            UUID driverId = UUID.fromString(request.syndicatDriverId());

            return memberRepository.findByUserId(driverId)
                    .map(SyndicatMember::syndicatId)
                    .defaultIfEmpty(null)
                    .flatMap(syndicatId -> {
                        if (syndicatId == null) {
                            log.warn("Feedback reçu pour un chauffeur sans syndicat : {}", driverId);
                        }

                        Avis avis = new Avis(
                                UUID.randomUUID(),
                                null,
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
        String cacheKey = buildComplianceCacheKey(driverId);
        log.info("Invalidation manuelle du cache pour le chauffeur : {}", driverId);
        return deleteComplianceCacheBestEffort(cacheKey);
    }
}