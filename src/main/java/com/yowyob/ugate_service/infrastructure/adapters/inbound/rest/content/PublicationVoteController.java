package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.content;

import com.yowyob.ugate_service.domain.model.PublicationVoteModel;
import com.yowyob.ugate_service.domain.ports.in.content.CastVoteUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.CreatePublicationVoteUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.GetPublicationVoteResultsUseCase;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CastVoteRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreatePublicationVoteRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.PublicationVoteWithResultsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/publication-votes")
@RequiredArgsConstructor
@Tag(name = "Publication Votes", description = "API for managing publication votes (polls)")
public class PublicationVoteController {

        private final CreatePublicationVoteUseCase createPublicationVoteUseCase;
        private final CastVoteUseCase castVoteUseCase;
        private final GetPublicationVoteResultsUseCase getPublicationVoteResultsUseCase;

        @Operation(summary = "Create a new publication vote (poll)", description = "Creates a new poll that can be associated with a publication.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Poll created successfully"),
                        @ApiResponse(responseCode = "400", description = "Invalid input")
        })
        @PostMapping
        public Mono<ResponseEntity<Void>> createPublicationVote(
                        @Valid @RequestBody CreatePublicationVoteRequest request) {
                PublicationVoteModel model = new PublicationVoteModel();
                model.setTitle(request.getTitle());
                model.setDescription(request.getDescription());
                model.setClosingAt(request.getClosingAt());
                model.setType(request.getType());
                model.setBranchId(request.getBranchId());

                return createPublicationVoteUseCase.createPublicationVote(model)
                                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
        }

        @Operation(summary = "Cast a vote on a poll", description = "Allows an authenticated user to cast a vote on a specific poll.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Vote cast successfully"),
                        @ApiResponse(responseCode = "400", description = "Invalid input"),
                        @ApiResponse(responseCode = "404", description = "Poll not found"),
                        @ApiResponse(responseCode = "409", description = "Poll is closed")
        })
        @PostMapping("/{publicationVoteId}/cast")
        public Mono<ResponseEntity<Void>> castVote(
                        @AuthenticationPrincipal Jwt jwt,
                        @PathVariable UUID publicationVoteId,
                        @Valid @RequestBody CastVoteRequest request) {

                UUID userId = UUID.fromString(jwt.getSubject());
                return castVoteUseCase.castVote(userId, publicationVoteId, request.getChoiceLabel())
                                .then(Mono.just(ResponseEntity.ok().build()));
        }

        @Operation(summary = "Get results for a poll", description = "Retrieves the results for a specific poll, including total votes, vote distribution, and whether the current user has voted.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Poll results retrieved successfully"),
                        @ApiResponse(responseCode = "404", description = "Poll not found")
        })
        @GetMapping("/{publicationVoteId}/results")
        public Mono<PublicationVoteWithResultsDTO> getPublicationVoteResults(
                        @AuthenticationPrincipal Jwt jwt,
                        @PathVariable UUID publicationVoteId) {

                UUID userId = UUID.fromString(jwt.getSubject());
                return getPublicationVoteResultsUseCase.getPublicationVoteResults(publicationVoteId, userId);
        }
}