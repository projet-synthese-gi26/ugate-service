package com.yowyob.ugate_service.infrastructure.config;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                // 1. On applique la configuration CORS ici
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 2. On désactive CSRF (standard pour les API REST sans état)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                // 3. On définit les règles d'autorisation
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/webjars/**").permitAll()
                        .anyExchange().authenticated()
                )

                // 4. On configure le serveur de ressources JWT
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtDecoder(unsafeJwtDecoder())) // Votre décodeur de dev
                );

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("*"));

        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Headers autorisés
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    /**
     * Méthode passoire temporaire qu'on va rétirer plutard, c'est pour faire face au
     * probleme d'authentifications en attendant d'avoir la clé utilisé par le service d'authentification
     * pour signer ses tokens
     */
    @Bean
    public ReactiveJwtDecoder unsafeJwtDecoder() {
        return token -> Mono.fromCallable(() -> {
            try {
                // On parse le token brut
                SignedJWT parsedToken = SignedJWT.parse(token);
                JWTClaimsSet claims = parsedToken.getJWTClaimsSet();

                // On construit l'objet JWT Spring Security manuellement
                Map<String, Object> headers = new HashMap<>(parsedToken.getHeader().toJSONObject());
                Map<String, Object> claimsMap = new HashMap<>(claims.getClaims());


                Instant issuedAt = claims.getIssueTime() != null ? claims.getIssueTime().toInstant() : Instant.now();
                Instant expiresAt = claims.getExpirationTime() != null ? claims.getExpirationTime().toInstant() : Instant.now().plusSeconds(3600);

                return new Jwt(
                        token,
                        issuedAt,
                        expiresAt,
                        headers,
                        claimsMap
                );
            } catch (Exception e) {
                throw new JwtException("Impossible de parser le token : " + e.getMessage());
            }
        });
    }
}