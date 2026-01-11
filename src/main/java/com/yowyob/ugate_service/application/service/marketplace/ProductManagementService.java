package com.yowyob.ugate_service.application.service.marketplace;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yowyob.ugate_service.domain.exception.InsufficientStockException;
import com.yowyob.ugate_service.domain.exception.ProductNotFoundException;
import com.yowyob.ugate_service.domain.model.Product;
import com.yowyob.ugate_service.domain.ports.in.marketplace.ManageProductUseCase;
import com.yowyob.ugate_service.domain.ports.out.marketplace.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductManagementService implements ManageProductUseCase {
    private final ProductRepositoryPort productRepositoryPort;

    @Override
    @Transactional 
    public Mono<Product> createProduct(Product product) {

        Product productToSave  = new Product(
            product.id() != null ? product.id() : UUID.randomUUID(),
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

        return productRepositoryPort.saveProduct(productToSave);
    }

    @Override
    public Mono<Product> updateStock(UUID id, Integer quantity) {
        return productRepositoryPort.findById(id)
            .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found")))
            .flatMap(existing -> {
                int newStock = existing.stock() + quantity;
                if (newStock < 0) return Mono.error(new InsufficientStockException("Insufficient stock available"));
                Product productToUpdate = new Product(
                    existing.id(),
                    existing.syndicatId(),
                    existing.name(),
                    existing.description(),
                    existing.price(),
                    existing.sku(),
                    existing.category(),
                    newStock,
                    existing.imageUrl(),
                    existing.isActive()
                );
                return productRepositoryPort.updateProduct(productToUpdate);
            });
    }
    
    @Override
    public Mono<Void> deleteProduct(UUID id) {
        return productRepositoryPort.findById(id)
            .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found")))
            .flatMap(existing -> productRepositoryPort.deleteById(id));
    }

    @Override
    public Mono<Product> updateProduct(Product product) {
        return productRepositoryPort.findById(product.id())
            .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found")))
            .flatMap(existing -> {
                
                Product productToUpdate = new Product(
                    existing.id(),
                    product.syndicatId() == null ? existing.syndicatId() : product.syndicatId(),
                    product.name() == null ? existing.name() :  product.name(),
                    product.description() == null ? existing.description() : product.description(),
                    product.price() == null ? existing.price() : product.price(),
                    product.sku() == null ? existing.sku() : product.sku(),
                    product.category() == null ? existing.category() : product.category(),
                    existing.stock(),
                    product.imageUrl() == null ? existing.imageUrl() : product.imageUrl(),
                    existing.isActive()
                );
                return productRepositoryPort.updateProduct(productToUpdate);
            });
    }

    @Override
    public Flux<Product> getSyndicatProducts(UUID syndicatId) {
        return productRepositoryPort.findBySyndicatId(syndicatId);
    }

    @Override
    public Mono<Product> getProductDetails(UUID id) {
       return productRepositoryPort.findById(id)
            .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found")));
    }
}
