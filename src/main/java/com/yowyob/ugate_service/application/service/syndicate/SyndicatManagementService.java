package com.yowyob.ugate_service.application.service.syndicate;

import com.yowyob.ugate_service.domain.ports.out.media.FileStoragePort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.PaginatedResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Syndicat;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatRepository;
import com.yowyob.ugate_service.infrastructure.mappers.syndicate.SyndicateMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyndicatManagementService {

    private final SyndicatRepository syndicatRepository;
    private final FileStoragePort fileStoragePort;
    private final SyndicateMapper syndicateMapper;

    @Transactional
    public Mono<SyndicateResponse> createSyndicate(String name,
                                                   String description,
                                                   String domain,
                                                   FilePart logoFile,
                                                   FilePart documentFile) {

        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> {
                    Jwt jwt = (Jwt) ctx.getAuthentication().getPrincipal();
                    String subject = jwt.getSubject();
                    try {
                        return UUID.fromString(subject);
                    } catch (IllegalArgumentException e) {
                        log.error("Tentative de création avec un ID utilisateur invalide dans le token : {}", subject);
                        throw new IllegalArgumentException("Token invalide : L'ID utilisateur n'est pas un UUID valide.");
                    }
                })
                .flatMap(creatorId -> {

                    return fileStoragePort.uploadFile(logoFile, "syndicats/logos")
                            .flatMap(logoUrl -> {


                                return fileStoragePort.uploadFile(documentFile, "syndicats/statuts")
                                        .flatMap(docUrl -> {

                                            UUID newId = UUID.randomUUID();

                                            Syndicat syndicat = new Syndicat(
                                                    newId,          // 1. id
                                                    null,           // 2. organizationId
                                                    creatorId,      // 3. creatorId
                                                    true,          // 4. isApproved
                                                    name,           // 5. name
                                                    description,    // 6. description
                                                    domain,         // 7. domain
                                                    "STANDARD",     // 8. type
                                                    null,           // 9. charteUrl
                                                    logoUrl,        // 10. logoUrl (reçu du media-service)
                                                    docUrl,         // 11. statusUrl (reçu du media-service)
                                                    null,           // 12. membersListUrl
                                                    null,           // 13. commitmentCertificateUrl
                                                    null,           // 14. createdAt (Géré par R2DBC)
                                                    null            // 15. updatedAt (Géré par R2DBC)
                                            );

                                            return syndicatRepository.save(syndicat);
                                        });
                            });
                })
                .map(syndicateMapper::toResponse)
                .doOnSuccess(dto -> log.info("Nouveau syndicat créé avec succès : ID {} par User {}", dto.id(), dto.creatorId()))
                .doOnError(e -> log.error("Erreur lors de la création du syndicat : {}", e.getMessage()));
    }



    public Mono<PaginatedResponse<SyndicateResponse>> getAllSyndicates(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdAt").descending());

        // 1. Récupérer le nombre total d'éléments (Pour calculer le nb de pages)
        Mono<Long> countMono = syndicatRepository.count();

        // 2. Récupérer les données de la page courante
        Mono<List<SyndicateResponse>> contentMono = syndicatRepository.findAllBy(pageRequest)
                .map(syndicateMapper::toResponse) // Conversion Entité -> DTO
                .collectList(); // On transforme le Flux en List pour construire la réponse

        // 3. Combiner les deux résultats (Zip)
        return Mono.zip(countMono, contentMono)
                .map(tuple -> {
                    Long total = tuple.getT1();
                    List<SyndicateResponse> content = tuple.getT2();

                    return PaginatedResponse.of(content, page, size, total);
                });
    }


    public Flux<SyndicateResponse> getUserSyndicates(UUID userId) {
        return syndicatRepository.findAllByMemberUserId(userId)
                .map(syndicateMapper::toResponse)
                .doOnError(e -> log.error("Erreur lors de la récupération des syndicats pour l'user {}", userId, e));
    }
}