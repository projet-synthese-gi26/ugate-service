package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Table("publication_vote")
public record PublicationVote(
                @Id UUID id,

                UUID branchId, // FK -> Branch

                String title,
                String description,

                // "closing_at" suggère un timestamp précis
                @Column("closing_at") Instant closingAt,

                String type) {
}
