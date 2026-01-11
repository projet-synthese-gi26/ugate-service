package com.yowyob.ugate_service.application.service.content;

import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.model.MediaInfo;
import com.yowyob.ugate_service.domain.model.PublicationModel;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.dto.PublicationResponseDTO;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class PublicationService {

        private final PublicationPersistencePort publicationModelRepository;
        private final MediaPersistencePort mediaPersistencePort;
        private final UserGatewayPort userGatewayPort;

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
                                                                .flatMap(imageUrl -> mediaPersistencePort
                                                                                .saveImageMedia(imageUrl, "altText",
                                                                                                savedPublication.getId()))
                                                                .then();
                                        }

                                        Mono<Void> videosMono = Mono.empty();
                                        if (videoUrls != null) {
                                                videosMono = Flux.fromArray(videoUrls)
                                                                .flatMap(videoUrl -> mediaPersistencePort
                                                                                .saveVideoMedia(videoUrl, "title",
                                                                                                savedPublication.getId()))
                                                                .then();
                                        }

                                        Mono<Void> filesMono = Mono.empty();
                                        if (filesUrls != null) {
                                                filesMono = Flux.fromArray(filesUrls)
                                                                .flatMap(fileUrl -> mediaPersistencePort.saveAudioMedia(
                                                                                fileUrl, "title",
                                                                                savedPublication.getId()))
                                                                .then();
                                        }

                                        return Mono.when(imagesMono, videosMono, filesMono);
                                });
        }

        public Flux<PublicationResponseDTO> getSyndicatPublication(UUID branchId) {
                // Récupérer toutes les publications d'une branche
                // etenfin pour chaque publication récupérer les infos et retourner.
                return publicationModelRepository.findByBranchId(branchId)
                                .flatMap(publicationModel -> {
                                        Mono<ExternalUserInfo> authorMono = userGatewayPort
                                                        .findById(publicationModel.getAuthorId());
                                        Mono<List<MediaInfo>> mediaListMono = mediaPersistencePort
                                                        .getMediaByPublicationId(publicationModel.getId())
                                                        .map(mediaInfo -> {
                                                                MediaInfo fileDto = new MediaInfo();
                                                                fileDto.setUrl(mediaInfo.getUrl());
                                                                fileDto.setType(mediaInfo.getType());
                                                                return fileDto;
                                                        })
                                                        .collectList(); // Collect Flux to Mono<List>

                                        return Mono.zip(authorMono, mediaListMono)
                                                        .map(tuple -> {
                                                                ExternalUserInfo author = tuple.getT1();
                                                                List<MediaInfo> mediaList = tuple.getT2();

                                                                PublicationResponseDTO dto = new PublicationResponseDTO();
                                                                dto.setId(publicationModel.getId());
                                                                dto.setBranchId(publicationModel.getBranchI());
                                                                dto.setAuthorFullName(author.firstName() + " "
                                                                                + author.lastName());
                                                                dto.setContent(publicationModel.getContent());
                                                                dto.setNLikes(publicationModel.getNLikes());
                                                                // dto.setNComments(publicationModel.get); normalement
                                                                // pour le nombre de
                                                                // commentaire
                                                                dto.setCreatedAt(publicationModel.getCreatedAt());
                                                                dto.setFileUrlAndType(mediaList); // Set the collected
                                                                                                  // List
                                                                return dto;
                                                        });
                                });
        }

        public Mono<Void> incrementLikes(UUID publicationId) {
                return publicationModelRepository.incrementLikes(publicationId);
        }
}