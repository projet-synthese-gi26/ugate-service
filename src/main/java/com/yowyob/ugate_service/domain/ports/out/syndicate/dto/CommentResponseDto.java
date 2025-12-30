package com.yowyob.ugate_service.domain.ports.out.syndicate.dto;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class CommentResponseDto {
  UUID id;

  UUID authorId;

  String authorFullName;

  UUID publicationId;

  UUID parentId;

  String imageUrl;

  String content;

  Instant createdAt;
}
