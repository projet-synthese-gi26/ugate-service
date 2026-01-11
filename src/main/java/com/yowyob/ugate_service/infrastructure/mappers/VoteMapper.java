package com.yowyob.ugate_service.infrastructure.mappers;

import com.yowyob.ugate_service.domain.model.VoteModel;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Vote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoteMapper {
    Vote toEntity(VoteModel model);
    VoteModel toModel(Vote entity);
}
