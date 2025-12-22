package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.SyndicatMember;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface SyndicatMemberRepository extends ReactiveCrudRepository<SyndicatMember, Void> {

    // AJOUTER UN MEMBRE
    @Modifying
    @Query("INSERT INTO syndicat_members (syndicat_id, user_id, joined_at, is_active) VALUES (:syndicatId, :userId, NOW(), :isActive)")
    Mono<Void> addMember(UUID syndicatId, UUID userId, boolean isActive);

    // RETIRER UN MEMBRE
    @Modifying
    @Query("DELETE FROM syndicat_members WHERE syndicat_id = :syndicatId AND user_id = :userId")
    Mono<Void> removeMember(UUID syndicatId, UUID userId);

    // SAVOIR SI MEMBRE
    @Query("SELECT count(*) > 0 FROM syndicat_members WHERE syndicat_id = :syndicatId AND user_id = :userId AND is_active = true")
    Mono<Boolean> isActiveMember(UUID syndicatId, UUID userId);

    // RECUPERER LES IDS DES MEMBRES (Pour charger les Users ensuite)
    @Query("SELECT user_id FROM syndicat_members WHERE syndicat_id = :syndicatId")
    Flux<UUID> findUserIdsBySyndicatId(UUID syndicatId);
}
