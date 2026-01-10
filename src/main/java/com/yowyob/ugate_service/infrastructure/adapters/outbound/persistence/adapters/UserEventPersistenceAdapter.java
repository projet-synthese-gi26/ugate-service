package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.model.UserEventModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserEventPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.UserEvent;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.UserEventRepository;
import com.yowyob.ugate_service.infrastructure.mappers.UserEventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

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
}
