package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Service;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface ServicesRepository extends R2dbcRepository<Service, UUID> {

    // Insertion forcée (puisque l'ID vient du parent)
    @Modifying
    @Query("INSERT INTO services (id, tarif_horaire) VALUES (:#{#s.id}, :#{#s.tarifHoraire})")
    Mono<Void> insert(@org.springframework.data.repository.query.Param("s") Service service);
}