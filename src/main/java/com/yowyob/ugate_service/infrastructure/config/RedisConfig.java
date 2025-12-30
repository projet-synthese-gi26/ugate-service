package com.yowyob.ugate_service.infrastructure.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ComplianceResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {


    @Primary
    @Bean
    public ReactiveRedisTemplate<String, ExternalUserInfo> externalUserRedisTemplate(ReactiveRedisConnectionFactory factory) {
        return createTemplate(factory, ExternalUserInfo.class);
    }

    @Bean
    public ReactiveRedisTemplate<String, ComplianceResponse> complianceRedisTemplate(ReactiveRedisConnectionFactory factory) {
        return createTemplate(factory, ComplianceResponse.class);
    }

    private <T> ReactiveRedisTemplate<String, T> createTemplate(ReactiveRedisConnectionFactory factory, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());


        Jackson2JsonRedisSerializer<T> serializer = new Jackson2JsonRedisSerializer<>(mapper, clazz);

        RedisSerializationContext<String, T> context = RedisSerializationContext
                .<String, T>newSerializationContext(new StringRedisSerializer())
                .value(serializer)
                .build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}