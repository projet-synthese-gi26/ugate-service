package com.yowyob.ugate_service.domain.ports.out.syndicate;

import com.yowyob.ugate_service.domain.model.VoteModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface VotePersistencePort {
    Mono<Void> save(VoteModel model);
    Flux<VoteModel> findByPublicationVoteId(UUID publicationVoteId);
}
