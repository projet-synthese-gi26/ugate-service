package com.yowyob.ugate_service.infrastructure.mappers.syndicate;

import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.BranchResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Branch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    // MapStruct mappe automatiquement id, name, bannerUrl, etc.
    BranchResponse toResponse(Branch entity);
}