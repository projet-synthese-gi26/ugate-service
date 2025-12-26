package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("membership_request")
public record MembershipRequest(
        @Id UUID id,
        @Column("user_id") UUID userId,
        @Column("syndicat_id") UUID syndicatId,
        @Column("branch_id") UUID branchId,
        @Column("status") MembershipStatus status,
        String motivation,
        @Column("rejection_reason") String rejectionReason,
        @Column("created_at") Instant createdAt,
        @Column("updated_at") Instant updatedAt
) {
    public enum MembershipStatus { PENDING, APPROVED, REJECTED }
}
