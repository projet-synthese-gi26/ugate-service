package com.yowyob.ugate_service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.media.MediaService;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Publication;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
class PublicationControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private PublicationRepository publicationRepository;

    @MockBean
    private MediaService mediaService;

    @BeforeEach
    void setUp() {
        publicationRepository.deleteAll().block();
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
        bodyBuilder.part("images", new ClassPathResource("test-image.png")).contentType(MediaType.IMAGE_PNG);

        webTestClient.post()
                .uri("/publications")
                .contentType(MediaType.MULTIPART_FORM_DATA)
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
                .contentType(MediaType.MULTIPART_FORM_DATA)
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
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .exchange()
                .expectStatus().isBadRequest();
    }
}
