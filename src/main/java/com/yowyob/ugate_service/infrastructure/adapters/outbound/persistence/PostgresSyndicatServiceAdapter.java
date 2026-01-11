package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yowyob.ugate_service.domain.model.SyndicatService;
import com.yowyob.ugate_service.domain.ports.out.marketplace.ServiceOfferingRepositoryPort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatServiceRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatServiceRow;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PostgresSyndicatServiceAdapter implements ServiceOfferingRepositoryPort {

    private final SyndicatServiceRepository syndicatServiceRepository;
    private final DatabaseClient databaseClient;

    @Override
    public Mono<SyndicatService> findServiceById(UUID id) {
        return syndicatServiceRepository.findFullById(id)
                .map(this::mapToDomain);
    }

    @Override
    public Flux<SyndicatService> findAllActiveServices() {
        return syndicatServiceRepository.findAllActiveFull()
                .map(this::mapToDomain);
    }

    @Override
    @Transactional
    public Mono<SyndicatService> save(SyndicatService service) {
        // 1. Insertion dans la table commune 'services'
        return databaseClient.sql("""
                INSERT INTO services (id, title, description, is_active)
                VALUES (:id, :title, :description, :isActive)
                ON CONFLICT (id) DO UPDATE SET 
                    title = EXCLUDED.title, 
                    description = EXCLUDED.description,
                    is_active = EXCLUDED.is_active
                """)
                .bind("id", service.id())
                .bind("title", service.title())
                .bind("description", service.description())
                .bind("isActive", service.isActive())
                .then()
                // 2. Insertion dans la table d'extension 'syndicat_services'
                .then(databaseClient.sql("""
                    INSERT INTO syndicat_services (service_id, price, features)
                    VALUES (:id, :price, :features)
                    ON CONFLICT (service_id) DO UPDATE SET 
                        price = EXCLUDED.price, 
                        features = EXCLUDED.features
                    """)
                    .bind("id", service.id())
                    .bind("price", service.price())
                    // Conversion de List<String> en tableau String[] pour Postgres
                    .bind("features", service.features().toArray(new String[0]))
                    .then())
                .thenReturn(service);
    }

    @Override
    @Transactional
    public Mono<SyndicatService> updateService(SyndicatService service) {
        return save(service); // L'UPSERT gère l'update
    }

    @Override
    @Transactional
    public Mono<Void> deleteService(UUID id) {
        return databaseClient.sql("DELETE FROM syndicat_services WHERE service_id = :id")
                .bind("id", id)
                .then()
                .then(databaseClient.sql("DELETE FROM services WHERE id = :id")
                        .bind("id", id)
                        .then());
    }

    // --- MAPPER ---
    private SyndicatService mapToDomain(SyndicatServiceRow row) {
        List<String> featuresList = row.features() != null 
                ? Arrays.asList(row.features()) 
                : List.of();

        return new SyndicatService(
            row.id(),
            row.title(),
            row.description(),
            row.price(),
            featuresList,
            row.isActive()
        );
    }
}