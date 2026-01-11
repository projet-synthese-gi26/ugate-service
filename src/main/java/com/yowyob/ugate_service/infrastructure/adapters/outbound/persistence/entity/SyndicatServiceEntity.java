package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;


@Table("syndicat_services")
@Getter
@Setter
public class SyndicatServiceEntity {
    @Id
    private UUID serviceId;
    private BigDecimal price;
    private List<String> features;
}
