package com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.authentication;

import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class TraMaSysUserAdapter implements UserGatewayPort {

    private final WebClient webClient;
    private final ReactiveRedisTemplate<String, ExternalUserInfo> userRedisTemplate;
    private static final Duration CACHE_TTL = Duration.ofHours(1);

    public TraMaSysUserAdapter(WebClient.Builder builder,
                               @Value("${application.external.auth-service-url}") String authUrl,
                               ReactiveRedisTemplate<String, ExternalUserInfo> userRedisTemplate) {
        this.webClient = builder.baseUrl(authUrl).build();
        this.userRedisTemplate = userRedisTemplate;
    }

    @Override
    public Mono<ExternalUserInfo> registerUser(String email, String firstName, String lastName, String password) {
        var registerRequest = new RegisterRequest(
                email,      // username = email
                password,
                email,
                null,
                firstName,
                lastName,
                "SYNDICAT",
                List.of("CLIENT")
        );

        return webClient.post()
                .uri("/api/auth/register")
                .bodyValue(registerRequest)
                .retrieve()
                .bodyToMono(AuthResponse.class)
                .map(response -> mapToDomain(response.user()))
                .doOnSuccess(user -> log.info("Utilisateur créé sur TraMaSys : {}", user.id()))
                .doOnError(e -> log.error("Erreur registration TraMaSys : {}", e.getMessage()));
    }

    @Override
    public Mono<ExternalUserInfo> findById(UUID id) {
        String cacheKey = "v2_user:" + id;
        return userRedisTemplate.opsForValue().get(cacheKey)
                .cast(ExternalUserInfo.class)
                .switchIfEmpty(webClient.get()
                        .uri("/api/users/{id}", id)
                        .retrieve()
                        .bodyToMono(TraMaSysUserDTO.class)
                        .map(this::mapToDomain)
                        .flatMap(dto -> userRedisTemplate.opsForValue()
                                .set(cacheKey, dto, CACHE_TTL)
                                .thenReturn(dto)));
    }

    @Override
    public Mono<ExternalUserInfo> updateProfile(ExternalUserInfo user) {
        // Implémentation via PUT /api/users/{id}
        var updateRequest = new UserUpdateRequest(user.firstName(), user.lastName(), user.phone());
        return webClient.put()
                .uri("/api/users/{id}", user.id())
                .bodyValue(updateRequest)
                .retrieve()
                .bodyToMono(TraMaSysUserDTO.class)
                .map(this::mapToDomain)
                .doOnSuccess(u -> userRedisTemplate.opsForValue().delete("ext_user:" + u.id()).subscribe());
    }

    @Override
    public Mono<Boolean> existsById(UUID id) {
        return findById(id).map(u -> true).defaultIfEmpty(false);
    }


    private record RegisterRequest(String username, String password, String email, String phone,
                                   String firstName, String lastName, String service, List<String> roles) {}

    private record AuthResponse(TraMaSysUserDTO user) {}

    private record TraMaSysUserDTO(String id, String firstName, String lastName, String email,
                                   String phone, List<String> permissions, List<String> roles) {}

    private record UserUpdateRequest(String firstName, String lastName, String phone) {}

    private ExternalUserInfo mapToDomain(TraMaSysUserDTO dto) {
        return new ExternalUserInfo(
                UUID.fromString(dto.id()),
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                dto.phone(),
                dto.permissions(),
                dto.roles()
        );
    }
}