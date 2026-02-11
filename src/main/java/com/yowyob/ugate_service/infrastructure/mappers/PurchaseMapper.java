package com.yowyob.ugate_service.infrastructure.mappers;

import com.yowyob.ugate_service.domain.model.Purchase;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.PurchaseResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.PurchaseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {
    PurchaseResponse mapToResponse(Purchase purchase);
    Purchase mapToDomain (PurchaseEntity entity);
}
