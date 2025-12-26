package com.yowyob.ugate_service.domain.ports.out.media;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface FileStoragePort {
    /**
     * Upload un fichier et retourne l'URL publique ou l'URI d'accès
     * @param file Le fichier binaire (FilePart de WebFlux)
     * @param location Le dossier ou contexte (ex: "syndicats", "avatars")
     * @return L'URL du média uploadé
     */
    Mono<String> uploadFile(FilePart file, String location);
}