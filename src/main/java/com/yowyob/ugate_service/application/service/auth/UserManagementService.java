package com.yowyob.ugate_service.application.service.auth;

import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.model.UserEventModel;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.media.FileStoragePort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.UpdateFullProfileRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.MemberResponse;
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
    private final UserGatewayPort userGatewayPort;
    private final FileStoragePort fileStoragePort;
    private final ProfileRepository profileRepository;
    private final ReactiveRedisTemplate<String, ExternalUserInfo> redisTemplate;

    private static final Duration USER_EXISTENCE_CACHE_TTL = Duration.ofHours(24);

    @Transactional
    public Mono<Void> synchronizeUser(UUID userId, String username) {
        String cacheKey = "user_exists:" + userId;

        return redisTemplate.hasKey(cacheKey)
                .doOnError(e -> log.warn(
                        "⚠️ Redis indisponible pendant la vérification du cache utilisateur [{}] : {}",
                        cacheKey,
                        e.getMessage()
                ))
                .onErrorReturn(false)
                .flatMap(existsInCache -> {
                    if (Boolean.TRUE.equals(existsInCache)) {
                        log.debug("Cache hit utilisateur déjà synchronisé : {}", userId);
                        return Mono.empty();
                    }

                    return userRepository.existsById(userId)
                            .flatMap(existsInDb -> {
                                if (Boolean.TRUE.equals(existsInDb)) {
                                    log.debug("Utilisateur {} trouvé en base locale, tentative de mise en cache.", userId);
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
                .set(
                        key,
                        new ExternalUserInfo(null, null, null, null, null, null, null),
                        USER_EXISTENCE_CACHE_TTL
                )
                .doOnSuccess(saved ->
                        log.debug("Présence utilisateur mise en cache [{}] : {}", key, saved)
                )
                .doOnError(e -> log.warn(
                        "⚠️ Impossible de mettre en cache la présence utilisateur [{}] : {}",
                        key,
                        e.getMessage()
                ))
                .onErrorResume(e -> Mono.empty())
                .then();
    }

    @Transactional
    public Mono<MemberResponse> updateFullProfile(UUID userId, UpdateFullProfileRequest request) {
        return userRepository.findById(userId)
                .switchIfEmpty(Mono.error(new RuntimeException("Utilisateur introuvable")))
                .flatMap(user ->
                        profileRepository.findByUserId(userId)
                                .defaultIfEmpty(Profile.createNew(userId, "", ""))
                                .flatMap(profile -> {

                                    Mono<String> imageMono = (request.profileImage() != null)
                                            ? fileStoragePort.uploadFile(request.profileImage(), "avatars")
                                            : Mono.justOrEmpty(profile.profilImageUrl()).defaultIfEmpty("");

                                    return imageMono.flatMap(imgUrl -> {
                                        String finalImageUrl = (imgUrl == null || imgUrl.isBlank())
                                                ? profile.profilImageUrl()
                                                : imgUrl;

                                        String newFirstName = request.firstName() != null
                                                ? request.firstName()
                                                : profile.firstName();

                                        String newLastName = request.lastName() != null
                                                ? request.lastName()
                                                : profile.lastName();

                                        String safeFirstName = newFirstName != null ? newFirstName : "";
                                        String safeLastName = newLastName != null ? newLastName : "";
                                        String fullName = (safeFirstName + " " + safeLastName).trim();

                                        String newPhone = request.phoneNumber() != null
                                                ? request.phoneNumber()
                                                : user.phoneNumber();

                                        User updatedUser = new User(
                                                user.id(),
                                                fullName,
                                                newPhone,
                                                user.email(),
                                                false
                                        );

                                        Profile updatedProfile = new Profile(
                                                profile.id(),
                                                profile.userId(),
                                                finalImageUrl,
                                                newFirstName,
                                                newLastName,
                                                request.birthDate() != null ? request.birthDate() : profile.birth_date(),
                                                request.nationality() != null ? request.nationality() : profile.nationality(),
                                                request.gender() != null ? request.gender() : profile.gender(),
                                                request.language() != null ? request.language() : profile.language(),
                                                profile.createdAt(),
                                                Instant.now(),
                                                false
                                        );

                                        ExternalUserInfo extInfo = new ExternalUserInfo(
                                                userId,
                                                newFirstName,
                                                newLastName,
                                                user.email(),
                                                newPhone,
                                                null,
                                                null
                                        );

                                        return userRepository.save(updatedUser)
                                                .then(profileRepository.save(updatedProfile))
                                                .then(syncExternalProfileBestEffort(extInfo))
                                                .thenReturn(new MemberResponse(
                                                        userId,
                                                        newFirstName,
                                                        newLastName,
                                                        user.email(),
                                                        finalImageUrl,
                                                        null,
                                                        null,
                                                        null
                                                ));
                                    });
                                })
                );
    }

    private Mono<Void> syncExternalProfileBestEffort(ExternalUserInfo extInfo) {
        return userGatewayPort.updateProfile(extInfo)
                .doOnSuccess(updated ->
                        log.info("Profil externe synchronisé avec succès pour l'utilisateur {}", extInfo.id())
                )
                .doOnError(e -> log.warn(
                        "⚠️ Échec de synchronisation externe du profil pour l'utilisateur {} (non bloquant) : {}",
                        extInfo.id(),
                        e.getMessage()
                ))
                .onErrorResume(e -> Mono.empty())
                .then();
    }
}
