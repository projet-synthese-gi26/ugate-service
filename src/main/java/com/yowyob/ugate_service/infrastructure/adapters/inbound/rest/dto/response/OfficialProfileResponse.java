package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;



public record OfficialProfileResponse(
        String id,
        String firstName,
        String lastName,
        String photoUrl,
        VehicleInfo vehicle
) {
    public record VehicleInfo(
            String brand,
            String model,
            String licensePlate
    ) {}
}
