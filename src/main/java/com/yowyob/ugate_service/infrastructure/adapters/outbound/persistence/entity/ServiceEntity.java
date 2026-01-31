package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import java.util.List;
import java.util.UUID;


@Table("syndicat_services") 
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceEntity implements Persistable<UUID>{
    @Id
    private UUID id;

    @Column("syndicat_id")
    private UUID syndicatId;
    
    private String title;

    private String description;

    private BigDecimal price;
   
    private String[] features;

    @Column("is_active")
    private Boolean isActive;

    @Transient
    private boolean isNew = false;



    // 4. C'est ici que la magie opère
    @Override
    public boolean isNew() {
        // Si isNew est true OU si l'id est null, Spring fera un INSERT
        return isNew || id == null;
    }
}