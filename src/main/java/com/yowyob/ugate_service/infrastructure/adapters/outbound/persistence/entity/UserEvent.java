package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("user_events")
public record UserEvent(

    @Column("user_event_id") //
    UUID id,

    @Column("user_id") //
    String userId,

    @Column("event_id") //
    String eventId,

    @Column("timestamp") //
    Instant timestamp) {

  public UserEvent(String userId, String eventId, Instant timestamp) {
    this(null, userId, eventId, timestamp);
  }
}
