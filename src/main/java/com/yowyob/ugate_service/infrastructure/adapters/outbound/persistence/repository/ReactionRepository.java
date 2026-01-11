package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Reaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReactionRepository extends ReactiveCrudRepository<Reaction, UUID> {
}
