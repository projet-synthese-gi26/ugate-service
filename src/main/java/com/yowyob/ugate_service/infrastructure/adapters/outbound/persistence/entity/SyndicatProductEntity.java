package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;


@Table("syndicat_products")
@Getter
@Setter
public class SyndicatProductEntity{
    @Id
    UUID productId;
    String sku;
    String category;
    Integer stock;
}
