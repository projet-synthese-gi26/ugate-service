package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest;

import com.yowyob.ugate_service.application.service.marketplace.PurchaseService;
import com.yowyob.ugate_service.domain.ports.in.marketplace.ManagePurchaseUseCase;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.PurchaseRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/marketplace")
@RequiredArgsConstructor
@Tag(name = "Marketplace Public", description = "Endpoints d'achat ouverts à tous")
public class PurchaseController {

    private final ManagePurchaseUseCase purchaseService;

    @PostMapping("/buy")
    @Operation(summary = "Acheter un produit ou souscrire à un service (Paiement Hors-App)")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> buy(@RequestBody PurchaseRequest request) {
        return purchaseService.processPurchase(
                request.itemId(),
                request.itemType(),
                request.buyerInfo()
        );
    }
}


