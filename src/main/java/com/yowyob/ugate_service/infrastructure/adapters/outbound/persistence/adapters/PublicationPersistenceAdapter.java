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
                publicationModel.getCreatedAt());

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

    @Override
    public Mono<Void> incrementLikes(UUID publicationId) {
        return publicationRepository.findById(publicationId)
                .flatMap(publication -> {
                    Publication updatedPublication = new Publication(
                            publication.id(),
                            publication.branchId(),
                            publication.authorId(),
                            publication.content(),
                            publication.nLikes() + 1,
                            publication.createdAt());
                    return publicationRepository.save(updatedPublication);
                })
                .then();
    }

    @Override
    public Flux<PublicationModel> findAllPaginated(int page, int size) {
        // The FeedService will handle the actual pagination after merging and sorting.
        // This method should return all publications for now.
        return publicationRepository.findAll()
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
