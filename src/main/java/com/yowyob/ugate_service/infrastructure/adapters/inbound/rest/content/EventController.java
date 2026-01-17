package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.content;

import com.yowyob.ugate_service.domain.ports.in.content.*;
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
import org.springframework.http.codec.multipart.FilePart; // Added import

import java.time.LocalDate;
import java.time.LocalTime;
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
        private final LeaveEventUseCase leaveEventUseCase;
        private final MediaService mediaService;

        @Operation(summary = "Create a new event", description = "Creates a new event with optional image, video, and file attachments.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Event created successfully", content = @Content(schema = @Schema(hidden = true))),
                        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(hidden = true)))
        })
        @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public Mono<ResponseEntity<Void>> createEvent(
                        @Parameter(description = "Creator ID") @RequestPart("creatorId") Mono<String> creatorId,
                        @Parameter(description = "Branch ID") @RequestPart("branchId") Mono<String> branchId,
                        @Parameter(description = "Title") @RequestPart("title") Mono<String> title,
                        @Parameter(description = "Description") @RequestPart("description") Mono<String> description,
                        @Parameter(description = "Event Date (YYYY-MM-DD)") @RequestPart("eventDate") Mono<String> eventDate,
                        @Parameter(description = "Location") @RequestPart("location") Mono<String> location,
                        @Parameter(description = "Start Time (HH:MM)") @RequestPart("startTime") Mono<String> startTime,
                        @Parameter(description = "End Time (HH:MM)") @RequestPart("endTime") Mono<String> endTime,
                        @Parameter(description = "Optional image files to be attached") @RequestPart(name = "images", required = false) Flux<FilePart> images,
                        @Parameter(description = "Optional video files to be attached") @RequestPart(name = "videos", required = false) Flux<FilePart> videos,
                        @Parameter(description = "Optional general files to be attached") @RequestPart(name = "files", required = false) Flux<FilePart> files) {

                Mono<List<String>> imagesUrlsMono = mediaService.uploadImage(images == null ? Flux.empty() : images);
                Mono<List<String>> videosUrlsMono = mediaService.uploadVideo(videos == null ? Flux.empty() : videos);
                Mono<List<String>> filesUrlsMono = mediaService.uploadFiles(files == null ? Flux.empty() : files);

                return Mono.zip(creatorId, branchId, title, description, eventDate, location, startTime, endTime)
                                .zipWith(Mono.zip(imagesUrlsMono, videosUrlsMono, filesUrlsMono))
                                .flatMap(tuple -> {
                                        var fields = tuple.getT1();
                                        var media = tuple.getT2();

                                        UUID creatorIdVal = UUID.fromString(fields.getT1());
                                        UUID branchIdVal = UUID.fromString(fields.getT2());
                                        String titleVal = fields.getT3();
                                        String descriptionVal = fields.getT4();
                                        LocalDate eventDateVal = LocalDate.parse(fields.getT5());
                                        String locationVal = fields.getT6();
                                        LocalTime startTimeVal = LocalTime.parse(fields.getT7());
                                        LocalTime endTimeVal = LocalTime.parse(fields.getT8());

                                        List<String> imageUrls = media.getT1();
                                        List<String> videoUrls = media.getT2();
                                        List<String> fileUrls = media.getT3();

                                        return createEventUseCase.createEvent(
                                                        creatorIdVal,
                                                        branchIdVal,
                                                        titleVal,
                                                        descriptionVal,
                                                        eventDateVal,
                                                        locationVal,
                                                        startTimeVal,
                                                        endTimeVal,
                                                        imageUrls.toArray(new String[0]),
                                                        videoUrls.toArray(new String[0]),
                                                        fileUrls.toArray(new String[0]));
                                })
                                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
        }

        @Operation(summary = "Join an event", description = "Allows an authenticated user to join an existing event.")
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

        @Operation(summary = "Get events by branch", description = "Retrieves a list of events for a specific branch, including participant counts.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Events retrieved successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EventResponseDTO.class))),
                        @ApiResponse(responseCode = "404", description = "Branch not found", content = @Content(schema = @Schema(hidden = true)))
        })
        @GetMapping("/branch/{branchId}")
        public Flux<EventResponseDTO> getEventsByBranch(
                        @Parameter(description = "ID of the branch to retrieve events from") @PathVariable UUID branchId) {
                return getEventsByBranchUseCase.getEventsByBranch(branchId);
        }

        @Operation(summary = "Get participants for an event", description = "Retrieves a list of participants for a specific event.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Participants retrieved successfully"),
                        @ApiResponse(responseCode = "404", description = "Event not found")
        })
        @GetMapping("/{eventId}/participants")
        public Flux<ParticipantDTO> getParticipants(
                        @Parameter(description = "ID of the event to retrieve participants from") @PathVariable UUID eventId) {
                return getEventParticipantsUseCase.getParticipants(eventId);
        }

        @Operation(summary = "Leave an event", description = "Allows an authenticated user to leave an event they have joined.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Successfully left the event"),
                        @ApiResponse(responseCode = "404", description = "Event or participation not found")
        })
        @DeleteMapping("/{eventId}/leave")
        public Mono<ResponseEntity<Void>> leaveEvent(
                        @AuthenticationPrincipal Jwt jwt,
                        @Parameter(description = "ID of the event to leave") @PathVariable UUID eventId) {

                UUID userId = UUID.fromString(jwt.getSubject());
                return leaveEventUseCase.leaveEvent(userId, eventId)
                                .then(Mono.just(ResponseEntity.noContent().build()));
        }
}
