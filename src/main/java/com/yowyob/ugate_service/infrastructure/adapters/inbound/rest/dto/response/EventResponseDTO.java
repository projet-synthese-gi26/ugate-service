package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDTO {
    private UUID id;
    private UUID creatorId;
    private UUID branchId;
    private String title;
    private String description;
    private String location;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Instant createdAt;
    private Instant updatedAt;
    private long participantCount;
    private List<String> imageUrls;
}
