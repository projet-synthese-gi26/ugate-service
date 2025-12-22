package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Syndicat;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface SyndicatRepository extends R2dbcRepository<Syndicat, UUID> {
    // Trouver les syndicats créés par un admin
    Flux<Syndicat> findByCreatorId(UUID creatorId);

    // Filtrer par domaine ou nom
    Flux<Syndicat> findByNameContainingIgnoreCase(String name);
}
