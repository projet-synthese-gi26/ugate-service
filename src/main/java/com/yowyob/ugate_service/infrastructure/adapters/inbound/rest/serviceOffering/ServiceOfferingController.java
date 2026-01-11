package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.serviceOffering;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.yowyob.ugate_service.domain.model.SyndicatService;
import com.yowyob.ugate_service.domain.ports.in.marketplace.ManageServiceUseCase;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.ServiceOfferingRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ServiceOfferingResponse;
import com.yowyob.ugate_service.infrastructure.mappers.serviceOffering.ServiceOfferingMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/service-offerings")
@RequiredArgsConstructor
@Tag(name = "Gestion des services", description = "API pour la gestion des services dans le marketplace.")
public class ServiceOfferingController {

    private final ManageServiceUseCase serviceOfferingUseCase;
    private final ServiceOfferingMapper mapper;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Créer un nouveau service", 
        description = "Permet de créer un nouveau service dans le marketplace.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Service créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public Mono<ServiceOfferingResponse> createServiceOffering(@RequestBody @Valid Mono<ServiceOfferingRequest> dto) {
        return dto
            .map(mapper::mapToDomain)
            .flatMap(serviceOfferingUseCase::createService)
            .map(mapper::mapToResponse);
    }

    @PatchMapping("/{id}")
    @Operation(
        summary = "Mettre à jour un service existant", 
        description = "Permet de mettre à jour les détails d'un service existant dans le marketplace.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Service mis à jour avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "404", description = "Service non trouvé")
    })
    public Mono<ServiceOfferingResponse> updateServiceOffering(@PathVariable UUID id, @RequestBody @Valid ServiceOfferingRequest dto) {
        SyndicatService serviceToUpdate = new SyndicatService(
            id,
            dto.title(),
            dto.description(),
            dto.price(),
            dto.features(),
            dto.isActive()
        );
        return serviceOfferingUseCase.updateService(serviceToUpdate)
            .map(mapper::mapToResponse);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Supprimer un service", 
        description = "Permet de supprimer un service spécifique du marketplace.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Service supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Service non trouvé")
    })
    public Mono<Void> deleteServiceOffering(@PathVariable UUID id) {
        return serviceOfferingUseCase.deleteService(id);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtenir les détails d'un service", 
        description = "Permet de récupérer les détails d'un service spécifique dans le marketplace.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Détails du service récupérés avec succès"),
        @ApiResponse(responseCode = "404", description = "Service non trouvé")
    })
    public Mono<ServiceOfferingResponse> getServiceOfferingDetails(@PathVariable UUID id) {
        return serviceOfferingUseCase.getServiceDetails(id)
            .map(mapper::mapToResponse);
    }

    @GetMapping("/active")
    @Operation(
        summary = "Lister tous les services actifs", 
        description = "Permet de récupérer une liste de tous les services actifs dans le marketplace.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Liste des services actifs récupérée avec succès")
    })
    public Flux<ServiceOfferingResponse> getAllActiveServiceOfferings() {
        return serviceOfferingUseCase.getAllActiveServices()
            .map(mapper::mapToResponse);
    }
}
