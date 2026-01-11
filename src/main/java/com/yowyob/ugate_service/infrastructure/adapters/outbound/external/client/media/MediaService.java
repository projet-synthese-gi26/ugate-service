package com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.media;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Data
@Service
public class MediaService {

  @Value("${spring.application.name}")
  private String serviceName;

  @Value("${application.external.media-service-url}")
  private String mediaServiceUrl;

  private String imagesFolder = "images";
  private String videosFolder = "videos";
  private String filesFolder = "files";

  private final WebClient webClient;

  public MediaService(WebClient.Builder webClientBuilder) {
    // Initialisation du WebClient avec la base URL du service média
    this.webClient = webClientBuilder.baseUrl(mediaServiceUrl).build();
  }

  public Mono<List<String>> uploadImage(FilePart[] imageData) {
    return uploadToMediaService(imageData, imagesFolder);
  }

  public Mono<List<String>> uploadVideo(FilePart[] videoData) {
    return uploadToMediaService(videoData, videosFolder);
  }

  public Mono<List<String>> uploadFiles(FilePart[] filesData) {
    return uploadToMediaService(filesData, filesFolder);
  }

  private Mono<List<String>> uploadToMediaService(FilePart[] parts, String location) {
    if (parts == null || parts.length == 0) {
      return Mono.just(List.of());
    }

    MultipartBodyBuilder builder = new MultipartBodyBuilder();
    builder.part("service", serviceName);
    builder.part("location", location);

    for (FilePart filePart : parts) {
      builder.asyncPart("files", Mono.just(filePart), FilePart.class);
    }

    return webClient.post()
        .uri("/media/upload-multiple")
        .contentType(MediaType.MULTIPART_FORM_DATA)
        .body(BodyInserters.fromMultipartData(builder.build()))
        .retrieve()
        .bodyToFlux(MediaDto.class) // Désérialise le flux de MediaDto retourné
        .map(dto -> String.format("%s/media/%s", mediaServiceUrl, dto.getId()))
        .collectList(); // On collecte tout dans une liste pour respecter votre besoin
  }

  @Data
  private static class MediaDto {
    private String id;
    private String name;
    private String uri;
    private String service;
    private String path;
  }
}