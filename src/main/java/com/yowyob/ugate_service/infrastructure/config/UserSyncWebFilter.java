package com.yowyob.ugate_service.infrastructure.config;

import com.yowyob.ugate_service.application.service.auth.UserManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserSyncWebFilter implements WebFilter {

    private final UserManagementService userManagementService;

    @Override
    @NonNull
    public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {

        return ReactiveSecurityContextHolder.getContext()
                .map(securityContext -> securityContext.getAuthentication().getPrincipal())
                .filter(principal -> principal instanceof Jwt)
                .cast(Jwt.class)
                .flatMap(jwt -> {
                    try {
                        // Extraction ID (sub)
                        String sub = jwt.getSubject();
                        UUID userId = UUID.fromString(sub);

                        String username = jwt.getClaimAsString("username");
                        if (username == null) {
                            username = "unknown_user";
                        }

                        return userManagementService.synchronizeUser(userId, username);

                    } catch (IllegalArgumentException e) {
                        log.warn("UUID malformé dans le token : {}", e.getMessage());
                        return Mono.empty();
                    } catch (Exception e) {
                        log.error("Erreur synchro user", e);
                        return Mono.empty();
                    }
                })
                // On continue toujours la chaîne, même si pas de token ou erreur
                .then(chain.filter(exchange));
    }
}