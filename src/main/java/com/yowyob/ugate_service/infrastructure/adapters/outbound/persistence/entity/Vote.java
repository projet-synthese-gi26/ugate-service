package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("vote")
public record Vote(
        @Id
        UUID id,

        @Column("user_id")
        UUID userId, // FK -> User

        @Column("publication_vote_id")
        Long publicationVoteId, // FK -> PublicationVote

        String label // Le choix voté
) {}