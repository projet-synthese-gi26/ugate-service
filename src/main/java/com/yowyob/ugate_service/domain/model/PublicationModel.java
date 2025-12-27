package com.yowyob.ugate_service.domain.model;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class PublicationModel {
  UUID id;

  UUID branchI; // FK -> Branch

  UUID authorId; // FK -> User

  String content;
  // String status, // Ex: "PUBLISHED", "DRAFT"

  Long nLikes; // Compteur de likes

  Instant createdAt;
}