package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Table("event")
public record Event(
        @Id UUID id,
        @Column("creator_id") UUID creatorId,
        @Column("branch_id") UUID branchId,
        String title,
        String description,
        String location,
        @Column("event_date") LocalDate date,
        @Column("start_time") LocalTime startTime,
        @Column("end_time") LocalTime endTime,
        @CreatedDate @Column("created_at") Instant createdAt,
        @Column("updated_at") Instant updatedAt) {

    public Event(UUID creatorId, UUID branchId, String title, String description, String location,
                 LocalDate date, LocalTime startTime, LocalTime endTime, Instant createdAt, Instant updatedAt) {
        this(null, creatorId, branchId, title, description, location, date, startTime, endTime, createdAt, updatedAt);
    }
}
