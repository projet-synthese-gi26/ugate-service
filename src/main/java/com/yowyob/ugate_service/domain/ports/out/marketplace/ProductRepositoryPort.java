package com.yowyob.ugate_service.domain.ports.out.marketplace;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.UUID;

import com.yowyob.ugate_service.domain.model.Product;

public interface ProductRepositoryPort {
    Mono<Product> saveProduct(Product product);
    Mono<Product> findById(UUID id);
    Mono<Void> deleteById( UUID id);
    Mono<Product> updateProduct(Product product);
    Flux<Product> findBySyndicatId(UUID syndicatId);
    
} 