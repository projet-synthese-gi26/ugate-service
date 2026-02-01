package com.yowyob.ugate_service;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Event;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Publication;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.EventRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.UUID;

import com.yowyob.ugate_service.domain.model.ImageModel;
import reactor.core.publisher.Flux;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.model.MediaInfo;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserEventPersistencePort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class FeedControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private EventRepository eventRepository;

    @MockBean
    private UserGatewayPort userGatewayPort;
    @MockBean
    private MediaPersistencePort mediaPersistencePort;
    @MockBean
    private UserEventPersistencePort userEventPersistencePort;


    @BeforeEach
    void setUp() {
        publicationRepository.deleteAll().block();
        eventRepository.deleteAll().block();

        // Mock external dependencies for DTO enrichment
        when(userGatewayPort.findById(any(UUID.class)))
                .thenReturn(Mono.just(new ExternalUserInfo(UUID.randomUUID(), "Test", "User", "test@example.com", "123", Collections.emptyList(), Collections.emptyList())));
        when(mediaPersistencePort.getMediaByPublicationId(any(UUID.class)))
                .thenReturn(Flux.just(new MediaInfo("http://pub.img/1", "IMAGE")));
        ImageModel mockImageModel = new ImageModel();
        mockImageModel.setId(UUID.randomUUID());
        mockImageModel.setUrl("http://event.img/1");
        mockImageModel.setAltText("event image alt");
        mockImageModel.setUploadedAt(Instant.now());
        when(mediaPersistencePort.getImagesByEventId(any(UUID.class)))
                .thenReturn(Flux.just(mockImageModel));
        when(userEventPersistencePort.countByEventId(any(UUID.class)))
                .thenReturn(Mono.just(5L));
    }

    @Test
    void testGetFeed() {
        // Arrange
        UUID branchId = UUID.randomUUID();
        UUID authorId = UUID.randomUUID();

        // Create some publications and events with different timestamps
        Publication pub1 = new Publication(branchId, authorId, "Publication 1", 0L, Instant.now().minus(1, ChronoUnit.DAYS));
        Event event1 = new Event(authorId, branchId, "Event 1", "Description 1", "Location 1", LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1), Instant.now().minus(2, ChronoUnit.DAYS), null);
        Publication pub2 = new Publication(branchId, authorId, "Publication 2", 0L, Instant.now());
        Event event2 = new Event(authorId, branchId, "Event 2", "Description 2", "Location 2", LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1), Instant.now().minus(5, ChronoUnit.HOURS), null);

        publicationRepository.save(pub1).block();
        eventRepository.save(event1).block();
        publicationRepository.save(pub2).block();
        eventRepository.save(event2).block();

        // Act & Assert
        webTestClient.get()
                .uri("/api/v1/feed?page=0&size=4")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith("application/json")
                .expectBody()
                .jsonPath("$.length()").isEqualTo(4)
                .jsonPath("$[0].type").isEqualTo("publication")
                .jsonPath("$[0].data.content").isEqualTo("Publication 2") // Most recent
                .jsonPath("$[0].data.authorFullName").isEqualTo("Test User")
                .jsonPath("$[0].data.fileUrlAndType[0].url").isEqualTo("http://pub.img/1")
                .jsonPath("$[1].type").isEqualTo("event")
                .jsonPath("$[1].data.title").isEqualTo("Event 2")
                .jsonPath("$[1].data.participantCount").isEqualTo(5L)
                .jsonPath("$[1].data.imageUrls[0]").isEqualTo("http://event.img/1")
                .jsonPath("$[2].type").isEqualTo("publication")
                .jsonPath("$[2].data.content").isEqualTo("Publication 1")
                .jsonPath("$[3].type").isEqualTo("event")
                .jsonPath("$[3].data.title").isEqualTo("Event 1"); // Oldest
    }

     @Test
    void testGetFeedPagination() {
        // Arrange
        UUID branchId = UUID.randomUUID();
        UUID authorId = UUID.randomUUID();

        for (int i = 0; i < 10; i++) {
            publicationRepository.save(new Publication(branchId, authorId, "Pub " + i, 0L, Instant.now().minus(i, ChronoUnit.MINUTES))).block();
        }

        // Act & Assert
        webTestClient.get()
                .uri("/api/v1/feed?page=1&size=5")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith("application/json")
                .expectBody()
                .jsonPath("$.length()").isEqualTo(5)
                .jsonPath("$[0].type").isEqualTo("publication")
                .jsonPath("$[0].data.content").isEqualTo("Pub 5")
                .jsonPath("$[0].data.authorFullName").isEqualTo("Test User");
    }
}
