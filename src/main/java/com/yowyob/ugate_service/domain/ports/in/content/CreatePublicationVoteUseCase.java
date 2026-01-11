package com.yowyob.ugate_service.domain.ports.in.content;

import com.yowyob.ugate_service.domain.model.PublicationVoteModel;
import reactor.core.publisher.Mono;

public interface CreatePublicationVoteUseCase {
    Mono<Void> createPublicationVote(PublicationVoteModel publicationVoteModel);
}
