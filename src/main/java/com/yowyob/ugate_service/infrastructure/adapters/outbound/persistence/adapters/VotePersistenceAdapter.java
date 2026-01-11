package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.model.VoteModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.VotePersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.VoteRepository;
import com.yowyob.ugate_service.infrastructure.mappers.VoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VotePersistenceAdapter implements VotePersistencePort {

    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;

    @Override
    public Mono<Void> save(VoteModel model) {
        return voteRepository.save(voteMapper.toEntity(model)).then();
    }

    @Override
    public Flux<VoteModel> findByPublicationVoteId(UUID publicationVoteId) {
        return voteRepository.findByPublicationVoteId(publicationVoteId)
                .map(voteMapper::toModel);
    }
}