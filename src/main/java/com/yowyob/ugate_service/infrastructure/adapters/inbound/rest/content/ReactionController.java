package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.content;

import com.yowyob.ugate_service.application.service.content.ReactionService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreateReactionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/publications/{publicationId}/reactions")
@RequiredArgsConstructor
@Tag(name = "Reactions", description = "API for managing reactions on publications")
public class ReactionController {

    private final ReactionService reactionService;

    @PostMapping
    @Operation(summary = "Add a reaction to a publication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reaction added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @ApiResponse(responseCode = "404", description = "Publication not found")
    })
    public Mono<ResponseEntity<Void>> addReaction(
            @Parameter(description = "ID of the publication to react to", required = true)
            @PathVariable UUID publicationId,
            @RequestBody CreateReactionRequest request) {
        return reactionService.addReactionToPublication(
                publicationId,
                UUID.fromString(request.getUserId()),
                request.getReactionType()
        ).then(Mono.just(ResponseEntity.ok().build()));
    }
}
