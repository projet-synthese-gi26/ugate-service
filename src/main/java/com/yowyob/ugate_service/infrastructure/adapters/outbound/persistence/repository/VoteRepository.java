package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Vote;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface VoteRepository extends R2dbcRepository<Vote, UUID> {
    Flux<Vote> findByPublicationVoteId(UUID publicationVoteId);
}
