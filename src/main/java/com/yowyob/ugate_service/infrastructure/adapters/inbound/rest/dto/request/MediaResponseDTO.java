package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

public record MediaResponseDTO(
        String id,
        String uri,
        String path,
        String name,
        String mime
) {}
