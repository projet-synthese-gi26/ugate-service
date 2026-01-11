package com.yowyob.ugate_service.infrastructure.mappers.syndicate;

import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Syndicat;
import org.springframework.stereotype.Component;

@Component
public class SyndicateMapper {

    public SyndicateResponse toResponse(Syndicat entity) {
        if (entity == null) return null;

        return new SyndicateResponse(
                entity.id(),
                entity.name(),
                entity.description(),
                entity.domain(),
                entity.isApproved(),
                entity.logoUrl(),
                entity.statusUrl(),
                entity.creatorId(),
                entity.createdAt()
        );
    }
}
