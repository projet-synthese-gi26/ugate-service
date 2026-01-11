package com.yowyob.ugate_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicationVoteModel {
    private UUID id;
    private UUID branchId;
    private String title;
    private String description;
    private Instant closingAt;
    private String type;
}
