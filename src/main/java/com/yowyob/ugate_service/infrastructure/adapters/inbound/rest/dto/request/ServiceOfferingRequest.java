package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ServiceOfferingRequest(
   
    UUID syndicatId,
    String title,
    String description,
    BigDecimal price,
    // String duration, // ex: "1h", "par session"
    List<String> features,
    Boolean isActive
) {

}
