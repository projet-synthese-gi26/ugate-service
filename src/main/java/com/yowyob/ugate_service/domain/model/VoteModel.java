package com.yowyob.ugate_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteModel {
    private UUID id;
    private UUID userId;
    private UUID publicationVoteId;
    private String label;
}
