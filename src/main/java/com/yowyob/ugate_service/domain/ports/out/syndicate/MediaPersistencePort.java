package com.yowyob.ugate_service.domain.ports.out.syndicate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import com.yowyob.ugate_service.domain.model.ImageModel;
import com.yowyob.ugate_service.domain.model.MediaInfo;

public interface MediaPersistencePort {
  Mono<Void> saveImageMedia(String imageUrl, String altText, UUID publicationId);

  Mono<Void> saveVideoMedia(String videoUrl, String title, UUID publicationId);

  Mono<Void> saveAudioMedia(String audioUrl, String title, UUID publicationId);

  Flux<MediaInfo> getMediaByPublicationId(UUID publicationId);

  Mono<ImageModel> saveImage(String audioUrl, String altext);

  Mono<ImageModel> getImageById(UUID imageId);

  Mono<Void> saveEventMedia(String imageUrl, String altText, UUID publicationId);
}
