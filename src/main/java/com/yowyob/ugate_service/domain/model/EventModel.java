package com.yowyob.ugate_service.domain.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import lombok.Data;


@Data
public class EventModel {
  UUID id;
  UUID creatorId;
  UUID branchId;

  String title;
  String description;
  String location;

  LocalDate date;
  LocalTime startTime;
  LocalTime endTime;
  Instant createdAt;
  Instant updatedAt;
}
