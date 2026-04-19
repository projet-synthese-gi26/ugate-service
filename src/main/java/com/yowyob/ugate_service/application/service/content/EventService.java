package com.yowyob.ugate_service.application.service.content;
import com.yowyob.ugate_service.domain.model.EventModel;
import com.yowyob.ugate_service.domain.model.ImageModel;
import com.yowyob.ugate_service.domain.model.UserEventModel;
import com.yowyob.ugate_service.domain.model.UserModel;
import com.yowyob.ugate_service.domain.ports.in.content.CreateEventUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.GetEventParticipantsUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.GetEventsByBranchUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.JoinEventUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.LeaveEventUseCase;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.EventPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserEventPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.EventResponseDTO;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ParticipantDTO;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.Loggers;
import reactor.util.Logger;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class EventService implements CreateEventUseCase,
        JoinEventUseCase,
        GetEventsByBranchUseCase,
        GetEventParticipantsUseCase,
        LeaveEventUseCase {

    private static final Logger log = Loggers.getLogger(EventService.class);
    private static final Duration NOTIFICATION_TIMEOUT = Duration.ofSeconds(3);

    private final EventPersistencePort eventPersistencePort;
    private final MediaPersistencePort mediaPersistencePort;
    private final UserEventPersistencePort userEventPersistencePort;
    private final UserGatewayPort userGatewayPort;
    private final NotificationPort notificationPort;
    private final UserPersistencePort userPersistencePort;

    @Override
    public Mono<Void> createEvent(UUID creatorId,
                                  UUID branchId,
                                  String title,
                                  String description,
                                  LocalDate eventDate,
                                  String location,
                                  LocalTime startTime,
                                  LocalTime endTime,
                                  String[] imagesUrls,
                                  String[] videoUrls,
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
                    UUID eventId = savedEvent.getId();

                    // 1) Médias = partie "métier" (on ne masque pas les erreurs par défaut)
                    Mono<Void> mediaMono = saveEventMedias(eventId, imagesUrls, videoUrls, filesUrls);

                    // 2) Notification = BEST-EFFORT (ne bloque jamais)
                    Mono<Void> notificationMono = notifyBranchUsersBestEffort(branchId, title);

                    // Exécuter en parallèle. La notification absorbe ses erreurs, donc ne fera jamais échouer l'ensemble.
                    return Mono.when(mediaMono, notificationMono).then();
                })
                .doOnSuccess(v -> log.info("✅ createEvent OK - branchId={}, title={}", branchId, title))
                .doOnError(e -> log.error("❌ createEvent FAILED - branchId={}, title={}", branchId, title, e));
    }

    private Mono<Void> saveEventMedias(UUID eventId, String[] imagesUrls, String[] videoUrls, String[] filesUrls) {

        Mono<Void> imagesMono = Mono.empty();
        if (imagesUrls != null && imagesUrls.length > 0) {
            imagesMono = Flux.fromArray(imagesUrls)
                    .filter(url -> url != null && !url.isBlank())
                    .flatMap(url -> mediaPersistencePort.saveEventMedia(url, "altText", eventId))
                    .then();
        }

        Mono<Void> videosMono = Mono.empty();
        if (videoUrls != null && videoUrls.length > 0) {
            videosMono = Flux.fromArray(videoUrls)
                    .filter(url -> url != null && !url.isBlank())
                    .flatMap(url -> mediaPersistencePort.saveVideoMedia(url, "title", eventId))
                    .then();
        }

        Mono<Void> filesMono = Mono.empty();
        if (filesUrls != null && filesUrls.length > 0) {
            filesMono = Flux.fromArray(filesUrls)
                    .filter(url -> url != null && !url.isBlank())
                    .flatMap(url -> mediaPersistencePort.saveAudioMedia(url, "title", eventId))
                    .then();
        }

        return Mono.when(imagesMono, videosMono, filesMono);
    }

    private Mono<Void> notifyBranchUsersBestEffort(UUID branchId, String eventTitle) {
        return userPersistencePort.findUsersByBranchId(branchId)
                .collectList()
                .flatMap(users -> {
                    List<String> emails = users.stream()
                            .map(UserModel::getEmail)
                            .filter(email -> email != null && !email.isBlank())
                            .toList();

                    if (emails.isEmpty()) {
                        log.debug("ℹ️ Aucun email à notifier pour l'event (branchId={})", branchId);
                        return Mono.empty();
                    }

                    return notificationPort.sendNewEventAlert(emails, eventTitle);
                })
                .timeout(NOTIFICATION_TIMEOUT)
                .doOnSuccess(v -> log.debug("✅ Notification event envoyée (best-effort) branchId={}", branchId))
                .doOnError(e -> log.warn("⚠️ Notification event échouée (ignorée) branchId={} : {}", branchId, e.getMessage()))
                .onErrorResume(e -> Mono.empty())
                .then();
    }

    @Override
    public Mono<Void> joinEvent(UUID userId, UUID eventId) {
        UserEventModel userEventModel = new UserEventModel();
        userEventModel.setUserId(userId);
        userEventModel.setEventId(eventId);
        userEventModel.setTimestamp(Instant.now());
        return userEventPersistencePort.save(userEventModel);
    }

    @Override
    public Flux<EventResponseDTO> getEventsByBranch(UUID branchId) {
        return eventPersistencePort.findByBranchId(branchId)
                .flatMap(eventModel -> {
                    Mono<Long> participantCountMono = userEventPersistencePort.countByEventId(eventModel.getId())
                            .onErrorResume(e -> {
                                log.warn("⚠️ countByEventId failed eventId={} : {}", eventModel.getId(), e.getMessage());
                                return Mono.just(0L);
                            });

                    Mono<List<String>> imageUrlsMono = mediaPersistencePort.getImagesByEventId(eventModel.getId())
                            .map(ImageModel::getUrl)
                            .collectList()
                            .onErrorResume(e -> {
                                log.warn("⚠️ getImagesByEventId failed eventId={} : {}", eventModel.getId(), e.getMessage());
                                return Mono.just(List.of());
                            });

                    return Mono.zip(participantCountMono, imageUrlsMono)
                            .map(tuple -> new EventResponseDTO(
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
                                    tuple.getT1(),
                                    tuple.getT2()
                            ));
                });
    }

    @Override
    public Flux<ParticipantDTO> getParticipants(UUID eventId) {
        return userEventPersistencePort.findByEventId(eventId)
                .flatMap(userEvent ->
                        userGatewayPort.findById(userEvent.getUserId())
                                .map(userInfo -> new ParticipantDTO(
                                        userInfo.id(),
                                        safeFullName(userInfo.firstName(), userInfo.lastName())
                                ))
                                .onErrorResume(e -> {
                                    log.warn("⚠️ userGateway findById failed (fallback) userId={} : {}",
                                            userEvent.getUserId(), e.getMessage());
                                    return Mono.just(new ParticipantDTO(userEvent.getUserId(), "Utilisateur inconnu"));
                                })
                                .switchIfEmpty(Mono.just(new ParticipantDTO(userEvent.getUserId(), "Utilisateur inconnu")))
                );
    }

    @Override
    public Mono<Void> leaveEvent(UUID userId, UUID eventId) {
        return userEventPersistencePort.delete(userId, eventId);
    }

    private String safeFullName(String firstName, String lastName) {
        String f = (firstName == null) ? "" : firstName.trim();
        String l = (lastName == null) ? "" : lastName.trim();
        String full = (f + " " + l).trim();
        return full.isBlank() ? "Utilisateur inconnu" : full;
    }
}

