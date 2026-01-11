package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.model.PublicationVoteModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationVotePersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.PublicationVote;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationVoteRepository;
import com.yowyob.ugate_service.infrastructure.mappers.PublicationVoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PublicationVotePersistenceAdapter implements PublicationVotePersistencePort {

    private final PublicationVoteRepository publicationVoteRepository;
    private final PublicationVoteMapper publicationVoteMapper;

    @Override
    public Mono<PublicationVoteModel> save(PublicationVoteModel model) {
        PublicationVote entity = publicationVoteMapper.toEntity(model);
        return publicationVoteRepository.save(entity)
                .map(publicationVoteMapper::toModel);
    }

    @Override
    public Mono<PublicationVoteModel> findById(UUID id) {
        return publicationVoteRepository.findById(id)
                .map(publicationVoteMapper::toModel);
    }
}
