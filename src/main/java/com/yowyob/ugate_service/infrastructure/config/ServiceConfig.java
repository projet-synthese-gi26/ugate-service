package com.yowyob.ugate_service.infrastructure.config;

import com.yowyob.ugate_service.application.service.content.PublicationService;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public PublicationService publicationService(PublicationPersistencePort publicationPersistencePort, MediaPersistencePort mediaPersistencePort) {
        return new PublicationService(publicationPersistencePort, mediaPersistencePort);
    }
}
