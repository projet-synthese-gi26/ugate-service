package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Transient;
import java.math.BigDecimal;
import java.util.UUID;

@Table("syndicat_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity implements Persistable<UUID> {

                
                @Id
                private UUID id;
        
                private UUID syndicatId;

                private String name;

                private String description;

                private BigDecimal price;

                private String sku;

                private String category;

                private Integer stock;

                private String imageUrl;

                private Boolean isActive;

                @Transient
                private boolean isNew = false;

                
                @Override
                public boolean isNew() {
                    // Si isNew est true OU si l'id est null, Spring fera un INSERT
                    return isNew || id == null;
                }

} 