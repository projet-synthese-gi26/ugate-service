package com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.authentication;

import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Slf4j
@Component
public class TraMaSysUserAdapter implements UserGatewayPort {

    private final WebClient webClient;
    private final ReactiveRedisTemplate<String, ExternalUserInfo> userRedisTemplate;

    private static final Duration CACHE_TTL = Duration.ofHours(1);
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    public TraMaSysUserAdapter(WebClient.Builder builder,
                               @Value("${application.external.auth-service-url}") String authUrl,
                               ReactiveRedisTemplate<String, ExternalUserInfo> userRedisTemplate) {
        this.webClient = builder.baseUrl(authUrl).build();
        this.userRedisTemplate = userRedisTemplate;
    }

    @Override
    public Mono<ExternalUserInfo> registerUser(String email, String firstName, String lastName, String password) {
        RegisterRequest registerData = new RegisterRequest(
                email,
                password,
                email,
                null,
                firstName,
                lastName,
                "SYNDICAT",
                List.of("CLIENT")
        );

        MultipartBodyBuilder multipartBuilder = new MultipartBodyBuilder();
        multipartBuilder.part("data", registerData, MediaType.APPLICATION_JSON);

        return webClient.post()
                .uri("/api/auth/register")
                .body(BodyInserters.fromMultipartData(multipartBuilder.build()))
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(AuthResponse.class);
                    }

                    return response.bodyToMono(String.class)
                            .defaultIfEmpty("")
                            .flatMap(body -> {
                                log.error(
                                        "❌ Erreur TraMaSys register - email={}, status={}, body={}",
                                        email,
                                        response.statusCode(),
                                        body
                                );
                                return Mono.error(new RuntimeException("Erreur Auth-Service: " + body));
                            });
                })
                .timeout(REQUEST_TIMEOUT)
                .onErrorMap(
                        TimeoutException.class,
                        e -> new RuntimeException("Auth-Service timeout pendant la création de l'utilisateur", e)
                )
                .map(AuthResponse::user)
                .map(this::mapToDomain)
                .flatMap(user ->
                        cacheUserBestEffort(buildUserCacheKey(user.id()), user)
                                .thenReturn(user)
                )
                .doOnSuccess(user ->
                        log.info("✅ Utilisateur créé avec succès sur TraMaSys : {}", user.id())
                );
    }

    @Override
    public Mono<ExternalUserInfo> findById(UUID id) {
        String cacheKey = buildUserCacheKey(id);

        return readUserCacheBestEffort(cacheKey)
                .switchIfEmpty(
                        fetchUserFromRemote(id)
                                .flatMap(user ->
                                        cacheUserBestEffort(cacheKey, user)
                                                .thenReturn(user)
                                )
                );
    }

    @Override
    public Mono<ExternalUserInfo> updateProfile(ExternalUserInfo user) {
        String cacheKey = buildUserCacheKey(user.id());
        UserUpdateRequest updateRequest = new UserUpdateRequest(
                user.firstName(),
                user.lastName(),
                user.phone()
        );

        return webClient.put()
                .uri("/api/users/{id}", user.id())
                .bodyValue(updateRequest)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(TraMaSysUserDTO.class)
                                .map(this::mapToDomain);
                    }

                    return response.bodyToMono(String.class)
                            .defaultIfEmpty("")
                            .flatMap(body -> {
                                log.warn(
                                        "⚠️ Échec updateProfile TraMaSys - userId={}, status={}, body={} (fallback local non bloquant)",
                                        user.id(),
                                        response.statusCode(),
                                        body
                                );
                                return Mono.just(user);
                            });
                })
                .timeout(REQUEST_TIMEOUT)
                .onErrorResume(TimeoutException.class, e -> {
                    log.warn(
                            "⚠️ Timeout updateProfile TraMaSys pour l'utilisateur {} (fallback local non bloquant) : {}",
                            user.id(),
                            e.getMessage()
                    );
                    return Mono.just(user);
                })
                .onErrorResume(e -> {
                    log.warn(
                            "⚠️ Erreur updateProfile TraMaSys pour l'utilisateur {} (fallback local non bloquant) : {}",
                            user.id(),
                            e.getMessage()
                    );
                    return Mono.just(user);
                })
                .flatMap(updatedUser ->
                        evictUserCacheBestEffort(cacheKey)
                                .thenReturn(updatedUser)
                );
    }

    @Override
    public Mono<Boolean> existsById(UUID id) {
        return findById(id)
                .map(u -> true)
                .defaultIfEmpty(false);
    }

    private Mono<ExternalUserInfo> readUserCacheBestEffort(String cacheKey) {
        return userRedisTemplate.opsForValue()
                .get(cacheKey)
                .doOnSuccess(user -> {
                    if (user != null) {
                        log.debug("✅ Utilisateur trouvé en cache [{}]", cacheKey);
                    }
                })
                .doOnError(e -> log.warn(
                        "⚠️ Redis indisponible pendant lecture user cache [{}] : {}",
                        cacheKey,
                        e.getMessage()
                ))
                .onErrorResume(e -> Mono.empty());
    }

    private Mono<Void> cacheUserBestEffort(String cacheKey, ExternalUserInfo user) {
        return userRedisTemplate.opsForValue()
                .set(cacheKey, user, CACHE_TTL)
                .doOnSuccess(saved ->
                        log.debug("✅ Utilisateur mis en cache [{}] : {}", cacheKey, saved)
                )
                .doOnError(e -> log.warn(
                        "⚠️ Impossible de mettre l'utilisateur en cache [{}] : {}",
                        cacheKey,
                        e.getMessage()
                ))
                .onErrorResume(e -> Mono.empty())
                .then();
    }

    private Mono<Void> evictUserCacheBestEffort(String cacheKey) {
        return userRedisTemplate.opsForValue()
                .delete(cacheKey)
                .doOnSuccess(deleted ->
                        log.debug("✅ Cache utilisateur invalidé [{}] : {}", cacheKey, deleted)
                )
                .doOnError(e -> log.warn(
                        "⚠️ Impossible d'invalider le cache utilisateur [{}] : {}",
                        cacheKey,
                        e.getMessage()
                ))
                .onErrorResume(e -> Mono.empty())
                .then();
    }

    private Mono<ExternalUserInfo> fetchUserFromRemote(UUID id) {
        return webClient.get()
                .uri("/api/users/{id}", id)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(TraMaSysUserDTO.class)
                                .map(this::mapToDomain);
                    }

                    if (response.statusCode().value() == 404) {
                        log.debug("ℹ️ Utilisateur {} introuvable sur TraMaSys", id);
                        return Mono.<ExternalUserInfo>empty();
                    }

                    return response.bodyToMono(String.class)
                            .defaultIfEmpty("")
                            .flatMap(body -> {
                                log.warn(
                                        "⚠️ Erreur TraMaSys findById - userId={}, status={}, body={}",
                                        id,
                                        response.statusCode(),
                                        body
                                );
                                return Mono.<ExternalUserInfo>empty();
                            });
                })
                .timeout(REQUEST_TIMEOUT)
                .onErrorResume(TimeoutException.class, e -> {
                    log.warn(
                            "⚠️ Timeout TraMaSys findById pour user {} : {}",
                            id,
                            e.getMessage()
                    );
                    return Mono.empty();
                })
                .onErrorResume(e -> {
                    log.warn(
                            "⚠️ Erreur TraMaSys findById pour user {} : {}",
                            id,
                            e.getMessage()
                    );
                    return Mono.empty();
                });
    }

    private String buildUserCacheKey(UUID id) {
        return "v2_user:" + id;
    }

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

    private record RegisterRequest(
            String username,
            String password,
            String email,
            String phone,
            String firstName,
            String lastName,
            String service,
            List<String> roles
    ) {}

    private record AuthResponse(TraMaSysUserDTO user) {}

    private record TraMaSysUserDTO(
            String id,
            String firstName,
            String lastName,
            String email,
            String phone,
            List<String> permissions,
            List<String> roles
    ) {}

    private record UserUpdateRequest(
            String firstName,
            String lastName,
            String phone
    ) {}
}