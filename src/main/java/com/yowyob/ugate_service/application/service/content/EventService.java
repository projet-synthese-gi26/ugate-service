package com.yowyob.ugate_service.application.service.content;

import com.yowyob.ugate_service.domain.ports.in.content.CreateEventUseCase;
import lombok.AllArgsConstructor;
import com.yowyob.ugate_service.domain.model.EventModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.EventPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
public class EventService implements CreateEventUseCase {

    private final EventPersistencePort eventPersistencePort;
    private final MediaPersistencePort mediaPersistencePort;

    @Override
    public Mono<Void> createEvent(UUID creatorId, UUID branchId, String title, String description, LocalDate eventDate,
            String location, LocalTime startTime, LocalTime endTime, String[] imagesUrls, String[] videoUrls,
            String[] filesUrls) {
        EventModel event = new EventModel();
        event.setCreatorId(creatorId);
        event.setBranchId(branchId);
        event.setTitle(title);
        event.setDescription(description);
        event.setDate(eventDate);
        event.setLocation(location);
        event.setCreatedAt(Instant.now());
        event.setUpdatedAt(Instant.now());
        event.setStartTime(startTime);
        event.setEndTime(endTime);

        return eventPersistencePort.save(event)
                .flatMap(savedEvent -> {
                    Mono<Void> imagesMono = Mono.empty();
                    if (imagesUrls != null) {
                        imagesMono = Flux.fromArray(imagesUrls)
                                .flatMap(imageUrl -> mediaPersistencePort.saveImageMedia(imageUrl, "altText",
                                        savedEvent.getId()))
                                .then();
                    }

                    Mono<Void> videosMono = Mono.empty();
                    if (videoUrls != null) {
                        videosMono = Flux.fromArray(videoUrls)
                                .flatMap(videoUrl -> mediaPersistencePort.saveVideoMedia(videoUrl, "title",
                                        savedEvent.getId()))
                                .then();
                    }

                    Mono<Void> filesMono = Mono.empty();
                    if (filesUrls != null) {
                        filesMono = Flux.fromArray(filesUrls)
                                .flatMap(fileUrl -> mediaPersistencePort.saveAudioMedia(fileUrl, "title",
                                        savedEvent.getId()))
                                .then();
                    }

                    return Mono.when(imagesMono, videosMono, filesMono);
                });
    }
}
