package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.content;

import com.yowyob.ugate_service.domain.ports.in.content.CreateEventUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.GetEventParticipantsUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.GetEventsByBranchUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.JoinEventUseCase;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreateEventRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.EventResponseDTO;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ParticipantDTO;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.media.MediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Validated
@Tag(name = "Events", description = "API for managing events")
public class EventController {

    private final CreateEventUseCase createEventUseCase;
    private final JoinEventUseCase joinEventUseCase;
    private final GetEventsByBranchUseCase getEventsByBranchUseCase;
    private final GetEventParticipantsUseCase getEventParticipantsUseCase;
    private final MediaService mediaService;

    @Operation(summary = "Create a new event",
            description = "Creates a new event with optional image, video, and file attachments.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created successfully",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<Void>> createEvent(
            @Parameter(description = "Event request data including creator, branch, title, description, date, time, location, and optional media files",
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = CreateEventRequest.class)))
            @Valid @ModelAttribute CreateEventRequest request) {

        Mono<List<String>> imagesUrlsMono = mediaService.uploadImage(request.getImages());
        Mono<List<String>> videosUrlsMono = mediaService.uploadVideo(request.getVideos());
        Mono<List<String>> filesUrlsMono = mediaService.uploadFiles(request.getFiles());

        return Mono.zip(imagesUrlsMono, videosUrlsMono, filesUrlsMono)
                .flatMap(tuple -> {
                    List<String> imageUrls = tuple.getT1();
                    List<String> videoUrls = tuple.getT2();
                    List<String> fileUrls = tuple.getT3();

                    return createEventUseCase.createEvent(
                            UUID.fromString(request.getCreatorId()),
                            UUID.fromString(request.getBranchId()),
                            request.getTitle(),
                            request.getDescription(),
                            request.getEventDate(),
                            request.getLocation(),
                            request.getStartTime(),
                            request.getEndTime(),
                            imageUrls.toArray(new String[0]),
                            videoUrls.toArray(new String[0]),
                            fileUrls.toArray(new String[0])
                    );
                })
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
    }

    @Operation(summary = "Join an event",
            description = "Allows an authenticated user to join an existing event.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully joined the event"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @PostMapping("/{eventId}/join")
    public Mono<ResponseEntity<Void>> joinEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Parameter(description = "ID of the event to join") @PathVariable UUID eventId) {

        UUID userId = UUID.fromString(jwt.getSubject());
        return joinEventUseCase.joinEvent(userId, eventId)
                .then(Mono.just(ResponseEntity.ok().build()));
    }

    @Operation(summary = "Get events by branch",
            description = "Retrieves a list of events for a specific branch, including participant counts.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Branch not found")
    })
    @GetMapping("/branch/{branchId}")
    public Flux<EventResponseDTO> getEventsByBranch(
            @Parameter(description = "ID of the branch to retrieve events from")
            @PathVariable UUID branchId) {
        return getEventsByBranchUseCase.getEventsByBranch(branchId);
    }

    @Operation(summary = "Get participants for an event",
            description = "Retrieves a list of participants for a specific event.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Participants retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @GetMapping("/{eventId}/participants")
    public Flux<ParticipantDTO> getParticipants(
            @Parameter(description = "ID of the event to retrieve participants from")
            @PathVariable UUID eventId) {
        return getEventParticipantsUseCase.getParticipants(eventId);
    }
}