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

        private final Integer eventAlertTemplateId;
        private final Integer publicationCommentAlertTemplateId;
        private final Integer publicationReactAlertTemplateId;
        private final Integer adminAlertWhenNewPublicationTemplateId;
        private final Integer adminAlertAcceptEventTemplateId;

        public HttpNotificationAdapter(WebClient.Builder builder,
                        @Value("${application.external.notification-service-url}") String notificationUrl,
                        @Value("${application.external.notification-service-token}") String serviceToken,
                        @Value("${application.external.notification-invite-template-id}") Integer inviteTemplateId,
                        @Value("${application.external.notification-new-event-alert-templatet-id}") Integer eventAlertTemplateId,
                        @Value("${application.external.notification-publication-comment-alert-template-id}") Integer publicationCommentAlertTemplate,
                        @Value("${application.external.notification-publication-react-alert-template-id}") Integer publicationReactAlertTemplateId,
                        @Value("${application.external.notification-admin-alert-when-new-publication-template-id}") Integer adminAlertWhenNewPublicationTemplateId,
                        @Value("${application.external.notification-admin-alert-accept-event-template-id}") Integer adminAlertAcceptEventTemplateId) {
                this.webClient = builder.baseUrl(notificationUrl).build();
                this.serviceToken = serviceToken;
                this.inviteTemplateId = inviteTemplateId;
                this.eventAlertTemplateId = eventAlertTemplateId;
                this.publicationCommentAlertTemplateId = publicationCommentAlertTemplate;
                this.publicationReactAlertTemplateId = publicationReactAlertTemplateId;
                this.adminAlertWhenNewPublicationTemplateId = adminAlertWhenNewPublicationTemplateId;
                this.adminAlertAcceptEventTemplateId = adminAlertAcceptEventTemplateId;
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
                                                "loginUrl", "https://ugate.yowyob.com/reset-password"));

                return webClient.post()
                                .uri("/api/v1/notifications/send")
                                .header("X-Service-Token", serviceToken)
                                .bodyValue(requestBody)
                                .retrieve()
                                .onStatus(status -> status.isError(), response -> response.bodyToMono(String.class)
                                                .flatMap(error -> Mono.error(
                                                                new RuntimeException("Notification Error: " + error))))
                                .toBodilessEntity()
                                .doOnSuccess(v -> log.info("Invitation envoyée avec succès à {}", email))
                                .doOnError(e -> log.error("Échec de l'envoi de l'invitation à {}: {}", email,
                                                e.getMessage()))
                                .then();
        }

        private Mono<Void> sendEmailNotification(Integer templateId, List<String> recipients,
                        Map<String, Object> data) {
                Map<String, Object> requestBody = Map.of(
                                "notificationType", "EMAIL",
                                "templateId", templateId,
                                "to", recipients,
                                "data", data);

                return webClient.post()
                                .uri("/api/v1/notifications/send")
                                .header("X-Service-Token", serviceToken)
                                .bodyValue(requestBody)
                                .retrieve()
                                .onStatus(status -> status.isError(), response -> response.bodyToMono(String.class)
                                                .flatMap(error -> Mono.error(
                                                                new RuntimeException("Notification Error: " + error))))
                                .toBodilessEntity()
                                .doOnSuccess(v -> log.info("Notification envoyée avec succès à {}", recipients))
                                .doOnError(e -> log.error("Échec de l'envoi de la notification à {}: {}", recipients,
                                                e.getMessage()))
                                .then();
        }

        @Override
        public Mono<Void> sendNewEventAlert(List<String> emails, String eventName) {
                Map<String, Object> data = Map.of(
                                "eventName", eventName,
                                "eventUrl",
                                "https://ugate.yowyob.com/events/" + eventName.replaceAll(" ", "-").toLowerCase());

                return sendEmailNotification(eventAlertTemplateId, emails, data);
        }

        @Override
        public Mono<Void> sendPublicationCommentAlert(String authorEmail, String publicationTitle, String firstName) {
                Map<String, Object> data = Map.of(
                                "publicationTitle", publicationTitle,
                                "firstName", firstName,
                                "publicationUrl", "https://ugate.yowyob.com/publications/"
                                                + publicationTitle.replaceAll(" ", "-").toLowerCase());

                return sendEmailNotification(publicationCommentAlertTemplateId, List.of(authorEmail), data);
        }

        @Override
        public Mono<Void> sendPublicationReactAlert(String authorEmail, String publicationTitle, String firstName) {
                Map<String, Object> data = Map.of(
                                "publicationTitle", publicationTitle,
                                "firstName", firstName,
                                "publicationUrl", "https://ugate.yowyob.com/publications/"
                                                + publicationTitle.replaceAll(" ", "-").toLowerCase());

                return sendEmailNotification(publicationReactAlertTemplateId, List.of(authorEmail), data);
        }

        @Override
        public Mono<Void> sendAdminAlertWhenNewPublication(List<String> emails, String publicationTitle,
                        String authorName) {
                Map<String, Object> data = Map.of(
                                "publicationTitle", publicationTitle,
                                "authorName", authorName,
                                "publicationUrl", "https://ugate.yowyob.com/publications/"
                                                + publicationTitle.replaceAll(" ", "-").toLowerCase());

                return sendEmailNotification(adminAlertWhenNewPublicationTemplateId, emails, data);
        }

        @Override
        public Mono<Void> sendAdminAlertAcceptEvent(List<String> emails, String eventName, String organizerName) {
                Map<String, Object> data = Map.of(
                                "eventName", eventName,
                                "organizerName", organizerName,
                                "eventUrl",
                                "https://ugate.yowyob.com/events/" + eventName.replaceAll(" ", "-").toLowerCase());

                return sendEmailNotification(adminAlertAcceptEventTemplateId, emails, data);
        }

}