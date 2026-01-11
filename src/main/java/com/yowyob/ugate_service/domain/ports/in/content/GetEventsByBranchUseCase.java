package com.yowyob.ugate_service.domain.ports.in.content;

import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.EventResponseDTO;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface GetEventsByBranchUseCase {
    Flux<EventResponseDTO> getEventsByBranch(UUID branchId);
}
