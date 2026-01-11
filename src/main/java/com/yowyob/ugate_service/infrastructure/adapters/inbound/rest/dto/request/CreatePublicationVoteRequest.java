package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Schema(name = "CreatePublicationVoteRequest", description = "Request DTO for creating a new publication vote (poll)")
public class CreatePublicationVoteRequest {

    @NotBlank(message = "Title cannot be blank")
    @Schema(description = "The title of the poll.", example = "What is the best programming language?")
    private String title;

    @Schema(description = "A short description of the poll.", example = "Let us know your favorite language.")
    private String description;

    @Future(message = "Closing date must be in the future")
    @Schema(description = "The date and time when the poll will close.")
    private Instant closingAt;

    @NotBlank(message = "Type cannot be blank")
    @Schema(description = "The type of poll.", example = "SINGLE_CHOICE", allowableValues = { "SINGLE_CHOICE",
            "MULTIPLE_CHOICE" })
    private String type;

    @NotNull(message = "Branch ID cannot be null")
    @Schema(description = "The branch ID associated with the poll.", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID branchId;
}
