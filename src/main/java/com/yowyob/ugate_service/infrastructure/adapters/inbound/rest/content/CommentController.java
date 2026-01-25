package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.content;

import com.yowyob.ugate_service.application.service.content.CommentService;
import com.yowyob.ugate_service.domain.ports.out.syndicate.dto.CommentResponseDto;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreateCommentRequest;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.media.MediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

import org.springframework.security.oauth2.jwt.Jwt;

@RestController
@RequestMapping("/publications/{publicationId}/comments")
@AllArgsConstructor
@Tag(name = "Comments", description = "API for managing comments on publications")
public class CommentController {

        private final CommentService commentService;
        private final MediaService mediaService;

        @Operation(summary = "Create a new comment", description = "Add a comment to a specific publication.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Comment created successfully"),
                        @ApiResponse(responseCode = "400", description = "Invalid input provided"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized access")
        })
        @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public Mono<ResponseEntity<Void>> createComment(
                        @Parameter(description = "UUID of the publication") @PathVariable UUID publicationId,
                        @Parameter(description = "Content of the comment") @RequestPart(name = "content") Mono<String> content,
                        @Parameter(description = "Image file for the comment") @RequestPart(name = "image", required = false) Mono<FilePart> image,
                        @Parameter(description = "Parent comment ID") @RequestPart(name = "parentId", required = false) Mono<String> parentId) {

                Mono<String> imageUrlMono = mediaService.uploadImage(image == null ? Flux.empty() : image.flux())
                                .map(list -> list.isEmpty() ? "" : list.get(0));

                return ReactiveSecurityContextHolder.getContext()
                                .map(SecurityContext::getAuthentication)
                                .map(authentication -> {
                                        Jwt jwt = (Jwt) authentication.getPrincipal();
                                        return UUID.fromString(jwt.getSubject());
                                })
                                .flatMap(authorId -> Mono.zip(content, imageUrlMono, parentId.defaultIfEmpty(""))
                                                .flatMap(tuple -> {
                                                        String contentVal = tuple.getT1();
                                                        String imageUrlVal = tuple.getT2().isEmpty() ? null
                                                                        : tuple.getT2();
                                                        String parentIdStr = tuple.getT3();
                                                        UUID parentUuid = (parentIdStr == null || parentIdStr.isEmpty())
                                                                        ? null
                                                                        : UUID.fromString(parentIdStr);

                                                        return commentService.createComment(
                                                                        authorId,
                                                                        publicationId,
                                                                        parentUuid,
                                                                        imageUrlVal,
                                                                        contentVal);
                                                }))
                                .map(v -> ResponseEntity.ok().build());
        }

        @Operation(summary = "Get comments for a publication", description = "Retrieve a list of comments associated with a publication.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successfully retrieved comments", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentResponseDto.class))),
                        @ApiResponse(responseCode = "404", description = "Publication not found")
        })
        @GetMapping
        public Flux<CommentResponseDto> getComments(
                        @Parameter(description = "UUID of the publication") @PathVariable UUID publicationId) {
                return commentService.getCommentsByPublicationId(publicationId);
        }
}
