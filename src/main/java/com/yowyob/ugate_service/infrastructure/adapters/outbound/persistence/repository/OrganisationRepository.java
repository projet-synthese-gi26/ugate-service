package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.BusinessActor;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Organization;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface OrganisationRepository extends R2dbcRepository<Organization, UUID> {
}
