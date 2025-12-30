package com.yowyob.ugate_service;

import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.dto.PublicationResponseDTO;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.external.MediaService;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Image;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Publication;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.PublicationImage;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ImageRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationImageRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class PublicationControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private PublicationImageRepository publicationImageRepository;

    @MockBean
    private MediaService mediaService;

    @MockBean
    private UserGatewayPort userGatewayPort;

    @BeforeEach
    void setUp() {
        publicationRepository.deleteAll().block();
        imageRepository.deleteAll().block();
        publicationImageRepository.deleteAll().block();
        when(mediaService.uploadImage(any())).thenReturn(Mono.just(List.of("http://localhost:8080/media/1")));
        when(mediaService.uploadVideo(any())).thenReturn(Mono.just(List.of()));
        when(mediaService.uploadFiles(any())).thenReturn(Mono.just(List.of()));
    }

    @Test
    void testCreatePublicationWithImage() {
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("content", "Test publication content");
        bodyBuilder.part("authorId", UUID.randomUUID().toString());
        bodyBuilder.part("branchId", UUID.randomUUID().toString());
        bodyBuilder.part("images", new ClassPathResource("test-image.png")).contentType(org.springframework.http.MediaType.IMAGE_PNG);

        webTestClient.post()
                .uri("/publications")
                .contentType(org.springframework.http.MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .exchange()
                .expectStatus().isCreated();

        Publication publication = publicationRepository.findAll().blockFirst();
        assertNotNull(publication);
        assertEquals("Test publication content", publication.content());
    }

    @Test
    void testCreatePublicationWithoutMedia() {
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("content", "Test publication content without media");
        bodyBuilder.part("authorId", UUID.randomUUID().toString());
        bodyBuilder.part("branchId", UUID.randomUUID().toString());

        webTestClient.post()
                .uri("/publications")
                .contentType(org.springframework.http.MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .exchange()
                .expectStatus().isCreated();

        Publication publication = publicationRepository.findAll().blockFirst();
        assertNotNull(publication);
        assertEquals("Test publication content without media", publication.content());
    }

    @Test
    void testCreatePublicationWithInvalidInput() {
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("content", ""); // Empty content
        bodyBuilder.part("authorId", UUID.randomUUID().toString());
        bodyBuilder.part("branchId", UUID.randomUUID().toString());

        webTestClient.post()
                .uri("/publications")
                .contentType(org.springframework.http.MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .exchange()
                .expectStatus().isBadRequest();
    }


    @Test
    void testGetPublicationsByBranch() {
        // Arrange
        UUID branchId = UUID.randomUUID();
        UUID authorId = UUID.randomUUID();
        String authorFirstName = "John";
        String authorLastName = "Doe";

        Publication publication = new Publication(
                branchId,
                authorId,
                "Content for branch test",
                5L,
                Instant.now()
        );
        Publication savedPublication = publicationRepository.save(publication).block();
        assertNotNull(savedPublication);

        Image image = new Image("http://image.url/test.png", "test image", Instant.now());
        Image savedImage = imageRepository.save(image).block();
        assertNotNull(savedImage);

        PublicationImage publicationImage = new PublicationImage(savedPublication.id(), savedImage.id(), Instant.now(), Instant.now());
        publicationImageRepository.save(publicationImage).block();


        ExternalUserInfo authorInfo = new ExternalUserInfo(authorId, authorFirstName, authorLastName, "email@test.com", "123456789", Collections.emptyList(), Collections.emptyList());
        when(userGatewayPort.findById(authorId)).thenReturn(Mono.just(authorInfo));


        // Act & Assert
        webTestClient.get()
                .uri("/publications/branch/{branchId}", branchId)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .expectBodyList(PublicationResponseDTO.class)
                .hasSize(1)
                .value(list -> {
                    PublicationResponseDTO dto = list.get(0);
                    assertEquals(savedPublication.id(), dto.getId());
                    assertEquals(savedPublication.branchId(), dto.getBranchId());
                    assertEquals(savedPublication.content(), dto.getContent());
                    assertEquals(authorFirstName + " " + authorLastName, dto.getAuthorFullName());
                    assertEquals(savedPublication.nLikes(), dto.getNLikes());

                    List<com.yowyob.ugate_service.domain.model.MediaInfo> mediaInfos = dto.getFileUrlAndType();
                    assertNotNull(mediaInfos);
                    assertEquals(1, mediaInfos.size());
                    assertEquals(savedImage.url(), mediaInfos.get(0).getUrl());
                    assertEquals("IMAGE", mediaInfos.get(0).getType());
                });
    }
}