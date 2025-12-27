package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.http.codec.multipart.FilePart;

@Data
@Schema(name = "CreatePublicationRequest", description = "Request DTO for creating a new publication")
public class CreatePublicationRequest {
    @NotBlank(message = "Content cannot be blank")
    @Schema(description = "The main textual content of the publication", example = "This is a new post about my day.")
    private String content;

    @NotNull(message = "Author ID cannot be null")
    @Schema(description = "The unique identifier of the author of the publication", example = "a1b2c3d4-e5f6-7890-1234-567890abcdef")
    private String authorId;

    @NotNull(message = "Branch ID cannot be null")
    @Schema(description = "The unique identifier of the branch to which the publication belongs", example = "f1e2d3c4-b5a6-9876-5432-10fedcba9876")
    private String branchId;

    @Schema(description = "An array of image files to be attached to the publication")
    private FilePart[] images;

    @Schema(description = "An array of video files to be attached to the publication")
    private FilePart[] videos;

    @Schema(description = "An array of general files to be attached to the publication")
    private FilePart[] files;
}
