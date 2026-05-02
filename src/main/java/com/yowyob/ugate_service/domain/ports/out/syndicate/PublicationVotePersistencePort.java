package com.yowyob.ugate_service.domain.ports.out.syndicate;

import com.yowyob.ugate_service.domain.model.PublicationVoteModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PublicationVotePersistencePort {
    Mono<PublicationVoteModel> save(PublicationVoteModel model);
    Mono<PublicationVoteModel> findById(UUID id);
    Flux<PublicationVoteModel> findByBranchId(UUID branchId);
}