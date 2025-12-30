package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.model.PublicationModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Publication;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PublicationPersistenceAdapter implements PublicationPersistencePort {

    private final PublicationRepository publicationRepository;

    @Override
    public Mono<PublicationModel> save(PublicationModel publicationModel) {
        Publication publication = new Publication(
                publicationModel.getBranchI(),
                publicationModel.getAuthorId(),
                publicationModel.getContent(),
                publicationModel.getNLikes(),
                publicationModel.getCreatedAt()
        );

        return publicationRepository.save(publication)
                .map(savedPublication -> {
                    publicationModel.setId(savedPublication.id());
                    return publicationModel;
                });
    }

    @Override
    public Flux<PublicationModel> findByBranchId(UUID branchId) {
        return publicationRepository.findByBranchId(branchId)
                .map(publication -> {
                    PublicationModel model = new PublicationModel();
                    model.setId(publication.id());
                    model.setBranchI(publication.branchId());
                    model.setAuthorId(publication.authorId());
                    model.setContent(publication.content());
                    model.setNLikes(publication.nLikes());
                    model.setCreatedAt(publication.createdAt());
                    return model;
                });
    }
}


