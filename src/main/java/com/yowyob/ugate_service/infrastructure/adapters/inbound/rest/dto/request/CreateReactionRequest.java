package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import java.util.UUID;

import com.yowyob.ugate_service.domain.enumeration.ReactionTypeEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Request object for creating a reaction")
public class CreateReactionRequest {

    @Schema(description = "The UUID of the user creating the reaction", example = "a1b2c3d4-e5f6-7890-1234-567890abcdef")
    private UUID userId;

    @Schema(description = "The type of the reaction", example = "LIKE")
    private ReactionTypeEnum reactionType;
}
