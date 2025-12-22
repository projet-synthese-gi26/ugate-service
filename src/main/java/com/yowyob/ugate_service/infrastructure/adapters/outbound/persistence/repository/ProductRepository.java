package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Product;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface ProductRepository extends R2dbcRepository<Product, UUID> {

    @Modifying
    // Correction : table "product" et champ "standardPrice" (ou regularAmount selon votre logique métier)
    @Query("INSERT INTO product (id, standard_price) VALUES (:#{#p.id}, :#{#p.standardPrice})")
    Mono<Void> insert(@org.springframework.data.repository.query.Param("p") Product produit);
}
