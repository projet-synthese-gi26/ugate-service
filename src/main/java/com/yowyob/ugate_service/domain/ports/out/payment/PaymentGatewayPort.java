package com.yowyob.ugate_service.domain.ports.out.payment;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentGatewayPort {
    /**
     * Vérifie si le wallet possède des points (solde > 0)
     */
    Mono<Boolean> canOperate(UUID walletId);

    Mono<UUID> createWallet(UUID ownerId, String ownerName);

    Mono<Void> deductCommission(UUID walletId, BigDecimal totalAmount);
}