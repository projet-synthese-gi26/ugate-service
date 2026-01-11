package com.yowyob.ugate_service.infrastructure.mappers;

import com.yowyob.ugate_service.domain.model.PublicationVoteModel;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.PublicationVote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublicationVoteMapper {
    PublicationVote toEntity(PublicationVoteModel model);
    PublicationVoteModel toModel(PublicationVote entity);
}
