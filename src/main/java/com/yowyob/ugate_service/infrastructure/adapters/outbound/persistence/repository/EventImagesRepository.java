package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.EventImages;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EventImagesRepository extends R2dbcRepository<EventImages, UUID> {
    // Note: EventImages does not have a single @Id, so we might need a custom approach or composite key setup
    // For now, extending R2dbcRepository<EventImages, UUID> assumes a singular ID if one were to be defined
    // or relies on Spring Data R2DBC's ability to handle entities without a direct @Id (e.g., using all fields as key)
    // If a composite key is explicitly needed, this interface might need adjustment (e.g., extend ReactiveCrudRepository instead)
    // or use specific methods that take eventId and imageId.

    Mono<EventImages> findByEventIdAndImageId(UUID eventId, UUID imageId);
}
