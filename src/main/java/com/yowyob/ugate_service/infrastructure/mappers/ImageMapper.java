package com.yowyob.ugate_service.infrastructure.mappers;

import com.yowyob.ugate_service.domain.model.ImageModel;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    Image toEntity(ImageModel model);
    ImageModel toModel(Image entity);
}
