package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import org.springframework.http.codec.multipart.FilePart;

public record UpdateSyndicateFullRequest(
        String name,
        String description,
        String domain,
        // Fichiers optionnels (s'ils sont null, on garde l'ancien)
        FilePart logo,
        FilePart charte,
        FilePart statusDoc
) {}