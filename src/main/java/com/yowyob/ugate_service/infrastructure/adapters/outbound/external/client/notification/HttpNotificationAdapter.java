package com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.notification;

import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class HttpNotificationAdapter implements NotificationPort {

        private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

        private final WebClient webClient;
        private final String serviceToken;

        private final Integer inviteTemplateId;
        private final Integer eventAlertTemplateId;
        private final Integer publicationCommentAlertTemplateId;
        private final Integer publicationReactAlertTemplateId;
        private final Integer adminAlertWhenNewPublicationTemplateId;
        private final Integer adminAlertAcceptEventTemplateId;

        public HttpNotificationAdapter(
                WebClient.Builder builder,
                @Value("${application.external.notification-service-url}") String notificationUrl,
                @Value("${application.external.notification-service-token}") String serviceToken,
                @Value("${application.external.notification-invite-template-id}") Integer inviteTemplateId,
                @Value("${application.external.notification-new-event-alert-templatet-id}") Integer eventAlertTemplateId,
                @Value("${application.external.notification-publication-comment-alert-template-id}") Integer publicationCommentAlertTemplateId,
                @Value("${application.external.notification-publication-react-alert-template-id}") Integer publicationReactAlertTemplateId,
                @Value("${application.external.notification-admin-alert-when-new-publication-template-id}") Integer adminAlertWhenNewPublicationTemplateId,
                @Value("${application.external.notification-admin-alert-accept-event-template-id}") Integer adminAlertAcceptEventTemplateId
        ) {
                this.webClient = builder.baseUrl(notificationUrl).build();
                this.serviceToken = serviceToken;
                this.inviteTemplateId = inviteTemplateId;
                this.eventAlertTemplateId = eventAlertTemplateId;
                this.publicationCommentAlertTemplateId = publicationCommentAlertTemplateId;
                this.publicationReactAlertTemplateId = publicationReactAlertTemplateId;
                this.adminAlertWhenNewPublicationTemplateId = adminAlertWhenNewPublicationTemplateId;
                this.adminAlertAcceptEventTemplateId = adminAlertAcceptEventTemplateId;
        }

        @Override
        public Mono<Void> sendSyndicateInvitation(String email, String syndicateName, String firstName) {
                if (email == null || email.isBlank()) {
                        log.warn("Notification invitation ignorée: email vide");
                        return Mono.empty();
                }

                Map<String, Object> data = Map.of(
                        "syndicateName", safe(syndicateName, "N/A"),
                        "firstName", safe(firstName, "N/A"),
                        "loginUrl", "https://ugate.yowyob.com/reset-password"
                );

                return sendEmailNotification(inviteTemplateId, List.of(email), data, "SYNDICATE_INVITATION");
        }

        @Override
        public Mono<Void> sendNewEventAlert(List<String> emails, String eventName) {
                List<String> recipients = normalizeRecipients(emails);
                if (recipients.isEmpty()) return Mono.empty();

                String safeEventName = safe(eventName, "Nouvel évènement");
                Map<String, Object> data = Map.of(
                        "eventName", safeEventName,
                        "eventUrl", "https://ugate.yowyob.com/events/" + slugify(safeEventName)
                );

                return sendEmailNotification(eventAlertTemplateId, recipients, data, "NEW_EVENT_ALERT");
        }

        @Override
        public Mono<Void> sendPublicationCommentAlert(String authorEmail, String publicationTitle, String firstName) {
                if (authorEmail == null || authorEmail.isBlank()) {
                        log.warn("Notification commentaire ignorée: email auteur vide");
                        return Mono.empty();
                }

                String safeTitle = safe(publicationTitle, "Nouvelle publication");
                Map<String, Object> data = Map.of(
                        "publicationTitle", safeTitle,
                        "firstName", safe(firstName, "N/A"),
                        "publicationUrl", "https://ugate.yowyob.com/publications/" + slugify(safeTitle)
                );

                return sendEmailNotification(publicationCommentAlertTemplateId, List.of(authorEmail), data, "PUBLICATION_COMMENT_ALERT");
        }

        @Override
        public Mono<Void> sendPublicationReactAlert(String authorEmail, String publicationTitle, String firstName) {
                if (authorEmail == null || authorEmail.isBlank()) {
                        log.warn("Notification réaction ignorée: email auteur vide");
                        return Mono.empty();
                }

                String safeTitle = safe(publicationTitle, "Nouvelle publication");
                Map<String, Object> data = Map.of(
                        "publicationTitle", safeTitle,
                        "firstName", safe(firstName, "N/A"),
                        "publicationUrl", "https://ugate.yowyob.com/publications/" + slugify(safeTitle)
                );

                return sendEmailNotification(publicationReactAlertTemplateId, List.of(authorEmail), data, "PUBLICATION_REACT_ALERT");
        }

        @Override
        public Mono<Void> sendAdminAlertWhenNewPublication(List<String> emails, String publicationTitle, String authorName) {
                List<String> recipients = normalizeRecipients(emails);
                if (recipients.isEmpty()) return Mono.empty();

                String safeTitle = safe(publicationTitle, "Nouvelle publication");
                Map<String, Object> data = Map.of(
                        "publicationTitle", safeTitle,
                        "authorName", safe(authorName, "N/A"),
                        "publicationUrl", "https://ugate.yowyob.com/publications/" + slugify(safeTitle)
                );

                return sendEmailNotification(adminAlertWhenNewPublicationTemplateId, recipients, data, "ADMIN_NEW_PUBLICATION_ALERT");
        }

        @Override
        public Mono<Void> sendAdminAlertAcceptEvent(List<String> emails, String eventName, String organizerName) {
                List<String> recipients = normalizeRecipients(emails);
                if (recipients.isEmpty()) return Mono.empty();

                String safeEventName = safe(eventName, "Evènement");
                Map<String, Object> data = Map.of(
                        "eventName", safeEventName,
                        "organizerName", safe(organizerName, "N/A"),
                        "eventUrl", "https://ugate.yowyob.com/events/" + slugify(safeEventName)
                );

                return sendEmailNotification(adminAlertAcceptEventTemplateId, recipients, data, "ADMIN_ACCEPT_EVENT_ALERT");
        }

        /**
         * Envoi email résilient :
         * - timeout court
         * - ne remonte jamais d'erreur (best-effort)
         * - logs structurés
         */
        private Mono<Void> sendEmailNotification(Integer templateId,
                                                 List<String> recipients,
                                                 Map<String, Object> data,
                                                 String context) {
                if (templateId == null) {
                        log.warn("Notification ignorée (templateId null) context={}", context);
                        return Mono.empty();
                }

                if (recipients == null || recipients.isEmpty()) {
                        log.debug("Notification ignorée (aucun destinataire) context={}", context);
                        return Mono.empty();
                }

                Map<String, Object> requestBody = Map.of(
                        "notificationType", "EMAIL",
                        "templateId", templateId,
                        "to", recipients,
                        "data", data
                );

                return webClient.post()
                        .uri("/api/v1/notifications/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Service-Token", serviceToken)
                        .bodyValue(requestBody)
                        .exchangeToMono(response -> {
                                if (response.statusCode().is2xxSuccessful()) {
                                        return response.releaseBody().then();
                                }
                                // Lire le body pour log (best-effort)
                                return response.bodyToMono(String.class)
                                        .defaultIfEmpty("")
                                        .flatMap(body -> {
                                                log.warn("Notification service error context={}, status={}, recipients={}, body={}",
                                                        context, response.statusCode(), recipients, body);
                                                return Mono.empty(); // <-- important : ne pas bloquer l'application
                                        });
                        })
                        .timeout(REQUEST_TIMEOUT)
                        .doOnSuccess(v -> log.debug("Notification envoyée (best-effort) context={}, recipients={}", context, recipients))
                        .onErrorResume(e -> {
                                log.warn("Notification échouée (ignorée) context={}, recipients={}, cause={}",
                                        context, recipients, e.getMessage());
                                return Mono.empty();
                        });
        }

        private List<String> normalizeRecipients(List<String> recipients) {
                if (recipients == null) return List.of();
                return recipients.stream()
                        .filter(e -> e != null && !e.isBlank())
                        .distinct()
                        .toList();
        }

        private String safe(String value, String fallback) {
                return (value == null || value.isBlank()) ? fallback : value;
        }

        private String slugify(String input) {
                if (input == null) return "item";
                return input.trim()
                        .toLowerCase()
                        .replaceAll("\\s+", "-")
                        .replaceAll("[^a-z0-9\\-]", "");
        }
}