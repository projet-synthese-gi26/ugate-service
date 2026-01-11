package com.yowyob.ugate_service.domain.ports.out.notification;

import reactor.core.publisher.Mono;

public interface NotificationPort {
    Mono<Void> sendSyndicateInvitation(String email, String syndicateName, String firstName);
}