package com.yowyob.ugate_service.domain.ports.in.marketplace;
import java.util.UUID;

import com.yowyob.ugate_service.domain.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ManageProductUseCase {
    Mono<Product> createProduct(Product product);
    Mono<Product> updateProduct(Product product);
    Mono<Product> updateStock(UUID id, Integer quantity);
    Mono<Void> deleteProduct(UUID id);
    Flux<Product> getSyndicatProducts(UUID syndicatId);
    Mono<Product> getProductDetails(UUID id);
}