package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import reactor.core.publisher.Flux;
import com.yowyob.ugate_service.domain.model.EventModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.EventPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Event;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.EventRepository;
import com.yowyob.ugate_service.infrastructure.mappers.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EventPersistenceAdapter implements EventPersistencePort {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public Mono<EventModel> save(EventModel eventModel) {
        Event eventEntity = eventMapper.toEntity(eventModel);
        return eventRepository.save(eventEntity)
                .map(eventMapper::toModel);
    }

    @Override
    public Flux<EventModel> findByBranchId(UUID branchId) {
        return eventRepository.findByBranchId(branchId)
                .map(eventMapper::toModel);
    }
}
