package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CastVoteRequest {
    @NotBlank(message = "Choice label cannot be blank")
    private String choiceLabel;
}
