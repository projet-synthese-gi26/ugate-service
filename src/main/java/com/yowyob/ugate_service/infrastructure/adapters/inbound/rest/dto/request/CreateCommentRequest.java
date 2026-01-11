package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateCommentRequest {
    private String content;
    private String imageUrl;
    private UUID parentId;
}
