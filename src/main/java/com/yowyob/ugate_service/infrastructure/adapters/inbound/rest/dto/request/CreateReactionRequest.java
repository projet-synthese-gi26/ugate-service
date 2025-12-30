package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import lombok.Data;

@Data
public class CreateReactionRequest {
    private String userId;
    private String reactionType;
}
