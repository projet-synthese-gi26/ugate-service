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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class CommentService {

  private final MediaPersistencePort mediaPersistencePort;
  private final CommentPersistencePort commentPersistencePort;
  private final UserGatewayPort userGatewayPort;
  private final NotificationPort notificationPort;
  private final PublicationPersistencePort publicationPersistencePort;

  public Mono<Void> createComment(UUID authorId, UUID publicationId, UUID parentId, String ImageUrl, String content) {

    CommentModel comment = new CommentModel();
    comment.setAuthorId(authorId);
    comment.setPublicationId(publicationId);
    comment.setParentId(parentId);
    comment.setContent(content);
    comment.setCreatedAt(Instant.now());

    Mono<CommentModel> saveCommentMono;
    if (ImageUrl == null) {
      saveCommentMono = this.commentPersistencePort.saveComment(comment).thenReturn(comment);
    } else {
      saveCommentMono = this.mediaPersistencePort.saveImage(ImageUrl, "alt text")
          .flatMap(imageModel -> {
            comment.setImageId(imageModel.getId());
            return this.commentPersistencePort.saveComment(comment).thenReturn(comment);
          });
    }

    return saveCommentMono.flatMap(savedComment -> Mono.zip(
        publicationPersistencePort.findById(publicationId),
        userGatewayPort.findById(savedComment.getAuthorId()))
        .flatMap(tuple -> {
          var publication = tuple.getT1();
          var commenterInfo = tuple.getT2();

          return userGatewayPort.findById(publication.getAuthorId())
              .flatMap(publicationAuthorInfo -> notificationPort.sendPublicationCommentAlert(
                  publicationAuthorInfo.email(),
                  publication.getContent(), // Using content as title for now
                  commenterInfo.firstName()));
        }));
  }

  public Flux<CommentResponseDto> getCommentsByPublicationId(UUID publicationId) {
    return commentPersistencePort.findCommentsByPublicationId(publicationId)
        .flatMap(commentModel -> {

          Mono<ExternalUserInfo> authorMono = userGatewayPort.findById(commentModel.getAuthorId());

          Mono<Optional<ImageModel>> imageMono = Mono.justOrEmpty(commentModel.getImageId())
              .flatMap(mediaPersistencePort::getImageById)
              .map(Optional::of)
              .defaultIfEmpty(Optional.empty());

          return authorMono.zipWith(imageMono)
              .map(tuple -> {
                ExternalUserInfo author = tuple.getT1();
                Optional<ImageModel> imageOpt = tuple.getT2();

                CommentResponseDto dto = new CommentResponseDto();
                dto.setAuthorFullName(author.firstName() + " " + author.lastName());
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

}
