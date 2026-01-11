package com.yowyob.ugate_service.infrastructure.mappers.products;

import org.mapstruct.Mapper;

import com.yowyob.ugate_service.domain.model.Product;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.ProductRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ProductResponse;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product mapToDomain(ProductRequest dto);
    ProductResponse mapToResponse(Product product);
    
}


