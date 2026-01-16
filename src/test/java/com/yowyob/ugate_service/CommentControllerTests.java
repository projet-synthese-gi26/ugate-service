package com.yowyob.ugate_service;

import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.model.ImageModel;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreateCommentRequest;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Branch;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Comment;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Image;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Publication;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Syndicat;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.User;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.BranchRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.CommentRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ImageRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Instant;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CommentControllerTests {

        @Autowired
        private ApplicationContext context;

        private WebTestClient webTestClient;

        @Autowired
        private PublicationRepository publicationRepository;

        @Autowired
        private CommentRepository commentRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private BranchRepository branchRepository;

        @Autowired
        private SyndicatRepository syndicatRepository;

        @Autowired
        private ImageRepository imageRepository;

        @MockBean
        private UserGatewayPort userGatewayPort;

        @MockBean
        private MediaPersistencePort mediaPersistencePort;

        private User testUser;
        private Publication testPublication;
        private Branch testBranch;
        private Syndicat testSyndicat;
        private Image testImage;

        @BeforeEach
        public void setUp() {
                webTestClient = WebTestClient
                                .bindToApplicationContext(context)
                                .apply(SecurityMockServerConfigurers.springSecurity())
                                .configureClient()
                                .build();

                commentRepository.deleteAll().block();
                publicationRepository.deleteAll().block();
                branchRepository.deleteAll().block();
                syndicatRepository.deleteAll().block();
                userRepository.deleteAll().block();
                imageRepository.deleteAll().block();

                // Setup test data
                testUser = userRepository.save(new User(null, "Test user", "6587895423", "test@mail.co", true)).block();
                testSyndicat = syndicatRepository.save(new Syndicat(null, testUser.id(), "Test Syndicat", "description",
                                "domain", "logo", "status"))
                                .block();
                testBranch = branchRepository.save(new Branch(null, testSyndicat.id(), "Test Branch", "location",
                                "contact", Instant.now(), Instant.now())).block();
                testPublication = publicationRepository
                                .save(new Publication(testBranch.id(), testUser.id(), "Test Content", 0L,
                                                Instant.now()))
                                .block();
                testImage = imageRepository.save(
                                new Image("http://example.com/mock-image.png", "Mock Image Alt Text", Instant.now()))
                                .block();

                // Mock external dependencies
                when(userGatewayPort.findById(any(UUID.class))).thenReturn(Mono.just(new ExternalUserInfo(testUser.id(),
                                "Test", "User", "test@example.com", "1234567890", null, null)));

                ImageModel mockImageModel = new ImageModel();
                mockImageModel.setId(UUID.randomUUID());
                mockImageModel.setUrl("http://example.com/mock-image.png");
                mockImageModel.setAltText("Mock Image Alt Text");
                mockImageModel.setUploadedAt(Instant.now());
                when(mediaPersistencePort.getImageById(any(UUID.class))).thenReturn(Mono.just(mockImageModel));
                when(mediaPersistencePort.saveImage(any(String.class), any(String.class)))
                                .thenReturn(Mono.just(mockImageModel));
        }

        @Test
        public void createComment_shouldSucceed() {
                CreateCommentRequest request = new CreateCommentRequest();
                request.setContent("This is a test comment");
                request.setImageUrl("http://example.com/new-comment-image.png");

                webTestClient
                                .mutateWith(mockJwt().jwt(jwt -> jwt.subject(testUser.id().toString())))
                                .post()
                                .uri("/publications/" + testPublication.id() + "/comments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(request)
                                .exchange()
                                .expectStatus().isOk();

                StepVerifier.create(commentRepository.findByPublicationId(testPublication.id()))
                                .expectNextMatches(comment -> comment.content().equals("This is a test comment"))
                                .verifyComplete();
        }

        @Test
        public void getComments_shouldReturnComments() {
                Comment comment1 = this.commentRepository
                                .save(new Comment(null, testUser.id(), testPublication.id(),
                                                null,
                                                testImage.id(),
                                                "First comment", Instant.now()))
                                .block();
                Comment comment2 = this.commentRepository
                                .save(new Comment(null, testUser.id(), testPublication.id(),
                                                null,
                                                testImage.id(),
                                                "Second comment", Instant.now()))
                                .block();

                webTestClient.get()
                                .uri("/publications/{publicationId}/comments", testPublication.id().toString())
                                .exchange()
                                .expectStatus().isOk()
                                .expectBody()
                                .jsonPath("$[0].content").isEqualTo(comment1.content())
                                .jsonPath("$[0].authorId").isEqualTo(testUser.id().toString())
                                .jsonPath("$[1].content").isEqualTo(comment2.content())
                                .jsonPath("$[1].authorId").isEqualTo(testUser.id().toString());
        }
}
