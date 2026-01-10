package com.yowyob.ugate_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEventModel {
    private UUID id;
    private UUID userId;
    private UUID eventId;
    private Instant timestamp;
}
