package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.SyndicatMember;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface SyndicatMemberRepository extends ReactiveCrudRepository<SyndicatMember, Void> {

    @Modifying
    @Query("""
        INSERT INTO syndicat_members (syndicat_id, user_id, branch_id, joined_at, is_active, role) 
        VALUES (:syndicatId, :userId, :branchId, NOW(), :isActive, CAST(:role AS role_type_enum))
    """)
    Mono<Void> insertMember(UUID syndicatId, UUID userId, UUID branchId, boolean isActive, String role);

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

    @Query("SELECT * FROM syndicat_members WHERE user_id = :userId")
    Mono<SyndicatMember> findByUserId(UUID userId);


    @Query("SELECT * FROM syndicat_members WHERE syndicat_id = :syndicatId AND role = CAST(:role AS role_type_enum)")
    Flux<SyndicatMember> findBySyndicatIdAndRole(UUID syndicatId, String role);


    Mono<Long> countByIsActiveTrue();

    Mono<SyndicatMember> findBySyndicatIdAndBranchIdAndUserId(UUID syndicatId, UUID branchId, UUID userId);


    Flux<SyndicatMember> findBySyndicatId(UUID syndicatId);


    Flux<SyndicatMember> findByBranchId(UUID branchId);


    Mono<Long> countBySyndicatIdAndIsActiveTrue(UUID syndicatId);


    Mono<Long> countByBranchIdAndIsActiveTrue(UUID branchId);

    Flux<SyndicatMember> findAllByUserId(UUID userId);


    Mono<SyndicatMember> findBySyndicatIdAndUserId(UUID syndicatId, UUID userId);


    Mono<Boolean> existsBySyndicatIdAndUserId(UUID syndicatId, UUID userId);

}
