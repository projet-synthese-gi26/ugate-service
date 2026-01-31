package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;



import java.time.Instant;
import org.springframework.http.codec.multipart.FilePart;

public record UpdateFullProfileRequest(
        String firstName,
        String lastName,
        String phoneNumber,
        String nationality,
        String gender,
        String language,
        Instant birthDate,
        FilePart profileImage // Optionnel : pour mettre à jour la photo
) {}