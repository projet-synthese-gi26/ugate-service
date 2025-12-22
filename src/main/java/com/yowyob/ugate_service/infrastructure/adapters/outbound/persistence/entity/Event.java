package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Table("event")
public record Event(
        @Id
        Long id,

        @Column("branch_id")
        Long branchId, // FK -> Branch

        String title,
        String description,
        String location,

        LocalDate date,          // Pour la colonne 'date'

        @Column("start_time")
        LocalTime startTime,     // Pour 'start_time'

        @Column("end_time")
        LocalTime endTime,       // Pour 'end_time'

        @CreatedDate
        @Column("created_at")
        Instant createdAt,

        @LastModifiedDate
        @Column("updated_at")
        Instant updatedAt
) {}