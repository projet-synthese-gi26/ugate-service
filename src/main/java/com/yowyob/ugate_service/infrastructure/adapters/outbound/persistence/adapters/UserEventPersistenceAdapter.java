package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.model.UserEventModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserEventPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.UserEvent;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.UserEventRepository;
import com.yowyob.ugate_service.infrastructure.mappers.UserEventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserEventPersistenceAdapter implements UserEventPersistencePort {

    private final UserEventRepository userEventRepository;
    private final UserEventMapper userEventMapper;

    @Override
    public Mono<Void> save(UserEventModel userEventModel) {
        UserEvent userEventEntity = userEventMapper.toEntity(userEventModel);
        return userEventRepository.save(userEventEntity).then();
    }

    @Override
    public Mono<Long> countByEventId(UUID eventId) {
        return userEventRepository.countByEventId(eventId.toString());
    }

    @Override
    public Flux<UserEventModel> findByEventId(UUID eventId) {
        return userEventRepository.findByEventId(eventId.toString())
                .map(userEventMapper::toModel);
    }

    @Override
    public Mono<Void> delete(UUID userId, UUID eventId) {
        return userEventRepository.deleteByUserIdAndEventId(userId.toString(), eventId.toString());
    }
}