package com.yowyob.ugate_service.application.service.marketplace;

import com.yowyob.ugate_service.domain.model.Purchase;
import com.yowyob.ugate_service.domain.ports.in.marketplace.ManagePurchaseUseCase;
import com.yowyob.ugate_service.domain.ports.out.marketplace.ProductRepositoryPort;
import com.yowyob.ugate_service.domain.ports.out.marketplace.PurchaseRepositoryPort;
import com.yowyob.ugate_service.domain.ports.out.marketplace.ServiceOfferingRepositoryPort;
import com.yowyob.ugate_service.domain.ports.out.payment.PaymentGatewayPort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.ItemType;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.PurchaseEntity;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseService implements ManagePurchaseUseCase {

    private final ProductRepositoryPort productRepo;
    private final ServiceOfferingRepositoryPort serviceRepo;
    private final SyndicatRepository syndicatRepo;
    private final PaymentGatewayPort paymentGatewayPort;
    private final PurchaseRepositoryPort purchaseRepository;

    @Transactional
    @Override
    public Mono<Void> processPurchase(UUID itemId, ItemType itemType, String buyerInfo) {

        // 1. Récupérer l'item pour avoir le prix et l'ID du syndicat
        Mono<ItemData> itemMono = itemType.equals(ItemType.PRODUCT) ?
                productRepo.findById(itemId).map(p -> new ItemData(p.syndicatId(), p.price(), p.name())) :
                serviceRepo.findServiceById(itemId).map(s -> new ItemData(s.syndicatId(), s.price(), s.title()));

        return itemMono
                .switchIfEmpty(Mono.error(new RuntimeException("Item introuvable")))
                .flatMap(item -> syndicatRepo.findById(item.syndicatId)
                        .flatMap(syndicat -> {
                            Purchase purchase = new Purchase(null, syndicat.id(), itemId, itemType, buyerInfo, item.price, Instant.now());

                            return purchaseRepository.save(purchase)
                                    .then(paymentGatewayPort.deductCommission(
                                            syndicat.walletId(),
                                            item.price
                                    ));
                        })
                );
    }

    private record ItemData(UUID syndicatId, BigDecimal price, String name) {}
}
