package com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.media;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux; // Added import for Flux

import java.util.List;

@Data
@Service
public class MediaService {

  private final String serviceName;
  private final String mediaServiceUrl;
  private final WebClient webClient;

  private String imagesFolder = "images";
  private String videosFolder = "videos";
  private String filesFolder = "files";

  public MediaService(WebClient.Builder webClientBuilder,
      @Value("${application.external.media-service-name}") String serviceName,
      @Value("${application.external.media-service-url}") String mediaServiceUrl) {
    this.serviceName = serviceName;
    this.mediaServiceUrl = mediaServiceUrl;
    this.webClient = webClientBuilder.baseUrl(mediaServiceUrl).build();
  }

  public Mono<List<String>> uploadImage(Flux<FilePart> imageData) { // Changed input type
    return uploadToMediaService(imageData, imagesFolder);
  }

  public Mono<List<String>> uploadVideo(Flux<FilePart> videoData) { // Changed input type
    return uploadToMediaService(videoData, videosFolder);
  }

  public Mono<List<String>> uploadFiles(Flux<FilePart> filesData) { // Changed input type
    return uploadToMediaService(filesData, filesFolder);
  }

  private Mono<List<String>> uploadToMediaService(Flux<FilePart> parts, String location) { // Changed input type
    return parts.collectList().flatMap(collectedParts -> {
      if (collectedParts.isEmpty()) {
        return Mono.just(List.of());
      }

      MultipartBodyBuilder builder = new MultipartBodyBuilder();
      builder.part("service", serviceName);
      builder.part("location", location);

      for (FilePart filePart : collectedParts) {
        MediaType contentType = filePart.headers().getContentType();
        if (contentType == null)
          contentType = MediaType.APPLICATION_OCTET_STREAM;

        builder.asyncPart("files", filePart.content(), DataBuffer.class)
            .filename(filePart.filename())
            .contentType(contentType);
      }

      return webClient.post()
          .uri("/media/upload-multiple")
          .contentType(MediaType.MULTIPART_FORM_DATA)
          .body(BodyInserters.fromMultipartData(builder.build()))
          .retrieve()
          .bodyToFlux(MediaDto.class) // Désérialise le flux de MediaDto retourné
          .map(dto -> {
            // Gestion propre du slash pour éviter les doubles slashs
            String baseUrl = mediaServiceUrl.endsWith("/") ? mediaServiceUrl.substring(0, mediaServiceUrl.length() - 1)
                : mediaServiceUrl;
            return String.format("%s/media/%s", baseUrl, dto.getId());
          })
          .collectList(); // On collecte tout dans une liste pour respecter votre besoin
    });
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