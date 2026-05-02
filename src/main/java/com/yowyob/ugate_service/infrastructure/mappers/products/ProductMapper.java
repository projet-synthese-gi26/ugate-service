package com.yowyob.ugate_service.infrastructure.mappers.products;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.yowyob.ugate_service.domain.model.Product;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.ProductRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ProductResponse;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "imageUrl", ignore = true)
    Product mapToDomain(ProductRequest dto);

    @Mapping(target = "image", source = "imageUrl") 
    ProductResponse mapToResponse(Product product);
}