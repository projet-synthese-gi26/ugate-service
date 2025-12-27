package com.yowyob.ugate_service.application.service.content;

import com.yowyob.ugate_service.domain.model.PublicationModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
public class PublicationService {

    private final PublicationPersistencePort publicationModelRepository;
    private final MediaPersistencePort mediaPersistencePort;

    public Mono<Void> createPublication(UUID authorId, UUID branchId, String content, String[] imagesUrls,
                                        String[] videoUrls, String[] filesUrls) {
        PublicationModel publication = new PublicationModel();
        publication.setAuthorId(authorId);
        publication.setContent(content);
        publication.setBranchI(branchId);
        publication.setNLikes(0L);
        publication.setCreatedAt(Instant.now());

        return publicationModelRepository.save(publication)
                .flatMap(savedPublication -> {
                    Mono<Void> imagesMono = Mono.empty();
                    if (imagesUrls != null) {
                        imagesMono = Flux.fromArray(imagesUrls)
                                .flatMap(imageUrl -> mediaPersistencePort.saveImageMedia(imageUrl, "altText", savedPublication.getId()))
                                .then();
                    }

                    Mono<Void> videosMono = Mono.empty();
                    if (videoUrls != null) {
                        videosMono = Flux.fromArray(videoUrls)
                                .flatMap(videoUrl -> mediaPersistencePort.saveVideoMedia(videoUrl, "title", savedPublication.getId()))
                                .then();
                    }

                    Mono<Void> filesMono = Mono.empty();
                    if (filesUrls != null) {
                        filesMono = Flux.fromArray(filesUrls)
                                .flatMap(fileUrl -> mediaPersistencePort.saveAudioMedia(fileUrl, "title", savedPublication.getId()))
                                .then();
                    }

                    return Mono.when(imagesMono, videosMono, filesMono);
                });
    }
}

