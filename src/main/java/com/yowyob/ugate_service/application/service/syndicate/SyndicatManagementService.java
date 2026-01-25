package com.yowyob.ugate_service.application.service.syndicate;

import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.media.FileStoragePort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.PaginatedResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.*;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.enumeration.RoleTypeEnum;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.*;
import com.yowyob.ugate_service.infrastructure.mappers.syndicate.SyndicateMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.dao.DuplicateKeyException;
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
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyndicatManagementService {

    private final SyndicatRepository syndicatRepository;
    private final FileStoragePort fileStoragePort;
    private final SyndicateMapper syndicateMapper;
    private final SyndicatMemberRepository syndicatMemberRepository;
    private final OrganisationRepository organizationRepository;
    private final BusinessActorRepository businessActorRepository;
    private final UserRepository userRepository;
    private final UserGatewayPort userGatewayPort;

    @Transactional
    public Mono<SyndicateResponse> createSyndicate(String name, String description, String domain,
                                                   FilePart logoFile, FilePart documentFile) {
        return extractUserIdFromContext()
                .flatMap(this::ensureUserExistsLocally)
                .flatMap(creatorId -> uploadRequiredFiles(logoFile, documentFile)
                        .flatMap(urls -> persistFullSyndicateStack(creatorId, name, description, domain, urls))
                )
                .map(syndicateMapper::toResponse)
                .doOnSuccess(dto -> log.info("Syndicat et Organisation créés avec succès : ID {}", dto.id()))
                .doOnError(e -> log.error("Erreur fatale lors de la création de la pile syndicale : {}", e.getMessage()));
    }


    private BusinessActor createBusinessActor(UUID userId, String name) {
        return BusinessActor.createNew(userId, name, null, null);
    }

    private Organization createOrganization(UUID id, UUID creatorId, String name) {
        String orgCode = name.toUpperCase().replaceAll("\\s+", "_");
        return new Organization(id, creatorId, orgCode, null, name);
    }

    private Syndicat createSyndicat(UUID id, UUID orgId, UUID creatorId, String name,
                                    String description, String domain, UploadResults urls) {
        return new Syndicat(
                id,
                orgId,
                creatorId,
                false, // isApproved
                name,
                description,
                domain,
                "STANDARD",
                null,
                urls.logoUrl(),
                urls.docUrl(),
                null, null, null, null, false // isActive
        );
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

    private Mono<Syndicat> persistFullSyndicateStack(UUID creatorId, String name, String description,
                                                     String domain, UploadResults urls) {

        UUID orgId = UUID.randomUUID();
        UUID syndicatId = UUID.randomUUID();

        Mono<BusinessActor> businessActorMono = businessActorRepository.findById(creatorId)
                .switchIfEmpty(Mono.defer(() -> {
                    BusinessActor newActor = createBusinessActor(creatorId, name);
                    return businessActorRepository.save(newActor);
                }));

        Organization organization = createOrganization(orgId, creatorId, name);
        Syndicat syndicat = createSyndicat(syndicatId, orgId, creatorId, name, description, domain, urls);
        SyndicatMember adminMember = SyndicatMember.create(syndicatId, creatorId, RoleTypeEnum.ADMIN);

        return businessActorMono
                .then(organizationRepository.save(organization))
                .then(syndicatRepository.save(syndicat))
                .then(syndicatMemberRepository.save(adminMember))
                .thenReturn(syndicat);
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


    //TODO pour ces suite de methodes plutard on va vérifier le role de l'utilisateur connecté
    public Mono<SyndicateResponse> approve(UUID id) {
        return updateState(id, s -> s.withApproval(true), "Approbation");
    }


    public Mono<SyndicateResponse> disapprove(UUID id) {
        return updateState(id, s -> s.withApproval(false), "Désapprobation");
    }


    public Mono<SyndicateResponse> activate(UUID id) {
        return updateState(id, s -> s.withActive(true), "Activation");
    }



    public Mono<SyndicateResponse> deactivate(UUID id) {
        return updateState(id, s -> s.withActive(false), "Désactivation");
    }



    private Mono<UUID> ensureUserExistsLocally(UUID userId) {
        return userRepository.existsById(userId)
                .flatMap(exists -> {
                    if (exists) return Mono.just(userId);

                    log.info("Synchronisation complète de l'utilisateur {} en base locale", userId);
                    return userGatewayPort.findById(userId)
                            .switchIfEmpty(Mono.error(new RuntimeException("Utilisateur introuvable sur TraMaSys")))
                            .flatMap(extUser -> {
                                return userRepository.findByEmail(extUser.email())
                                        .flatMap(existingUser -> {
                                            log.info("Utilisateur existant trouvé par email: {}", existingUser.getId());

                                            return Mono.just(existingUser.getId());
                                        })
                                        .switchIfEmpty(Mono.defer(() -> {

                                            String fullName = extUser.firstName() + " " + extUser.lastName();
                                            User newUser = new User(
                                                    extUser.id(),
                                                    fullName,
                                                    extUser.phone(),
                                                    extUser.email()
                                            );
                                            return userRepository.save(newUser).map(User::getId);
                                        }));
                            });
                });
    }

    private Mono<SyndicateResponse> updateState(UUID id, Function<Syndicat, Syndicat> stateTransformer, String actionName) {
        return syndicatRepository.findById(id)
                .map(stateTransformer)
                .flatMap(syndicatRepository::save)
                .map(syndicateMapper::toResponse)
                .doOnSuccess(s -> log.info("{} réussie pour le syndicat ID: {}", actionName, id))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Syndicat introuvable avec l'ID: " + id)));
    }
}