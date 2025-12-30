package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.content;

import com.yowyob.ugate_service.application.service.content.ReactionService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreateReactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/publications/{publicationId}/reactions")
@RequiredArgsConstructor
public class ReactionController {

    private final ReactionService reactionService;

    @PostMapping
    public Mono<ResponseEntity<Void>> addReaction(
            @PathVariable UUID publicationId,
            @RequestBody CreateReactionRequest request) {
        return reactionService.addReactionToPublication(
                publicationId,
                UUID.fromString(request.getUserId()),
                request.getReactionType()
        ).then(Mono.just(ResponseEntity.ok().build()));
    }
}
