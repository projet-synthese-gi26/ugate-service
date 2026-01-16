package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.http.codec.multipart.FilePart;

@Data
public class CreateBranchRequest {

        @Schema(description = "Nom de la branche", example = "Agence Centrale")
        @NotBlank(message = "Le nom est obligatoire")
        private String name;

        @Schema(description = "Ville ou quartier", example = "Douala")
        @NotBlank(message = "La localisation est obligatoire")
        private String location;

        @Schema(description = "Numéro de téléphone ou email", example = "+237 699999999")
        private String contact;


        @Schema(description = "Image de la bannière (JPG/PNG)", type = "string", format = "binary")
        private FilePart banner;
}