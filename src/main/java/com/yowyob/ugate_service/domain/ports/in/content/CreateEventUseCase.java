package com.yowyob.ugate_service.domain.ports.in.content;

import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public interface CreateEventUseCase {
    Mono<Void> createEvent(UUID creatorId, UUID branchId, String title, String description, LocalDate eventDate,
                                 String location, LocalTime startTime, LocalTime endTime, String[] imagesUrls, String[] videoUrls,
                                 String[] filesUrls);
}
