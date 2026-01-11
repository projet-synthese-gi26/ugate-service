package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteResultDTO {
    private String choiceLabel;
    private long count;
}
