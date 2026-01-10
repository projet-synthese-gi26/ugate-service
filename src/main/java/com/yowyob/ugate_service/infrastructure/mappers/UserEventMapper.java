package com.yowyob.ugate_service.infrastructure.mappers;

import com.yowyob.ugate_service.domain.model.UserEventModel;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.UserEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEventMapper {

    @Mapping(target = "userId", expression = "java(model.getUserId().toString())")
    @Mapping(target = "eventId", expression = "java(model.getEventId().toString())")
    UserEvent toEntity(UserEventModel model);

    @Mapping(target = "userId", expression = "java(java.util.UUID.fromString(entity.userId()))")
    @Mapping(target = "eventId", expression = "java(java.util.UUID.fromString(entity.eventId()))")
    UserEventModel toModel(UserEvent entity);
}
