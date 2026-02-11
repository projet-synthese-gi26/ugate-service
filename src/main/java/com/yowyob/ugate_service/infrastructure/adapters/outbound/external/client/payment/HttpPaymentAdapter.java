package com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.payment;

import com.yowyob.ugate_service.domain.ports.out.payment.PaymentGatewayPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class HttpPaymentAdapter implements PaymentGatewayPort {

    private final WebClient webClient;

    public HttpPaymentAdapter(WebClient.Builder builder,
                              @Value("${application.external.payment-service-url}") String paymentUrl) {
        this.webClient = builder.baseUrl(paymentUrl).build();
    }

    @Override
    public Mono<UUID> createWallet(UUID ownerId, String ownerName) {
        // DTO pour correspondre à ce que ton Payment-Service attend
        Map<String, Object> request = Map.of(
                "ownerId", ownerId,
                "ownerName", ownerName
        );

        return webClient.post()
                .uri("/wallets")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> UUID.fromString(response.get("id").toString()))
                .doOnError(e -> log.error("Erreur création wallet pour {}: {}", ownerName, e.getMessage()));
    }

    @Override
    public Mono<Boolean> canOperate(UUID walletId) {
        if (walletId == null) return Mono.just(false);

        return webClient.get()
                .uri("/wallets/{id}/can-operate", walletId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorReturn(false); // Sécurité : si le service paiement est down, on cache par défaut
    }


    @Override
    public Mono<Void> deductCommission(UUID walletId, BigDecimal totalAmount) {
        if (walletId == null) return Mono.error(new RuntimeException("Syndicat sans wallet configuré"));

        Map<String, Object> request = Map.of(
                "walletId", walletId,
                "amount", totalAmount, // Le service paiement calculera les 10%
                "type", "PAYMENT"
        );

        return webClient.post()
                .uri("/transactions/payment")
                .bodyValue(request)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class).flatMap(error ->
                                Mono.error(new RuntimeException("Erreur Paiement: " + error))))
                .toBodilessEntity()
                .then();
    }
}