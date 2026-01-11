package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence;

import java.util.UUID;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.yowyob.ugate_service.domain.model.Product;
import com.yowyob.ugate_service.domain.ports.out.marketplace.ProductRepositoryPort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatProductRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatProductRow;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PostgresSyndicatProductAdapter implements ProductRepositoryPort {

    private final SyndicatProductRepository syndicatProductRepository;
    private final DatabaseClient databaseClient;

    @Override
    public Mono<Product> findById(UUID id) {
        return syndicatProductRepository.findFullById(id)
                .map(this::mapToDomain);
    }

    @Override
    public Flux<Product> findBySyndicatId(UUID syndicatId) {
        return syndicatProductRepository.findFullBySyndicatId(syndicatId)
                .map(this::mapToDomain);
    }

    @Override
    @Transactional // Transactionnel car on touche à deux tables
    public Mono<Product> saveProduct(Product product) {
        // 1. On insère/update dans la table commune (abstract_products)
        return databaseClient.sql("""
                INSERT INTO abstract_products (id, organization_id, name, description, is_active, standard_price)
                VALUES (:id, :orgId, :name, :desc, :active, :price)
                ON CONFLICT (id) DO UPDATE SET 
                    name = EXCLUDED.name, 
                    description = EXCLUDED.description,
                    standard_price = EXCLUDED.standard_price
                """)
                .bind("id", product.id())
                .bind("orgId", product.syndicatId())
                .bind("name", product.name())
                .bind("desc", product.description())
                .bind("active", product.isActive())
                .bind("price", product.price())
                .then()
                // 2. On insère/update dans TA table spécifique (syndicat_products)
                .then(databaseClient.sql("""
                    INSERT INTO syndicat_products (product_id, sku, category, stock)
                    VALUES (:id, :sku, :cat, :stock)
                    ON CONFLICT (product_id) DO UPDATE SET 
                        sku = EXCLUDED.sku, 
                        category = EXCLUDED.category, 
                        stock = EXCLUDED.stock
                    """)
                    .bind("id", product.id())
                    .bind("sku", product.sku())
                    .bind("cat", product.category())
                    .bind("stock", product.stock())
                    .then())
                .thenReturn(product);
    }

    @Override
    public Mono<Product> updateProduct(Product product) {
        // En PostgreSQL, l'UPSERT (ON CONFLICT) gère l'update de la même manière que le save
        return saveProduct(product);
    }

    @Override
    @Transactional
    public Mono<Void> deleteById(UUID id) {
        // On supprime d'abord l'extension pour respecter les FK, puis le parent
        return databaseClient.sql("DELETE FROM syndicat_products WHERE product_id = :id")
                .bind("id", id)
                .then()
                .then(databaseClient.sql("DELETE FROM abstract_products WHERE id = :id")
                        .bind("id", id)
                        .then());
    }

    // --- MAPPER INTERNE (PROPRE À L'INFRA) ---
    private Product mapToDomain(SyndicatProductRow row) {
        return new Product(
            row.id(),
            row.organizationId(),
            row.name(),
            row.description(),
            row.standardPrice(),
            row.sku(),
            row.category(),
            row.stock(),
            row.productUrls(), // mappé sur imageUrl dans le domaine
            row.isActive()
        );
    }
}