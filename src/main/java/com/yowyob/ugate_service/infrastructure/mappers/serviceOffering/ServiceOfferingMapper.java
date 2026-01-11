package com.yowyob.ugate_service.infrastructure.mappers.serviceOffering;

import org.mapstruct.Mapper;

import com.yowyob.ugate_service.domain.model.SyndicatService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.ServiceOfferingRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ServiceOfferingResponse;

@Mapper(componentModel = "spring")
public interface ServiceOfferingMapper {

    SyndicatService mapToDomain( ServiceOfferingRequest dto );
    ServiceOfferingResponse mapToResponse( SyndicatService serviceOffering );
    

}
