package com.yowyob.ugate_service;

import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreateReactionRequest;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Publication;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ReactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @BeforeEach
    void setUp() {
        publicationRepository.deleteAll().block();
        reactionRepository.deleteAll().block();
    }

    @Test
    void testAddReactionToPublication() {
        // Arrange: Create and save a publication
        UUID authorId = UUID.randomUUID();
        UUID branchId = UUID.randomUUID();
        Publication publication = new Publication(branchId, authorId, "Test content", 0L, Instant.now());
        Publication savedPublication = publicationRepository.save(publication).block();
        assertNotNull(savedPublication);
        assertEquals(0L, savedPublication.nLikes());

        // Prepare the reaction request
        UUID userId = UUID.randomUUID();
        String reactionType = "LIKE";
        CreateReactionRequest request = new CreateReactionRequest();
        request.setUserId(userId);
        request.setReactionType(reactionType);

        // Act: Call the endpoint to add a reaction
        webTestClient.post()
                .uri("/api/v1/publications/{publicationId}/reactions", savedPublication.id())
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
