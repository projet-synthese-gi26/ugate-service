# Contexte Complet du Projet

**Projet:** ugate-service  
**Date de génération:** 22/12/2025 12:40:49  
**Chemin:** D:\Projets\Scolaire\Reseau\New Backend\ugate-service

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
│   │   │               │       └── .gitkeep
│   │   │               ├── domain
│   │   │               │   ├── exception
│   │   │               │   │   └── .gitkeep
│   │   │               │   ├── model
│   │   │               │   │   └── .gitkeep
│   │   │               │   └── ports
│   │   │               │       ├── in
│   │   │               │       │   └── .gitkeep
│   │   │               │       └── out
│   │   │               │           └── .gitkeep
│   │   │               ├── infrastructure
│   │   │               │   ├── adapters
│   │   │               │   │   ├── inbound
│   │   │               │   │   │   ├── kafka
│   │   │               │   │   │   │   └── .gitkeep
│   │   │               │   │   │   └── rest
│   │   │               │   │   │       ├── dto
│   │   │               │   │   │       │   └── .gitkeep
│   │   │               │   │   │       └── .gitkeep
│   │   │               │   │   └── outbound
│   │   │               │   │       ├── cache
│   │   │               │   │       │   └── .gitkeep
│   │   │               │   │       ├── external
│   │   │               │   │       │   ├── client
│   │   │               │   │       │   │   └── .gitkeep
│   │   │               │   │       │   └── .gitkeep
│   │   │               │   │       ├── messaging
│   │   │               │   │       │   └── .gitkeep
│   │   │               │   │       └── persistence
│   │   │               │   │           ├── entity
│   │   │               │   │           │   ├── .gitkeep
│   │   │               │   │           │   ├── AbstractProduct.java
│   │   │               │   │           │   ├── Avis.java
│   │   │               │   │           │   ├── Branch.java
│   │   │               │   │           │   ├── Comment.java
│   │   │               │   │           │   ├── Country.java
│   │   │               │   │           │   ├── Event.java
│   │   │               │   │           │   ├── EventImages.java
│   │   │               │   │           │   ├── Image.java
│   │   │               │   │           │   ├── Product.java
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
│   │   │               │   │               ├── .gitkeep
│   │   │               │   │               ├── BranchRepository.java
│   │   │               │   │               ├── EventRepository.java
│   │   │               │   │               ├── ImageRepository.java
│   │   │               │   │               ├── ProductRepository.java
│   │   │               │   │               ├── ServicesRepository.java
│   │   │               │   │               ├── SyndicatMemberRepository.java
│   │   │               │   │               ├── SyndicatRepository.java
│   │   │               │   │               └── UserRepository.java
│   │   │               │   ├── config
│   │   │               │   │   ├── .gitkeep
│   │   │               │   │   ├── KafkaConfig.java
│   │   │               │   │   ├── OpenApiConfig.java
│   │   │               │   │   └── RedisConfig.java
│   │   │               │   └── mappers
│   │   │               └── UgateServiceApplication.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── com
│               └── yowyob
│                   └── ugate_service
│                       └── UgateServiceApplicationTests.java
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

*Lignes: 168*

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

@Table("abstract_product")
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

@Table("branch")
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

@Table("image")
public record Image(
        @Id
        UUID id,

        String url,

        @Column("alt_text")
        String altText,

        @CreatedDate
        @Column("uploaded_at")
        Instant uploadedAt
) {}
```

*Lignes: 23*

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
        BigDecimal standardPrice, // Utilisez BigDecimal pour les prix !

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
        @Id
        UUID id,

        @Column("branch_id")
        UUID branchId,    // FK -> Branch

        @Column("author_id")
        UUID authorId,    // FK -> User

        String content,
        String status,    // Ex: "PUBLISHED", "DRAFT"

        @Column("n_likes")
        Long nLikes,      // Compteur de likes

        @CreatedDate
        @Column("created_at")
        Instant createdAt
) {}

```

*Lignes: 31*

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

public record Service() {
}

```

*Lignes: 5*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\Syndicat.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("syndicat")
public record Syndicat(
        @Id
        UUID id,

        @Column("organization_id")
        UUID organizationId, // FK vers Organization

        UUID creatorId, // FK vers Le createur

        @Column("is_approved")
        Boolean isApproved,

        String name,
        String description,
        String domain,
        String type,

        // URLs
        @Column("charte_url")
        String charteUrl,

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
        Instant updatedAt
) {
    // Méthode "Wither" pour mettre à jour lors d'un UPDATE
    public Syndicat withStatus(Boolean isApproved, String charteUrl, String statusUrl) {
        // On garde l'ID et les dates, on change le reste
        return new Syndicat(
                this.id, this.organizationId, this.creatorId, isApproved, this.name, this.description,
                this.domain, this.type, charteUrl, statusUrl, this.membersListUrl,
                this.commitmentCertificateUrl, this.createdAt, this.updatedAt
        );
    }
}

```

*Lignes: 63*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\SyndicatMember.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

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
        Boolean isActive
) {
    // Constructeur utilitaire pour une adhésion par défaut
    public static SyndicatMember create(UUID syndicatId, UUID userId) {
        return new SyndicatMember(syndicatId, userId, null, true);
    }
}
```

*Lignes: 29*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\User.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table("app_user")
public record User(
        @Id UUID id, // UUID au lieu de Long

        @Column("profil_url") String profilUrl,
        @Column("first_name") String firstName,
        @Column("last_name") String lastName,
        String email,
        @Column("password_hash") String passwordHash,
        @Column("date_of_birth") LocalDate dateOfBirth

        //TODO la structure de cette table est à revoir

) {}

```

*Lignes: 24*

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
    Flux<Event> findByBranchIdAndDateEventAfter(UUID brancheId, LocalDate date);
}
```

*Lignes: 14*

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
    Flux<Image> findByPublicationId(UUID pubId);

    // Même chose pour les événements
    @Query("""
        SELECT i.* FROM images i 
        JOIN event_images ei ON i.id = ei.image_id 
        WHERE ei.event_id = :eventId
        ORDER BY ei.display_order ASC
    """)
    Flux<Image> findByEventId(UUID eventId);
}
```

*Lignes: 28*

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
    @Query("INSERT INTO produits (id, prix) VALUES (:#{#p.id}, :#{#p.prix})")
    Mono<Void> insert(@org.springframework.data.repository.query.Param("p") Product produit);
}

```

*Lignes: 16*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\ServicesRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Service;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface ServicesRepository extends R2dbcRepository<Service, UUID> {

    // Insertion forcée (puisque l'ID vient du parent)
    @Modifying
    @Query("INSERT INTO services (id, tarif_horaire) VALUES (:#{#s.id}, :#{#s.tarifHoraire})")
    Mono<Void> insert(@org.springframework.data.repository.query.Param("s") Service service);
}
```

*Lignes: 16*

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
}

```

*Lignes: 31*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\SyndicatRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Syndicat;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface SyndicatRepository extends R2dbcRepository<Syndicat, UUID> {
    // Trouver les syndicats créés par un admin
    Flux<Syndicat> findByCreatorId(UUID creatorId);

    // Filtrer par domaine ou nom
    Flux<Syndicat> findByNameContainingIgnoreCase(String name);
}

```

*Lignes: 15*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\UserRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface UserRepository extends R2dbcRepository<User, UUID> {
    // Essentiel pour le login
    Mono<User> findByEmail(String email);

    // Vérifier l'existence
    Mono<Boolean> existsByEmail(String email);
}
```

*Lignes: 14*

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

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("YowYob Microservice UGate - API")
                        .version("1.0.0")
                        .description("Documentation de l'API UGate")
                        .contact(new Contact()
                                .name("Équipe Backend")
                                .email("dev@yowyob.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}

```

*Lignes: 26*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\config\RedisConfig.java

```java
package com.yowyob.ugate_service.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.*;

@Configuration
public class RedisConfig {

    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory factory) {

        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new JavaTimeModule());

        Jackson2JsonRedisSerializer<Object> jsonSerializer =
                new Jackson2JsonRedisSerializer<>(mapper, Object.class);

        RedisSerializationContext<String, Object> context =
                RedisSerializationContext.<String, Object>newSerializationContext(new StringRedisSerializer())
                        .value(jsonSerializer)
                        .hashValue(jsonSerializer)
                        .build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}
```

*Lignes: 34*

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

# SERVER
server.port=8080
spring.docker.compose.enabled=false

# POSTGRESQL (R2DBC)
spring.r2dbc.url=r2dbc:postgresql://168.119.122.86:5432/yowyob
spring.r2dbc.username=master
spring.r2dbc.password=Azerty1234*

# SQL INIT
spring.sql.init.mode=always

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
```

*Lignes: 41*

---

### 📄 src\test\java\com\yowyob\ugate_service\UgateServiceApplicationTests.java

```java
package com.yowyob.ugate_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UgateServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

*Lignes: 14*

---

## Statistiques

- **Total de fichiers analysés:** 35
- **Total de lignes de code:** 1 129
- **Moyenne de lignes par fichier:** 32

### Répartition par type de fichier

- **.java:** 31 fichiers
- **.yaml:** 1 fichier
- **.md:** 1 fichier
- **.xml:** 1 fichier
- **.properties:** 1 fichier

---

*Contexte généré automatiquement pour analyse par IA*
