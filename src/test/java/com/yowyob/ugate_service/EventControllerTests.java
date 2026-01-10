package com.yowyob.ugate_service;

import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.EventResponseDTO;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ParticipantDTO;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.media.MediaService;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Event;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.UserEvent;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.EventImagesRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.EventRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ImageRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.UserEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;

import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class EventControllerTests {

    private WebTestClient webTestClient;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private EventImagesRepository eventImagesRepository;

    @Autowired
    private UserEventRepository userEventRepository;

    @MockBean
    private MediaService mediaService;

    @MockBean
    private UserGatewayPort userGatewayPort;

    @BeforeEach
    void setUp() {
        this.webTestClient = WebTestClient
                .bindToApplicationContext(this.context)
                .apply(springSecurity())
                .configureClient()
                .build();
        
        eventRepository.deleteAll().block();
        imageRepository.deleteAll().block();
        eventImagesRepository.deleteAll().block();
        userEventRepository.deleteAll().block();
        
        // Mock the media service to return a dummy URL
        when(mediaService.uploadImage(any())).thenReturn(Mono.just(List.of("http://localhost:8080/media/test-image.png")));
        when(mediaService.uploadVideo(any())).thenReturn(Mono.just(List.of()));
        when(mediaService.uploadFiles(any())).thenReturn(Mono.just(List.of()));
    }

    @Test
    void testCreateEventWithImage() {
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("creatorId", UUID.randomUUID().toString());
        bodyBuilder.part("branchId", UUID.randomUUID().toString());
        bodyBuilder.part("title", "Community BBQ");
        bodyBuilder.part("description", "Annual community barbecue event.");
        bodyBuilder.part("eventDate", "2026-07-20");
        bodyBuilder.part("location", "Central Park");
        bodyBuilder.part("startTime", "12:00");
        bodyBuilder.part("endTime", "16:00");
        bodyBuilder.part("images", new ClassPathResource("test-image.png")).contentType(MediaType.IMAGE_PNG);

        webTestClient.post()
                .uri("/events")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .exchange()
                .expectStatus().isCreated();

        // Verify the event was saved to the database
        Event event = eventRepository.findAll().blockFirst();
        assertNotNull(event);
        assertEquals("Community BBQ", event.title());
        assertEquals(LocalDate.parse("2026-07-20"), event.date());
        assertEquals(LocalTime.parse("12:00"), event.startTime());
    }

    @Test
    void testCreateEventWithoutMedia() {
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("creatorId", UUID.randomUUID().toString());
        bodyBuilder.part("branchId", UUID.randomUUID().toString());
        bodyBuilder.part("title", "Team Meeting");
        bodyBuilder.part("description", "Weekly team sync meeting.");
        bodyBuilder.part("eventDate", "2026-01-12");
        bodyBuilder.part("location", "Office Conference Room");
        bodyBuilder.part("startTime", "10:00");
        bodyBuilder.part("endTime", "11:00");

        webTestClient.post()
                .uri("/events")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .exchange()
                .expectStatus().isCreated();

        Event event = eventRepository.findAll().blockFirst();
        assertNotNull(event);
        assertEquals("Team Meeting", event.title());
    }

    @Test
    void testCreateEventWithInvalidInput() {
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("creatorId", UUID.randomUUID().toString());
        bodyBuilder.part("branchId", UUID.randomUUID().toString());
        bodyBuilder.part("title", ""); // Invalid: empty title
        bodyBuilder.part("description", "A description without a title.");
        bodyBuilder.part("eventDate", "2026-02-10");
        bodyBuilder.part("location", "A Location");
        bodyBuilder.part("startTime", "09:00");
        bodyBuilder.part("endTime", "10:00");

        webTestClient.post()
                .uri("/events")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testJoinEvent_Success() {
        // 1. Create a test event
        Event testEvent = new Event(
            UUID.randomUUID(),
            UUID.randomUUID(),
            "Test Event for Joining",
            "Description",
            "Online",
            LocalDate.now().plusDays(10),
            LocalTime.of(18, 0),
            LocalTime.of(20, 0),
            Instant.now(),
            Instant.now()
        );
        Event savedEvent = eventRepository.save(testEvent).block();
        assertNotNull(savedEvent);

        // 2. Create a test user UUID
        UUID testUserId = UUID.randomUUID();

        // 3. Perform authenticated POST request
        webTestClient
            .mutateWith(mockJwt().jwt(jwt -> jwt.subject(testUserId.toString())))
            .post()
            .uri("/events/{eventId}/join", savedEvent.id())
            .exchange()
            .expectStatus().isOk();

        // 4. Verify that the user-event link was created
        UserEvent userEvent = userEventRepository.findAll().blockFirst();
        assertNotNull(userEvent);
        assertEquals(testUserId.toString(), userEvent.userId());
        assertEquals(savedEvent.id().toString(), userEvent.eventId());
    }

    @Test
    void testGetEventsByBranch_Success() {
        // 1. Create test data
        UUID branchId = UUID.randomUUID();
        Event event1 = new Event(UUID.randomUUID(), branchId, "Event 1", "Desc 1", "Loc 1", LocalDate.now(), LocalTime.now(), LocalTime.now(), Instant.now(), Instant.now());
        Event event2 = new Event(UUID.randomUUID(), branchId, "Event 2", "Desc 2", "Loc 2", LocalDate.now(), LocalTime.now(), LocalTime.now(), Instant.now(), Instant.now());
        Event savedEvent1 = eventRepository.save(event1).block();
        Event savedEvent2 = eventRepository.save(event2).block();
        assertNotNull(savedEvent1);
        assertNotNull(savedEvent2);

        // 2. Simulate users joining event1 (2 participants)
        userEventRepository.save(new UserEvent(null, UUID.randomUUID().toString(), savedEvent1.id().toString(), Instant.now())).block();
        userEventRepository.save(new UserEvent(null, UUID.randomUUID().toString(), savedEvent1.id().toString(), Instant.now())).block();

        // 3. Perform GET request
        webTestClient.get()
            .uri("/events/branch/{branchId}", branchId)
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(EventResponseDTO.class)
            .hasSize(2)
            .value(events -> {
                EventResponseDTO dto1 = events.stream().filter(e -> e.getId().equals(savedEvent1.id())).findFirst().orElseThrow();
                assertEquals(2, dto1.getParticipantCount());
                assertEquals("Event 1", dto1.getTitle());

                EventResponseDTO dto2 = events.stream().filter(e -> e.getId().equals(savedEvent2.id())).findFirst().orElseThrow();
                assertEquals(0, dto2.getParticipantCount());
                assertEquals("Event 2", dto2.getTitle());
            });
    }

    @Test
    void testGetEventParticipants_Success() {
        // 1. Create test data
        Event testEvent = new Event(UUID.randomUUID(), UUID.randomUUID(), "Event With Participants", "Desc", "Loc", LocalDate.now(), LocalTime.now(), LocalTime.now(), Instant.now(), Instant.now());
        Event savedEvent = eventRepository.save(testEvent).block();
        assertNotNull(savedEvent);

        UUID userId1 = UUID.randomUUID();
        UUID userId2 = UUID.randomUUID();
        userEventRepository.save(new UserEvent(null, userId1.toString(), savedEvent.id().toString(), Instant.now())).block();
        userEventRepository.save(new UserEvent(null, userId2.toString(), savedEvent.id().toString(), Instant.now())).block();

        // 2. Mock the UserGatewayPort
        when(userGatewayPort.findById(userId1)).thenReturn(Mono.just(new ExternalUserInfo(userId1, "John", "Doe", "j.doe@mail.com", "123", null, null)));
        when(userGatewayPort.findById(userId2)).thenReturn(Mono.just(new ExternalUserInfo(userId2, "Jane", "Smith", "j.smith@mail.com", "456", null, null)));

        // 3. Perform GET request
        webTestClient.get()
            .uri("/events/{eventId}/participants", savedEvent.id())
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(ParticipantDTO.class)
            .hasSize(2)
            .value(participants -> {
                ParticipantDTO p1 = participants.stream().filter(p -> p.getUserId().equals(userId1)).findFirst().orElseThrow();
                assertEquals("John Doe", p1.getFullName());

                ParticipantDTO p2 = participants.stream().filter(p -> p.getUserId().equals(userId2)).findFirst().orElseThrow();
                assertEquals("Jane Smith", p2.getFullName());
            });
    }

    @Test
    void testLeaveEvent_Success() {
        // 1. Create a test event and a user who has joined it
        Event testEvent = new Event(UUID.randomUUID(), UUID.randomUUID(), "Event to Leave", "Desc", "Loc", LocalDate.now(), LocalTime.now(), LocalTime.now(), Instant.now(), Instant.now());
        Event savedEvent = eventRepository.save(testEvent).block();
        assertNotNull(savedEvent);

        UUID testUserId = UUID.randomUUID();
        UserEvent userEvent = new UserEvent(null, testUserId.toString(), savedEvent.id().toString(), Instant.now());
        userEventRepository.save(userEvent).block();
        assertEquals(1, userEventRepository.count().block());

        // 2. Perform authenticated DELETE request
        webTestClient
            .mutateWith(mockJwt().jwt(jwt -> jwt.subject(testUserId.toString())))
            .delete()
            .uri("/events/{eventId}/leave", savedEvent.id())
            .exchange()
            .expectStatus().isNoContent();

        // 3. Verify that the user-event link has been deleted
        assertEquals(0, userEventRepository.count().block());
    }
}