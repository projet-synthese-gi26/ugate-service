package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Event;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import java.time.LocalDate;
import java.util.UUID;

public interface EventRepository extends R2dbcRepository<Event, UUID> {
    Flux<Event> findByBranchId(UUID brancheId);

    // Trouver les événements à venir

    Flux<Event> findByBranchIdAndDateAfter(UUID brancheId, LocalDate date);
}