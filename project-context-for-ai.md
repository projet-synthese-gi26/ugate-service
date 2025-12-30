# Contexte Complet du Projet

**Projet:** ugate-service  
**Date de génération:** 29/12/2025 07:02:17  
**Chemin:** D:\Projets\Scolaire\Reseau\New Version\ugate-service

---

## Table des matières
1. [Structure du projet](#structure-du-projet)
2. [Contenu des fichiers](#contenu-des-fichiers)
3. [Statistiques](#statistiques)

---

## Structure du projet

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── yowyob
│   │   │           └── ugate_service
│   │   │               ├── application
│   │   │               │   └── service
│   │   │               │       ├── auth
│   │   │               │       │   └── UserManagementService.java
│   │   │               │       ├── compliance
│   │   │               │       │   └── ComplianceService.java
│   │   │               │       ├── content
│   │   │               │       │   ├── EventService.java
│   │   │               │       │   ├── FeedService.java
│   │   │               │       │   ├── InteractionService.java
│   │   │               │       │   └── PublicationService.java
│   │   │               │       ├── marketplace
│   │   │               │       │   ├── ProductService.java
│   │   │               │       │   └── ServiceOfferingService.java
│   │   │               │       ├── messaging
│   │   │               │       │   ├── GroupChatService.java
│   │   │               │       │   └── PrivateChatService.java
│   │   │               │       ├── notification
│   │   │               │       │   └── NotificationService.java
│   │   │               │       ├── syndicate
│   │   │               │       │   ├── BranchManagementService.java
│   │   │               │       │   ├── MembershipService.java
│   │   │               │       │   ├── SyndicateMembershipService.java
│   │   │               │       │   └── SyndicatManagementService.java
│   │   │               │       └── .gitkeep
│   │   │               ├── domain
│   │   │               │   ├── exception
│   │   │               │   │   └── .gitkeep
│   │   │               │   ├── model
│   │   │               │   │   ├── ExternalUserInfo.java
│   │   │               │   │   └── PublicationModel.java
│   │   │               │   └── ports
│   │   │               │       ├── in
│   │   │               │       │   ├── syndicate
│   │   │               │       │   │   ├── ApproveMemberUseCase.java
│   │   │               │       │   │   └── CreateSyndicateUseCase.java
│   │   │               │       │   └── .gitkeep
│   │   │               │       └── out
│   │   │               │           ├── gateway
│   │   │               │           │   └── UserGatewayPort.java
│   │   │               │           ├── media
│   │   │               │           │   └── FileStoragePort.java
│   │   │               │           ├── notification
│   │   │               │           │   └── NotificationPort.java
│   │   │               │           └── syndicate
│   │   │               │               ├── MediaPersistencePort.java
│   │   │               │               ├── PublicationPersistencePort.java
│   │   │               │               └── SyndicatePersistencePort.java
│   │   │               ├── infrastructure
│   │   │               │   ├── adapters
│   │   │               │   │   ├── inbound
│   │   │               │   │   │   ├── kafka
│   │   │               │   │   │   │   └── .gitkeep
│   │   │               │   │   │   └── rest
│   │   │               │   │   │       ├── compliance
│   │   │               │   │   │       │   └── ComplianceController.java
│   │   │               │   │   │       ├── content
│   │   │               │   │   │       │   └── PublicationController.java
│   │   │               │   │   │       ├── dto
│   │   │               │   │   │       │   ├── request
│   │   │               │   │   │       │   │   ├── BatchComplianceRequest.java
│   │   │               │   │   │       │   │   ├── CreatePublicationRequest.java
│   │   │               │   │   │       │   │   ├── CreateSyndicateRequest.java
│   │   │               │   │   │       │   │   ├── FeedbackRequest.java
│   │   │               │   │   │       │   │   ├── MediaResponseDTO.java
│   │   │               │   │   │       │   │   ├── MembershipApprovalRequest.java
│   │   │               │   │   │       │   │   ├── MembershipRequestRequest.java
│   │   │               │   │   │       │   │   └── WebhookStatusChangeRequest.java
│   │   │               │   │   │       │   ├── response
│   │   │               │   │   │       │   │   ├── BasicResponse.java
│   │   │               │   │   │       │   │   ├── BatchComplianceResponse.java
│   │   │               │   │   │       │   │   ├── ComplianceResponse.java
│   │   │               │   │   │       │   │   ├── OfficialProfileResponse.java
│   │   │               │   │   │       │   │   ├── PaginatedResponse.java
│   │   │               │   │   │       │   │   └── SyndicateResponse.java
│   │   │               │   │   │       │   └── .gitkeep
│   │   │               │   │   │       ├── superadmin
│   │   │               │   │   │       │   └── SyndicateSuperAdminController.java
│   │   │               │   │   │       └── syndicate
│   │   │               │   │   │           ├── SyndicateController.java
│   │   │               │   │   │           ├── SyndicateMangementController.java
│   │   │               │   │   │           └── SyndicateMembershipController.java
│   │   │               │   │   └── outbound
│   │   │               │   │       ├── cache
│   │   │               │   │       │   └── .gitkeep
│   │   │               │   │       ├── external
│   │   │               │   │       │   ├── client
│   │   │               │   │       │   │   ├── authentication
│   │   │               │   │       │   │   │   └── TraMaSysUserAdapter.java
│   │   │               │   │       │   │   ├── media
│   │   │               │   │       │   │   │   ├── HttpMediaServiceAdapter.java
│   │   │               │   │       │   │   │   └── MediaService.java
│   │   │               │   │       │   │   ├── notification
│   │   │               │   │       │   │   │   └── HttpNotificationAdapter.java
│   │   │               │   │       │   │   └── .gitkeep
│   │   │               │   │       │   └── .gitkeep
│   │   │               │   │       ├── messaging
│   │   │               │   │       │   └── .gitkeep
│   │   │               │   │       └── persistence
│   │   │               │   │           ├── adapters
│   │   │               │   │           │   ├── MediaPersistenceAdapter.java
│   │   │               │   │           │   └── PublicationPersistenceAdapter.java
│   │   │               │   │           ├── entity
│   │   │               │   │           │   ├── enumeration
│   │   │               │   │           │   │   ├── ComplianceStatus.java
│   │   │               │   │           │   │   └── RoleTypeEnum.java
│   │   │               │   │           │   ├── AbstractProduct.java
│   │   │               │   │           │   ├── Avis.java
│   │   │               │   │           │   ├── Branch.java
│   │   │               │   │           │   ├── BusinessActor.java
│   │   │               │   │           │   ├── Comment.java
│   │   │               │   │           │   ├── ComplianceDetails.java
│   │   │               │   │           │   ├── Country.java
│   │   │               │   │           │   ├── Event.java
│   │   │               │   │           │   ├── EventImages.java
│   │   │               │   │           │   ├── Image.java
│   │   │               │   │           │   ├── MembershipRequest.java
│   │   │               │   │           │   ├── Organization.java
│   │   │               │   │           │   ├── Product.java
│   │   │               │   │           │   ├── Profile.java
│   │   │               │   │           │   ├── Publication.java
│   │   │               │   │           │   ├── PublicationImage.java
│   │   │               │   │           │   ├── PublicationVote.java
│   │   │               │   │           │   ├── Reaction.java
│   │   │               │   │           │   ├── Service.java
│   │   │               │   │           │   ├── Syndicat.java
│   │   │               │   │           │   ├── SyndicatMember.java
│   │   │               │   │           │   ├── User.java
│   │   │               │   │           │   └── Vote.java
│   │   │               │   │           └── repository
│   │   │               │   │               ├── AvisRepository.java
│   │   │               │   │               ├── BranchRepository.java
│   │   │               │   │               ├── BusinessActorRepository.java
│   │   │               │   │               ├── ComplianceDetailsRepository.java
│   │   │               │   │               ├── EventRepository.java
│   │   │               │   │               ├── ImageRepository.java
│   │   │               │   │               ├── MembershipRequestRepository.java
│   │   │               │   │               ├── OrganisationRepository.java
│   │   │               │   │               ├── ProductRepository.java
│   │   │               │   │               ├── ProfileRepository.java
│   │   │               │   │               ├── PublicationImageRepository.java
│   │   │               │   │               ├── PublicationRepository.java
│   │   │               │   │               ├── SyndicatMemberRepository.java
│   │   │               │   │               ├── SyndicatRepository.java
│   │   │               │   │               └── UserRepository.java
│   │   │               │   ├── config
│   │   │               │   │   ├── .gitkeep
│   │   │               │   │   ├── KafkaConfig.java
│   │   │               │   │   ├── OpenApiConfig.java
│   │   │               │   │   ├── RedisConfig.java
│   │   │               │   │   ├── SecurityConfig.java
│   │   │               │   │   ├── ServiceConfig.java
│   │   │               │   │   └── WebClientConfig.java
│   │   │               │   └── mappers
│   │   │               │       ├── media
│   │   │               │       └── syndicate
│   │   │               │           └── SyndicateMapper.java
│   │   │               └── UgateServiceApplication.java
│   │   └── resources
│   │       ├── db
│   │       │   └── changelog
│   │       │       ├── changes
│   │       │       │   └── v1.1-sync-java-entities.xml
│   │       │       └── db.changelog-master.xml
│   │       └── application.properties
│   └── test
│       ├── java
│       │   └── com
│       │       └── yowyob
│       │           └── ugate_service
│       │               ├── PublicationControllerTests.java
│       │               ├── TestSecurityConfig.java
│       │               └── UgateServiceApplicationTests.java
│       └── resources
│           ├── application-test.properties
│           ├── schema.sql
│           └── test-image.png
├── .gitattributes
├── .gitignore
├── compose.yaml
├── generate.js
├── HELP.md
├── mvnw
├── mvnw.cmd
└── pom.xml
```

---

## Contenu des fichiers

### 📄 compose.yaml

```yaml
services:
  postgres:
    image: 'postgres:latest'
    environment:
      - 'POSTGRES_DB=mydatabase'
      - 'POSTGRES_PASSWORD=secret'
      - 'POSTGRES_USER=myuser'
    ports:
      - '5432'
  redis:
    image: 'redis:latest'
    ports:
      - '6379'

```

*Lignes: 14*

---

### 📄 HELP.md

```markdown
# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.yowyob.ugate-service' is invalid and this project uses 'com.yowyob.ugate_service' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.0.1/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.0.1/maven-plugin/build-image.html)
* [Docker Compose Support](https://docs.spring.io/spring-boot/4.0.1/reference/features/dev-services.html#features.dev-services.docker-compose)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/4.0.1/reference/web/reactive.html)
* [Spring Data R2DBC](https://docs.spring.io/spring-boot/4.0.1/reference/data/sql.html#data.sql.r2dbc)
* [Spring Data Reactive Redis](https://docs.spring.io/spring-boot/4.0.1/reference/data/nosql.html#data.nosql.redis)
* [Spring for Apache Kafka](https://docs.spring.io/spring-boot/4.0.1/reference/messaging/kafka.html)
* [Validation](https://docs.spring.io/spring-boot/4.0.1/reference/io/validation.html)
* [Cloud Bootstrap](https://docs.spring.io/spring-cloud-commons/reference/spring-cloud-commons/application-context-services.html)
* [Resilience4J](https://docs.spring.io/spring-cloud-circuitbreaker/reference/spring-cloud-circuitbreaker-resilience4j.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)
* [Accessing data with R2DBC](https://spring.io/guides/gs/accessing-data-r2dbc/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)

### Additional Links
These additional references should also help you:

* [R2DBC Homepage](https://r2dbc.io)

### Docker Compose support
This project contains a Docker Compose file named `compose.yaml`.
In this file, the following services have been defined:

* postgres: [`postgres:latest`](https://hub.docker.com/_/postgres)
* redis: [`redis:latest`](https://hub.docker.com/_/redis)

Please review the tags of the used images and set them to the same as you're running in production.

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.


```

*Lignes: 52*

---

### 📄 pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
		 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.2.6</version>
		<relativePath/>
	</parent>

	<groupId>com.yowyob</groupId>
	<artifactId>ugate-service</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>reactive-hexagonal</name>
	<description>Template Hexagonal Reactive pour com.yowyob</description>

	<properties>
		<java.version>21</java.version>
		<spring-cloud.version>2023.0.0</spring-cloud.version>
		<mapstruct.version>1.5.5.Final</mapstruct.version>
	</properties>

	<dependencies>
		<!-- REACTIVE CORE -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webflux</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>

		<!-- DATA (R2DBC & REDIS) -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-r2dbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>r2dbc-postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.liquibase</groupId>
			<artifactId>liquibase-core</artifactId>
		</dependency>
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis-reactive</artifactId>
		</dependency>

		<!-- MESSAGING (KAFKA) -->
		<dependency>
			<groupId>org.springframework.kafka</groupId>
			<artifactId>spring-kafka</artifactId>
		</dependency>
		<dependency>
			<groupId>io.projectreactor.kafka</groupId>
			<artifactId>reactor-kafka</artifactId>
			<version>1.3.22</version>
		</dependency>

		<!-- CLOUD & RESILIENCE -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-circuitbreaker-reactor-resilience4j</artifactId>
		</dependency>

		<!-- TOOLS (Lombok & Mapstruct) -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.mapstruct</groupId>
			<artifactId>mapstruct</artifactId>
			<version>${mapstruct.version}</version>
		</dependency>

		<!-- Docker Compose Auto-setup -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-docker-compose</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
		</dependency>

		<!-- Test -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.projectreactor</groupId>
			<artifactId>reactor-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.r2dbc</groupId>
			<artifactId>r2dbc-h2</artifactId>
			<scope>test</scope>
		</dependency>

		<!-- Documentation API (Swagger/OpenAPI) -->
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webflux-ui</artifactId>
			<version>2.5.0</version>
		</dependency>
	</dependencies>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.11.0</version>
				<configuration>
					<source>${java.version}</source>
					<target>${java.version}</target>
					<annotationProcessorPaths>
						<path>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
							<version>1.18.30</version>
						</path>
						<path>
							<groupId>org.mapstruct</groupId>
							<artifactId>mapstruct-processor</artifactId>
							<version>${mapstruct.version}</version>
						</path>
						<path>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok-mapstruct-binding</artifactId>
							<version>0.2.0</version>
						</path>
					</annotationProcessorPaths>
				</configuration>
			</plugin>
		</plugins>
	</build>
</project>
```

*Lignes: 193*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\auth\UserManagementService.java

```java
package com.yowyob.ugate_service.application.service.auth;

public class UserManagementService {
}

```

*Lignes: 5*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\compliance\ComplianceService.java

```java
package com.yowyob.ugate_service.application.service.compliance;


import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.FeedbackRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.BatchComplianceResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ComplianceResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.OfficialProfileResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.*;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComplianceService {

    private final ReactiveRedisTemplate<String, ComplianceResponse> redisTemplate;

    // Repositories Locaux (Sources de vérité)
    private final SyndicatMemberRepository memberRepository;
    private final SyndicatRepository syndicatRepository;
    private final AvisRepository avisRepository;
    private final ComplianceDetailsRepository complianceDetailsRepository;
    private final ProfileRepository profileRepository;

    // Gateway vers TraMaSys (Auth)
    private final UserGatewayPort userGatewayPort;

    private static final Duration CACHE_TTL = Duration.ofMinutes(15);


    public Mono<ComplianceResponse> checkCompliance(UUID driverId) {
        String cacheKey = "v2_compliance:" + driverId;

        // 1. Check Cache Redis
        return redisTemplate.opsForValue().get(cacheKey)
                .cast(ComplianceResponse.class)
                .switchIfEmpty(
                        // 2. Cache Miss -> Vérification complète en Base de Données
                        verifyDriverStatusLocally(driverId)
                                .flatMap(response -> redisTemplate.opsForValue()
                                        .set(cacheKey, response, CACHE_TTL)
                                        .thenReturn(response))
                );
    }

    private Mono<ComplianceResponse> verifyDriverStatusLocally(UUID driverId) {

        // 1. On récupère TOUTES les adhésions du chauffeur (renvoie un Flux)
        return memberRepository.findAllByUserId(driverId)
                // 2. Pour chaque adhésion, on récupère les détails du syndicat associé
                .flatMap(member ->
                        syndicatRepository.findById(member.syndicatId())
                                .map(syndicat -> buildPartialResponse(member, syndicat))
                )
                // 3. On rassemble toutes les "décisions partielles" dans une liste
                .collectList()
                .map(partialResults -> {
                    // Si la liste est vide, le chauffeur n'est membre d'aucun syndicat
                    if (partialResults.isEmpty()) {
                        return buildBannedResponse(driverId, "NOT_A_MEMBER");
                    }

                    // RÈGLE MÉTIER : On cherche si au moins UNE des adhésions est valide (statut AUTHORIZED)
                    boolean isAuthorized = partialResults.stream()
                            .anyMatch(res -> "AUTHORIZED".equals(res.globalStatus()));

                    if (isAuthorized) {
                        // Si on trouve une adhésion valide, on retourne ce statut.
                        return partialResults.stream()
                                .filter(res -> "AUTHORIZED".equals(res.globalStatus()))
                                .findFirst().get();
                    } else {
                        // Sinon (toutes les adhésions sont suspendues ou dans des syndicats non approuvés),
                        // on retourne le premier statut "RESTRICTED" trouvé.
                        return partialResults.get(0);
                    }
                })
                // Sécurité : si le flux est vide du début à la fin
                .defaultIfEmpty(buildBannedResponse(driverId, "NOT_A_MEMBER"));
    }

    private ComplianceResponse buildPartialResponse(SyndicatMember member, Syndicat syndicat) {
        boolean isMembershipActive = Boolean.TRUE.equals(member.isActive());
        boolean isSyndicatApproved = Boolean.TRUE.equals(syndicat.isApproved());

        String globalStatus = (isMembershipActive && isSyndicatApproved) ? "AUTHORIZED" : "RESTRICTED";

        List<String> restrictions = new java.util.ArrayList<>();
        if (!isMembershipActive) restrictions.add("MEMBERSHIP_SUSPENDED");
        if (!isSyndicatApproved) restrictions.add("SYNDICATE_NOT_APPROVED");

        return new ComplianceResponse(
                member.userId().toString(),
                Instant.now(),
                globalStatus,
                new ComplianceResponse.ComplianceDetails(isSyndicatApproved, isSyndicatApproved, isMembershipActive, true),
                restrictions
        );
    }

    private ComplianceResponse buildResponseFromEntities(UUID driverId, SyndicatMember member, Syndicat syndicat, ComplianceDetails details) {
        boolean isMembershipActive = Boolean.TRUE.equals(member.isActive());
        boolean isSyndicatApproved = Boolean.TRUE.equals(syndicat.isApproved());

        // Vérification du dossier administratif
        // Le permis est considéré valide si le numéro existe ET que le dossier a été marqué "isVerified" par un admin
        boolean isLicenseValid = details.licenseNumber() != null && !details.licenseNumber().isBlank() && Boolean.TRUE.equals(details.isVerified());

        // TODO: Ajouter table Assurance plus tard. Pour l'instant on considère OK si le dossier est vérifié.
        boolean isInsuranceValid = Boolean.TRUE.equals(details.isVerified());

        // Règle métier globale
        boolean isAuthorized = isMembershipActive && isSyndicatApproved && isLicenseValid;
        String globalStatus = isAuthorized ? "AUTHORIZED" : "RESTRICTED";

        List<String> restrictions = new ArrayList<>();
        if (!isMembershipActive) restrictions.add("MEMBERSHIP_SUSPENDED");
        if (!isSyndicatApproved) restrictions.add("SYNDICATE_NOT_APPROVED");
        if (!isLicenseValid) restrictions.add("DOCUMENTS_NOT_VERIFIED");

        return new ComplianceResponse(
                driverId.toString(),
                Instant.now(),
                globalStatus,
                new ComplianceResponse.ComplianceDetails(
                        isLicenseValid,
                        isInsuranceValid,
                        isMembershipActive,
                        true // Medical check (Mock pour l'instant)
                ),
                restrictions
        );
    }

    private ComplianceResponse buildBannedResponse(UUID driverId, String reason) {
        return new ComplianceResponse(
                driverId.toString(),
                Instant.now(),
                "BANNED",
                new ComplianceResponse.ComplianceDetails(false, false, false, false),
                List.of(reason)
        );
    }


    public Mono<OfficialProfileResponse> getOfficialProfile(UUID driverId) {

        Mono<ExternalUserInfo> userMono = userGatewayPort.findById(driverId);
        Mono<Profile> profile = profileRepository.findByUserId(driverId);

        Mono<ComplianceDetails> detailsMono = complianceDetailsRepository.findById(driverId)
                .defaultIfEmpty(ComplianceDetails.createEmpty(driverId));

        return Mono.zip(userMono, detailsMono, profile)
                .map(tuple -> {
                    ExternalUserInfo user = tuple.getT1();
                    ComplianceDetails details = tuple.getT2();
                    Profile profile1 = tuple.getT3();

                    String officialPhotoUrl = (details.profilePhotoUrl() != null && !details.profilePhotoUrl().isBlank())
                            ? details.profilePhotoUrl()
                            : profile1.profilImageUrl();


                    return new OfficialProfileResponse(
                            user.id().toString(),
                            profile1.firstName(),
                            profile1.lastName(),
                            officialPhotoUrl,
                            details.cvUrl(),
                            details.cniNumber(),
                            details.cniRectoUrl(),
                            details.cniVersoUrl(),
                            details.licenseNumber(),
                            details.licenseRectoUrl(),
                            details.licenseVersoUrl(),
                            details.isVerified()
                    );
                });
    }


    public Mono<BatchComplianceResponse> checkComplianceBatch(List<UUID> driverIds) {

        return Flux.fromIterable(driverIds)
                // Pour chaque ID, on appelle la logique de check unitaire
                .flatMap(driverId -> checkCompliance(driverId)
                        .map(response -> new java.util.AbstractMap.SimpleEntry<>(driverId, response))
                )
                .collect(Collectors.toMap(java.util.Map.Entry::getKey, java.util.Map.Entry::getValue))
                .map(BatchComplianceResponse::new);
    }


    public Mono<Void> processFeedback(FeedbackRequest request) {
        log.info("Enregistrement Feedback local pour chauffeur {}", request.syndicatDriverId());

        try {
            UUID driverId = UUID.fromString(request.syndicatDriverId());

            // On récupère l'ID du syndicat du chauffeur pour lier l'avis correctement
            return memberRepository.findByUserId(driverId)
                    .map(SyndicatMember::syndicatId)
                    .defaultIfEmpty(null) // Si pas membre, syndicatId reste null
                    .flatMap(syndicatId -> {
                        if (syndicatId == null) {
                            log.warn("Feedback reçu pour un chauffeur sans syndicat : {}", driverId);
                        }

                        Avis avis = new Avis(
                                UUID.randomUUID(),
                                null, // User ID noteur (non fourni)
                                null,
                                syndicatId,
                                request.comment(),
                                request.rating(),
                                Instant.now()
                        );
                        return avisRepository.save(avis);
                    })
                    .then();

        } catch (IllegalArgumentException e) {
            return Mono.error(new RuntimeException("ID Chauffeur invalide"));
        }
    }

    public Mono<Void> invalidateCache(UUID driverId) {
        String cacheKey = "compliance:" + driverId;
        log.info("Invalidation manuelle du cache pour le chauffeur : {}", driverId);
        return redisTemplate.opsForValue().delete(cacheKey).then();
    }
}
```

*Lignes: 248*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\content\EventService.java

```java
package com.yowyob.ugate_service.application.service.content;

public class EventService {
}

```

*Lignes: 5*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\content\FeedService.java

```java
package com.yowyob.ugate_service.application.service.content;

public class FeedService {
}

```

*Lignes: 5*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\content\InteractionService.java

```java
package com.yowyob.ugate_service.application.service.content;

public class InteractionService {
}

```

*Lignes: 5*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\content\PublicationService.java

```java
package com.yowyob.ugate_service.application.service.content;

import com.yowyob.ugate_service.domain.model.PublicationModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
public class PublicationService {

    private final PublicationPersistencePort publicationModelRepository;
    private final MediaPersistencePort mediaPersistencePort;

    public Mono<Void> createPublication(UUID authorId, UUID branchId, String content, String[] imagesUrls,
                                        String[] videoUrls, String[] filesUrls) {
        PublicationModel publication = new PublicationModel();
        publication.setAuthorId(authorId);
        publication.setContent(content);
        publication.setBranchI(branchId);
        publication.setNLikes(0L);
        publication.setCreatedAt(Instant.now());

        return publicationModelRepository.save(publication)
                .flatMap(savedPublication -> {
                    Mono<Void> imagesMono = Mono.empty();
                    if (imagesUrls != null) {
                        imagesMono = Flux.fromArray(imagesUrls)
                                .flatMap(imageUrl -> mediaPersistencePort.saveImageMedia(imageUrl, "altText", savedPublication.getId()))
                                .then();
                    }

                    Mono<Void> videosMono = Mono.empty();
                    if (videoUrls != null) {
                        videosMono = Flux.fromArray(videoUrls)
                                .flatMap(videoUrl -> mediaPersistencePort.saveVideoMedia(videoUrl, "title", savedPublication.getId()))
                                .then();
                    }

                    Mono<Void> filesMono = Mono.empty();
                    if (filesUrls != null) {
                        filesMono = Flux.fromArray(filesUrls)
                                .flatMap(fileUrl -> mediaPersistencePort.saveAudioMedia(fileUrl, "title", savedPublication.getId()))
                                .then();
                    }

                    return Mono.when(imagesMono, videosMono, filesMono);
                });
    }
}


```

*Lignes: 56*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\marketplace\ProductService.java

```java
package com.yowyob.ugate_service.application.service.marketplace;

public class ProductService {
}

```

*Lignes: 5*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\marketplace\ServiceOfferingService.java

```java
package com.yowyob.ugate_service.application.service.marketplace;

public class ServiceOfferingService {
}

```

*Lignes: 5*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\messaging\GroupChatService.java

```java
package com.yowyob.ugate_service.application.service.messaging;

public class GroupChatService {
}

```

*Lignes: 5*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\messaging\PrivateChatService.java

```java
package com.yowyob.ugate_service.application.service.messaging;

public class PrivateChatService {
}

```

*Lignes: 5*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\notification\NotificationService.java

```java
package com.yowyob.ugate_service.application.service.notification;

public class NotificationService {
}

```

*Lignes: 5*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\syndicate\BranchManagementService.java

```java
package com.yowyob.ugate_service.application.service.syndicate;

public class BranchManagementService {
}

```

*Lignes: 5*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\syndicate\MembershipService.java

```java
package com.yowyob.ugate_service.application.service.syndicate;

public class MembershipService {
}

```

*Lignes: 5*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\syndicate\SyndicateMembershipService.java

```java
package com.yowyob.ugate_service.application.service.syndicate;


import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.MembershipRequest;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Profile;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.SyndicatMember;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.User;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.enumeration.RoleTypeEnum;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyndicateMembershipService {

    private final MembershipRequestRepository requestRepository;
    private final SyndicatMemberRepository memberRepository;
    private final UserGatewayPort userGateway;
    private final NotificationPort notificationPort;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final SyndicatRepository syndicatRepository;


    /**
     * Un utilisateur demande à rejoindre un syndicat via une branche.
     */
    @Transactional
    public Mono<MembershipRequest> requestToJoin(UUID syndicatId, UUID branchId, String motivation) {
        return getCurrentUserId()
                .flatMap(userId ->

                        requestRepository.findLastRequest(userId, syndicatId)
                                .flatMap(lastRequest -> {
                                    if (lastRequest.status() == MembershipRequest.MembershipStatus.PENDING) {
                                        return Mono.error(new IllegalStateException("Vous avez déjà une demande en attente."));
                                    }
                                    if (lastRequest.status() == MembershipRequest.MembershipStatus.APPROVED) {
                                        return Mono.error(new IllegalStateException("Vous êtes déjà membre de ce syndicat."));
                                    }

                                    return createNewRequest(userId, syndicatId, branchId, motivation);
                                })
                                .switchIfEmpty(createNewRequest(userId, syndicatId, branchId, motivation))
                );
    }

    private Mono<MembershipRequest> createNewRequest(UUID userId, UUID syndicatId, UUID branchId, String motivation) {
        MembershipRequest newRequest = new MembershipRequest(
                null, userId, syndicatId, branchId, MembershipRequest.MembershipStatus.PENDING,
                motivation, null, Instant.now(), Instant.now()
        );
        return requestRepository.save(newRequest);
    }

    /**
     * Un admin de syndicat approuve ou rejette une demande.
     */
    @Transactional
    public Mono<Void> processRequest(UUID requestId, boolean approve, String rejectionReason) {
        // TODO: Vérifier que l'utilisateur courant a les droits d'admin sur le syndicat

        return requestRepository.findById(requestId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Demande introuvable")))
                .flatMap(request -> {
                    if (request.status() != MembershipRequest.MembershipStatus.PENDING) {
                        return Mono.error(new IllegalStateException("Cette demande a déjà été traitée."));
                    }

                    if (approve) {
                        // 1. Mettre à jour la demande en APPROUVÉ
                        MembershipRequest approvedRequest = new MembershipRequest(
                                request.id(), request.userId(), request.syndicatId(), request.branchId(),
                                MembershipRequest.MembershipStatus.APPROVED, request.motivation(),
                                null, request.createdAt(), Instant.now()
                        );

                        // 2. Créer l'entrée dans la table des membres
                        SyndicatMember newMember = new SyndicatMember(
                                request.syndicatId(), request.userId(), Instant.now(), true, RoleTypeEnum.CLIENT
                        );

                        // On combine les deux sauvegardes dans une transaction
                        return requestRepository.save(approvedRequest)
                                .then(memberRepository.save(newMember))
                                .then();
                    } else {
                        // Rejet
                        if (rejectionReason == null || rejectionReason.isBlank()) {
                            return Mono.error(new IllegalArgumentException("Le motif de rejet est obligatoire."));
                        }
                        MembershipRequest rejectedRequest = new MembershipRequest(
                                request.id(), request.userId(), request.syndicatId(), request.branchId(),
                                MembershipRequest.MembershipStatus.REJECTED, request.motivation(),
                                rejectionReason, request.createdAt(), Instant.now()
                        );
                        return requestRepository.save(rejectedRequest).then();
                    }
                });
    }


    @Transactional
    public Mono<Void> addMemberByAdmin(UUID syndicateId, String email, String firstName, String lastName) {
        return syndicatRepository.findById(syndicateId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Syndicat introuvable")))
                .flatMap(syndicate ->

                        userGateway.registerUser(email, firstName, lastName, "00000000")
                                .flatMap(extUser -> {
                                    UUID userId = extUser.id();


                                    User localUser = new User(userId, firstName + " "+ lastName, null, email);
                                    Profile localProfile = new Profile(
                                            UUID.randomUUID(), userId, null, firstName, lastName,
                                            null, null, null, null, Instant.now(), Instant.now()
                                    );


                                    SyndicatMember member = SyndicatMember.create(syndicateId, userId, RoleTypeEnum.CLIENT);

                                    return userRepository.save(localUser)
                                            .then(profileRepository.save(localProfile))
                                            .then(memberRepository.save(member))
                                            .then(notificationPort.sendSyndicateInvitation(email, syndicate.name(), firstName));
                                })
                );
    }

    private Mono<UUID> getCurrentUserId() {
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> UUID.fromString(((Jwt) ctx.getAuthentication().getPrincipal()).getSubject()));
    }
}
```

*Lignes: 148*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\syndicate\SyndicatManagementService.java

```java
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
                null, null, null, null, true // isActive
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


        BusinessActor actor = createBusinessActor(creatorId, name);
        Organization organization = createOrganization(orgId, creatorId, name);
        Syndicat syndicat = createSyndicat(syndicatId, orgId, creatorId, name, description, domain, urls);
        SyndicatMember adminMember = SyndicatMember.create(syndicatId, creatorId, RoleTypeEnum.ADMIN);

        return businessActorRepository.save(actor)
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

    // Dans SyndicatManagementService.java

    private Mono<UUID> ensureUserExistsLocally(UUID userId) {
        return userRepository.existsById(userId)
                .flatMap(exists -> {
                    if (exists) return Mono.just(userId);

                    log.info("Synchronisation complète de l'utilisateur {} en base locale", userId);
                    return userGatewayPort.findById(userId)
                            .switchIfEmpty(Mono.error(new RuntimeException("Utilisateur introuvable sur TraMaSys")))
                            .flatMap(extUser -> {
                                String fullName = extUser.firstName() + " " + extUser.lastName();
                                User newUser = new User(
                                        extUser.id(),
                                        fullName,
                                        extUser.phone(),
                                        extUser.email()
                                );
                                return userRepository.save(newUser);
                            })
                            .thenReturn(userId);
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
```

*Lignes: 200*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\model\ExternalUserInfo.java

```java
package com.yowyob.ugate_service.domain.model;

import java.util.List;
import java.util.UUID;

public record ExternalUserInfo(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        List<String> permissions,
        List<String> roles
)
{}

```

*Lignes: 16*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\model\PublicationModel.java

```java
package com.yowyob.ugate_service.domain.model;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class PublicationModel {
  UUID id;

  UUID branchI; // FK -> Branch

  UUID authorId; // FK -> User

  String content;
  // String status, // Ex: "PUBLISHED", "DRAFT"

  Long nLikes; // Compteur de likes

  Instant createdAt;
}
```

*Lignes: 22*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\in\syndicate\ApproveMemberUseCase.java

```java
package com.yowyob.ugate_service.domain.ports.in.syndicate;

public class ApproveMemberUseCase {
}

```

*Lignes: 5*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\in\syndicate\CreateSyndicateUseCase.java

```java
package com.yowyob.ugate_service.domain.ports.in.syndicate;

public class CreateSyndicateUseCase {
}

```

*Lignes: 5*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\gateway\UserGatewayPort.java

```java
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

    /**
     * Enregistre un nouvel utilisateur dans le système d'authentification externe.
     *
     * @param email     Email (servira d'identifiant)
     * @param firstName Prénom
     * @param lastName  Nom
     * @param password  Mot de passe initial
     * @return Les informations de l'utilisateur créé
     */
    Mono<ExternalUserInfo> registerUser(String email, String firstName, String lastName, String password);
}
```

*Lignes: 54*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\media\FileStoragePort.java

```java
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
```

*Lignes: 14*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\notification\NotificationPort.java

```java
package com.yowyob.ugate_service.domain.ports.out.notification;

import reactor.core.publisher.Mono;

public interface NotificationPort {
    Mono<Void> sendSyndicateInvitation(String email, String syndicateName, String firstName);
}
```

*Lignes: 7*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\syndicate\MediaPersistencePort.java

```java
package com.yowyob.ugate_service.domain.ports.out.syndicate;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface MediaPersistencePort {
  Mono<Void> saveImageMedia(String imageUrl, String altText, UUID publicationId);

  Mono<Void> saveVideoMedia(String videoUrl, String title, UUID publicationId);

  Mono<Void> saveAudioMedia(String audioUrl, String title, UUID publicationId);
}


```

*Lignes: 15*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\syndicate\PublicationPersistencePort.java

```java
package com.yowyob.ugate_service.domain.ports.out.syndicate;

import com.yowyob.ugate_service.domain.model.PublicationModel;
import reactor.core.publisher.Mono;

public interface PublicationPersistencePort {

  Mono<PublicationModel> save(PublicationModel publicationModel);
}


```

*Lignes: 11*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\syndicate\SyndicatePersistencePort.java

```java
package com.yowyob.ugate_service.domain.ports.out.syndicate;

public class SyndicatePersistencePort {
}

```

*Lignes: 5*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\compliance\ComplianceController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.compliance;


import com.yowyob.ugate_service.application.service.compliance.ComplianceService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.BatchComplianceRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.FeedbackRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.WebhookStatusChangeRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.BatchComplianceResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ComplianceResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.OfficialProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/compliance")
@RequiredArgsConstructor
@Tag(name = "Conformité (Middleware)", description = "API  pour la vérification temps réel des chauffeurs, la récupération du Jumeau Numérique et la gestion de la réputation.")
public class ComplianceController {

    private final ComplianceService complianceService;


    @Operation(
            summary = "Vérifier l'éligibilité d'un chauffeur (Check-In)",
            description = "Appelé par les services comme Ride & Go avant la mise en ligne. Vérifie le statut syndical, les suspensions et la validité du dossier. Utilise un cache Redis pour la haute performance.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vérification effectuée (Voir le champ 'globalStatus' dans la réponse)",
                    content = @Content(schema = @Schema(implementation = ComplianceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Format de l'ID invalide"),
            @ApiResponse(responseCode = "401", description = "Non authentifié")
    })
    @GetMapping("/check/{driverId}")
    public Mono<ResponseEntity<ComplianceResponse>> checkEligibility(
            @Parameter(description = "UUID du chauffeur (User ID)", required = true)
            @PathVariable UUID driverId
    ) {
        return complianceService.checkCompliance(driverId)
                .map(ResponseEntity::ok);
    }


    @Operation(
            summary = "Récupérer le profil officiel (Jumeau Numérique)",
            description = "Récupère les données certifiées par le syndicat (Nom légal, Photo officielle, Véhicule déclaré) pour affichage client.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profil trouvé",
                    content = @Content(schema = @Schema(implementation = OfficialProfileResponse.class))),
            @ApiResponse(responseCode = "404", description = "Chauffeur introuvable ou non syndiqué")
    })
    @GetMapping("/details/{driverId}")
    public Mono<ResponseEntity<OfficialProfileResponse>> getOfficialProfile(
            @Parameter(description = "UUID du chauffeur", required = true)
            @PathVariable UUID driverId
    ) {
        return complianceService.getOfficialProfile(driverId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Soumettre un avis ou un signalement",
            description = "Permet à Ride & Go de transmettre un feedback (Note, Commentaire, Incident grave) vers le dossier syndical du chauffeur. Traitement asynchrone.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Feedback accepté pour traitement"),
            @ApiResponse(responseCode = "400", description = "Données invalides (ex: Note > 5, ID manquant)")
    })
    @PostMapping("/feedback")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> submitFeedback(
            @Parameter(description = "Détails de l'avis ou de l'incident", required = true)
            @Valid @RequestBody FeedbackRequest request
    ) {
        log.info("Réception signalement (RideID: {})", request.rideGoTripId());
        return complianceService.processFeedback(request);
    }


    @PostMapping("/check/batch")
    @Operation(summary = "Vérifier l'éligibilité d'un lot de chauffeurs")
    public Mono<BatchComplianceResponse> checkEligibilityBatch(@RequestBody BatchComplianceRequest request) {
        return complianceService.checkComplianceBatch(request.driverIds());
    }

    @PostMapping("/syndicats/status-change")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Notification de changement de statut (Bannissement)")
    public Mono<Void> onDriverStatusChange(@RequestBody WebhookStatusChangeRequest request) {
        return complianceService.invalidateCache(request.driverId());
    }

}
```

*Lignes: 114*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\content\PublicationController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.content;

import com.yowyob.ugate_service.application.service.content.PublicationService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreatePublicationRequest;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.media.MediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/publications")
@RequiredArgsConstructor
@Validated
@Tag(name = "Publications", description = "API for managing publications")
public class PublicationController {

    private final PublicationService publicationService;
    private final MediaService mediaService;

    @Operation(summary = "Create a new publication",
            description = "Creates a new publication with optional image, video, and file attachments.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Publication created successfully",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<Void>> createPublication(
            @Parameter(description = "Publication request data including content, author, branch, and optional media files",
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = CreatePublicationRequest.class)))
            @Valid @ModelAttribute CreatePublicationRequest request) {

        Mono<List<String>> imagesUrlsMono = mediaService.uploadImage(request.getImages());
        Mono<List<String>> videosUrlsMono = mediaService.uploadVideo(request.getVideos());
        Mono<List<String>> filesUrlsMono = mediaService.uploadFiles(request.getFiles());

        return Mono.zip(imagesUrlsMono, videosUrlsMono, filesUrlsMono)
                .flatMap(tuple -> {
                    List<String> imageUrls = tuple.getT1();
                    List<String> videoUrls = tuple.getT2();
                    List<String> fileUrls = tuple.getT3();

                    return publicationService.createPublication(
                            UUID.fromString(request.getAuthorId()),
                            UUID.fromString(request.getBranchId()),
                            request.getContent(),
                            imageUrls.toArray(new String[0]),
                            videoUrls.toArray(new String[0]),
                            fileUrls.toArray(new String[0])
                    );
                })
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
    }
}

```

*Lignes: 72*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\BatchComplianceRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

public record BatchComplianceRequest(
        java.util.List<java.util.UUID> driverIds
) {}

```

*Lignes: 6*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\CreatePublicationRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.http.codec.multipart.FilePart;

@Data
@Schema(name = "CreatePublicationRequest", description = "Request DTO for creating a new publication")
public class CreatePublicationRequest {
    @NotBlank(message = "Content cannot be blank")
    @Schema(description = "The main textual content of the publication", example = "This is a new post about my day.")
    private String content;

    @NotNull(message = "Author ID cannot be null")
    @Schema(description = "The unique identifier of the author of the publication", example = "a1b2c3d4-e5f6-7890-1234-567890abcdef")
    private String authorId;

    @NotNull(message = "Branch ID cannot be null")
    @Schema(description = "The unique identifier of the branch to which the publication belongs", example = "f1e2d3c4-b5a6-9876-5432-10fedcba9876")
    private String branchId;

    @Schema(description = "An array of image files to be attached to the publication")
    private FilePart[] images;

    @Schema(description = "An array of video files to be attached to the publication")
    private FilePart[] videos;

    @Schema(description = "An array of general files to be attached to the publication")
    private FilePart[] files;
}

```

*Lignes: 33*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\CreateSyndicateRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

public class CreateSyndicateRequest {
}

```

*Lignes: 5*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\FeedbackRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;



import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.List;

public record FeedbackRequest(

        @NotBlank(message = "L'ID du trajet Ride & Go est obligatoire")
        String rideGoTripId,

        @NotBlank(message = "L'ID du chauffeur est obligatoire")
        String syndicatDriverId,

        @NotNull(message = "La date de l'incident est obligatoire")
        Instant incidentDate,

        @NotNull(message = "La note est obligatoire")
        @Min(value = 1, message = "La note doit être comprise entre 1 et 5")
        @Max(value = 5, message = "La note doit être comprise entre 1 et 5")
        Integer rating,

        @NotBlank(message = "La sévérité est obligatoire (LOW, MEDIUM, HIGH, CRITICAL)")
        String severity,

        // Liste optionnelle de catégories (ex: ["DANGEROUS_DRIVING"])
        List<String> categories,

        String comment,

        @NotBlank(message = "Le hash du passager est obligatoire pour l'anonymat")
        String passengerHash
) {}

```

*Lignes: 40*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\MediaResponseDTO.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

public record MediaResponseDTO(
        String id,
        String uri,
        String path,
        String name,
        String mime
) {}

```

*Lignes: 10*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\MembershipApprovalRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

public record MembershipApprovalRequest(
        boolean approve,
        String rejectionReason
) {}
```

*Lignes: 6*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\MembershipRequestRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import java.util.UUID;

public record MembershipRequestRequest(
        UUID branchId,
        String motivation
) {}

```

*Lignes: 9*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\WebhookStatusChangeRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

public record WebhookStatusChangeRequest(
        java.util.UUID driverId,
        String newStatus, // BANNED, SUSPENDED, ACTIVE
        String reason
) {}

```

*Lignes: 8*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\response\BasicResponse.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

public record BasicResponse (
        String message
){
}

```

*Lignes: 7*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\response\BatchComplianceResponse.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

public record BatchComplianceResponse(
        java.util.Map<java.util.UUID, ComplianceResponse> results
) {}
```

*Lignes: 5*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\response\ComplianceResponse.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;



import java.time.Instant;
import java.util.List;

public record ComplianceResponse(
        String syndicatDriverId,
        Instant verificationTimestamp,
        String globalStatus, // AUTHORIZED, RESTRICTED, BANNED
        ComplianceDetails details,
        List<String> restrictions
) {
    public record ComplianceDetails(
            boolean licenseValid,
            boolean insuranceValid,
            boolean membershipCurrent,
            boolean medicalCheck
    ) {}
}
```

*Lignes: 21*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\response\OfficialProfileResponse.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;



public record OfficialProfileResponse(
        String id,
        String firstName,
        String lastName,
        String photoUrl,
        // Détails de conformité
        String cvUrl,
        String cniNumber,
        String cniRectoUrl,
        String cniVersoUrl,
        String licenseNumber,
        String licenseRectoUrl,
        String licenseVersoUrl,
        Boolean isVerified
) {}

```

*Lignes: 20*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\response\PaginatedResponse.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;



import java.util.List;

public record PaginatedResponse<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages
) {
    public static <T> PaginatedResponse<T> of(List<T> content, int page, int size, long totalElements) {
        int totalPages = (int) Math.ceil((double) totalElements / size);
        return new PaginatedResponse<>(content, page, size, totalElements, totalPages);
    }
}
```

*Lignes: 18*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\response\SyndicateResponse.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import java.time.Instant;
import java.util.UUID;

public record SyndicateResponse(
        UUID id,
        String name,
        String description,
        String domain,
        Boolean isApproved,
        String logoUrl,
        String statusUrl,
        UUID creatorId,
        Instant createdAt
) {}
```

*Lignes: 16*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\superadmin\SyndicateSuperAdminController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.superadmin;

import com.yowyob.ugate_service.application.service.syndicate.SyndicatManagementService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/super-admin/syndicates")
@RequiredArgsConstructor
@Tag(name = "SuperAdmin Management", description = "Gestion de l'état d'un Syndicat ( l'approuver, le désactiver, etc)")
public class SyndicateSuperAdminController {

    private final SyndicatManagementService syndicateService;



    @Operation(
            summary = "Approuver un syndicat",
            description = "Valide officiellement le syndicat après vérification des documents.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PatchMapping("/{id}/approve")
    public Mono<SyndicateResponse> approveSyndicate(@PathVariable UUID id) {
        log.info("Requête d'approbation pour le syndicat: {}", id);
        return syndicateService.approve(id);
    }

    @Operation(summary = "Désapprouver un syndicat", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping("/{id}/disapprove")
    public Mono<SyndicateResponse> disapproveSyndicate(@PathVariable UUID id) {
        log.info("Requête de désapprobation pour le syndicat: {}", id);
        return syndicateService.disapprove(id);
    }

    @Operation(
            summary = "Activer un syndicat",
            description = "Rend le syndicat visible et opérationnel sur la plateforme.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PatchMapping("/{id}/activate")
    public Mono<SyndicateResponse> activateSyndicate(@PathVariable UUID id) {
        log.info("Requête d'activation pour le syndicat: {}", id);
        return syndicateService.activate(id);
    }

    @Operation(summary = "Désactiver un syndicat", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping("/{id}/deactivate")
    public Mono<SyndicateResponse> deactivateSyndicate(@PathVariable UUID id) {
        log.info("Requête de désactivation pour le syndicat: {}", id);
        return syndicateService.deactivate(id);
    }
}

```

*Lignes: 66*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\syndicate\SyndicateController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.syndicate;


import com.yowyob.ugate_service.application.service.syndicate.SyndicatManagementService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.PaginatedResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/syndicates")
@RequiredArgsConstructor
@Tag(name = "Syndicats", description = "Gestion du cycle de vie des syndicats (Création, Recherche, Profil)")
public class SyndicateController {

    private final SyndicatManagementService syndicateService;

    @Operation(
            summary = "Créer un nouveau syndicat",
            description = "Permet à un utilisateur authentifié de soumettre une demande de création de syndicat. Nécessite l'upload du logo et des statuts.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Demande de création enregistrée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SyndicateResponse.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides ou fichiers manquants"),
            @ApiResponse(responseCode = "401", description = "Utilisateur non authentifié"),
            @ApiResponse(responseCode = "415", description = "Type de média non supporté (attendu: multipart/form-data)")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<SyndicateResponse> createSyndicate(
            @Parameter(description = "Nom officiel du syndicat", required = true)
            @RequestPart("name") String name,

            @Parameter(description = "Description complète et objectifs", required = true)
            @RequestPart("description") String description,

            @Parameter(description = "Domaine d'activité (ex: Transport, Commerce)", required = true)
            @RequestPart("domain") String domain,

            @Parameter(description = "Fichier image du logo (PNG/JPG)", required = true)
            @RequestPart("logo") FilePart logo,

            @Parameter(description = "Document officiel des statuts (PDF)", required = true)
            @RequestPart("document") FilePart document
    ) {
        log.debug("Réception demande création syndicat: {}", name);
        return syndicateService.createSyndicate(name, description, domain, logo, document);
    }



    @Operation(summary = "Lister les syndicats", description = "Récupère une liste paginée des syndicats enregistrés.", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public Mono<PaginatedResponse<SyndicateResponse>> getAllSyndicates(
            @Parameter(description = "Numéro de la page (0..N)")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Nombre d'éléments par page")
            @RequestParam(defaultValue = "10") int size
    ) {
        return syndicateService.getAllSyndicates(page, size);
    }



    @Operation(
            summary = "Lister les syndicats d'un utilisateur",
            description = "Récupère la liste de tous les syndicats dont l'utilisateur spécifié est membre.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste récupérée (peut être vide)")
    })
    @GetMapping("/user/{userId}")
    public Flux<SyndicateResponse> getUserSyndicates(
            @Parameter(description = "UUID de l'utilisateur", required = true)
            @PathVariable UUID userId
    ) {
        return syndicateService.getUserSyndicates(userId);
    }


    @Operation(summary = "Mes syndicats", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/mine")
    public Flux<SyndicateResponse> getMySyndicates() {
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> {
                    Jwt jwt = (Jwt) ctx.getAuthentication().getPrincipal();
                    return UUID.fromString(jwt.getSubject());
                })
                .flatMapMany(currentUserId -> syndicateService.getUserSyndicates(currentUserId));
    }
}
```

*Lignes: 114*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\syndicate\SyndicateMangementController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.syndicate;


import com.yowyob.ugate_service.application.service.syndicate.MembershipService;
import com.yowyob.ugate_service.application.service.syndicate.SyndicateMembershipService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.BasicResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/admin/syndicates")
@RequiredArgsConstructor
@Tag(name = "Syndicat Admin Management", description = "Gestion de l'état d'un Syndicat ( l'approuver, le désactiver, etc)")
public class SyndicateMangementController {
    private final SyndicateMembershipService membershipService;
    @PostMapping("/admin/add-member")
    @Operation(summary = "Ajouter un membre manuellement (Admin)")
    public Mono<ResponseEntity<BasicResponse>> addMember(
            @PathVariable UUID syndicatId,
            @RequestBody AddMemberRequest request) {

        return membershipService.addMemberByAdmin(
                syndicatId,
                request.email(),
                request.firstName(),
                request.lastName()
        ).thenReturn(ResponseEntity.ok(new BasicResponse("Membre invité avec succès")));
    }

    public record AddMemberRequest(String email, String firstName, String lastName) {}
}

```

*Lignes: 40*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\syndicate\SyndicateMembershipController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.syndicate;


import com.yowyob.ugate_service.application.service.syndicate.SyndicateMembershipService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.MembershipApprovalRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.MembershipRequestRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/syndicates/{syndicatId}/memberships")
@RequiredArgsConstructor
@Tag(name = "Adhésions", description = "Gestion des demandes et statuts des membres")
public class SyndicateMembershipController {

    private final SyndicateMembershipService membershipService;

    @PostMapping("/request")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Demander à rejoindre un syndicat")
    public Mono<?> requestToJoin(@PathVariable UUID syndicatId, @Valid @RequestBody MembershipRequestRequest request) {
        return membershipService.requestToJoin(syndicatId, request.branchId(), request.motivation());
    }

    @PostMapping("/requests/{requestId}/process")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Traiter une demande d'adhésion (Admin)")
    public Mono<Void> processRequest(@PathVariable UUID syndicatId, @PathVariable UUID requestId, @Valid @RequestBody MembershipApprovalRequest request) {
        // La validation de l'admin se fera dans le service
        return membershipService.processRequest(requestId, request.approve(), request.rejectionReason());
    }
}
```

*Lignes: 39*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\external\client\authentication\TraMaSysUserAdapter.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.authentication;

import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class TraMaSysUserAdapter implements UserGatewayPort {

    private final WebClient webClient;
    private final ReactiveRedisTemplate<String, ExternalUserInfo> userRedisTemplate;
    private static final Duration CACHE_TTL = Duration.ofHours(1);

    public TraMaSysUserAdapter(WebClient.Builder builder,
                               @Value("${application.external.auth-service-url}") String authUrl,
                               ReactiveRedisTemplate<String, ExternalUserInfo> userRedisTemplate) {
        this.webClient = builder.baseUrl(authUrl).build();
        this.userRedisTemplate = userRedisTemplate;
    }

    @Override
    public Mono<ExternalUserInfo> registerUser(String email, String firstName, String lastName, String password) {
        var registerRequest = new RegisterRequest(
                email,      // username = email
                password,
                email,
                null,
                firstName,
                lastName,
                "SYNDICAT",
                List.of("CLIENT")
        );

        return webClient.post()
                .uri("/api/auth/register")
                .bodyValue(registerRequest)
                .retrieve()
                .bodyToMono(AuthResponse.class)
                .map(response -> mapToDomain(response.user()))
                .doOnSuccess(user -> log.info("Utilisateur créé sur TraMaSys : {}", user.id()))
                .doOnError(e -> log.error("Erreur registration TraMaSys : {}", e.getMessage()));
    }

    @Override
    public Mono<ExternalUserInfo> findById(UUID id) {
        String cacheKey = "v2_user:" + id;
        return userRedisTemplate.opsForValue().get(cacheKey)
                .cast(ExternalUserInfo.class)
                .switchIfEmpty(webClient.get()
                        .uri("/api/users/{id}", id)
                        .retrieve()
                        .bodyToMono(TraMaSysUserDTO.class)
                        .map(this::mapToDomain)
                        .flatMap(dto -> userRedisTemplate.opsForValue()
                                .set(cacheKey, dto, CACHE_TTL)
                                .thenReturn(dto)));
    }

    @Override
    public Mono<ExternalUserInfo> updateProfile(ExternalUserInfo user) {
        // Implémentation via PUT /api/users/{id}
        var updateRequest = new UserUpdateRequest(user.firstName(), user.lastName(), user.phone());
        return webClient.put()
                .uri("/api/users/{id}", user.id())
                .bodyValue(updateRequest)
                .retrieve()
                .bodyToMono(TraMaSysUserDTO.class)
                .map(this::mapToDomain)
                .doOnSuccess(u -> userRedisTemplate.opsForValue().delete("ext_user:" + u.id()).subscribe());
    }

    @Override
    public Mono<Boolean> existsById(UUID id) {
        return findById(id).map(u -> true).defaultIfEmpty(false);
    }


    private record RegisterRequest(String username, String password, String email, String phone,
                                   String firstName, String lastName, String service, List<String> roles) {}

    private record AuthResponse(TraMaSysUserDTO user) {}

    private record TraMaSysUserDTO(String id, String firstName, String lastName, String email,
                                   String phone, List<String> permissions, List<String> roles) {}

    private record UserUpdateRequest(String firstName, String lastName, String phone) {}

    private ExternalUserInfo mapToDomain(TraMaSysUserDTO dto) {
        return new ExternalUserInfo(
                UUID.fromString(dto.id()),
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                dto.phone(),
                dto.permissions(),
                dto.roles()
        );
    }
}
```

*Lignes: 109*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\external\client\media\HttpMediaServiceAdapter.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.media;

import com.yowyob.ugate_service.domain.ports.out.media.FileStoragePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class HttpMediaServiceAdapter implements FileStoragePort {

    private final WebClient webClient;
    private final String serviceName;
    private final String mediaServiceBaseUrl;

    public HttpMediaServiceAdapter(WebClient.Builder builder,
                                   @Value("${application.external.media-service-url}") String mediaUrl,
                                   @Value("${application.external.media-service-name}") String serviceName) {
        this.webClient = builder.baseUrl(mediaUrl).build();
        this.mediaServiceBaseUrl = mediaUrl;
        this.serviceName = serviceName;
    }

    @Override
    public Mono<String> uploadFile(FilePart file, String location) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();

        MediaType contentType = file.headers().getContentType();
        if (contentType == null) contentType = MediaType.APPLICATION_OCTET_STREAM;

        builder.asyncPart("file", file.content(), DataBuffer.class)
                .filename(file.filename())
                .contentType(contentType);

        builder.part("service", serviceName != null ? serviceName : "UGATE_DEFAULT");
        builder.part("location", location);

        return webClient.post()
                .uri("/media/upload")
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .onStatus(status -> status.isError(), response -> {
                    return response.bodyToMono(String.class)
                            .flatMap(errorBody -> Mono.error(new RuntimeException(
                                    "Erreur Media Service (" + response.statusCode() + ") : " + errorBody
                            )));
                })
                .bodyToMono(MediaResponseDTO.class)
                .map(this::constructPublicUrl) // <--- CHANGEMENT ICI
                .doOnError(e -> log.error("Échec upload vers {} : {}", location, e.getMessage()));
    }

    /**
     * Construit l'URL publique de téléchargement.
     * Utilise l'endpoint /media/proxy/{id} défini dans le Swagger du Media Service.
     */
    private String constructPublicUrl(MediaResponseDTO dto) {
        String baseUrl = this.mediaServiceBaseUrl.endsWith("/")
                ? this.mediaServiceBaseUrl.substring(0, this.mediaServiceBaseUrl.length() - 1)
                : this.mediaServiceBaseUrl;

        return baseUrl + "/media/proxy/" + dto.id();
    }


    record MediaResponseDTO(String id, String uri, String path, String name, String mime) {}
}
```

*Lignes: 74*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\external\client\media\MediaService.java

```java
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
```

*Lignes: 79*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\external\client\notification\HttpNotificationAdapter.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.notification;

import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;


@Slf4j
@Component
public class HttpNotificationAdapter implements NotificationPort {

    private final WebClient webClient;
    private final String serviceToken;
    private final Integer inviteTemplateId;

    public HttpNotificationAdapter(WebClient.Builder builder,
                                   @Value("${application.external.notification-service-url}") String notificationUrl,
                                   @Value("${application.external.notification-service-token}") String serviceToken,
                                   @Value("${application.external.notification-invite-template-id}") Integer inviteTemplateId) {
        this.webClient = builder.baseUrl(notificationUrl).build();
        this.serviceToken = serviceToken;
        this.inviteTemplateId = inviteTemplateId;
    }

    @Override
    public Mono<Void> sendSyndicateInvitation(String email, String syndicateName, String firstName) {

        Map<String, Object> requestBody = Map.of(
                "notificationType", "EMAIL",
                "templateId", inviteTemplateId,
                "to", List.of(email),
                "data", Map.of(
                        "syndicateName", syndicateName,
                        "firstName", firstName,
                        "loginUrl", "https://ugate.yowyob.com/reset-password"
                )
        );

        return webClient.post()
                .uri("/api/v1/notifications/send")
                .header("X-Service-Token", serviceToken)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(status -> status.isError(), response ->
                        response.bodyToMono(String.class)
                                .flatMap(error -> Mono.error(new RuntimeException("Notification Error: " + error)))
                )
                .toBodilessEntity()
                .doOnSuccess(v -> log.info("Invitation envoyée avec succès à {}", email))
                .doOnError(e -> log.error("Échec de l'envoi de l'invitation à {}: {}", email, e.getMessage()))
                .then();
    }
}
```

*Lignes: 61*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\adapters\MediaPersistenceAdapter.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Image;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.PublicationImage;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ImageRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MediaPersistenceAdapter implements MediaPersistencePort {

    private final ImageRepository imageRepository;
    private final PublicationImageRepository publicationImageRepository;

    @Override
    public Mono<Void> saveImageMedia(String imageUrl, String altText, UUID publicationId) {
        Image image = new Image(imageUrl, altText, Instant.now());

        return imageRepository.save(image)
                .flatMap(savedImage -> {
                    PublicationImage publicationImage = new PublicationImage(publicationId, savedImage.id(), Instant.now(), Instant.now());
                    return publicationImageRepository.save(publicationImage);
                }).then();
    }

    @Override
    public Mono<Void> saveVideoMedia(String videoUrl, String title, UUID publicationId) {
        // TODO: Implement video saving logic
        return Mono.empty();
    }

    @Override
    public Mono<Void> saveAudioMedia(String audioUrl, String title, UUID publicationId) {
        // TODO: Implement audio saving logic
        return Mono.empty();
    }
}



```

*Lignes: 47*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\adapters\PublicationPersistenceAdapter.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.model.PublicationModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Publication;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PublicationPersistenceAdapter implements PublicationPersistencePort {

    private final PublicationRepository publicationRepository;

    @Override
    public Mono<PublicationModel> save(PublicationModel publicationModel) {
        Publication publication = new Publication(
                publicationModel.getBranchI(),
                publicationModel.getAuthorId(),
                publicationModel.getContent(),
                publicationModel.getNLikes(),
                publicationModel.getCreatedAt()
        );

        return publicationRepository.save(publication)
                .map(savedPublication -> {
                    publicationModel.setId(savedPublication.id());
                    return publicationModel;
                });
    }
}



```

*Lignes: 36*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\AbstractProduct.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Table("abstract_products")
public record AbstractProduct(
        @Id
        UUID id,

        @Column("syndicat_id")
        UUID syndicatId, // FK -> Syndicat

        String name,
        String description,

        @CreatedDate
        @Column("created_at")
        Instant createdAt
) {}

```

*Lignes: 25*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\Avis.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("avis")
public record Avis(
        @Id
        UUID id,

        @Column("user_id")
        UUID userId,      // FK User

        @Column("product_id")
        UUID productId,   // FK Product

        @Column("syndicat_id")
        UUID syndicatId,  // FK Syndicat

        String comment,

        Integer number,   // Note / Rating

        @CreatedDate
        @Column("created_at")
        Instant createdAt
) {
    // Les avis sont souvent immuables fonctionnellement (on modifie rarement un avis),
    // donc pas de méthode "with" nécessaire immédiatement.
}
```

*Lignes: 35*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\Branch.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("branches")
public record Branch(
        @Id
        UUID id, // FK vers Agency.id selon le schéma

        @Column("syndicat_id")
        UUID syndicatId, // FK vers Syndicat

        String name,
        String location,
        String contact,

        @CreatedDate
        @Column("created_at")
        Instant createdAt,

        @LastModifiedDate
        @Column("updated_at")
        Instant updatedAt
) {
    // Exemple de wither pour mise à jour simple
    public Branch withContactInfo(String name, String location, String contact) {
        return new Branch(
                this.id, this.syndicatId, name, location, contact,
                this.createdAt, null // null déclenchera la mise à jour de @LastModifiedDate
        );
    }
}
```

*Lignes: 41*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\BusinessActor.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("business_actors")
public record BusinessActor(
        @Id
        UUID id, // PK et FK vers User.id

        String name,

        @Column("phone_number")
        String phoneNumber,

        @Column("email_address")
        String emailAddress,

        @Transient
        @org.springframework.data.annotation.ReadOnlyProperty
        boolean isNewRecord

) implements Persistable<UUID> {


    public static BusinessActor createNew(UUID userId, String name, String phone, String email) {
        return new BusinessActor(userId, name, phone, email, true);
    }


    public static BusinessActor existing(UUID userId, String name, String phone, String email) {
        return new BusinessActor(userId, name, phone, email, false);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    @Transient
    public boolean isNew() {
        return isNewRecord;
    }
}
```

*Lignes: 50*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\Comment.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Table("comment")
public record Comment(
        @Id
        UUID id,

        @Column("author_id")
        UUID authorId,       // FK -> User

        @Column("publication_id")
        UUID publicationId,  // FK -> Publication

        @Column("parent_id")
        UUID parentId,       // FK -> Comment (Nullable : null si commentaire racine)

        @Column("image_id")
        UUID imageId,        // FK -> Image (Nullable)

        String content,

        @CreatedDate
        @Column("created_at")
        Instant createdAt
) {}

```

*Lignes: 33*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\ComplianceDetails.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("compliance_details")
public record ComplianceDetails(
        @Id
        @Column("user_id")
        UUID userId,

        // --- Profil ---
        @Column("profile_photo_url") String profilePhotoUrl,
        @Column("cv_url") String cvUrl,

        // --- CNI ---
        @Column("cni_number") String cniNumber,
        @Column("cni_recto_url") String cniRectoUrl,
        @Column("cni_verso_url") String cniVersoUrl,

        // --- Permis ---
        @Column("license_number") String licenseNumber,
        @Column("license_recto_url") String licenseRectoUrl,
        @Column("license_verso_url") String licenseVersoUrl,

        // --- Meta ---
        @Column("is_verified") Boolean isVerified,

        @LastModifiedDate
        @Column("updated_at") Instant updatedAt,

        // Champ technique pour gérer l'INSERT manuel
        @Transient boolean isNewRecord

) implements Persistable<UUID> {


    @PersistenceCreator
    public ComplianceDetails(UUID userId, String profilePhotoUrl, String cvUrl, String cniNumber,
                             String cniRectoUrl, String cniVersoUrl, String licenseNumber,
                             String licenseRectoUrl, String licenseVersoUrl, Boolean isVerified, Instant updatedAt) {
        this(userId, profilePhotoUrl, cvUrl, cniNumber, cniRectoUrl, cniVersoUrl,
                licenseNumber, licenseRectoUrl, licenseVersoUrl, isVerified, updatedAt, false);
    }

    /**
     * Constructeur utilitaire pour créer un NOUVEL objet (pour un INSERT).
     */
    public static ComplianceDetails createNew(UUID userId) {
        return new ComplianceDetails(
                userId, null, null, null, null, null, null, null, null, false, null, true
        );
    }





    // Constructeur vide pour permettre l'initialisation par défaut
    public static ComplianceDetails createEmpty(UUID userId) {
        return new ComplianceDetails(
                userId, null, null, null, null, null, null, null, null, false, null, true
        );
    }

    @Override
    public UUID getId() {
        return userId;
    }

    @Override
    @Transient
    public boolean isNew() {
        // Si true -> Spring fait un INSERT
        // Si false -> Spring fait un UPDATE
        return isNewRecord;
    }

    // --- Méthodes "Wither" pour mettre à jour partiellement ---

    public ComplianceDetails withProfilePhoto(String url) {
        return new ComplianceDetails(
                this.userId, url, this.cvUrl, this.cniNumber, this.cniRectoUrl, this.cniVersoUrl,
                this.licenseNumber, this.licenseRectoUrl, this.licenseVersoUrl, this.isVerified, Instant.now(), false
        );
    }

    public ComplianceDetails withCni(String number, String rectoUrl, String versoUrl) {
        return new ComplianceDetails(
                this.userId, this.profilePhotoUrl, this.cvUrl, number, rectoUrl, versoUrl,
                this.licenseNumber, this.licenseRectoUrl, this.licenseVersoUrl, this.isVerified, Instant.now(), false
        );
    }

    public ComplianceDetails withLicense(String number, String rectoUrl, String versoUrl) {
        return new ComplianceDetails(
                this.userId, this.profilePhotoUrl, this.cvUrl, this.cniNumber, this.cniRectoUrl, this.cniVersoUrl,
                number, rectoUrl, versoUrl, this.isVerified, Instant.now(), false
        );
    }
}
```

*Lignes: 111*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\Country.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Table("country")
public record Country(
        @Id
        UUID id,

        String name,
        String code, // Ex: "FR", "CM", "US"

        @CreatedDate
        @Column("created_at")
        Instant createdAt,

        @LastModifiedDate
        @Column("updated_at")
        Instant updatedAt
) {}
```

*Lignes: 26*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\enumeration\ComplianceStatus.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.enumeration;

public enum ComplianceStatus {
    AUTHORIZED,
    BANNED
}

```

*Lignes: 7*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\enumeration\RoleTypeEnum.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.enumeration;

public enum RoleTypeEnum {
    CUSTOMER, DRIVER, FLEET_MANAGER, ADMIN, PASSENGER, PRESIDENT,
    MODERATOR, CLIENT
}

```

*Lignes: 7*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\Event.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Table("event")
public record Event(
        @Id
        UUID id,

        @Column("branch_id")
        UUID branchId, // FK -> Branch

        String title,
        String description,
        String location,

        LocalDate date,          // Pour la colonne 'date'

        @Column("start_time")
        LocalTime startTime,     // Pour 'start_time'

        @Column("end_time")
        LocalTime endTime,       // Pour 'end_time'

        @CreatedDate
        @Column("created_at")
        Instant createdAt,

        @LastModifiedDate
        @Column("updated_at")
        Instant updatedAt
) {}
```

*Lignes: 40*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\EventImages.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Table("event_images")
public record EventImages(
        // Pas de @Id unique

        @Column("event_id")
        UUID eventId,

        @Column("image_id")
        UUID imageId,

        @CreatedDate
        @Column("created_at")
        Instant createdAt,

        @LastModifiedDate
        @Column("updated_at")
        Instant updatedAt
) {}

```

*Lignes: 28*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\Image.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Table("images")
public record Image(
        @Id
        UUID id,

        String url,

        @Column("alt_text")
        String altText,

        @CreatedDate
        @Column("uploaded_at")
        Instant uploadedAt
) {
    public Image(String url, String altText, Instant uploadedAt) {
        this(null, url, altText, uploadedAt);
    }
}
```

*Lignes: 27*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\MembershipRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("membership_request")
public record MembershipRequest(
        @Id UUID id,
        @Column("user_id") UUID userId,
        @Column("syndicat_id") UUID syndicatId,
        @Column("branch_id") UUID branchId,
        @Column("status") MembershipStatus status,
        String motivation,
        @Column("rejection_reason") String rejectionReason,
        @Column("created_at") Instant createdAt,
        @Column("updated_at") Instant updatedAt
) {
    public enum MembershipStatus { PENDING, APPROVED, REJECTED }
}

```

*Lignes: 25*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\Organization.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Table("organizations")
public record Organization(
        @Id UUID id,
        @Column("business_actor_id") UUID businessActorId,
        String code,

        @Column("short_name") String shortName,
        @Column("long_name") String longName,
        @Column("email") String email,
        @Column("is_active") Boolean isActive,

        @Transient boolean isNewRecord
) implements Persistable<UUID> {

    public Organization(UUID id, UUID businessActorId, String code, String email, String name) {
        this(id, businessActorId, code, name, name, email, true, true);
    }

    @Override public UUID getId() { return id; }
    @Override @Transient public boolean isNew() { return isNewRecord; }
}

```

*Lignes: 36*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\Product.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Table("product")
public record Product(
        // Cet ID doit correspondre à celui de abstract_product correspondant
        @Id
        UUID id,

        @Column("organization_id")
        UUID organizationId, // FK -> Organization

        // Certains champs semblent redondants avec AbstractProduct (name, description)
        // Je les inclus car ils sont dans le schéma spécifique de Product
        String name,
        String description,

        @Column("is_active")
        Boolean isActive,

        @Column("standard_price")
        BigDecimal standardPrice,

        @Column("departure_location")
        String departureLocation,

        @Column("arrival_location")
        String arrivalLocation,

        @Column("start_date")
        LocalDate startDate,

        @Column("start_time")
        LocalTime startTime,

        @Column("end_date")
        LocalDate endDate,

        @Column("end_time")
        LocalTime endTime,

        @Column("baggage_info")
        String baggageInfo,

        @Column("is_negotiable")
        Boolean isNegotiable,

        @Column("payment_method")
        String paymentMethod,

        String title,
        String status,

        // URLs : Souvent stocké en JSON ou tableau de texte dans Postgres
        // Ici mappé en String (JSON brut) pour simplifier
        @Column("product_urls")
        String productUrls,

        @Column("regular_amount")
        BigDecimal regularAmount,

        @Column("discount_percentage")
        Double discountPercentage, // Pourcentage peut être Double ou BigDecimal

        @Column("discounted_amount")
        BigDecimal discountedAmount,

        // Metadata : JSONB dans Postgres
        String metadata,

        @CreatedDate
        @Column("created_at")
        Instant createdAt,

        @LastModifiedDate
        @Column("updated_at")
        Instant updatedAt
) {}
```

*Lignes: 89*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\Profile.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;


@Table("profiles")
public record Profile (
        @Id UUID id,
        @Column("user_id")
        UUID userId ,
        @Column("profile_image_url")
        String profilImageUrl,

        @Column("first_name")
        String firstName,
        @Column("last_name")
        String lastName,
        @Column("birth_date")
        Instant birth_date,
        String nationality,
        String gender,
        String language,
        @CreatedDate
        @Column("created_at")
        Instant createdAt,
        @LastModifiedDate
        @Column("updated_at")
        Instant updatedAt

)
{}

```

*Lignes: 39*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\Publication.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Table("publication")
public record Publication(
        @Id UUID id,

        @Column("branch_id") UUID branchId, // FK -> Branch

        @Column("author_id") UUID authorId, // FK -> User

        String content,
        // String status, // Ex: "PUBLISHED", "DRAFT"

        @Column("n_likes") Long nLikes, // Compteur de likes

        @CreatedDate @Column("created_at") Instant createdAt) {

    public Publication(UUID branchId, UUID authorId, String content, Long nLikes, Instant createdAt) {
        this(null, branchId, authorId, content, nLikes, createdAt);
    }
}


```

*Lignes: 30*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\PublicationImage.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Table("publication_image")
public record PublicationImage(
        // Pas de @Id unique ici car c'est une clé composite (publication_id + image_id)

        @Column("publication_id")
        UUID publicationId,

        @Column("image_id")
        UUID imageId,

        @CreatedDate
        @Column("created_at")
        Instant createdAt,

        @LastModifiedDate
        @Column("updated_at")
        Instant updatedAt
) {}
```

*Lignes: 27*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\PublicationVote.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Table("publication_vote")
public record PublicationVote(
        @Id
        UUID id,

        String title,
        String description,

        // "closing_at" suggère un timestamp précis
        @Column("closing_at")
        Instant closingAt,

        String type
) {}

```

*Lignes: 23*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\Reaction.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Table("reaction")
public record Reaction(
        @Id
        UUID id,

        @Column("publication_id")
        Long publicationId, // FK -> Publication

        @Column("user_id")
        UUID userId,        // FK -> User

        String type,        // "LIKE", "LOVE", etc.

        @CreatedDate
        @Column("reacted_at")
        Instant reactedAt
) {}
```

*Lignes: 26*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\Service.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Table("service") // Nom de table au singulier pour cohérence
public record Service(
        @Id
        UUID id,

        @Column("organization_id")
        UUID organizationId, // FK -> Organization

        // Certains champs semblent redondants avec AbstractProduct (name, description)
        // Je les inclus car ils sont dans le schéma spécifique de Product
        String name,
        String description,

        @Column("is_active")
        Boolean isActive,

        @Column("standard_price")
        BigDecimal standardPrice,

        @Column("departure_location")
        String departureLocation,

        @Column("arrival_location")
        String arrivalLocation,

        @Column("start_date")
        LocalDate startDate,

        @Column("start_time")
        LocalTime startTime,

        @Column("end_date")
        LocalDate endDate,

        @Column("end_time")
        LocalTime endTime,

        @Column("baggage_info")
        String baggageInfo,

        @Column("is_negotiable")
        Boolean isNegotiable,

        @Column("payment_method")
        String paymentMethod,

        String title,
        String status,

        // URLs : Souvent stocké en JSON ou tableau de texte dans Postgres
        // Ici mappé en String (JSON brut) pour simplifier
        @Column("product_urls")
        String productUrls,

        @Column("regular_amount")
        BigDecimal regularAmount,

        @Column("discount_percentage")
        Double discountPercentage, // Pourcentage peut être Double ou BigDecimal

        @Column("discounted_amount")
        BigDecimal discountedAmount,

        // Metadata : JSONB dans Postgres
        String metadata,

        @CreatedDate
        @Column("created_at")
        Instant createdAt,

        @LastModifiedDate
        @Column("updated_at")
        Instant updatedAt
) {}
```

*Lignes: 87*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\Syndicat.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("syndicats")
public record Syndicat(
        @Id
        UUID id,

        @Column("organization_id")
        UUID organizationId, // FK vers Organization

        @Column("creator_id")
        UUID creatorId, // FK vers Le createur   //ajouté par moi, nécessite une migraton

        @Column("is_approved")
        Boolean isApproved,

        String name,
        String description,
        String domain,
        String type,

        // URLs
        @Column("charte_url")
        String charteUrl,

        @Column("logo_url")
        String logoUrl,

        @Column("status_url")
        String statusUrl,

        @Column("members_list_url")
        String membersListUrl,

        @Column("commitment_certificate_url")
        String commitmentCertificateUrl,

        // Audit
        @CreatedDate
        @Column("created_at")
        Instant createdAt,

        @LastModifiedDate
        @Column("updated_at")
        Instant updatedAt,

        @Column("is_active")
        Boolean isActive
)implements Persistable<UUID> {


    // Constructeur pour créer un Syndicat
    public Syndicat(UUID id, UUID creatorId, String name, String description,
                    String domain, String logoUrl, String statusUrl) {
        this(id, null, creatorId, false, name, description, domain, "STANDARD",
                null, logoUrl, statusUrl, null, null, null, null, true);
    }



    public Syndicat withStatus(Boolean isApproved, String charteUrl,String logoUrl,  String statusUrl, Boolean isActive) {
        return new Syndicat(
                this.id, this.organizationId, this.creatorId, isApproved, this.name, this.description,
                this.domain, this.type, charteUrl, logoUrl, statusUrl, this.membersListUrl,
                this.commitmentCertificateUrl, this.createdAt, this.updatedAt, isActive
        );
    }

    public Syndicat withApproval(boolean approved) {
        return new Syndicat(
                this.id, this.organizationId, this.creatorId, approved, this.name, this.description,
                this.domain, this.type, this.charteUrl, this.logoUrl, this.statusUrl, this.membersListUrl,
                this.commitmentCertificateUrl, this.createdAt, this.updatedAt, this.isActive
        );
    }

    public Syndicat withActive(boolean active) {
        return new Syndicat(
                this.id, this.organizationId, this.creatorId, this.isApproved, this.name, this.description,
                this.domain, this.type, this.charteUrl, this.logoUrl, this.statusUrl, this.membersListUrl,
                this.commitmentCertificateUrl, this.createdAt, this.updatedAt, active
        );
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    @Transient
    public boolean isNew() {
        return createdAt == null || updatedAt == null;
    }
}

```

*Lignes: 108*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\SyndicatMember.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;


import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.enumeration.RoleTypeEnum;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Table("syndicat_members")
public record SyndicatMember(

        @Column("syndicat_id")
        UUID syndicatId, // FK -> Syndicat

        @Column("user_id")
        UUID userId,     // FK -> AppUser

        @CreatedDate     // Rempli automatiquement si l'auditing est activé
        @Column("joined_at")
        Instant joinedAt,

        @Column("is_active")
        Boolean isActive,

        @Column("role") RoleTypeEnum role   // MEMBER, MODERATOR, ADMIN
) {
    // Constructeur utilitaire pour une adhésion par défaut
    public static SyndicatMember create(UUID syndicatId, UUID userId, RoleTypeEnum role) {
        return new SyndicatMember(syndicatId, userId, null, true, role);
    }

    public boolean isStatusActive() {
        return Boolean.TRUE.equals(isActive);
    }

    public boolean isStatusDisabled() {
        return !isStatusActive();
    }

    public boolean isAdmin() {
        return RoleTypeEnum.ADMIN.equals(this.role);
    }

    public boolean isModerator() {
        return RoleTypeEnum.MODERATOR.equals(this.role);
    }

    /**
     * Vérifie si l'utilisateur possède un privilège de gestion (Admin ou Modérateur)
     */
    public boolean hasManagementPrivileges() {
        return isAdmin() || isModerator();
    }

    /**
     * Version générique pour vérifier n'importe quel rôle
     */
    public boolean hasRole(RoleTypeEnum targetRole) {
        return this.role == targetRole;
    }
}
```

*Lignes: 63*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\User.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table("users")
public record User(
        @Id UUID id,

        String name,

        @Column("phone_number")
        String phoneNumber,

        @Column("email_address")
        String email,
        @Transient boolean isNewRecord
) implements Persistable<UUID> {
    public User(UUID id, String name, String phoneNumber, String email) {
        this(id, name, phoneNumber, email, true);
    }

    @Override
    public UUID getId() { return id; }

    @Override
    @Transient
    public boolean isNew() { return isNewRecord; }
}

```

*Lignes: 36*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\Vote.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("vote")
public record Vote(
        @Id
        UUID id,

        @Column("user_id")
        UUID userId, // FK -> User

        @Column("publication_vote_id")
        Long publicationVoteId, // FK -> PublicationVote

        String label // Le choix voté
) {}
```

*Lignes: 21*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\AvisRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Avis;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import java.util.UUID;

public interface AvisRepository extends R2dbcRepository<Avis, UUID> {

}
```

*Lignes: 9*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\BranchRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Branch;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface BranchRepository extends R2dbcRepository<Branch, UUID> {
    Flux<Branch> findBySyndicatId(UUID syndicatId);
}
```

*Lignes: 10*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\BusinessActorRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.BusinessActor;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Profile;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface BusinessActorRepository extends R2dbcRepository<BusinessActor, UUID> {
}

```

*Lignes: 11*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\ComplianceDetailsRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.ComplianceDetails;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import java.util.UUID;

public interface ComplianceDetailsRepository extends R2dbcRepository<ComplianceDetails, UUID> {

}

```

*Lignes: 10*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\EventRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Event;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import java.time.LocalDate;
import java.util.UUID;

public interface EventRepository extends R2dbcRepository<Event, UUID> {
    Flux<Event> findByBranchId(UUID brancheId);

    // Trouver les événements à venir

    Flux<Event> findByBranchIdAndDateAfter(UUID brancheId, LocalDate date);
}
```

*Lignes: 15*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\ImageRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Image;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface ImageRepository extends R2dbcRepository<Image, UUID> {

    // Requête JOIN pratique : Récupérer toutes les images d'une publication directement
    @Query("""
        SELECT i.* FROM images i 
        JOIN publication_images pi ON i.id = pi.image_id 
        WHERE pi.publication_id = :pubId 
        ORDER BY pi.display_order ASC
    """)
    Flux<Image> findByPublicationId(UUID pubId);  //TODO a modifier

    // Même chose pour les événements
    @Query("""
        SELECT i.* FROM images i 
        JOIN event_images ei ON i.id = ei.image_id 
        WHERE ei.event_id = :eventId
        ORDER BY ei.display_order ASC
    """)
    Flux<Image> findByEventId(UUID eventId);   //TODO a modifier
}
```

*Lignes: 28*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\MembershipRequestRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.MembershipRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface MembershipRequestRepository extends R2dbcRepository<MembershipRequest, UUID> {

    @Query("SELECT * FROM membership_request WHERE user_id = :userId AND syndicat_id = :syndicatId ORDER BY created_at DESC LIMIT 1")
    Mono<MembershipRequest> findLastRequest(UUID userId, UUID syndicatId);


    Flux<MembershipRequest> findBySyndicatIdAndStatus(UUID syndicatId, MembershipRequest.MembershipStatus status, Pageable pageable);
}

```

*Lignes: 20*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\OrganisationRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.BusinessActor;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Organization;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface OrganisationRepository extends R2dbcRepository<Organization, UUID> {
}

```

*Lignes: 11*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\ProductRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Product;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface ProductRepository extends R2dbcRepository<Product, UUID> {

    @Modifying
    // Correction : table "product" et champ "standardPrice" (ou regularAmount selon votre logique métier)
    @Query("INSERT INTO product (id, standard_price) VALUES (:#{#p.id}, :#{#p.standardPrice})")
    Mono<Void> insert(@org.springframework.data.repository.query.Param("p") Product produit);
}

```

*Lignes: 17*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\ProfileRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Profile;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Syndicat;
import org.springframework.data.domain.Example;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProfileRepository extends R2dbcRepository<Profile, UUID> {
    Mono<Profile> findByUserId(UUID userId);
}

```

*Lignes: 14*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\PublicationImageRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.PublicationImage;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface PublicationImageRepository extends R2dbcRepository<PublicationImage, UUID> {
}

```

*Lignes: 10*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\PublicationRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Publication;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface PublicationRepository extends R2dbcRepository<Publication, UUID> {
}

```

*Lignes: 10*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\SyndicatMemberRepository.java

```java
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

    @Query("SELECT * FROM syndicat_members WHERE user_id = :userId")
    Mono<SyndicatMember> findByUserId(UUID userId);

    @Query("SELECT * FROM syndicat_members WHERE user_id = :userId")
    Flux<SyndicatMember> findAllByUserId(UUID userId);
}

```

*Lignes: 37*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\SyndicatRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Syndicat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;


import java.util.UUID;

public interface SyndicatRepository extends R2dbcRepository<Syndicat, UUID> {
    // Trouver les syndicats créés par un admin
    Flux<Syndicat> findByCreatorId(UUID creatorId);

    Flux<Syndicat> findAllBy(Pageable pageable);

    @Query("""
        SELECT s.* 
        FROM syndicats s 
        INNER JOIN syndicat_members sm ON s.id = sm.syndicat_id 
        WHERE sm.user_id = :userId
    """)
    Flux<Syndicat> findAllByMemberUserId(UUID userId);

    // Filtrer par domaine ou nom
    Flux<Syndicat> findByNameContainingIgnoreCase(String name);
}

```

*Lignes: 29*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\UserRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface UserRepository extends R2dbcRepository<User, UUID> {


}
```

*Lignes: 11*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\config\KafkaConfig.java

```java
package com.yowyob.ugate_service.infrastructure.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ReactiveKafkaProducerTemplate<String, Object> reactiveKafkaProducerTemplate() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, org.springframework.kafka.support.serializer.JsonSerializer.class);

        SenderOptions<String, Object> senderOptions = SenderOptions.create(props);

        return new ReactiveKafkaProducerTemplate<>(senderOptions);
    }
}


```

*Lignes: 33*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\config\OpenApiConfig.java

```java
package com.yowyob.ugate_service.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Définition du nom du schéma de sécurité (doit matcher celui dans vos Controllers)
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("YowYob Microservice UGate - API")
                        .version("1.0.0")
                        .description("Documentation de l'API UGate pour la gestion des Syndicats.")
                        .contact(new Contact()
                                .name("Équipe Backend")
                                .email("dev@yowyob.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))


                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )


                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
    }
}
```

*Lignes: 47*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\config\RedisConfig.java

```java
package com.yowyob.ugate_service.infrastructure.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ComplianceResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {


    @Primary
    @Bean
    public ReactiveRedisTemplate<String, ExternalUserInfo> externalUserRedisTemplate(ReactiveRedisConnectionFactory factory) {
        return createTemplate(factory, ExternalUserInfo.class);
    }

    @Bean
    public ReactiveRedisTemplate<String, ComplianceResponse> complianceRedisTemplate(ReactiveRedisConnectionFactory factory) {
        return createTemplate(factory, ComplianceResponse.class);
    }

    private <T> ReactiveRedisTemplate<String, T> createTemplate(ReactiveRedisConnectionFactory factory, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());


        Jackson2JsonRedisSerializer<T> serializer = new Jackson2JsonRedisSerializer<>(mapper, clazz);

        RedisSerializationContext<String, T> context = RedisSerializationContext
                .<String, T>newSerializationContext(new StringRedisSerializer())
                .value(serializer)
                .build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}
```

*Lignes: 47*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\config\SecurityConfig.java

```java
package com.yowyob.ugate_service.infrastructure.config;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                // 1. On applique la configuration CORS ici
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 2. On désactive CSRF (standard pour les API REST sans état)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                // 3. On définit les règles d'autorisation
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/webjars/**").permitAll()
                        .anyExchange().authenticated()
                )

                // 4. On configure le serveur de ressources JWT
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtDecoder(unsafeJwtDecoder())) // Votre décodeur de dev
                );

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("*"));

        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Headers autorisés
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    /**
     * Méthode passoire temporaire qu'on va rétirer plutard, c'est pour faire face au
     * probleme d'authentifications en attendant d'avoir la clé utilisé par le service d'authentification
     * pour signer ses tokens
     */
    @Bean
    public ReactiveJwtDecoder unsafeJwtDecoder() {
        return token -> Mono.fromCallable(() -> {
            try {
                // On parse le token brut
                SignedJWT parsedToken = SignedJWT.parse(token);
                JWTClaimsSet claims = parsedToken.getJWTClaimsSet();

                // On construit l'objet JWT Spring Security manuellement
                Map<String, Object> headers = new HashMap<>(parsedToken.getHeader().toJSONObject());
                Map<String, Object> claimsMap = new HashMap<>(claims.getClaims());


                Instant issuedAt = claims.getIssueTime() != null ? claims.getIssueTime().toInstant() : Instant.now();
                Instant expiresAt = claims.getExpirationTime() != null ? claims.getExpirationTime().toInstant() : Instant.now().plusSeconds(3600);

                return new Jwt(
                        token,
                        issuedAt,
                        expiresAt,
                        headers,
                        claimsMap
                );
            } catch (Exception e) {
                throw new JwtException("Impossible de parser le token : " + e.getMessage());
            }
        });
    }
}
```

*Lignes: 99*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\config\ServiceConfig.java

```java
package com.yowyob.ugate_service.infrastructure.config;

import com.yowyob.ugate_service.application.service.content.PublicationService;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public PublicationService publicationService(PublicationPersistencePort publicationPersistencePort, MediaPersistencePort mediaPersistencePort) {
        return new PublicationService(publicationPersistencePort, mediaPersistencePort);
    }
}

```

*Lignes: 17*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\config\WebClientConfig.java

```java
package com.yowyob.ugate_service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .filter(bearerTokenFilter());
    }

    private ExchangeFilterFunction bearerTokenFilter() {
        return (request, next) -> ReactiveSecurityContextHolder.getContext()
                .map(org.springframework.security.core.context.SecurityContext::getAuthentication)
                .filter(auth -> auth.getPrincipal() instanceof Jwt)
                .map(auth -> (Jwt) auth.getPrincipal())
                .map(Jwt::getTokenValue)
                .flatMap(token -> {
                    var newRequest = org.springframework.web.reactive.function.client.ClientRequest.from(request)
                            .header("Authorization", "Bearer " + token)
                            .build();
                    return next.exchange(newRequest);
                })
                .switchIfEmpty(next.exchange(request));
    }
}
```

*Lignes: 34*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\mappers\syndicate\SyndicateMapper.java

```java
package com.yowyob.ugate_service.infrastructure.mappers.syndicate;

import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Syndicat;
import org.springframework.stereotype.Component;

@Component
public class SyndicateMapper {

    public SyndicateResponse toResponse(Syndicat entity) {
        if (entity == null) return null;

        return new SyndicateResponse(
                entity.id(),
                entity.name(),
                entity.description(),
                entity.domain(),
                entity.isApproved(),
                entity.logoUrl(),
                entity.statusUrl(),
                entity.creatorId(),
                entity.createdAt()
        );
    }
}

```

*Lignes: 26*

---

### 📄 src\main\java\com\yowyob\ugate_service\UgateServiceApplication.java

```java
package com.yowyob.ugate_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UgateServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UgateServiceApplication.class, args);
	}

}

```

*Lignes: 14*

---

### 📄 src\main\resources\application.properties

```properties
spring.application.name=ugate-service
application.external.media-service-name=syndicat

# SERVER
server.port=8091
spring.docker.compose.enabled=false
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/v3/api-docs

# POSTGRESQL (R2DBC)
spring.r2dbc.url=r2dbc:postgresql://localhost:5432/synthese_bd
spring.r2dbc.username=postgres
spring.r2dbc.password=postgres

# SQL INIT
spring.sql.init.mode=always

# Configuration Liquibase
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml
spring.liquibase.url=jdbc:postgresql://localhost:5432/synthese_bd
spring.liquibase.user=postgres
spring.liquibase.password=postgres

# REDIS
spring.data.redis.host=168.119.122.86
spring.data.redis.port=7000
spring.data.redis.password=Azerty1234*
spring.data.redis.cluster.enabled=false

# KAFKA
spring.kafka.bootstrap-servers=168.119.122.86:9092

# KAFKA CONSUMER
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties.spring.json.trusted.packages=*

# KAFKA PRODUCER
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer

# CUSTOM CONFIG
application.external.stock-service-url=http://168.119.122.86:8081
application.kafka.topics.product-events=test-topic

# RESILIENCE4J
resilience4j.circuitbreaker.instances.stock-service.failureRateThreshold=50
resilience4j.circuitbreaker.instances.stock-service.waitDurationInOpenState=5s
resilience4j.circuitbreaker.instances.stock-service.slidingWindowSize=5

# API Externe

#Media Service
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=https://api.tramasys.com/.well-known/jwks.json
application.external.media-service-url=https://media-service.pynfi.com
application.external.auth-service-url=https://auth-service.pynfi.com/

#Notification Service
application.external.notification-service-url=https://notification-service.pynfi.com
application.external.notification-service-token=VOTRE_TOKEN_ICI
application.external.notification-invite-template-id=123
```

*Lignes: 62*

---

### 📄 src\main\resources\db\changelog\changes\v1.1-sync-java-entities.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
    http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.3.xsd">

    <changeSet id="v1.1-add-syndicat-creator" author="ERIC KOGHENE">
        <preConditions onFail="MARK_RAN">
            <not><columnExists tableName="syndicats" columnName="creator_id"/></not>
        </preConditions>
        <addColumn tableName="syndicats">
            <column name="creator_id" type="UUID"><constraints nullable="false"/></column>
        </addColumn>
    </changeSet>

    <changeSet id="v1.1-add-syndicat-active" author="ERIC KOGHENE">
        <preConditions onFail="MARK_RAN">
            <not><columnExists tableName="syndicats" columnName="is_active"/></not>
        </preConditions>
        <addColumn tableName="syndicats">
            <column name="is_active" type="boolean" defaultValueBoolean="true"/>
        </addColumn>
    </changeSet>

    <changeSet id="v1.0-create-members-table" author="ugate-service">
        <preConditions onFail="MARK_RAN">
            <not><tableExists tableName="syndicat_members"/></not>
        </preConditions>
        <createTable tableName="syndicat_members">
            <column name="syndicat_id" type="UUID">
                <constraints nullable="false" primaryKey="true" foreignKeyName="fk_member_syndicat" referencedTableName="syndicats" referencedColumnNames="id"/>
            </column>
            <column name="user_id" type="UUID">
                <constraints nullable="false" primaryKey="true"/>
            </column>
            <column name="joined_at" type="TIMESTAMP" defaultValueComputed="now()"/>
            <column name="is_active" type="boolean" defaultValueBoolean="true"/>
        </createTable>
    </changeSet>

    <changeSet id="v1.1-add-member-role" author="ugate-service">
        <preConditions onFail="MARK_RAN">
            <not><columnExists tableName="syndicat_members" columnName="role"/></not>
        </preConditions>
        <addColumn tableName="syndicat_members">
            <column name="role" type="role_type_enum" defaultValue="CLIENT"/>
        </addColumn>
    </changeSet>

    <changeSet id="v1.1-add-org-long-name" author="ugate-service">
        <preConditions onFail="MARK_RAN">
            <not><columnExists tableName="organizations" columnName="long_name"/></not>
        </preConditions>
        <addColumn tableName="organizations">
            <column name="long_name" type="text"/>
        </addColumn>
    </changeSet>

    <changeSet id="v1.1-add-org-logo-uri" author="ugate-service">
        <preConditions onFail="MARK_RAN">
            <not><columnExists tableName="organizations" columnName="logo_uri"/></not>
        </preConditions>
        <addColumn tableName="organizations">
            <column name="logo_uri" type="text"/>
        </addColumn>
    </changeSet>

    <changeSet id="v1.1-add-org-social" author="ugate-service">
        <preConditions onFail="MARK_RAN">
            <not><columnExists tableName="organizations" columnName="social_network"/></not>
        </preConditions>
        <addColumn tableName="organizations">
            <column name="social_network" type="text"/>
        </addColumn>
    </changeSet>

    <changeSet id="v1.1-add-org-keywords" author="ugate-service">
        <preConditions onFail="MARK_RAN">
            <not><columnExists tableName="organizations" columnName="keywords"/></not>
        </preConditions>
        <addColumn tableName="organizations">
            <column name="keywords" type="text"/>
        </addColumn>
    </changeSet>

    <changeSet id="v1.1-add-org-legal-form" author="ugate-service">
        <preConditions onFail="MARK_RAN">
            <not><columnExists tableName="organizations" columnName="legal_form"/></not>
        </preConditions>
        <addColumn tableName="organizations">
            <column name="legal_form" type="text"/>
        </addColumn>
    </changeSet>

    <changeSet id="v1.0-create-compliance-details" author="ugate-service">
        <preConditions onFail="MARK_RAN">
            <not><tableExists tableName="compliance_details"/></not>
        </preConditions>
        <createTable tableName="compliance_details">
            <column name="user_id" type="UUID">
                <constraints nullable="false" primaryKey="true" foreignKeyName="fk_compliance_user" referencedTableName="users" referencedColumnNames="id"/>
            </column>
            <column name="profile_photo_url" type="text"/>
            <column name="cv_url" type="text"/>

            <column name="cni_number" type="text"/>
            <column name="cni_recto_url" type="text"/>
            <column name="cni_verso_url" type="text"/>

            <column name="license_number" type="text"/>
            <column name="license_recto_url" type="text"/>
            <column name="license_verso_url" type="text"/>

            <column name="is_verified" type="boolean" defaultValueBoolean="false"/>
            <column name="updated_at" type="TIMESTAMP" defaultValueComputed="now()"/>
        </createTable>
    </changeSet>

</databaseChangeLog>
```

*Lignes: 120*

---

### 📄 src\main\resources\db\changelog\db.changelog-master.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
    http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.3.xsd">

    <include file="db/changelog/changes/v1.1-sync-java-entities.xml"/>

</databaseChangeLog>
```

*Lignes: 10*

---

### 📄 src\test\java\com\yowyob\ugate_service\PublicationControllerTests.java

```java
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

```

*Lignes: 105*

---

### 📄 src\test\java\com\yowyob\ugate_service\TestSecurityConfig.java

```java
package com.yowyob.ugate_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class TestSecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .authorizeExchange(exchanges -> exchanges.anyExchange().permitAll())
            .csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }
}

```

*Lignes: 21*

---

### 📄 src\test\java\com\yowyob\ugate_service\UgateServiceApplicationTests.java

```java
package com.yowyob.ugate_service;

import org.junit.jupiter.api.Test;

class UgateServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}

```

*Lignes: 12*

---

### 📄 src\test\resources\application-test.properties

```properties
spring.r2dbc.url=r2dbc:h2:mem:///testdb
spring.r2dbc.username=sa
spring.r2dbc.password=
spring.sql.init.mode=always
spring.application.external.media-service-url=http://localhost:8080

```

*Lignes: 6*

---

### 📄 src\test\resources\schema.sql

```sql
CREATE TABLE publication (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    branch_id UUID,
    author_id UUID,
    content VARCHAR(255),
    n_likes BIGINT,
    created_at TIMESTAMP
);

CREATE TABLE image (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    url VARCHAR(255),
    alt_text VARCHAR(255),
    uploaded_at TIMESTAMP
);

CREATE TABLE publication_image (
    publication_id UUID,
    image_id UUID,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    PRIMARY KEY (publication_id, image_id)
);

```

*Lignes: 24*

---

## Statistiques

- **Total de fichiers analysés:** 110
- **Total de lignes de code:** 4 134
- **Moyenne de lignes par fichier:** 38

### Répartition par type de fichier

- **.java:** 102 fichiers
- **.xml:** 3 fichiers
- **.properties:** 2 fichiers
- **.yaml:** 1 fichier
- **.md:** 1 fichier
- **.sql:** 1 fichier

---

*Contexte généré automatiquement pour analyse par IA*
