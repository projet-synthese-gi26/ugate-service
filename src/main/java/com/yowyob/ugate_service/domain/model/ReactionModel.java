package com.yowyob.ugate_service.domain.model;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class ReactionModel {
  UUID id;

  Long publicationId; // FK -> Publication

  UUID userId; // FK -> User

  String type; // "LIKE", "LOVE", etc.

  Instant reactedAt;
}
