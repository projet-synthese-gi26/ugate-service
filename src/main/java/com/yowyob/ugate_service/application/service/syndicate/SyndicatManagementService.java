package com.yowyob.ugate_service.application.service.syndicate;

import com.yowyob.ugate_service.domain.ports.out.media.FileStoragePort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.PaginatedResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Syndicat;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.SyndicatMember;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.enumeration.MemberRole;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.MembershipRequestRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatMemberRepository;
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

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyndicatManagementService {

    private final SyndicatRepository syndicatRepository;
    private final FileStoragePort fileStoragePort;
    private final SyndicateMapper syndicateMapper;
    private final SyndicatMemberRepository syndicatMemberRepository;

    @Transactional
    public Mono<SyndicateResponse> createSyndicate(String name, String description, String domain,
                                                   FilePart logoFile, FilePart documentFile) {

        return extractUserIdFromContext()
                .flatMap(creatorId -> uploadRequiredFiles(logoFile, documentFile)
                        .flatMap(urls -> persistSyndicateAndAdmin(creatorId, name, description, domain, urls))
                )
                .map(syndicateMapper::toResponse)
                .doOnSuccess(dto -> log.info("Syndicat créé : ID {} par {}", dto.id(), dto.creatorId()))
                .doOnError(e -> log.error("Échec création syndicat : {}", e.getMessage()));
    }



    private Mono<UUID> extractUserIdFromContext() {
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> {
                    String subject = ((Jwt) ctx.getAuthentication().getPrincipal()).getSubject();
                    return UUID.fromString(subject);
                })
                .onErrorMap(IllegalArgumentException.class, e ->
                        new IllegalArgumentException("ID utilisateur invalide dans le token."));
    }

    private Mono<UploadResults> uploadRequiredFiles(FilePart logo, FilePart doc) {
        return Mono.zip(
                fileStoragePort.uploadFile(logo, "syndicats/logos"),
                fileStoragePort.uploadFile(doc, "syndicats/statuts")
        ).map(tuple -> new UploadResults(tuple.getT1(), tuple.getT2()));
    }

    private Mono<Syndicat> persistSyndicateAndAdmin(UUID creatorId, String name, String description,
                                                    String domain, UploadResults urls) {
        UUID syndicatId = UUID.randomUUID();
        Syndicat syndicat = new Syndicat(
                syndicatId,
                creatorId,
                name,
                description,
                domain,
                urls.logoUrl(),
                urls.docUrl()
        );
        SyndicatMember adminMember = new SyndicatMember(
                syndicatId, creatorId, Instant.now(), true, MemberRole.ADMIN
        );
        return syndicatRepository.save(syndicat)
                .then(syndicatMemberRepository.save(adminMember))
                .thenReturn(syndicat); // Plus propre que .then(Mono.just(...))
    }

    private record UploadResults(String logoUrl, String docUrl) {}



    public Mono<PaginatedResponse<SyndicateResponse>> getAllSyndicates(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Mono<Long> countMono = syndicatRepository.count();

        Mono<List<SyndicateResponse>> contentMono = syndicatRepository.findAllBy(pageRequest)
                .map(syndicateMapper::toResponse)
                .collectList();
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