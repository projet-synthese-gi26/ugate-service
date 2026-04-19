package com.yowyob.ugate_service.infrastructure.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
public class ReactiveCacheService<K, V> {

    private final ReactiveRedisTemplate<K, V> redis;

    public ReactiveCacheService(ReactiveRedisTemplate<K, V> redis) {
        this.redis = redis;
    }

    public Mono<V> get(K key) {
        return redis.opsForValue()
                .get(key)
                .onErrorResume(e -> {
                    log.warn("⚠️ Redis GET échoué pour key={} : {}", key, e.getMessage());
                    return Mono.empty();
                });
    }

    public Mono<Void> set(K key, V value, Duration ttl) {
        return redis.opsForValue()
                .set(key, value, ttl)
                .doOnError(e ->
                        log.warn("⚠️ Redis SET échoué pour key={} : {}", key, e.getMessage())
                )
                .onErrorResume(e -> Mono.empty())
                .then();
    }

    public Mono<Void> delete(K key) {
        return redis.opsForValue()
                .delete(key)
                .onErrorResume(e -> Mono.empty())
                .then();
    }
}