package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDTO {
    private UUID userId;
    private String fullName;
}
