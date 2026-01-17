package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Agency;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import java.util.UUID;

public interface AgencyRepository extends R2dbcRepository<Agency, UUID> {
}