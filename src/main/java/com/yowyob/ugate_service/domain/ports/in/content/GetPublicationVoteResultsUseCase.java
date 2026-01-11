package com.yowyob.ugate_service.domain.ports.in.content;

import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.PublicationVoteWithResultsDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface GetPublicationVoteResultsUseCase {
    Mono<PublicationVoteWithResultsDTO> getPublicationVoteResults(UUID publicationVoteId, UUID userId);
}
