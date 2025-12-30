package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.model.CommentModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.CommentPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.CommentRepository;
import com.yowyob.ugate_service.infrastructure.mappers.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CommentPersistenceAdapter implements CommentPersistencePort {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public Mono<Void> saveComment(CommentModel commentModel) {
        return Mono.just(commentMapper.toEntity(commentModel))
                .flatMap(commentRepository::save)
                .then();
    }

    @Override
    public Flux<CommentModel> findCommentsByPublicationId(UUID publicationId) {
        return commentRepository.findByPublicationId(publicationId)
                .map(commentMapper::toModel);
    }
}
