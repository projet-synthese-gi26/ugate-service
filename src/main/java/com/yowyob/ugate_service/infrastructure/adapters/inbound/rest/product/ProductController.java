package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.product;

import java.util.List;
import java.util.UUID;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.media.MediaService;
import io.swagger.v3.oas.annotations.Parameter;
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
    @Operation(summary = "Créer un nouveau produit", description = "Permet de créer un nouveau produit avec ses champs individuels.")
    public Mono<ProductResponse> createProduct(
            @Parameter(description = "ID du syndicat") @RequestPart("syndicatId") Mono<String> syndicatId,
            @Parameter(description = "Nom du produit") @RequestPart("name") Mono<String> name,
            @Parameter(description = "Description") @RequestPart("description") Mono<String> description,
            @Parameter(description = "Prix") @RequestPart("price") Mono<String> price,
            @Parameter(description = "SKU") @RequestPart("sku") Mono<String> sku,
            @Parameter(description = "Catégorie") @RequestPart("category") Mono<String> category,
            @Parameter(description = "Stock initial") @RequestPart("stock") Mono<String> stock,
            @Parameter(description = "Est actif") @RequestPart("isActive") Mono<String> isActive,
            @Parameter(description = "Fichier image") @RequestPart(name = "image", required = false) Flux<FilePart> imageFile
    ) {
        // 1. Gérer l'upload de l'image de manière asynchrone
        Mono<List<String>> imagesUrlsMono = mediaService.uploadImage(imageFile != null ? imageFile : Flux.empty());

        // 2. Combiner tous les champs Mono
        return Mono.zip(syndicatId, name, description, price, sku, category, stock, isActive)
                .zipWith(imagesUrlsMono)
                .flatMap(tuple -> {
                    var fields = tuple.getT1(); // Contient les 8 premiers champs
                    List<String> imageUrls = tuple.getT2(); // Contient l'URL de l'image

                    String uploadedUrl = imageUrls.isEmpty() ? null : imageUrls.get(0);

                    // 3. Conversion manuelle des types (String -> UUID, BigDecimal, Integer, Boolean)
                    Product productDomain = new Product(
                            UUID.randomUUID(), // On génère l'ID ici pour l'insertion
                            UUID.fromString(fields.getT1()),      // syndicatId
                            fields.getT2(),                       // name
                            fields.getT3(),                       // description
                            new java.math.BigDecimal(fields.getT4()), // price
                            fields.getT5(),                       // sku
                            fields.getT6(),                       // category
                            Integer.parseInt(fields.getT7()),     // stock
                            uploadedUrl,
                            Boolean.parseBoolean(fields.getT8())  // isActive
                    );

                    return productUseCase.createProduct(productDomain);
                })
                .map(this::mapToResponse);
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
            .map(this::mapToResponse);
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
            .map(this::mapToResponse);
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
            .map(this::mapToResponse);
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
            .map(this::mapToResponse);
    }

    public ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.id(),
                product.syndicatId(),
                product.name(),
                product.description(),
                product.price(),
                product.sku(),
                product.category(),
                product.stock(),
                product.imageUrl(),
                product.isActive()
        );
    }
}
