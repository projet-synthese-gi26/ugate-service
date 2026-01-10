package com.yowyob.ugate_service.infrastructure.mappers;

import com.yowyob.ugate_service.domain.model.EventModel;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event toEntity(EventModel model);
    EventModel toModel(Event entity);
}
