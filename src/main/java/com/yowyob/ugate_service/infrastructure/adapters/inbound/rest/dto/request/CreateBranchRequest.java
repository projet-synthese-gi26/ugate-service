package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import org.springframework.http.codec.multipart.FilePart;

@Data
@Schema(description = "Requête de création d'une antenne (branch) d'un syndicat")
public class CreateBranchRequest {

        @Schema(
                description = "Nom de l’antenne",
                example = "Agence Centrale"
        )
        @NotBlank(message = "Le nom est obligatoire")
        private String name;

        @Schema(
                description = "Ville ou quartier de l’antenne",
                example = "Douala – Bonanjo"
        )
        @NotBlank(message = "La localisation est obligatoire")
        private String location;

        @Schema(
                description = "Contact de l’antenne (téléphone ou email)",
                example = "+237 699 99 99 99"
        )
        private String contact;

        @Schema(
                description = "Image de la bannière de l’antenne (JPG / PNG)",
                type = "string",
                format = "binary"
        )
        private FilePart banner;


        @Schema(
                description = "Latitude GPS de l’antenne",
                example = "4.0511"
        )
        @DecimalMin(value = "-90.0", message = "Latitude invalide")
        @DecimalMax(value = "90.0", message = "Latitude invalide")
        private Double latitude;

        @Schema(
                description = "Longitude GPS de l’antenne",
                example = "9.7679"
        )
        @DecimalMin(value = "-180.0", message = "Longitude invalide")
        @DecimalMax(value = "180.0", message = "Longitude invalide")
        private Double longitude;
}