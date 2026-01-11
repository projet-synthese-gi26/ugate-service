package com.yowyob.ugate_service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .filter(bearerTokenFilter());
    }

    private ExchangeFilterFunction bearerTokenFilter() {
        return (request, next) -> ReactiveSecurityContextHolder.getContext()
                .map(org.springframework.security.core.context.SecurityContext::getAuthentication)
                .filter(auth -> auth.getPrincipal() instanceof Jwt)
                .map(auth -> (Jwt) auth.getPrincipal())
                .map(Jwt::getTokenValue)
                .flatMap(token -> {
                    var newRequest = org.springframework.web.reactive.function.client.ClientRequest.from(request)
                            .header("Authorization", "Bearer " + token)
                            .build();
                    return next.exchange(newRequest);
                })
                .switchIfEmpty(next.exchange(request));
    }
}