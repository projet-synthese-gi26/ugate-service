package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.content;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.media.MediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import org.springframework.http.codec.multipart.FilePart;

import com.yowyob.ugate_service.application.service.content.PublicationService;
import com.yowyob.ugate_service.domain.ports.out.syndicate.dto.PublicationResponseDTO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/publications")
@AllArgsConstructor
@Validated
@Tag(name = "Publications", description = "API for managing publications")
public class PublicationController {

        private final PublicationService publicationService;
        private final MediaService mediaService;

        @Operation(summary = "Create a new publication", description = "Creates a new publication with optional image, video, and file attachments.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Publication created successfully", content = @Content(schema = @Schema(hidden = true))),
                        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(hidden = true)))
        })
        @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public Mono<ResponseEntity<Void>> createPublication(
                        @Parameter(description = "Content of the publication") @RequestPart("content") Mono<String> content,
                        @Parameter(description = "Author ID of the publication") @RequestPart("authorId") Mono<String> authorId,
                        @Parameter(description = "Branch ID") @RequestPart("branchId") Mono<String> branchId,
                        @Parameter(description = "Optional image files to be attached") @RequestPart(name = "images", required = false) Flux<FilePart> images,
                        @Parameter(description = "Optional video files to be attached") @RequestPart(name = "videos", required = false) Flux<FilePart> videos,
                        @Parameter(description = "Optional general files to be attached") @RequestPart(name = "files", required = false) Flux<FilePart> files) {

                Mono<List<String>> imagesUrlsMono = mediaService.uploadImage(images == null ? Flux.empty() : images);
                Mono<List<String>> videosUrlsMono = mediaService.uploadVideo(videos == null ? Flux.empty() : videos);
                Mono<List<String>> filesUrlsMono = mediaService.uploadFiles(files == null ? Flux.empty() : files);

                return Mono.zip(content, authorId, branchId, imagesUrlsMono, videosUrlsMono, filesUrlsMono)
                                .flatMap(tuple -> {
                                        String contentValue = tuple.getT1();
                                        UUID authorIdValue = UUID.fromString(tuple.getT2());
                                        UUID branchIdValue = UUID.fromString(tuple.getT3());
                                        List<String> imageUrls = tuple.getT4();
                                        List<String> videoUrls = tuple.getT5();
                                        List<String> fileUrls = tuple.getT6();

                                        return publicationService.createPublication(
                                                        authorIdValue,
                                                        branchIdValue,
                                                        contentValue,
                                                        imageUrls.toArray(new String[0]),
                                                        videoUrls.toArray(new String[0]),
                                                        fileUrls.toArray(new String[0]));
                                })
                                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
        }

        @Operation(summary = "Get publications by branch", description = "Retrieves a list of publications for a specific branch.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Publications retrieved successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PublicationResponseDTO.class))),
                        @ApiResponse(responseCode = "404", description = "Branch not found", content = @Content(schema = @Schema(hidden = true)))
        })
        @GetMapping("/branch/{branchId}")
        public Flux<PublicationResponseDTO> getPublicationsByBranch(
                        @Parameter(description = "ID of the branch to retrieve publications from") @PathVariable UUID branchId) {
                return publicationService.getSyndicatPublication(branchId);
        }

}
