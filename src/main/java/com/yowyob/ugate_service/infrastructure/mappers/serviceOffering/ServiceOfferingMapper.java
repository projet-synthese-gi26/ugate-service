package com.yowyob.ugate_service.infrastructure.mappers.serviceOffering;

import org.mapstruct.Mapper;

import com.yowyob.ugate_service.domain.model.SyndicatService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.ServiceOfferingRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ServiceOfferingResponse;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceOfferingMapper {

    @Mapping(target = "syndicatId", source = "syndicatId")
    SyndicatService mapToDomain( ServiceOfferingRequest dto );
    ServiceOfferingResponse mapToResponse( SyndicatService serviceOffering );
    

}
