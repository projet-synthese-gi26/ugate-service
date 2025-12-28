package com.yowyob.ugate_service.domain.ports.out.gateway;


import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Port de sortie (Output Port) pour la gestion des utilisateurs.
 * Dans l'architecture Hexagonale, cette interface permet au Domaine d'interagir
 * avec les données utilisateurs sans savoir qu'elles viennent d'une API externe (TraMaSys)
 * ou d'un Cache (Redis).
 */

public interface UserGatewayPort {

    /**
     * Récupère les détails d'un utilisateur (Nom, Prénom, Email, Photo).
     *
     * @param id L'identifiant UUID de l'utilisateur (sub du Token)
     * @return L'objet User du domaine (ou Mono.empty() si introuvable)
     */
    Mono<ExternalUserInfo> findById(UUID id);

    /**
     * Met à jour les informations de profil dans le système distant.
     *
     * @param user L'utilisateur avec les nouvelles données
     * @return L'utilisateur mis à jour
     */
    Mono<ExternalUserInfo> updateProfile(ExternalUserInfo user);

    /**
     * Vérifie rapidement si un ID utilisateur est valide/existant.
     *
     * @param id L'identifiant à vérifier
     * @return true si l'utilisateur existe
     */
    Mono<Boolean> existsById(UUID id);
}