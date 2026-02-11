package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;


@Table("purchases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseEntity {
    @Id
    private UUID id;

    private UUID syndicatId;

    private UUID itemId;

    private String itemType;

    private String buyerInfo;

    private BigDecimal amount;

    private Instant createdAt;
}
