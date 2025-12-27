package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Image;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.PublicationImage;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ImageRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MediaPersistenceAdapter implements MediaPersistencePort {

    private final ImageRepository imageRepository;
    private final PublicationImageRepository publicationImageRepository;

    @Override
    public Mono<Void> saveImageMedia(String imageUrl, String altText, UUID publicationId) {
        Image image = new Image(imageUrl, altText, Instant.now());

        return imageRepository.save(image)
                .flatMap(savedImage -> {
                    PublicationImage publicationImage = new PublicationImage(publicationId, savedImage.id(), Instant.now(), Instant.now());
                    return publicationImageRepository.save(publicationImage);
                }).then();
    }

    @Override
    public Mono<Void> saveVideoMedia(String videoUrl, String title, UUID publicationId) {
        // TODO: Implement video saving logic
        return Mono.empty();
    }

    @Override
    public Mono<Void> saveAudioMedia(String audioUrl, String title, UUID publicationId) {
        // TODO: Implement audio saving logic
        return Mono.empty();
    }
}


