package com.yowyob.ugate_service.application.service.content;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import com.yowyob.ugate_service.domain.model.CommentModel;
import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.model.ImageModel;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.CommentPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.dto.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
public class CommentService {

    private static final Duration NOTIFICATION_TIMEOUT = Duration.ofSeconds(3);

    private final MediaPersistencePort mediaPersistencePort;
    private final CommentPersistencePort commentPersistencePort;
    private final UserGatewayPort userGatewayPort;
    private final NotificationPort notificationPort;
    private final PublicationPersistencePort publicationPersistencePort;

    public Mono<Void> createComment(UUID authorId,
                                    UUID publicationId,
                                    UUID parentId,
                                    String imageUrl,
                                    String content) {

        CommentModel comment = new CommentModel();
        comment.setAuthorId(authorId);
        comment.setPublicationId(publicationId);
        comment.setParentId(parentId);
        comment.setContent(content);
        comment.setCreatedAt(Instant.now());

        Mono<CommentModel> saveCommentMono;

        if (imageUrl == null || imageUrl.isBlank()) {
            saveCommentMono = commentPersistencePort.saveComment(comment)
                    .thenReturn(comment);
        } else {
            saveCommentMono = mediaPersistencePort.saveImage(imageUrl, "alt text")
                    .flatMap(imageModel -> {
                        comment.setImageId(imageModel.getId());
                        return commentPersistencePort.saveComment(comment).thenReturn(comment);
                    });
        }

        return saveCommentMono
                .flatMap(savedComment ->
                        notifyPublicationAuthorBestEffort(savedComment)
                                .thenReturn(savedComment)
                )
                .then();
    }

    public Flux<CommentResponseDto> getCommentsByPublicationId(UUID publicationId) {
        return commentPersistencePort.findCommentsByPublicationId(publicationId)
                .flatMap(commentModel -> {
                    Mono<ExternalUserInfo> authorMono = loadUserForDisplay(commentModel.getAuthorId());

                    Mono<Optional<ImageModel>> imageMono = Mono.justOrEmpty(commentModel.getImageId())
                            .flatMap(mediaPersistencePort::getImageById)
                            .map(Optional::of)
                            .doOnError(e -> log.warn(
                                    "⚠️ Impossible de charger l'image du commentaire {} : {}",
                                    commentModel.getId(),
                                    e.getMessage()
                            ))
                            .onErrorResume(e -> Mono.empty())
                            .defaultIfEmpty(Optional.empty());

                    return authorMono.zipWith(imageMono)
                            .map(tuple -> {
                                ExternalUserInfo author = tuple.getT1();
                                Optional<ImageModel> imageOpt = tuple.getT2();

                                CommentResponseDto dto = new CommentResponseDto();
                                dto.setAuthorFullName(buildFullName(author.firstName(), author.lastName()));
                                dto.setAuthorId(author.id());
                                dto.setContent(commentModel.getContent());
                                dto.setCreatedAt(commentModel.getCreatedAt());
                                dto.setId(commentModel.getId());
                                dto.setParentId(commentModel.getParentId());
                                dto.setPublicationId(commentModel.getPublicationId());

                                imageOpt.ifPresent(img -> dto.setImageUrl(img.getUrl()));

                                return dto;
                            });
                });
    }

    private Mono<Void> notifyPublicationAuthorBestEffort(CommentModel savedComment) {
        return publicationPersistencePort.findById(savedComment.getPublicationId())
                .flatMap(publication ->
                        Mono.zip(
                                loadUserBestEffort(savedComment.getAuthorId()),
                                loadUserBestEffort(publication.getAuthorId())
                        ).flatMap(tuple -> {
                            ExternalUserInfo commenterInfo = tuple.getT1();
                            ExternalUserInfo publicationAuthorInfo = tuple.getT2();

                            if (publicationAuthorInfo.email() == null || publicationAuthorInfo.email().isBlank()) {
                                log.warn(
                                        "⚠️ Notification commentaire ignorée : email auteur publication absent [publicationId={}, authorId={}]",
                                        publication.getId(),
                                        publication.getAuthorId()
                                );
                                return Mono.empty();
                            }

                            String commenterFirstName = safeDisplayName(commenterInfo.firstName(), "Un utilisateur");

                            return notificationPort.sendPublicationCommentAlert(
                                    publicationAuthorInfo.email(),
                                    publication.getContent(),
                                    commenterFirstName
                            );
                        })
                )
                .timeout(NOTIFICATION_TIMEOUT)
                .doOnSuccess(v -> log.debug(
                        "✅ Notification commentaire traitée pour publication {}",
                        savedComment.getPublicationId()
                ))
                .doOnError(e -> log.warn(
                        "⚠️ Échec non bloquant notification commentaire [publicationId={}] : {}",
                        savedComment.getPublicationId(),
                        e.getMessage()
                ))
                .onErrorResume(e -> Mono.empty())
                .then();
    }

    private Mono<ExternalUserInfo> loadUserBestEffort(UUID userId) {
        return userGatewayPort.findById(userId)
                .doOnError(e -> log.warn(
                        "⚠️ Impossible de récupérer l'utilisateur {} depuis le gateway : {}",
                        userId,
                        e.getMessage()
                ))
                .onErrorResume(e -> Mono.empty());
    }

    private Mono<ExternalUserInfo> loadUserForDisplay(UUID userId) {
        return loadUserBestEffort(userId)
                .defaultIfEmpty(new ExternalUserInfo(
                        userId,
                        "Utilisateur",
                        "inconnu",
                        "",
                        "",
                        List.of(),
                        List.of()
                ));
    }

    private String buildFullName(String firstName, String lastName) {
        String safeFirstName = safeDisplayName(firstName, "Utilisateur");
        String safeLastName = safeDisplayName(lastName, "inconnu");
        return (safeFirstName + " " + safeLastName).trim();
    }

    private String safeDisplayName(String value, String fallback) {
        return (value == null || value.isBlank()) ? fallback : value;
    }
}

