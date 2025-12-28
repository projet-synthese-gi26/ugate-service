package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.content;

import com.yowyob.ugate_service.application.service.content.PublicationService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreatePublicationRequest;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.external.MediaService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/publications")
@RequiredArgsConstructor
@Validated
@Tag(name = "Publications", description = "API for managing publications")
public class PublicationController {

    private final PublicationService publicationService;
    private final MediaService mediaService;

    @Operation(summary = "Create a new publication",
            description = "Creates a new publication with optional image, video, and file attachments.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Publication created successfully",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<Void>> createPublication(
            @Parameter(description = "Publication request data including content, author, branch, and optional media files",
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = CreatePublicationRequest.class)))
            @Valid @ModelAttribute CreatePublicationRequest request) {

        Mono<List<String>> imagesUrlsMono = mediaService.uploadImage(request.getImages());
        Mono<List<String>> videosUrlsMono = mediaService.uploadVideo(request.getVideos());
        Mono<List<String>> filesUrlsMono = mediaService.uploadFiles(request.getFiles());

        return Mono.zip(imagesUrlsMono, videosUrlsMono, filesUrlsMono)
                .flatMap(tuple -> {
                    List<String> imageUrls = tuple.getT1();
                    List<String> videoUrls = tuple.getT2();
                    List<String> fileUrls = tuple.getT3();

                    return publicationService.createPublication(
                            UUID.fromString(request.getAuthorId()),
                            UUID.fromString(request.getBranchId()),
                            request.getContent(),
                            imageUrls.toArray(new String[0]),
                            videoUrls.toArray(new String[0]),
                            fileUrls.toArray(new String[0])
                    );
                })
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
    }
}
