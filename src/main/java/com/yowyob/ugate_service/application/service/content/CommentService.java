package com.yowyob.ugate_service.application.service.content;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.yowyob.ugate_service.domain.model.CommentModel;
import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.model.ImageModel;
import com.yowyob.ugate_service.domain.model.MediaInfo;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.CommentPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.dto.CommentResponseDto;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class CommentService {

  private final MediaPersistencePort mediaPersistencePort;
  private final CommentPersistencePort commentPersistencePort;
  private final UserGatewayPort userGatewayPort;

  public Mono<Void> createComment(UUID authorId, UUID publicationId, UUID parentId, String ImageUrl, String content) {
    return this.mediaPersistencePort.saveImage(ImageUrl, "alt text")
        .flatMap(imageModel -> {
          CommentModel comment = new CommentModel();
          comment.setAuthorId(authorId);
          comment.setPublicationId(publicationId);
          comment.setParentId(parentId);
          comment.setImageId(imageModel.getId());
          comment.setContent(content);
          comment.setCreatedAt(Instant.now());

          return this.commentPersistencePort.saveComment(comment);
        });
  }

  public Flux<CommentResponseDto> getCommentsByPublicationId(UUID publicationId) {
    return this.commentPersistencePort.findCommentsByPublicationId(publicationId).flatMap(
        commentModel -> {
          Mono<ExternalUserInfo> authorMono = userGatewayPort
              .findById(commentModel.getAuthorId());
          Mono<ImageModel> commentImage = this.mediaPersistencePort.getImageById(commentModel.getImageId());

          return Mono.zip(authorMono, commentImage).map(tuple -> {
            ExternalUserInfo author = tuple.getT1();
            ImageModel imageModel = tuple.getT2();

            CommentResponseDto commentResponseDto = new CommentResponseDto();
            commentResponseDto.setAuthorFullName(author.firstName() + author.lastName());
            commentResponseDto.setAuthorId(author.id());
            commentResponseDto.setContent(commentModel.getContent());
            commentResponseDto.setCreatedAt(commentModel.getCreatedAt());
            commentResponseDto.setId(commentModel.getId());
            commentResponseDto.setImageUrl(imageModel.getUrl());
            commentResponseDto.setParentId(commentModel.getParentId());
            commentResponseDto.setPublicationId(commentModel.getPublicationId());

            return commentResponseDto;

          });
        });
  }
}
