package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Reaction;

import reactor.core.publisher.Mono;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

@Repository
public interface ReactionRepository extends ReactiveCrudRepository<Reaction, UUID> {

  @Query("""
      INSERT INTO reactions (publication_id, user_id, type, reacted_at)
      VALUES (:publicationId, :userId, CAST(:type AS reaction_type_enum), :reactedAt)
      """)
  Mono<Void> insertReaction(
      UUID publicationId,
      UUID userId,
      String type,
      Instant reactedAt);

}
