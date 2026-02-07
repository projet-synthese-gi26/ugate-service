package com.yowyob.ugate_service.infrastructure.mappers.syndicate;

import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Branch;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Syndicat;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.SyndicatMember;
import org.springframework.stereotype.Component;

@Component
public class SyndicateMapper {

    public SyndicateResponse toResponse(Syndicat entity, Branch userBranch, SyndicatMember member) {
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
                entity.createdAt(),
                entity.isActive(),

                // Info Branche
                userBranch != null ? userBranch.id() : null,
                userBranch != null ? userBranch.name() : null,

                member != null ? member.role() : null
        );
    }


    public SyndicateResponse toResponse(Syndicat entity) {
        return toResponse(entity, null, null);
    }

    public SyndicateResponse toResponse(Syndicat entity, Branch userBranch) {
        return toResponse(entity, userBranch, null);
    }
}
