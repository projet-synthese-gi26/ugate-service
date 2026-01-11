package com.yowyob.ugate_service.domain.model;

import java.util.List;
import java.util.UUID;

public record ExternalUserInfo(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        List<String> permissions,
        List<String> roles
)
{}
