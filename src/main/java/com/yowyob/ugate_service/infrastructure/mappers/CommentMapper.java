package com.yowyob.ugate_service.infrastructure.mappers;

import com.yowyob.ugate_service.domain.model.CommentModel;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toEntity(CommentModel model);
    CommentModel toModel(Comment entity);
}
