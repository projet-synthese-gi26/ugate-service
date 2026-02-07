package com.yowyob.ugate_service;

import com.yowyob.ugate_service.domain.enumeration.ReactionTypeEnum;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreateReactionRequest;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Publication;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ReactionRepository;
import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class ReactionControllerTests {

        @Autowired
        private WebTestClient webTestClient;

        @Autowired
        private PublicationRepository publicationRepository;

        @Autowired
        private ReactionRepository reactionRepository;

        @MockBean
        private NotificationPort notificationPort;

        @MockBean
        private UserGatewayPort userGatewayPort;

        private UUID mockPublicationAuthorId;
        private UUID mockReactorUserId; // New field

        @BeforeEach
        void setUp() {
                publicationRepository.deleteAll().block();
                reactionRepository.deleteAll().block();

                mockPublicationAuthorId = UUID.randomUUID();
                mockReactorUserId = UUID.randomUUID(); // Initialize the reactor's user ID

                // Mock external dependencies for ReactionService
                // Mock for the publication author's ID
                when(userGatewayPort.findById(eq(mockPublicationAuthorId)))
                                .thenReturn(Mono.just(new ExternalUserInfo(mockPublicationAuthorId,
                                                "Publication", "Author", "pubauthor@example.com", "0987654321", null,
                                                null)));
                // Mock for the reactor's ID
                when(userGatewayPort.findById(eq(mockReactorUserId)))
                                .thenReturn(Mono.just(new ExternalUserInfo(mockReactorUserId,
                                                "Reactor", "User", "reactor@example.com", "1122334455", null, null)));

                when(notificationPort.sendPublicationReactAlert(anyString(), anyString(), anyString()))
                                .thenReturn(Mono.empty());
        }

        @Test
        void testAddReactionToPublication() {
                // Arrange: Create and save a publication
                // Use the fixed mockPublicationAuthorId for consistency
                Publication publication = new Publication(UUID.randomUUID(), mockPublicationAuthorId, "Test content",
                                0L, Instant.now());
                Publication savedPublication = publicationRepository.save(publication).block();
                assertNotNull(savedPublication);
                assertEquals(0L, savedPublication.nLikes());

                // Prepare the reaction request
                UUID userId = mockReactorUserId; // Use the fixed reactor user ID
                ReactionTypeEnum reactionType = ReactionTypeEnum.LIKE;
                CreateReactionRequest request = new CreateReactionRequest();
                request.setUserId(userId);
                request.setReactionType(reactionType);

                // Act: Call the endpoint to add a reaction
                webTestClient.post()
                                .uri("/publications/{publicationId}/reactions", savedPublication.id())
                                .bodyValue(request)
                                .exchange()
                                .expectStatus().isOk();

                // Assert: Verify the reaction was created
                StepVerifier.create(reactionRepository.findAll())
                                .assertNext(reaction -> {
                                        assertNotNull(reaction.id());
                                        assertEquals(savedPublication.id(), reaction.publicationId());
                                        assertEquals(userId, reaction.userId());
                                        assertEquals(reactionType, reaction.type());
                                })
                                .verifyComplete();

                // Assert: Verify the publication's like count was incremented
                StepVerifier.create(publicationRepository.findById(savedPublication.id()))
                                .assertNext(updatedPublication -> {
                                        assertEquals(1L, updatedPublication.nLikes());
                                })
                                .verifyComplete();
        }
}
