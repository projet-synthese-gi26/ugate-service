package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.PublicationImage;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface PublicationImageRepository extends R2dbcRepository<PublicationImage, UUID> {
}
