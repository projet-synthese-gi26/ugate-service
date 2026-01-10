package com.yowyob.ugate_service.infrastructure.config;

import com.yowyob.ugate_service.application.service.content.CommentService;
import com.yowyob.ugate_service.application.service.content.PublicationService;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.CommentPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import com.yowyob.ugate_service.application.service.content.ReactionService;
import com.yowyob.ugate_service.domain.ports.out.syndicate.ReactionPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserEventPersistencePort;
import com.yowyob.ugate_service.application.service.content.EventService;
import com.yowyob.ugate_service.domain.ports.out.syndicate.EventPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public PublicationService publicationService(PublicationPersistencePort publicationPersistencePort, MediaPersistencePort mediaPersistencePort, UserGatewayPort userGatewayPort) {
        return new PublicationService(publicationPersistencePort, mediaPersistencePort, userGatewayPort);
    }

    @Bean
    public CommentService commentService(MediaPersistencePort mediaPersistencePort, CommentPersistencePort commentPersistencePort, UserGatewayPort userGatewayPort) {
        return new CommentService(mediaPersistencePort, commentPersistencePort, userGatewayPort);
    }

    @Bean
    public ReactionService reactionService(PublicationService publicationService, ReactionPersistencePort reactionPersistencePort) {
        return new ReactionService(publicationService, reactionPersistencePort);
    }

    @Bean
    public EventService eventService(EventPersistencePort eventPersistencePort, MediaPersistencePort mediaPersistencePort, UserEventPersistencePort userEventPersistencePort, UserGatewayPort userGatewayPort) {
        return new EventService(eventPersistencePort, mediaPersistencePort, userEventPersistencePort, userGatewayPort);
    }
}
