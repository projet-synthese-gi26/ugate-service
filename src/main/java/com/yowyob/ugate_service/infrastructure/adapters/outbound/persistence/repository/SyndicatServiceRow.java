package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import java.math.BigDecimal;
import java.util.UUID;

public record SyndicatServiceRow(
    UUID id,
    String title,
    String description,
    BigDecimal price, // Vient de syndicat_services
    String[] features, // Tableau natif Postgres
    Boolean isActive
) {}