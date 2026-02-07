package com.yowyob.ugate_service.domain.ports.out.notification;

import java.util.List;

import reactor.core.publisher.Mono;

public interface NotificationPort {
    Mono<Void> sendSyndicateInvitation(String email, String syndicateName, String firstName);

    Mono<Void> sendNewEventAlert(List<String> emails, String eventName);

    Mono<Void> sendPublicationCommentAlert(String authorEmail, String publicationTitle, String firstName);

    Mono<Void> sendPublicationReactAlert(String authorEmail, String publicationTitle, String firstName);

    Mono<Void> sendAdminAlertWhenNewPublication(List<String> emails, String publicationTitle, String authorName);

    Mono<Void> sendAdminAlertAcceptEvent(List<String> emails, String eventName, String organizerName);
}