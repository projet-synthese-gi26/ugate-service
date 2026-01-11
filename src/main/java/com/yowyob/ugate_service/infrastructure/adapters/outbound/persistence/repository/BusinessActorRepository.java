package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.BusinessActor;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Profile;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface BusinessActorRepository extends R2dbcRepository<BusinessActor, UUID> {
}
