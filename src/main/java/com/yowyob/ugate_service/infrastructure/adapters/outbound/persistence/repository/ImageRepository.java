package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Image;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface ImageRepository extends R2dbcRepository<Image, UUID> {

    // Requête JOIN pratique : Récupérer toutes les images d'une publication
    // directement
    @Query("""
                SELECT i.* FROM images i
                JOIN publication_images pi ON i.id = pi.image_id
                WHERE pi.publication_id = :pubId
            """)
    Flux<Image> findByPublicationId(UUID pubId); // TODO a modifier

    // Même chose pour les événements
    @Query("""
                SELECT i.* FROM images i
                JOIN event_images ei ON i.id = ei.image_id
                WHERE ei.event_id = :eventId
            """)
    Flux<Image> findByEventId(UUID eventId); // TODO a modifier
}