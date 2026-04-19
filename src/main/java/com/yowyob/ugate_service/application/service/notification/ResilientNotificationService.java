package com.yowyob.ugate_service.application.service.notification;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class ResilientNotificationService {

    @CircuitBreaker(name = "notificationCB", fallbackMethod = "fallback")
    public Mono<Void> execute(Mono<Void> action, String context) {
        return action
                .doOnSuccess(v -> log.info("📧 Notification envoyée [{}]", context))
                .onErrorResume(e -> {
                    log.warn("⚠️ Notification échouée [{}] : {}", context, e.getMessage());
                    return Mono.empty();
                });
    }

    private Mono<Void> fallback(Throwable t, String context) {
        log.warn("⛔ Circuit breaker notification ouvert [{}] : {}", context, t.getMessage());
        return Mono.empty();
    }
}