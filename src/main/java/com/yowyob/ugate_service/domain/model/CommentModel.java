package com.yowyob.ugate_service.domain.model;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class CommentModel {
  UUID id;

  UUID authorId; // FK -> User

  UUID publicationId; // FK -> Publication

  UUID parentId; // FK -> Comment (Nullable : null si commentaire racine)

  UUID imageId; // FK -> Image (Nullable)

  String content;

  Instant createdAt;
}
