package com.yowyob.ugate_service.application.service.content;

import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.model.MediaInfo;
import com.yowyob.ugate_service.domain.model.PublicationModel;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.BranchPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.dto.PublicationResponseDTO;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Branch;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Syndicat;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.Loggers;
import reactor.util.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class PublicationService {

    private static final Logger log = Loggers.getLogger(PublicationService.class);
    private static final Duration NOTIFICATION_TIMEOUT = Duration.ofSeconds(3);

    private final PublicationPersistencePort publicationModelRepository;
    private final MediaPersistencePort mediaPersistencePort;
    private final UserGatewayPort userGatewayPort;
    private final NotificationPort notificationPort;
    private final BranchPersistencePort branchPersistencePort;
    private final SyndicatRepository syndicatRepository;

    /**
     * Flux principal :
     * 1) save publication (métier)
     * 2) save medias (métier)
     * 3) notify admin (BEST-EFFORT : ne bloque jamais)
     */
    public Mono<Void> createPublication(UUID authorId,
                                        UUID branchId,
                                        String content,
                                        String[] imagesUrls,
                                        String[] videoUrls,
                                        String[] filesUrls) {

        PublicationModel publication = new PublicationModel();
        publication.setAuthorId(authorId);
        publication.setContent(content);
        publication.setBranchI(branchId);
        publication.setNLikes(0L);
        publication.setCreatedAt(Instant.now());

        return publicationModelRepository.save(publication)
                .flatMap(savedPublication -> {
                    UUID publicationId = savedPublication.getId();

                    // 1) Médias = partie métier
                    Mono<Void> mediaMono = savePublicationMedias(publicationId, imagesUrls, videoUrls, filesUrls);

                    // 2) Notification admin = BEST-EFFORT (ne bloque jamais)
                    Mono<Void> notificationMono = notifyAdminBestEffort(savedPublication, branchId);

                    return Mono.when(mediaMono, notificationMono).then();
                })
                .doOnSuccess(v -> log.info("Publication créée - branchId={}, authorId={}", branchId, authorId))
                .doOnError(e -> log.error("createPublication FAILED - branchId={}, authorId={}", branchId, authorId, e));
    }

    private Mono<Void> savePublicationMedias(UUID publicationId,
                                             String[] imagesUrls,
                                             String[] videoUrls,
                                             String[] filesUrls) {

        Mono<Void> imagesMono = Mono.empty();
        if (imagesUrls != null && imagesUrls.length > 0) {
            imagesMono = Flux.fromArray(imagesUrls)
                    .filter(url -> url != null && !url.isBlank())
                    .flatMap(url -> mediaPersistencePort.saveImageMedia(url, "image", publicationId))
                    .then();
        }

        Mono<Void> videosMono = Mono.empty();
        if (videoUrls != null && videoUrls.length > 0) {
            videosMono = Flux.fromArray(videoUrls)
                    .filter(url -> url != null && !url.isBlank())
                    .flatMap(url -> mediaPersistencePort.saveVideoMedia(url, "video", publicationId))
                    .then();
        }

        Mono<Void> filesMono = Mono.empty();
        if (filesUrls != null && filesUrls.length > 0) {
            filesMono = Flux.fromArray(filesUrls)
                    .filter(url -> url != null && !url.isBlank())
                    .flatMap(url -> mediaPersistencePort.saveAudioMedia(url, "audio", publicationId))
                    .then();
        }

        return Mono.when(imagesMono, videosMono, filesMono);
    }

    /**
     * Notification BEST-EFFORT :
     * - si branch/syndicat/admin/author info échoue => ignore
     * - timeout court
     * - ne bloque jamais createPublication()
     */
    private Mono<Void> notifyAdminBestEffort(PublicationModel savedPublication, UUID branchId) {
        UUID publicationId = savedPublication.getId();

        Mono<Branch> branchMono = branchPersistencePort.findById(branchId)
                .doOnError(e -> log.warn("Cannot load branch {} for admin notification: {}", branchId, e.getMessage()))
                .onErrorResume(e -> Mono.empty());

        Mono<ExternalUserInfo> authorMono = userGatewayPort.findById(savedPublication.getAuthorId())
                .doOnError(e -> log.warn("Cannot load author {} for admin notification: {}", savedPublication.getAuthorId(), e.getMessage()))
                .onErrorResume(e -> Mono.empty());

        return Mono.zip(branchMono, authorMono)
                .flatMap(tuple -> {
                    Branch branch = tuple.getT1();
                    ExternalUserInfo authorInfo = tuple.getT2();

                    return syndicatRepository.findById(branch.syndicatId())
                            .doOnError(e -> log.warn("Cannot load syndicat {} for admin notification: {}", branch.syndicatId(), e.getMessage()))
                            .onErrorResume(e -> Mono.empty())
                            .flatMap(syndicat -> notifySyndicatCreatorBestEffort(syndicat, savedPublication, authorInfo));
                })
                .timeout(NOTIFICATION_TIMEOUT)
                .doOnError(e -> log.warn("Admin notification failed (ignored) publicationId={} : {}", publicationId, e.getMessage()))
                .onErrorResume(e -> Mono.empty())
                .then();
    }

    private Mono<Void> notifySyndicatCreatorBestEffort(Syndicat syndicat,
                                                       PublicationModel savedPublication,
                                                       ExternalUserInfo authorInfo) {

        UUID adminId = syndicat.creatorId();

        return userGatewayPort.findById(adminId)
                .doOnError(e -> log.warn("Cannot load admin {} for admin notification: {}", adminId, e.getMessage()))
                .onErrorResume(e -> Mono.empty())
                .flatMap(adminInfo -> {
                    String adminEmail = adminInfo.email();
                    if (adminEmail == null || adminEmail.isBlank()) {
                        log.warn("Admin notification skipped: admin email missing. adminId={}", adminId);
                        return Mono.empty();
                    }

                    String authorName = buildFullName(
                            authorInfo != null ? authorInfo.firstName() : "",
                            authorInfo != null ? authorInfo.lastName() : ""
                    );

                    String title = safeText(savedPublication.getContent(), "Nouvelle publication");

                    return notificationPort.sendAdminAlertWhenNewPublication(
                            List.of(adminEmail),
                            title,
                            authorName
                    );
                })
                .doOnError(e -> log.warn("sendAdminAlertWhenNewPublication failed (ignored): {}", e.getMessage()))
                .onErrorResume(e -> Mono.empty())
                .then();
    }

    public Flux<PublicationResponseDTO> getSyndicatPublication(UUID branchId) {
        return publicationModelRepository.findByBranchId(branchId)
                .flatMap(publicationModel -> {

                    Mono<ExternalUserInfo> authorMono = userGatewayPort.findById(publicationModel.getAuthorId())
                            .doOnError(e -> log.warn("userGateway findById failed authorId={} : {}", publicationModel.getAuthorId(), e.getMessage()))
                            .onErrorResume(e -> Mono.empty())
                            .defaultIfEmpty(defaultUser(publicationModel.getAuthorId()));

                    Mono<List<MediaInfo>> mediaListMono = mediaPersistencePort.getMediaByPublicationId(publicationModel.getId())
                            .map(mi -> {
                                MediaInfo dto = new MediaInfo();
                                dto.setUrl(mi.getUrl());
                                dto.setType(mi.getType());
                                return dto;
                            })
                            .collectList()
                            .doOnError(e -> log.warn("getMediaByPublicationId failed publicationId={} : {}", publicationModel.getId(), e.getMessage()))
                            .onErrorResume(e -> Mono.just(List.of()));

                    return Mono.zip(authorMono, mediaListMono)
                            .map(tuple -> {
                                ExternalUserInfo author = tuple.getT1();
                                List<MediaInfo> mediaList = tuple.getT2();

                                PublicationResponseDTO dto = new PublicationResponseDTO();
                                dto.setId(publicationModel.getId());
                                dto.setBranchId(publicationModel.getBranchI());
                                dto.setAuthorFullName(buildFullName(author.firstName(), author.lastName()));
                                dto.setContent(publicationModel.getContent());
                                dto.setNLikes(publicationModel.getNLikes());
                                dto.setCreatedAt(publicationModel.getCreatedAt());
                                dto.setFileUrlAndType(mediaList);
                                return dto;
                            });
                });
    }

    public Mono<Void> incrementLikes(UUID publicationId) {
        return publicationModelRepository.incrementLikes(publicationId)
                .switchIfEmpty(Mono.empty());
    }

    // ---------- Helpers ----------

    private ExternalUserInfo defaultUser(UUID userId) {
        return new ExternalUserInfo(
                userId,
                "Utilisateur",
                "inconnu",
                "",
                "",
                List.of(),
                List.of()
        );
    }

    private String buildFullName(String firstName, String lastName) {
        String f = safeText(firstName, "");
        String l = safeText(lastName, "");
        String full = (f + " " + l).trim();
        return full.isBlank() ? "Utilisateur inconnu" : full;
    }

    private String safeText(String value, String fallback) {
        return (value == null || value.isBlank()) ? fallback : value;
    }
}