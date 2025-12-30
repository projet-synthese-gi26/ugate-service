package com.yowyob.ugate_service.domain.model;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class ImageModel {
  UUID id;

  String url;

  String altText;

  Instant uploadedAt;
}
