package com.yowyob.ugate_service.infrastructure.config;

import com.yowyob.ugate_service.application.service.content.CommentService;
import com.yowyob.ugate_service.application.service.content.PublicationService;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.CommentPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import com.yowyob.ugate_service.application.service.content.ReactionService;
import com.yowyob.ugate_service.domain.ports.out.syndicate.ReactionPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.VotePersistencePort;
import com.yowyob.ugate_service.application.service.content.PublicationVoteService;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationVotePersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserEventPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.BranchPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatRepository;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserPersistencePort;
//... (keep existing imports)
import com.yowyob.ugate_service.application.service.content.EventService;
import com.yowyob.ugate_service.domain.ports.out.syndicate.EventPersistencePort;
import com.yowyob.ugate_service.application.service.content.FeedService;
import com.yowyob.ugate_service.domain.ports.out.syndicate.EventPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public PublicationService publicationService(PublicationPersistencePort publicationPersistencePort, MediaPersistencePort mediaPersistencePort, UserGatewayPort userGatewayPort, NotificationPort notificationPort, BranchPersistencePort branchPersistencePort, SyndicatRepository syndicatRepository) {
        return new PublicationService(publicationPersistencePort, mediaPersistencePort, userGatewayPort, notificationPort, branchPersistencePort, syndicatRepository);
    }

    @Bean
    public CommentService commentService(MediaPersistencePort mediaPersistencePort, CommentPersistencePort commentPersistencePort, UserGatewayPort userGatewayPort, NotificationPort notificationPort, PublicationPersistencePort publicationPersistencePort) {
        return new CommentService(mediaPersistencePort, commentPersistencePort, userGatewayPort, notificationPort, publicationPersistencePort);
    }

    @Bean
    public ReactionService reactionService(PublicationService publicationService, ReactionPersistencePort reactionPersistencePort, PublicationPersistencePort publicationPersistencePort, UserGatewayPort userGatewayPort, NotificationPort notificationPort) {
        return new ReactionService(publicationService, reactionPersistencePort, publicationPersistencePort, userGatewayPort, notificationPort);
    }

    @Bean
    public PublicationVoteService publicationVoteService(PublicationVotePersistencePort publicationVotePersistencePort, VotePersistencePort votePersistencePort) {
        return new PublicationVoteService(publicationVotePersistencePort, votePersistencePort);
    }

    @Bean
    public EventService eventService(EventPersistencePort eventPersistencePort, MediaPersistencePort mediaPersistencePort, UserEventPersistencePort userEventPersistencePort, UserGatewayPort userGatewayPort, NotificationPort notificationPort, UserPersistencePort userPersistencePort) {
        return new EventService(eventPersistencePort, mediaPersistencePort, userEventPersistencePort, userGatewayPort, notificationPort, userPersistencePort);
    }

    @Bean
    public FeedService feedService(PublicationPersistencePort publicationPersistencePort, EventPersistencePort eventPersistencePort, UserGatewayPort userGatewayPort, MediaPersistencePort mediaPersistencePort, UserEventPersistencePort userEventPersistencePort) {
        return new FeedService(publicationPersistencePort, eventPersistencePort, userGatewayPort, mediaPersistencePort, userEventPersistencePort);
    }
}
