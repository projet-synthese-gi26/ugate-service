package com.yowyob.ugate_service.domain.ports.out.syndicate.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.yowyob.ugate_service.domain.model.MediaInfo;

import lombok.Data;
import reactor.core.publisher.Flux;

@Data
public class PublicationResponseDTO {
  UUID id;

  UUID branchId; // FK -> Branch

  String AuthorFullName;

  String content;

  Long nLikes; // Compteur de likes

  Long nComments; // Compteur de comments

  Instant createdAt;

  List<MediaInfo> FileUrlAndType;

}
