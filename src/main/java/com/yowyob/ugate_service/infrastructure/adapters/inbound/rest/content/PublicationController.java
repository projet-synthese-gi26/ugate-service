package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.content;

import com.yowyob.ugate_service.application.service.content.PublicationService;
import com.yowyob.ugate_service.domain.ports.out.syndicate.dto.PublicationResponseDTO;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.media.MediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema; // Import important
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @Operation(summary = "Create a new publication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Publication created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<Void>> createPublication(
            @Parameter(description = "Content") @RequestPart("content") Mono<String> content,
            @Parameter(description = "Author ID") @RequestPart("authorId") Mono<String> authorId,
            @Parameter(description = "Branch ID") @RequestPart("branchId") Mono<String> branchId,

            // --- CORRECTION SWAGGER ICI ---
            // On dit explicitement à Swagger : "C'est un tableau de fichiers binaires", même si le code Java utilise 'Part'
            @Parameter(
                    description = "Optional image files",
                    array = @ArraySchema(schema = @Schema(type = "string", format = "binary"))
            )
            @RequestPart(name = "images", required = false) Flux<Part> images,

            @Parameter(
                    description = "Optional video files",
                    array = @ArraySchema(schema = @Schema(type = "string", format = "binary"))
            )
            @RequestPart(name = "videos", required = false) Flux<Part> videos,

            @Parameter(
                    description = "Optional general files",
                    array = @ArraySchema(schema = @Schema(type = "string", format = "binary"))
            )
            @RequestPart(name = "files", required = false) Flux<Part> files) {

        // Conversion et nettoyage (Le code backend reste robuste)
        Flux<FilePart> imageFiles = convertParts(images);
        Flux<FilePart> videoFiles = convertParts(videos);
        Flux<FilePart> genericFiles = convertParts(files);

        Mono<List<String>> imagesUrlsMono = mediaService.uploadImage(imageFiles);
        Mono<List<String>> videosUrlsMono = mediaService.uploadVideo(videoFiles);
        Mono<List<String>> filesUrlsMono = mediaService.uploadFiles(genericFiles);

        return Mono.zip(content, authorId, branchId)
                .zipWith(Mono.zip(imagesUrlsMono, videosUrlsMono, filesUrlsMono))
                .flatMap(tuple -> {
                    var textData = tuple.getT1();
                    var mediaData = tuple.getT2();

                    String contentValue = textData.getT1();
                    UUID authorIdValue = UUID.fromString(textData.getT2());
                    UUID branchIdValue = UUID.fromString(textData.getT3());

                    List<String> imageUrls = mediaData.getT1();
                    List<String> videoUrls = mediaData.getT2();
                    List<String> fileUrls = mediaData.getT3();

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

    private Flux<FilePart> convertParts(Flux<Part> parts) {
        if (parts == null) {
            return Flux.empty();
        }
        return parts
                .filter(part -> part instanceof FilePart)
                .cast(FilePart.class);
    }

    @GetMapping("/branch/{branchId}")
    public Flux<PublicationResponseDTO> getPublicationsByBranch(@PathVariable UUID branchId) {
        return publicationService.getSyndicatPublication(branchId);
    }
}