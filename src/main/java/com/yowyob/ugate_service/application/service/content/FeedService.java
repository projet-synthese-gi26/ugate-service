package com.yowyob.ugate_service.application.service.content;

import com.yowyob.ugate_service.domain.model.EventModel;
import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.model.ImageModel;
import com.yowyob.ugate_service.domain.model.MediaInfo;
import com.yowyob.ugate_service.domain.model.PublicationModel;
import com.yowyob.ugate_service.domain.ports.in.content.GetFeedUseCase;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.EventPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserEventPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.dto.PublicationResponseDTO;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.EventResponseDTO;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class FeedService implements GetFeedUseCase {
    private final PublicationPersistencePort publicationModelRepository;
    private final EventPersistencePort eventPersistencePort;
    private final UserGatewayPort userGatewayPort;
    private final MediaPersistencePort mediaPersistencePort;
    private final UserEventPersistencePort userEventPersistencePort;

    @Override
    public Flux<Map<String, Object>> getFeed(int page, int size) {
        Flux<Map<String, Object>> publicationsFlux = publicationModelRepository
                .findAllPaginated(page, size)
                .flatMap(this::toPublicationResponseDTO)
                .map(dto -> Map.of(
                        "type", "publication",
                        "data", dto));

        Flux<Map<String, Object>> eventsFlux = eventPersistencePort
                .findAllPaginated(page, size)
                .flatMap(this::toEventResponseDTO)
                .map(dto -> Map.of(
                        "type", "event",
                        "data", dto));

        return publicationsFlux.mergeWith(eventsFlux)
                .sort((a, b) -> {
                    Instant aCreatedAt = getInstant(a.get("data"));
                    Instant bCreatedAt = getInstant(b.get("data"));

                    if (aCreatedAt == null && bCreatedAt == null) {
                        return 0;
                    }
                    if (aCreatedAt == null) {
                        return 1;
                    }
                    if (bCreatedAt == null) {
                        return -1;
                    }

                    return bCreatedAt.compareTo(aCreatedAt);
                })
                .skip((long) page * size)
                .take(size);
    }

    private Instant getInstant(Object data) {
        if (data instanceof PublicationResponseDTO) {
            return ((PublicationResponseDTO) data).getCreatedAt();
        } else if (data instanceof EventResponseDTO) {
            return ((EventResponseDTO) data).getCreatedAt();
        }
        return null;
    }

    private Mono<PublicationResponseDTO> toPublicationResponseDTO(PublicationModel publicationModel) {
        Mono<ExternalUserInfo> authorMono = userGatewayPort.findById(publicationModel.getAuthorId());
        Mono<List<MediaInfo>> mediaListMono = mediaPersistencePort.getMediaByPublicationId(publicationModel.getId())
                .map(mediaInfo -> {
                    MediaInfo fileDto = new MediaInfo();
                    fileDto.setUrl(mediaInfo.getUrl());
                    fileDto.setType(mediaInfo.getType());
                    return fileDto;
                })
                .collectList();

        return Mono.zip(authorMono, mediaListMono)
                .map(tuple -> {
                    ExternalUserInfo author = tuple.getT1();
                    List<MediaInfo> mediaList = tuple.getT2();

                    PublicationResponseDTO dto = new PublicationResponseDTO();
                    dto.setId(publicationModel.getId());
                    dto.setBranchId(publicationModel.getBranchI());
                    dto.setAuthorFullName(author.firstName() + " " + author.lastName());
                    dto.setContent(publicationModel.getContent());
                    dto.setNLikes(publicationModel.getNLikes());
                    // dto.setNComments(publicationModel.get); // Assuming comments count is not directly in model
                    dto.setCreatedAt(publicationModel.getCreatedAt());
                    dto.setFileUrlAndType(mediaList);
                    return dto;
                });
    }

    private Mono<EventResponseDTO> toEventResponseDTO(EventModel eventModel) {
        Mono<Long> participantCountMono = userEventPersistencePort.countByEventId(eventModel.getId());
        Mono<List<String>> imageUrlsMono = mediaPersistencePort.getImagesByEventId(eventModel.getId())
                .map(ImageModel::getUrl) // Assuming ImageModel has getUrl()
                .collectList();

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
                        tuple.getT1(), // participant count
                        tuple.getT2()  // image urls
                ));
    }
}
