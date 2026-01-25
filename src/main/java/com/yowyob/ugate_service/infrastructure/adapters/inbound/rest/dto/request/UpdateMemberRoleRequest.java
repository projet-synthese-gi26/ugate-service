package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.enumeration.RoleTypeEnum;

public record UpdateMemberRoleRequest(
        RoleTypeEnum newRole
) {}