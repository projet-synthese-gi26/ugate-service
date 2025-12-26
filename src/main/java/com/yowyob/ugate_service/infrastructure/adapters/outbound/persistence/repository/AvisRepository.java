package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Avis;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import java.util.UUID;

public interface AvisRepository extends R2dbcRepository<Avis, UUID> {

}