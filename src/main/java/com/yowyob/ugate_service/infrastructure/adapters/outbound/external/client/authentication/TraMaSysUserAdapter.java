package com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.authentication;


import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Slf4j
@Component
public class TraMaSysUserAdapter implements UserGatewayPort {

    private final WebClient webClient;
    private final ReactiveRedisTemplate<String, Object> redisTemplate;

    private static final Duration CACHE_TTL = Duration.ofHours(1);

    public TraMaSysUserAdapter(WebClient.Builder builder,
                               @Value("${application.external.auth-service-url}") String authUrl,
                               ReactiveRedisTemplate<String, Object> redisTemplate) {
        this.webClient = builder.baseUrl(authUrl).build();
        this.redisTemplate = redisTemplate;
    }



    @Override
    public Mono<User> findById(UUID id) {
        String cacheKey = "user:" + id;

        return redisTemplate.opsForValue().get(cacheKey)
                .cast(User.class)
                .switchIfEmpty(
                        fetchFromApi(id)
                                .flatMap(user ->
                                        // Et on met en cache pour la prochaine fois
                                        redisTemplate.opsForValue().set(cacheKey, user, CACHE_TTL)
                                                .thenReturn(user)
                                )
                );
    }

    private Mono<User> fetchFromApi(UUID id) {
        return webClient.get()
                .uri("/api/users/" + id)
                .retrieve()
                .bodyToMono(TraMaSysUserDTO.class)
                .map(this::mapToDomain)
                .onErrorResume(WebClientResponseException.NotFound.class, e -> {
                    log.warn("Utilisateur {} introuvable dans TraMaSys", id);
                    return Mono.empty();
                })
                .doOnError(e -> log.error("Erreur technique lors de l'appel TraMaSys pour user {}", id, e));
    }


    @Override
    public Mono<User> updateProfile(User user) {
        String cacheKey = "user:" + user.id();

        UserUpdateRequest request = new UserUpdateRequest(
                user.firstName(),
                user.lastName(),
               user.phoneNumber()
        );

        return webClient.put()
                .uri("/api/users/" + user.id())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(TraMaSysUserDTO.class)
                .map(this::mapToDomain)
                .flatMap(updatedUser ->
                        redisTemplate.opsForValue().set(cacheKey, updatedUser, CACHE_TTL)
                                .thenReturn(updatedUser)
                )
                .doOnError(e -> log.error("Erreur lors de la mise à jour du profil {}", user.id(), e));
    }


    @Override
    public Mono<Boolean> existsById(UUID id) {
        return findById(id)
                .map(u -> true)
                .defaultIfEmpty(false);
    }


    record TraMaSysUserDTO(String id, String username, String email, String firstName, String lastName, String phone) {}


    record UserUpdateRequest(String firstName, String lastName, String phone) {}

    private User mapToDomain(TraMaSysUserDTO dto) {
        return new User(
                UUID.fromString(dto.id()),
                null,
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                null,
                null ,
                dto.phone()

        );
    }
}