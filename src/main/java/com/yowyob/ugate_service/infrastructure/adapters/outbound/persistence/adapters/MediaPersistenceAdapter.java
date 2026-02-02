package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.model.ImageModel;
import com.yowyob.ugate_service.domain.model.MediaInfo;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.EventImages;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Image;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.PublicationImage;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.EventImagesRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ImageRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationImageRepository;
import com.yowyob.ugate_service.infrastructure.mappers.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MediaPersistenceAdapter implements MediaPersistencePort {

    private final ImageRepository imageRepository;
    private final PublicationImageRepository publicationImageRepository;
    private final EventImagesRepository eventImagesRepository;
    private final ImageMapper imageMapper;

    @Override
    public Mono<Void> saveImageMedia(String imageUrl, String altText, UUID publicationId) {
        Image image = new Image(null, imageUrl, altText, Instant.now());

        return imageRepository.save(image)
                .flatMap(savedImage -> {
                    PublicationImage publicationImage = new PublicationImage(publicationId, savedImage.id(),
                            Instant.now(), Instant.now());
                    return publicationImageRepository.save(publicationImage);
                }).then();
    }

    @Override
    public Mono<Void> saveEventMedia(String imageUrl, String altText, UUID eventId) {
        Image image = new Image(null, imageUrl, altText, Instant.now());

        return imageRepository.save(image)
                .flatMap(savedImage -> {
                    EventImages eventImage = new EventImages(eventId, savedImage.id(),
                            Instant.now(), Instant.now());
                    return eventImagesRepository.save(eventImage);
                }).then();
    }

    @Override
    public Mono<Void> saveVideoMedia(String videoUrl, String title, UUID publicationId) {
        Image image = new Image(null, videoUrl, title, Instant.now());

        return imageRepository.save(image)
                .flatMap(savedImage -> {
                    PublicationImage publicationImage = new PublicationImage(publicationId, savedImage.id(),
                            Instant.now(), Instant.now());
                    return publicationImageRepository.save(publicationImage);
                }).then();
    }

    @Override
    public Mono<Void> saveAudioMedia(String audioUrl, String title, UUID publicationId) {
        Image image = new Image(null, audioUrl, title, Instant.now());

        return imageRepository.save(image)
                .flatMap(savedImage -> {
                    PublicationImage publicationImage = new PublicationImage(publicationId, savedImage.id(),
                            Instant.now(), Instant.now());
                    return publicationImageRepository.save(publicationImage);
                }).then();
    }

    @Override
    public Flux<MediaInfo> getMediaByPublicationId(UUID publicationId) {
        return imageRepository.findByPublicationId(publicationId)
                .map(image -> new MediaInfo(
                        image.url(),
                        image.altText().toUpperCase()));
    }

    @Override
    public Mono<ImageModel> saveImage(String imageUrl, String altText) {
        ImageModel imageModel = new ImageModel();
        imageModel.setUrl(imageUrl);
        imageModel.setAltText(altText);
        imageModel.setUploadedAt(Instant.now());
        Image image = imageMapper.toEntity(imageModel);
        return imageRepository.save(image).map(imageMapper::toModel);
    }

    @Override
    public Mono<ImageModel> getImageById(UUID imageId) {
        if (imageId == null) {
            return Mono.empty();
        }
        return imageRepository.findById(imageId)
                .map(imageMapper::toModel);
    }

    @Override
    public Flux<ImageModel> getImagesByEventId(UUID eventId) {
        return eventImagesRepository.findByEventId(eventId)
                .flatMap(eventImage -> imageRepository.findById(eventImage.imageId()))
                .map(imageMapper::toModel);
    }
}