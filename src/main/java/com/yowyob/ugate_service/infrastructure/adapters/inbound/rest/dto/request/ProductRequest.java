package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(
    UUID syndicatId,
    String name,
    String description,
    BigDecimal price,
    String sku,
    String category,
    Integer stock,
    Boolean isActive
) {

}
