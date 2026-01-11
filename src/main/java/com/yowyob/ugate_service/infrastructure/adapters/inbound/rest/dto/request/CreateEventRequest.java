package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.http.codec.multipart.FilePart;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Schema(name = "CreateEventRequest", description = "Request DTO for creating a new event")
public class CreateEventRequest {
    @NotNull(message = "Creator ID cannot be null")
    @Schema(description = "The unique identifier of the user creating the event", example = "a1b2c3d4-e5f6-7890-1234-567890abcdef")
    private UUID creatorId;

    @NotNull(message = "Branch ID cannot be null")
    @Schema(description = "The unique identifier of the branch to which the event belongs", example = "f1e2d3c4-b5a6-9876-5432-10fedcba9876")
    private UUID branchId;

    @NotBlank(message = "Title cannot be blank")
    @Schema(description = "The title of the event", example = "Community Meetup")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    @Schema(description = "A detailed description of the event", example = "A gathering to discuss local initiatives.")
    private String description;

    @NotNull(message = "Event date cannot be null")
    @Schema(description = "The date on which the event will take place (YYYY-MM-DD)", example = "2026-03-15")
    private LocalDate eventDate;

    @NotBlank(message = "Location cannot be blank")
    @Schema(description = "The location where the event will be held", example = "Community Hall")
    private String location;

    @NotNull(message = "Start time cannot be null")
    @Schema(description = "The start time of the event (HH:MM)", example = "10:00")
    private LocalTime startTime;

    @NotNull(message = "End time cannot be null")
    @Schema(description = "The end time of the event (HH:MM)", example = "12:00")
    private LocalTime endTime;

    @Schema(description = "An array of image files to be attached to the event")
    private FilePart[] images;

    @Schema(description = "An array of video files to be attached to the event")
    private FilePart[] videos;

    @Schema(description = "An array of general files to be attached to the event")
    private FilePart[] files;
}
