package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.UserEvent;

public interface UserEventRepository extends R2dbcRepository<UserEvent, UUID> {

}