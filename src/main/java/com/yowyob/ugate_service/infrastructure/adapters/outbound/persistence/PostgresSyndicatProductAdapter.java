package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence;

import java.util.Optional;
import java.util.UUID;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.yowyob.ugate_service.domain.model.Product;
import com.yowyob.ugate_service.domain.ports.out.marketplace.ProductRepositoryPort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.ProductEntity;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PostgresSyndicatProductAdapter implements ProductRepositoryPort {

    private final ProductRepository syndicatProductRepository;

    @Override
    public Mono<Product> findById(UUID id) {
        return syndicatProductRepository.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public Flux<Product> findBySyndicatId(UUID syndicatId) {
        return syndicatProductRepository.findBySyndicatId(syndicatId)
                .map(this::mapToDomain);
    }

    @Override
    public Mono<Product> saveProduct(Product product) {
       
        ProductEntity entity = new ProductEntity(
            product.id(),
            product.syndicatId(),
            product.name(),
            product.description(),
            product.price(),
            product.sku(),
            product.category(),
            product.stock(),
            product.imageUrl(),
            product.isActive(),
            true
        );
        return syndicatProductRepository.save(entity)
                .map(this::mapToDomain);
    }

    @Override
    public Mono<Product> updateProduct(Product product) {
        return syndicatProductRepository.findById(product.id())
        .switchIfEmpty(Mono.error(new RuntimeException("product not found")))

        .map(entity -> {

            Optional.ofNullable(product.syndicatId()).ifPresent(entity::setSyndicatId);
            Optional.ofNullable(product.name()).ifPresent(entity::setName);
            Optional.ofNullable(product.description()).ifPresent(entity::setDescription);
            Optional.ofNullable(product.price()).ifPresent(entity::setPrice);
            Optional.ofNullable(product.sku()).ifPresent(entity::setSku);
            Optional.ofNullable(product.category()).ifPresent(entity::setCategory);
            Optional.ofNullable(product.stock()).ifPresent(entity::setStock);
            Optional.ofNullable(product.imageUrl()).ifPresent(entity::setImageUrl);
            Optional.ofNullable(product.isActive()).ifPresent(entity::setIsActive);
            entity.setNew(false);
        
            return entity;
        })
        
        .flatMap(syndicatProductRepository::save)
        .map(this::mapToDomain);
        

    }

    @Override
    @Transactional
    public Mono<Void> deleteById(UUID id) {

        syndicatProductRepository.findById(id)
        .switchIfEmpty(Mono.error(new RuntimeException("Product not found")));

        return syndicatProductRepository.deleteById(id);
    }

    // --- MAPPER INTERNE (PROPRE À L'INFRA) ---
    private Product mapToDomain(ProductEntity row) {
        return new Product(
            row.getId(),
            row.getSyndicatId(),
            row.getName(),
            row.getDescription(),
            row.getPrice(),
            row.getSku(),
            row.getCategory(),
            row.getStock(),
            row.getImageUrl(),
            row.getIsActive()
        );
    }
}