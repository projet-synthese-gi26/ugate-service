package com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.notification;

import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;


@Slf4j
@Component
public class HttpNotificationAdapter implements NotificationPort {

    private final WebClient webClient;
    private final String serviceToken;
    private final Integer inviteTemplateId;

    public HttpNotificationAdapter(WebClient.Builder builder,
                                   @Value("${application.external.notification-service-url}") String notificationUrl,
                                   @Value("${application.external.notification-service-token}") String serviceToken,
                                   @Value("${application.external.notification-invite-template-id}") Integer inviteTemplateId) {
        this.webClient = builder.baseUrl(notificationUrl).build();
        this.serviceToken = serviceToken;
        this.inviteTemplateId = inviteTemplateId;
    }

    @Override
    public Mono<Void> sendSyndicateInvitation(String email, String syndicateName, String firstName) {

        Map<String, Object> requestBody = Map.of(
                "notificationType", "EMAIL",
                "templateId", inviteTemplateId,
                "to", List.of(email),
                "data", Map.of(
                        "syndicateName", syndicateName,
                        "firstName", firstName,
                        "loginUrl", "https://ugate.yowyob.com/reset-password"
                )
        );

        return webClient.post()
                .uri("/api/v1/notifications/send")
                .header("X-Service-Token", serviceToken)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(status -> status.isError(), response ->
                        response.bodyToMono(String.class)
                                .flatMap(error -> Mono.error(new RuntimeException("Notification Error: " + error)))
                )
                .toBodilessEntity()
                .doOnSuccess(v -> log.info("Invitation envoyée avec succès à {}", email))
                .doOnError(e -> log.error("Échec de l'envoi de l'invitation à {}: {}", email, e.getMessage()))
                .then();
    }
}