package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.product;

import java.util.UUID;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.media.MediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;


import com.yowyob.ugate_service.domain.model.Product;
import com.yowyob.ugate_service.domain.ports.in.marketplace.ManageProductUseCase;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.ProductRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ProductResponse;
import com.yowyob.ugate_service.infrastructure.mappers.products.ProductMapper;

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
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Gestion des Produits", description = "API pour la gestion des produits dans le marketplace.")
public class ProductController {
    private final ManageProductUseCase productUseCase;
    private final ProductMapper mapper;
    private final MediaService mediaService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Créer un nouveau produit", 
        description = "Permet de créer un nouveau produit dans le marketplace.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Produit créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public Mono<ProductResponse> createProduct(@ModelAttribute("product") @Valid ProductRequest dto,
                                               @RequestPart("image") Flux<FilePart> imageFile) {
        return mediaService.uploadImage(imageFile)
                .flatMap(urls -> {
                    String uploadedUrl = urls.isEmpty() ? null : urls.get(0);

                    // 2. On transforme le DTO en objet du Domaine avec l'URL
                    Product productDomain = new Product(
                            null,
                            dto.syndicatId(),
                            dto.name(),
                            dto.description(),
                            dto.price(),
                            dto.sku(),
                            dto.category(),
                            dto.stock(),
                            uploadedUrl,
                            dto.isActive()
                    );

                    return productUseCase.createProduct(productDomain);
                })
                .map(mapper::mapToResponse);
    }

    @PatchMapping("/{id}/stock")
    @Operation(
        summary = "Mettre à jour le stock d'un produit", 
        description = "Permet de mettre à jour la quantité en stock d'un produit spécifique.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Stock mis à jour avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "404", description = "Produit non trouvé")
    })
    public Mono<ProductResponse> updateStock(@PathVariable UUID id, @RequestBody int quantity) {
        return productUseCase.updateStock(id, quantity)
            .map(mapper::mapToResponse);
    }


    @PatchMapping("/{id}")
    @Operation(
        summary = "Mettre à jour les détails d'un produit", 
        description = "Permet de mettre à jour les informations d'un produit spécifique.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Produit mis à jour avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "404", description = "Produit non trouvé")
    })
    public Mono<ProductResponse> updateProduct(@PathVariable UUID id, @RequestBody @Valid ProductRequest dto) {
        Product product = new Product(
            id,
            dto.syndicatId(),
            dto.name(),
            dto.description(),
            dto.price(),
            dto.sku(),
            dto.category(),
            dto.stock(),
            null,
            dto.isActive()
        );
        return productUseCase.updateProduct(product)
            .map(mapper::mapToResponse);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Supprimer un produit", 
        description = "Permet de supprimer un produit spécifique du marketplace.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Produit supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Produit non trouvé")
    })
    public Mono<Void> deleteProduct(@PathVariable UUID id) {
        return productUseCase.deleteProduct(id);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Récupérer les détails d'un produit", 
        description = "Permet de récupérer les informations détaillées d'un produit spécifique.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Détails du produit récupérés avec succès"),
        @ApiResponse(responseCode = "404", description = "Produit non trouvé")
    })
    public Mono<ProductResponse> getProductDetails(@PathVariable UUID id) {
        return productUseCase.getProductDetails(id)
            .map(mapper::mapToResponse);
    }

    @GetMapping("/syndicates/{syndicatId}")
    @Operation(
        summary = "Récupérer les produits d'un syndicat", 
        description = "Permet de récupérer tous les produits associés à un syndicat spécifique.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Produits récupérés avec succès"),
        @ApiResponse(responseCode = "404", description = "Syndicat non trouvé")
    })
    public Flux<ProductResponse> getSyndicatProducts(@PathVariable UUID syndicatId) {
       return productUseCase.getSyndicatProducts(syndicatId)
            .map(mapper::mapToResponse);
    }
}
