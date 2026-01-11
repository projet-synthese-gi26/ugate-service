package com.yowyob.ugate_service.application.service.content;

import com.yowyob.ugate_service.domain.model.UserEventModel;
import com.yowyob.ugate_service.domain.ports.in.content.CreateEventUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.GetEventParticipantsUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.GetEventsByBranchUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.JoinEventUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.LeaveEventUseCase;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserEventPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.EventResponseDTO;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ParticipantDTO;
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
public class EventService implements CreateEventUseCase, JoinEventUseCase, GetEventsByBranchUseCase, GetEventParticipantsUseCase, LeaveEventUseCase {

    private final EventPersistencePort eventPersistencePort;
    private final MediaPersistencePort mediaPersistencePort;
    private final UserEventPersistencePort userEventPersistencePort;
    private final UserGatewayPort userGatewayPort;

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

    @Override
    public Mono<Void> joinEvent(UUID userId, UUID eventId) {
        // Here you might add logic to check if the event and user exist before creating the link
        UserEventModel userEventModel = new UserEventModel();
        userEventModel.setUserId(userId);
        userEventModel.setEventId(eventId);
        userEventModel.setTimestamp(Instant.now());
        return userEventPersistencePort.save(userEventModel);
    }

    @Override
    public Flux<EventResponseDTO> getEventsByBranch(UUID branchId) {
        return eventPersistencePort.findByBranchId(branchId)
                .flatMap(eventModel ->
                    userEventPersistencePort.countByEventId(eventModel.getId())
                        .map(count -> new EventResponseDTO(
                            eventModel.getId(),
                            eventModel.getCreatorId(),
                            eventModel.getBranchId(),
                            eventModel.getTitle(),
                            eventModel.getDescription(),
                            eventModel.getLocation(),
                            eventModel.getDate(),
                            eventModel.getStartTime(),
                            eventModel.getEndTime(),
                            eventModel.getCreatedAt(),
                            eventModel.getUpdatedAt(),
                            count
                        ))
                );
    }

    @Override
    public Flux<ParticipantDTO> getParticipants(UUID eventId) {
        return userEventPersistencePort.findByEventId(eventId)
                .flatMap(userEvent -> userGatewayPort.findById(userEvent.getUserId()))
                .map(userInfo -> new ParticipantDTO(userInfo.id(), userInfo.firstName() + " " + userInfo.lastName()));
    }

    @Override
    public Mono<Void> leaveEvent(UUID userId, UUID eventId) {
        return userEventPersistencePort.delete(userId, eventId);
    }
}