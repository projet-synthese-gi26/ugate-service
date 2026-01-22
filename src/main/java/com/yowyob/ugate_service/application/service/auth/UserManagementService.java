package com.yowyob.ugate_service.application.service.auth;

import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Profile;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.User;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ProfileRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final ReactiveRedisTemplate<String, ExternalUserInfo> redisTemplate;

    private static final Duration USER_EXISTENCE_CACHE_TTL = Duration.ofHours(24);

    @Transactional
    public Mono<Void> synchronizeUser(UUID userId, String username) {
        String cacheKey = "user_exists:" + userId;
        return redisTemplate.hasKey(cacheKey)
                .flatMap(existsInCache -> {
                    if (Boolean.TRUE.equals(existsInCache)) {
                        return Mono.empty();
                    }
                    return userRepository.existsById(userId)
                            .flatMap(existsInDb -> {
                                if (Boolean.TRUE.equals(existsInDb)) {
                                    return cacheUserExistence(cacheKey);
                                }
                                return createUserAndProfile(userId, username)
                                        .then(cacheUserExistence(cacheKey));
                            });
                });
    }

    private Mono<Void> createUserAndProfile(UUID userId, String username) {
        log.info("Création locale de l'utilisateur {}", userId);
        User newUser = new User(
                userId,
                username,
                null,
                username
        );
        Profile newProfile = Profile.createNew(
                userId,
                "", // FirstName
                ""  // LastName
        );

        return userRepository.save(newUser)
                .then(profileRepository.save(newProfile))
                .doOnSuccess(p -> log.info("Utilisateur {} synchronisé avec succès.", userId))
                .onErrorResume(DuplicateKeyException.class, e -> {
                    log.warn("L'utilisateur {} existe déjà (concurrence), on ignore.", userId);
                    return Mono.empty();
                })
                .then();
    }

    private Mono<Void> cacheUserExistence(String key) {
        return redisTemplate.opsForValue()
                .set(key, new ExternalUserInfo(null, null, null, null, null, null, null), USER_EXISTENCE_CACHE_TTL)
                .then();
    }
}