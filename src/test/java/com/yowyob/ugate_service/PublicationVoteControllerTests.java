package com.yowyob.ugate_service;

import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CastVoteRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreatePublicationVoteRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.PublicationVoteWithResultsDTO;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.VoteResultDTO;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.PublicationVote;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Vote;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationVoteRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class PublicationVoteControllerTests {

    private WebTestClient webTestClient;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private PublicationVoteRepository publicationVoteRepository;

    @Autowired
    private VoteRepository voteRepository;

    @BeforeEach
    void setUp() {
        this.webTestClient = WebTestClient
                .bindToApplicationContext(this.context)
                .apply(springSecurity())
                .configureClient()
                .build();

        publicationVoteRepository.deleteAll().block();
        voteRepository.deleteAll().block();
    }

    @Test
    void testCreateVote_Success() {
        CreatePublicationVoteRequest request = new CreatePublicationVoteRequest();
        request.setTitle("Best Framework");
        request.setDescription("Vote for the best framework");
        request.setClosingAt(Instant.now().plus(1, ChronoUnit.DAYS));
        request.setType("SINGLE_CHOICE");
        request.setBranchId(UUID.randomUUID());

        webTestClient.post()
                .uri("/publication-votes")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated();

        PublicationVote vote = publicationVoteRepository.findAll().blockFirst();
        assertNotNull(vote);
        assertEquals("Best Framework", vote.title());
        assertEquals("SINGLE_CHOICE", vote.type());
    }

    @Test
    void testCastVote_Success() {
        PublicationVote poll = new PublicationVote(null, UUID.randomUUID(), "Favorite Color", "desc",
                Instant.now().plus(5, ChronoUnit.DAYS), "SINGLE");
        PublicationVote savedPoll = publicationVoteRepository.save(poll).block();
        assertNotNull(savedPoll);

        UUID testUserId = UUID.randomUUID();
        CastVoteRequest request = new CastVoteRequest();
        request.setChoiceLabel("Blue");

        // 2. Perform authenticated POST request
        webTestClient
                .mutateWith(mockJwt().jwt(jwt -> jwt.subject(testUserId.toString())))
                .post()
                .uri("/publication-votes/{publicationVoteId}/cast", savedPoll.id())
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk();

        // 3. Verify the vote was saved
        Vote vote = voteRepository.findAll().blockFirst();
        assertNotNull(vote);
        assertEquals(testUserId, vote.userId());
        assertEquals(savedPoll.id(), vote.publicationVoteId());
        assertEquals("Blue", vote.label());
    }

    @Test
    void testCastVote_Error_PollClosed() {
        // 1. Create a poll that is already closed
        PublicationVote poll = new PublicationVote(null, UUID.randomUUID(), "Past Poll", "desc",
                Instant.now().minus(1, ChronoUnit.HOURS),
                "SINGLE");
        PublicationVote savedPoll = publicationVoteRepository.save(poll).block();
        assertNotNull(savedPoll);

        UUID testUserId = UUID.randomUUID();
        CastVoteRequest request = new CastVoteRequest();
        request.setChoiceLabel("Any");

        // 2. Perform authenticated POST request and expect an error
        webTestClient
                .mutateWith(mockJwt().jwt(jwt -> jwt.subject(testUserId.toString())))
                .post()
                .uri("/publication-votes/{publicationVoteId}/cast", savedPoll.id())
                .bodyValue(request)
                .exchange()
                .expectStatus().is5xxServerError(); // Or a specific 4xx error if you have exception handling
    }

    @Test
    void testGetVoteResults_Success() {
        // 1. Create a poll
        PublicationVote poll = new PublicationVote(null, UUID.randomUUID(), "Favorite Color", "desc",
                Instant.now().plus(5, ChronoUnit.DAYS), "SINGLE");
        PublicationVote savedPoll = publicationVoteRepository.save(poll).block();
        assertNotNull(savedPoll);

        // 2. Create votes
        UUID user1 = UUID.randomUUID();
        UUID user2 = UUID.randomUUID();
        UUID user3 = UUID.randomUUID(); // This is the user who will be making the request
        voteRepository.save(new Vote(null, user1, savedPoll.id(), "Blue")).block();
        voteRepository.save(new Vote(null, user2, savedPoll.id(), "Red")).block();
        voteRepository.save(new Vote(null, user3, savedPoll.id(), "Blue")).block();

        // 3. Perform authenticated GET request
        webTestClient
                .mutateWith(mockJwt().jwt(jwt -> jwt.subject(user3.toString())))
                .get()
                .uri("/publication-votes/{publicationVoteId}/results", savedPoll.id())
                .exchange()
                .expectStatus().isOk()
                .expectBody(PublicationVoteWithResultsDTO.class)
                .value(dto -> {
                    assertEquals(3, dto.getTotalVotes());
                    assertEquals(true, dto.isHasVoted());
                    assertEquals(2, dto.getResults().size());
                    VoteResultDTO blueResult = dto.getResults().stream().filter(r -> r.getChoiceLabel().equals("Blue"))
                            .findFirst().orElseThrow();
                    assertEquals(2, blueResult.getCount());
                    VoteResultDTO redResult = dto.getResults().stream().filter(r -> r.getChoiceLabel().equals("Red"))
                            .findFirst().orElseThrow();
                    assertEquals(1, redResult.getCount());
                });
    }

    @Test
    void testGetVoteResults_UserHasNotVoted() {
        // 1. Create a poll
        PublicationVote poll = new PublicationVote(null, UUID.randomUUID(), "Favorite Animal", "desc",
                Instant.now().plus(5, ChronoUnit.DAYS), "SINGLE");
        PublicationVote savedPoll = publicationVoteRepository.save(poll).block();
        assertNotNull(savedPoll);

        // 2. Create votes from other users
        voteRepository.save(new Vote(null, UUID.randomUUID(), savedPoll.id(), "Dog")).block();

        UUID nonVoterId = UUID.randomUUID(); // This user has not voted

        // 3. Perform authenticated GET request
        webTestClient
                .mutateWith(mockJwt().jwt(jwt -> jwt.subject(nonVoterId.toString())))
                .get()
                .uri("/publication-votes/{publicationVoteId}/results", savedPoll.id())
                .exchange()
                .expectStatus().isOk()
                .expectBody(PublicationVoteWithResultsDTO.class)
                .value(dto -> {
                    assertEquals(1, dto.getTotalVotes());
                    assertEquals(false, dto.isHasVoted());
                    assertEquals(1, dto.getResults().size());
                });
    }
}
