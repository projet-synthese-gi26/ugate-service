package com.yowyob.ugate_service.domain.ports.out.syndicate;

import java.util.UUID;

import com.yowyob.ugate_service.domain.model.CommentModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentPersistencePort {
  Mono<Void> saveComment(CommentModel commentModel);

  Flux<CommentModel> findCommentsByPublicationId(UUID publicationId);
}
