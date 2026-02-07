# Contexte Complet du Projet

**Projet:** ugate-service  
**Date de génération:** 07/02/2026 16:43:11  
**Chemin:** D:\Projets\Scolaire\Reseau\New Version\ugate-service

---

## Table des matières
1. [Structure du projet](#structure-du-projet)
2. [Contenu des fichiers](#contenu-des-fichiers)
3. [Statistiques](#statistiques)

---

## Structure du projet

```
├── .github
│   └── workflows
│       └── ci-cd.yml
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
│   │   │               │       │   ├── CommentService.java
│   │   │               │       │   ├── EventService.java
│   │   │               │       │   ├── FeedService.java
│   │   │               │       │   ├── InteractionService.java
│   │   │               │       │   ├── PublicationService.java
│   │   │               │       │   ├── PublicationVoteService.java
│   │   │               │       │   └── ReactionService.java
│   │   │               │       ├── marketplace
│   │   │               │       │   ├── ProductManagementService.java
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
│   │   │               │   ├── enumeration
│   │   │               │   │   └── ReactionTypeEnum.java
│   │   │               │   ├── exception
│   │   │               │   │   ├── .gitkeep
│   │   │               │   │   ├── InsufficientStockException.java
│   │   │               │   │   ├── ProductNotFoundException.java
│   │   │               │   │   └── ServiceNotFoundException.java
│   │   │               │   ├── model
│   │   │               │   │   ├── CommentModel.java
│   │   │               │   │   ├── EventModel.java
│   │   │               │   │   ├── ExternalUserInfo.java
│   │   │               │   │   ├── ImageModel.java
│   │   │               │   │   ├── MediaInfo.java
│   │   │               │   │   ├── Product.java
│   │   │               │   │   ├── PublicationModel.java
│   │   │               │   │   ├── PublicationVoteModel.java
│   │   │               │   │   ├── ReactionModel.java
│   │   │               │   │   ├── SyndicatService.java
│   │   │               │   │   ├── UserEventModel.java
│   │   │               │   │   ├── UserModel.java
│   │   │               │   │   └── VoteModel.java
│   │   │               │   └── ports
│   │   │               │       ├── in
│   │   │               │       │   ├── content
│   │   │               │       │   │   ├── CastVoteUseCase.java
│   │   │               │       │   │   ├── CreateEventUseCase.java
│   │   │               │       │   │   ├── CreatePublicationVoteUseCase.java
│   │   │               │       │   │   ├── GetEventParticipantsUseCase.java
│   │   │               │       │   │   ├── GetEventsByBranchUseCase.java
│   │   │               │       │   │   ├── GetFeedUseCase.java
│   │   │               │       │   │   ├── GetPublicationVoteResultsUseCase.java
│   │   │               │       │   │   ├── JoinEventUseCase.java
│   │   │               │       │   │   └── LeaveEventUseCase.java
│   │   │               │       │   ├── marketplace
│   │   │               │       │   │   ├── ManageProductUseCase.java
│   │   │               │       │   │   └── ManageServiceUseCase.java
│   │   │               │       │   ├── syndicate
│   │   │               │       │   │   ├── ApproveMemberUseCase.java
│   │   │               │       │   │   └── CreateSyndicateUseCase.java
│   │   │               │       │   └── .gitkeep
│   │   │               │       └── out
│   │   │               │           ├── gateway
│   │   │               │           │   └── UserGatewayPort.java
│   │   │               │           ├── marketplace
│   │   │               │           │   ├── ProductRepositoryPort.java
│   │   │               │           │   └── ServiceOfferingRepositoryPort.java
│   │   │               │           ├── media
│   │   │               │           │   └── FileStoragePort.java
│   │   │               │           ├── notification
│   │   │               │           │   └── NotificationPort.java
│   │   │               │           └── syndicate
│   │   │               │               ├── dto
│   │   │               │               │   ├── CommentResponseDto.java
│   │   │               │               │   └── PublicationResponseDTO.java
│   │   │               │               ├── BranchPersistencePort.java
│   │   │               │               ├── CommentPersistencePort.java
│   │   │               │               ├── EventPersistencePort.java
│   │   │               │               ├── MediaPersistencePort.java
│   │   │               │               ├── PublicationPersistencePort.java
│   │   │               │               ├── PublicationVotePersistencePort.java
│   │   │               │               ├── ReactionPersistencePort.java
│   │   │               │               ├── SyndicatePersistencePort.java
│   │   │               │               ├── UserEventPersistencePort.java
│   │   │               │               ├── UserPersistencePort.java
│   │   │               │               └── VotePersistencePort.java
│   │   │               ├── infrastructure
│   │   │               │   ├── adapters
│   │   │               │   │   ├── inbound
│   │   │               │   │   │   ├── kafka
│   │   │               │   │   │   │   └── .gitkeep
│   │   │               │   │   │   └── rest
│   │   │               │   │   │       ├── compliance
│   │   │               │   │   │       │   └── ComplianceController.java
│   │   │               │   │   │       ├── content
│   │   │               │   │   │       │   ├── CommentController.java
│   │   │               │   │   │       │   ├── EventController.java
│   │   │               │   │   │       │   ├── FeedController.java
│   │   │               │   │   │       │   ├── PublicationController.java
│   │   │               │   │   │       │   ├── PublicationVoteController.java
│   │   │               │   │   │       │   └── ReactionController.java
│   │   │               │   │   │       ├── dto
│   │   │               │   │   │       │   ├── request
│   │   │               │   │   │       │   │   ├── BatchComplianceRequest.java
│   │   │               │   │   │       │   │   ├── CastVoteRequest.java
│   │   │               │   │   │       │   │   ├── CreateBranchRequest.java
│   │   │               │   │   │       │   │   ├── CreateCommentRequest.java
│   │   │               │   │   │       │   │   ├── CreatePublicationVoteRequest.java
│   │   │               │   │   │       │   │   ├── CreateReactionRequest.java
│   │   │               │   │   │       │   │   ├── CreateSyndicateRequest.java
│   │   │               │   │   │       │   │   ├── FeedbackRequest.java
│   │   │               │   │   │       │   │   ├── MediaResponseDTO.java
│   │   │               │   │   │       │   │   ├── MembershipApprovalRequest.java
│   │   │               │   │   │       │   │   ├── MembershipRequestRequest.java
│   │   │               │   │   │       │   │   ├── ProductRequest.java
│   │   │               │   │   │       │   │   ├── ServiceOfferingRequest.java
│   │   │               │   │   │       │   │   ├── UpdateBranchRequest.java
│   │   │               │   │   │       │   │   ├── UpdateFullProfileRequest.java
│   │   │               │   │   │       │   │   ├── UpdateMemberRoleRequest.java
│   │   │               │   │   │       │   │   ├── UpdateSyndicateFullRequest.java
│   │   │               │   │   │       │   │   └── WebhookStatusChangeRequest.java
│   │   │               │   │   │       │   ├── response
│   │   │               │   │   │       │   │   ├── AddUserResponse.java
│   │   │               │   │   │       │   │   ├── BasicResponse.java
│   │   │               │   │   │       │   │   ├── BatchComplianceResponse.java
│   │   │               │   │   │       │   │   ├── BranchResponse.java
│   │   │               │   │   │       │   │   ├── ComplianceResponse.java
│   │   │               │   │   │       │   │   ├── EventResponseDTO.java
│   │   │               │   │   │       │   │   ├── MemberResponse.java
│   │   │               │   │   │       │   │   ├── OfficialProfileResponse.java
│   │   │               │   │   │       │   │   ├── PaginatedResponse.java
│   │   │               │   │   │       │   │   ├── ParticipantDTO.java
│   │   │               │   │   │       │   │   ├── ProductResponse.java
│   │   │               │   │   │       │   │   ├── PublicationVoteWithResultsDTO.java
│   │   │               │   │   │       │   │   ├── ServiceOfferingResponse.java
│   │   │               │   │   │       │   │   ├── StatsResponse.java
│   │   │               │   │   │       │   │   ├── SyndicateDetailsResponse.java
│   │   │               │   │   │       │   │   ├── SyndicateFullStatsResponse.java
│   │   │               │   │   │       │   │   ├── SyndicateResponse.java
│   │   │               │   │   │       │   │   └── VoteResultDTO.java
│   │   │               │   │   │       │   └── .gitkeep
│   │   │               │   │   │       ├── product
│   │   │               │   │   │       │   └── ProductController.java
│   │   │               │   │   │       ├── serviceOffering
│   │   │               │   │   │       │   └── ServiceOfferingController.java
│   │   │               │   │   │       ├── superadmin
│   │   │               │   │   │       │   ├── SuperAdminAnalyticsController.java
│   │   │               │   │   │       │   └── SyndicateSuperAdminController.java
│   │   │               │   │   │       └── syndicate
│   │   │               │   │   │           ├── BranchController.java
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
│   │   │               │   │           │   ├── BranchPersistenceAdapter.java
│   │   │               │   │           │   ├── CommentPersistenceAdapter.java
│   │   │               │   │           │   ├── EventPersistenceAdapter.java
│   │   │               │   │           │   ├── MediaPersistenceAdapter.java
│   │   │               │   │           │   ├── PublicationPersistenceAdapter.java
│   │   │               │   │           │   ├── PublicationVotePersistenceAdapter.java
│   │   │               │   │           │   ├── ReactionPersistenceAdapter.java
│   │   │               │   │           │   ├── UserEventPersistenceAdapter.java
│   │   │               │   │           │   ├── UserPersistenceAdapterPort.java
│   │   │               │   │           │   └── VotePersistenceAdapter.java
│   │   │               │   │           ├── entity
│   │   │               │   │           │   ├── enumeration
│   │   │               │   │           │   │   ├── ComplianceStatus.java
│   │   │               │   │           │   │   └── RoleTypeEnum.java
│   │   │               │   │           │   ├── AbstractProduct.java
│   │   │               │   │           │   ├── Agency.java
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
│   │   │               │   │           │   ├── ProductEntity.java
│   │   │               │   │           │   ├── Profile.java
│   │   │               │   │           │   ├── Publication.java
│   │   │               │   │           │   ├── PublicationImage.java
│   │   │               │   │           │   ├── PublicationVote.java
│   │   │               │   │           │   ├── Reaction.java
│   │   │               │   │           │   ├── ServiceEntity.java
│   │   │               │   │           │   ├── Syndicat.java
│   │   │               │   │           │   ├── SyndicatMember.java
│   │   │               │   │           │   ├── User.java
│   │   │               │   │           │   ├── UserEvent.java
│   │   │               │   │           │   └── Vote.java
│   │   │               │   │           ├── repository
│   │   │               │   │           │   ├── AgencyRepository.java
│   │   │               │   │           │   ├── AvisRepository.java
│   │   │               │   │           │   ├── BranchRepository.java
│   │   │               │   │           │   ├── BusinessActorRepository.java
│   │   │               │   │           │   ├── CommentRepository.java
│   │   │               │   │           │   ├── ComplianceDetailsRepository.java
│   │   │               │   │           │   ├── EventImagesRepository.java
│   │   │               │   │           │   ├── EventRepository.java
│   │   │               │   │           │   ├── ImageRepository.java
│   │   │               │   │           │   ├── MembershipRequestRepository.java
│   │   │               │   │           │   ├── OrganisationRepository.java
│   │   │               │   │           │   ├── ProductRepository.java
│   │   │               │   │           │   ├── ProfileRepository.java
│   │   │               │   │           │   ├── PublicationImageRepository.java
│   │   │               │   │           │   ├── PublicationRepository.java
│   │   │               │   │           │   ├── PublicationVoteRepository.java
│   │   │               │   │           │   ├── ReactionRepository.java
│   │   │               │   │           │   ├── ServiceRepository.java
│   │   │               │   │           │   ├── SyndicatMemberRepository.java
│   │   │               │   │           │   ├── SyndicatRepository.java
│   │   │               │   │           │   ├── UserEventRepository.java
│   │   │               │   │           │   ├── UserRepository.java
│   │   │               │   │           │   └── VoteRepository.java
│   │   │               │   │           ├── PostgresSyndicatProductAdapter.java
│   │   │               │   │           └── PostgresSyndicatServiceAdapter.java
│   │   │               │   ├── config
│   │   │               │   │   ├── KafkaConfig.java
│   │   │               │   │   ├── OpenApiConfig.java
│   │   │               │   │   ├── RedisConfig.java
│   │   │               │   │   ├── SecurityConfig.java
│   │   │               │   │   ├── ServiceConfig.java
│   │   │               │   │   ├── UserSyncWebFilter.java
│   │   │               │   │   └── WebClientConfig.java
│   │   │               │   └── mappers
│   │   │               │       ├── media
│   │   │               │       ├── products
│   │   │               │       │   └── ProductMapper.java
│   │   │               │       ├── serviceOffering
│   │   │               │       │   └── ServiceOfferingMapper.java
│   │   │               │       ├── syndicate
│   │   │               │       │   ├── BranchMapper.java
│   │   │               │       │   └── SyndicateMapper.java
│   │   │               │       ├── CommentMapper.java
│   │   │               │       ├── EventMapper.java
│   │   │               │       ├── ImageMapper.java
│   │   │               │       ├── PublicationVoteMapper.java
│   │   │               │       ├── UserEventMapper.java
│   │   │               │       └── VoteMapper.java
│   │   │               └── UgateServiceApplication.java
│   │   └── resources
│   │       ├── db
│   │       │   └── changelog
│   │       │       ├── changes
│   │       │       │   ├── v1.1-sync-java-entities.xml
│   │       │       │   ├── v1.10-make-branch-nullable.xml
│   │       │       │   ├── v1.11-create-membership-request-table.xml
│   │       │       │   ├── v1.2-create-event-table.xml
│   │       │       │   ├── v1.3-create-publication-vote-table.xml
│   │       │       │   ├── v1.4-create-vote-table.xml
│   │       │       │   ├── v1.5-add-branch-banner.xml
│   │       │       │   ├── v1.6-auto-uuid-comments.xml
│   │       │       │   ├── v1.6-setup-products.xml
│   │       │       │   ├── v1.7-create-event-images-table.xml
│   │       │       │   ├── v1.7-setup-services.xml
│   │       │       │   ├── v1.8-create-user-events-table.xml
│   │       │       │   └── v1.9-alter-user-events-table.xml
│   │       │       └── db.changelog-master.xml
│   │       └── application.properties
│   └── test
│       ├── java
│       │   └── com
│       │       └── yowyob
│       │           └── ugate_service
│       │               ├── CommentControllerTests.java
│       │               ├── EventControllerTests.java
│       │               ├── FeedControllerTests.java
│       │               ├── PublicationControllerTests.java
│       │               ├── PublicationVoteControllerTests.java
│       │               ├── ReactionControllerTests.java
│       │               ├── TestSecurityConfig.java
│       │               └── UgateServiceApplicationTests.java
│       └── resources
│           ├── application-test.properties
│           ├── schema.sql
│           └── test-image.png
├── .gitattributes
├── .gitignore
├── compose.yaml
├── create_templates.sh
├── Dockerfile
├── generate.js
├── HELP.md
├── mvnw
├── mvnw.cmd
└── pom.xml
```

---

## Contenu des fichiers

### 📄 .github\workflows\ci-cd.yml

```yaml
name: Spring Boot CI/CD

on:
  push:
    branches:
      - "**"
      #- main

env:
  REGISTRY_IMAGE: ghcr.io/${{ github.repository_owner }}/ugate
  APP_NAME: ugate
  HEALTH_DELAY: ${{ secrets.DEPLOY_DELAY }}
  CONTAINER_NAME: ugate

jobs:
  tests:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout code
        uses: actions/checkout@v3

      - name: Set up JDK
        uses: actions/setup-java@v3
        with:
          distribution: 'temurin'
          java-version: 21

      - name: Run Unit Tests + SonarQube Analysis
        continue-on-error: true
        env:
          SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
        run: |
          mvn clean verify sonar:sonar \
            -Dsonar.projectKey=${{ env.APP_NAME }} \
            -Dsonar.projectName=${{ env.APP_NAME }} \
            -Dsonar.host.url=${{ secrets.SONAR_HOST_URL }}

  build:
    needs: tests
    if: github.ref == 'refs/heads/main'
    runs-on: ubuntu-latest

    steps:
      - name: Checkout code
        uses: actions/checkout@v3

      - name: Log in to GitHub Container Registry
        run: |
          echo "${{ secrets.PERSONAL_ACCESS_TOKEN }}" | docker login ghcr.io -u ${{ secrets.REGISTRY_NAMESPACE }} --password-stdin

      - name: Build Docker image
        run: |
          echo "Building Docker image..."
          docker build -t ${{ env.REGISTRY_IMAGE }}:latest .

      - name: Push Docker image
        run: |
          echo "Pushing Docker image..."
          docker push ${{ env.REGISTRY_IMAGE }}:latest


  deploy:
    needs: build
    runs-on: ubuntu-latest
    if: github.ref == 'refs/heads/main'

    steps:
      - name: Checkout repository
        uses: actions/checkout@v3

      - name: Add SSH key
        uses: webfactory/ssh-agent@v0.7.0
        with:
          ssh-private-key: ${{ secrets.SSH_PRIVATE_KEY }}

      - name: Deploy on server
        run: |
          ssh -o StrictHostKeyChecking=no ${{ secrets.REMOTE_USER }}@${{ secrets.REMOTE_HOST }} << 'EOF'

            echo "Pulling latest image ${{ env.REGISTRY_IMAGE }}"

            docker login ghcr.io -u ${{ secrets.REGISTRY_NAMESPACE }} -p ${{ secrets.PERSONAL_ACCESS_TOKEN }}

            cd /root/projet_synthese/infra

            echo "Stopping container"
            docker compose down ${{ env.CONTAINER_NAME }}

            echo "Removing old image"
            docker rmi -f ${{ env.REGISTRY_IMAGE }}:latest || true

            echo "Pulling new image"
            docker pull ${{ env.REGISTRY_IMAGE }}:latest

            echo " Starting container"
            docker compose up -d ${{ env.CONTAINER_NAME }}

            echo " Waiting ${{ env.HEALTH_DELAY }} seconds for health check"
            sleep ${{ env.HEALTH_DELAY }}

            echo " Checking container health..."
            STATUS=$(docker inspect --format='{{json .State.Health.Status}}' ${{ env.CONTAINER_NAME }})

            echo "Health status: $STATUS"

            if [ "$STATUS" != "\"healthy\"" ]; then
              echo "ERROR: Container is not healthy"
              exit 1
            fi

            echo "Deployment successful & container healthy!"
          EOF

          

```

*Lignes: 116*

---

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
		<dependency>
	    	<groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
	     	<groupId>io.micrometer</groupId>
	     	<artifactId>micrometer-registry-prometheus</artifactId>
		</dependency>

		<!-- DATA (R2DBC & REDIS) -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-r2dbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>r2dbc-postgresql</artifactId>
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
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
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

*Lignes: 201*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\auth\UserManagementService.java

```java
package com.yowyob.ugate_service.application.service.auth;

import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.model.UserEventModel;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.media.FileStoragePort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.UpdateFullProfileRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.MemberResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Profile;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.User;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ProfileRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final UserRepository userRepository;
    private final UserGatewayPort userGatewayPort;
    private final FileStoragePort fileStoragePort;
    private final ProfileRepository profileRepository;
    private final ReactiveRedisTemplate<String, ExternalUserInfo> redisTemplate;

    private static final Duration USER_EXISTENCE_CACHE_TTL = Duration.ofHours(24);

    @Transactional
    public Mono<Void> synchronizeUser(UUID userId, String username) {
        String cacheKey = "user_exists:" + userId;
        return redisTemplate.hasKey(cacheKey)
                .flatMap(existsInCache -> {
                    if (Boolean.TRUE.equals(existsInCache)) {
                        return Mono.empty();
                    }
                    return userRepository.existsById(userId)
                            .flatMap(existsInDb -> {
                                if (Boolean.TRUE.equals(existsInDb)) {
                                    return cacheUserExistence(cacheKey);
                                }
                                return createUserAndProfile(userId, username)
                                        .then(cacheUserExistence(cacheKey));
                            });
                });
    }

    private Mono<Void> createUserAndProfile(UUID userId, String username) {
        log.info("Création locale de l'utilisateur {}", userId);
        User newUser = new User(
                userId,
                username,
                null,
                username
        );
        Profile newProfile = Profile.createNew(
                userId,
                "", // FirstName
                ""  // LastName
        );

        return userRepository.save(newUser)
                .then(profileRepository.save(newProfile))
                .doOnSuccess(p -> log.info("Utilisateur {} synchronisé avec succès.", userId))
                .onErrorResume(DuplicateKeyException.class, e -> {
                    log.warn("L'utilisateur {} existe déjà (concurrence), on ignore.", userId);
                    return Mono.empty();
                })
                .then();
    }

    private Mono<Void> cacheUserExistence(String key) {
        return redisTemplate.opsForValue()
                .set(key, new ExternalUserInfo(null, null, null, null, null, null, null), USER_EXISTENCE_CACHE_TTL)
                .then();
    }

    @Transactional
    public Mono<MemberResponse> updateFullProfile(UUID userId, UpdateFullProfileRequest request) {
        return userRepository.findById(userId)
                .switchIfEmpty(Mono.error(new RuntimeException("Utilisateur introuvable")))
                .flatMap(user ->
                        profileRepository.findByUserId(userId)
                                .defaultIfEmpty(Profile.createNew(userId, "", "")) // Fallback si pas de profil
                                .flatMap(profile -> {

                                    // 1. Gestion de l'image (si fournie)
                                    Mono<String> imageMono = (request.profileImage() != null)
                                            ? fileStoragePort.uploadFile(request.profileImage(), "avatars")
                                            : Mono.justOrEmpty(profile.profilImageUrl());

                                    return imageMono.flatMap(imgUrl -> {

                                        // 2. Mise à jour Entité USER (Base)
                                        String newFirstName = request.firstName() != null ? request.firstName() : profile.firstName();
                                        String newLastName = request.lastName() != null ? request.lastName() : profile.lastName();
                                        String fullName = newFirstName + " " + newLastName;
                                        String newPhone = request.phoneNumber() != null ? request.phoneNumber() : user.phoneNumber();

                                        User updatedUser = new User(
                                                user.id(),
                                                fullName,
                                                newPhone,
                                                user.email(),
                                                false // isNewRecord = false pour UPDATE
                                        );

                                        // 3. Mise à jour Entité PROFILE (Extended)
                                        Profile updatedProfile = new Profile(
                                                profile.id(),
                                                profile.userId(),
                                                imgUrl,
                                                newFirstName,
                                                newLastName,
                                                request.birthDate() != null ? request.birthDate() : profile.birth_date(),
                                                request.nationality() != null ? request.nationality() : profile.nationality(),
                                                request.gender() != null ? request.gender() : profile.gender(),
                                                request.language() != null ? request.language() : profile.language(),
                                                profile.createdAt(),
                                                Instant.now(), // UpdatedAt
                                                false
                                        );

                                        // 4. Sync Externe (Gateway)
                                        ExternalUserInfo extInfo = new ExternalUserInfo(
                                                userId, newFirstName, newLastName, user.email(), newPhone, null, null
                                        );

                                        // 5. Exécution transactionnelle
                                        return userRepository.save(updatedUser)
                                                .then(profileRepository.save(updatedProfile))
                                                .then(userGatewayPort.updateProfile(extInfo)) // Sync best-effort
                                                .thenReturn(new MemberResponse(
                                                        userId, newFirstName, newLastName, user.email(),
                                                        imgUrl, null, null, null
                                                ));
                                    });
                                })
                );
    }
}
```

*Lignes: 150*

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


    private final SyndicatMemberRepository memberRepository;
    private final SyndicatRepository syndicatRepository;
    private final AvisRepository avisRepository;
    private final ComplianceDetailsRepository complianceDetailsRepository;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;


    private final UserGatewayPort userGatewayPort;

    private static final Duration CACHE_TTL = Duration.ofMinutes(15);


    public Mono<ComplianceResponse> checkCompliance(UUID driverId) {
        String cacheKey = "v2_compliance:" + driverId;
        return redisTemplate.opsForValue().get(cacheKey)
                .cast(ComplianceResponse.class)
                .switchIfEmpty(
                        verifyDriverStatusLocally(driverId)
                                .flatMap(response -> redisTemplate.opsForValue()
                                        .set(cacheKey, response, CACHE_TTL)
                                        .thenReturn(response))
                );
    }

    private Mono<ComplianceResponse> verifyDriverStatusLocally(UUID driverId) {

        return memberRepository.findAllByUserId(driverId)
                .flatMap(member ->
                        syndicatRepository.findById(member.syndicatId())
                                .map(syndicat -> buildPartialResponse(member, syndicat))
                )
                .collectList()
                .map(partialResults -> {

                    if (partialResults.isEmpty()) {
                        return buildBannedResponse(driverId, "NOT_A_MEMBER");
                    }

                    boolean isAuthorized = partialResults.stream()
                            .anyMatch(res -> "AUTHORIZED".equals(res.globalStatus()));

                    if (isAuthorized) {
                        return partialResults.stream()
                                .filter(res -> "AUTHORIZED".equals(res.globalStatus()))
                                .findFirst().get();
                    } else {
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

        // 1. Récupération User Info (Robuste)
        // On essaie le portail externe/redis, sinon on fallback sur la table locale 'users'
        Mono<ExternalUserInfo> userMono = userGatewayPort.findById(driverId)
                .switchIfEmpty(
                        userRepository.findById(driverId)
                                .map(localUser -> {
                                    // Extraction basique Prénom/Nom si on n'a que le "name" complet
                                    String fullName = localUser.name() != null ? localUser.name() : "Utilisateur Inconnu";
                                    String[] parts = fullName.split(" ", 2);
                                    String firstName = parts.length > 0 ? parts[0] : "";
                                    String lastName = parts.length > 1 ? parts[1] : "";

                                    return new ExternalUserInfo(
                                            localUser.id(),
                                            firstName,
                                            lastName,
                                            localUser.email(),
                                            localUser.phoneNumber(),
                                            List.of(),
                                            List.of()
                                    );
                                })
                );

        // 2. Récupération Profile (Robuste)
        // Si pas de profil, on en crée un vide pour éviter que le ZIP ne plante
        Mono<Profile> profileMono = profileRepository.findByUserId(driverId)
                .defaultIfEmpty(new Profile(
                        UUID.randomUUID(), driverId, null, "", "", null, null, null, null, Instant.now(), Instant.now()
                ));

        // 3. Récupération Détails Compliance (Déjà robuste mais on garde)
        Mono<ComplianceDetails> detailsMono = complianceDetailsRepository.findById(driverId)
                .defaultIfEmpty(ComplianceDetails.createEmpty(driverId));

        // 4. Assemblage (Mono.zip n'échouera plus car tous les monos ont une valeur par défaut)
        return Mono.zip(userMono, detailsMono, profileMono)
                .map(tuple -> {
                    ExternalUserInfo user = tuple.getT1();
                    ComplianceDetails details = tuple.getT2();
                    Profile profile = tuple.getT3();

                    // Logique de priorité pour la photo : Compliance > Profile > Rien
                    String officialPhotoUrl = (details.profilePhotoUrl() != null && !details.profilePhotoUrl().isBlank())
                            ? details.profilePhotoUrl()
                            : (profile.profilImageUrl() != null ? profile.profilImageUrl() : "");

                    // Logique de priorité pour les noms : Profile > User > "N/A"
                    String finalFirstName = (profile.firstName() != null && !profile.firstName().isBlank())
                            ? profile.firstName() : user.firstName();
                    String finalLastName = (profile.lastName() != null && !profile.lastName().isBlank())
                            ? profile.lastName() : user.lastName();

                    return new OfficialProfileResponse(
                            user.id().toString(),
                            finalFirstName != null ? finalFirstName : "N/A",
                            finalLastName != null ? finalLastName : "N/A",
                            officialPhotoUrl,
                            details.cvUrl(),
                            details.cniNumber(),
                            details.cniRectoUrl(),
                            details.cniVersoUrl(),
                            details.licenseNumber(),
                            details.licenseRectoUrl(),
                            details.licenseVersoUrl(),
                            Boolean.TRUE.equals(details.isVerified())
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

*Lignes: 276*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\content\CommentService.java

```java
package com.yowyob.ugate_service.application.service.content;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import com.yowyob.ugate_service.domain.model.CommentModel;
import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.model.ImageModel;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.CommentPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.dto.CommentResponseDto;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class CommentService {

  private final MediaPersistencePort mediaPersistencePort;
  private final CommentPersistencePort commentPersistencePort;
  private final UserGatewayPort userGatewayPort;
  private final NotificationPort notificationPort;
  private final PublicationPersistencePort publicationPersistencePort;

  public Mono<Void> createComment(UUID authorId, UUID publicationId, UUID parentId, String ImageUrl, String content) {

    CommentModel comment = new CommentModel();
    comment.setAuthorId(authorId);
    comment.setPublicationId(publicationId);
    comment.setParentId(parentId);
    comment.setContent(content);
    comment.setCreatedAt(Instant.now());

    Mono<CommentModel> saveCommentMono;
    if (ImageUrl == null) {
      saveCommentMono = this.commentPersistencePort.saveComment(comment).thenReturn(comment);
    } else {
      saveCommentMono = this.mediaPersistencePort.saveImage(ImageUrl, "alt text")
          .flatMap(imageModel -> {
            comment.setImageId(imageModel.getId());
            return this.commentPersistencePort.saveComment(comment).thenReturn(comment);
          });
    }

    return saveCommentMono.flatMap(savedComment -> Mono.zip(
        publicationPersistencePort.findById(publicationId),
        userGatewayPort.findById(savedComment.getAuthorId()))
        .flatMap(tuple -> {
          var publication = tuple.getT1();
          var commenterInfo = tuple.getT2();

          return userGatewayPort.findById(publication.getAuthorId())
              .flatMap(publicationAuthorInfo -> notificationPort.sendPublicationCommentAlert(
                  publicationAuthorInfo.email(),
                  publication.getContent(), // Using content as title for now
                  commenterInfo.firstName()));
        }));
  }

  public Flux<CommentResponseDto> getCommentsByPublicationId(UUID publicationId) {
    return commentPersistencePort.findCommentsByPublicationId(publicationId)
        .flatMap(commentModel -> {

          Mono<ExternalUserInfo> authorMono = userGatewayPort.findById(commentModel.getAuthorId());

          Mono<Optional<ImageModel>> imageMono = Mono.justOrEmpty(commentModel.getImageId())
              .flatMap(mediaPersistencePort::getImageById)
              .map(Optional::of)
              .defaultIfEmpty(Optional.empty());

          return authorMono.zipWith(imageMono)
              .map(tuple -> {
                ExternalUserInfo author = tuple.getT1();
                Optional<ImageModel> imageOpt = tuple.getT2();

                CommentResponseDto dto = new CommentResponseDto();
                dto.setAuthorFullName(author.firstName() + " " + author.lastName());
                dto.setAuthorId(author.id());
                dto.setContent(commentModel.getContent());
                dto.setCreatedAt(commentModel.getCreatedAt());
                dto.setId(commentModel.getId());
                dto.setParentId(commentModel.getParentId());
                dto.setPublicationId(commentModel.getPublicationId());

                imageOpt.ifPresent(img -> dto.setImageUrl(img.getUrl()));

                return dto;
              });
        });
  }

}

```

*Lignes: 98*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\content\EventService.java

```java
package com.yowyob.ugate_service.application.service.content;

import com.yowyob.ugate_service.domain.model.ImageModel;
import com.yowyob.ugate_service.domain.model.UserEventModel;
import com.yowyob.ugate_service.domain.model.UserModel;
import com.yowyob.ugate_service.domain.ports.in.content.CreateEventUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.GetEventParticipantsUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.GetEventsByBranchUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.JoinEventUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.LeaveEventUseCase;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserEventPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.EventResponseDTO;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ParticipantDTO;
import lombok.AllArgsConstructor;
import com.yowyob.ugate_service.domain.model.EventModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.EventPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class EventService implements CreateEventUseCase, JoinEventUseCase, GetEventsByBranchUseCase,
                GetEventParticipantsUseCase, LeaveEventUseCase {

        private final EventPersistencePort eventPersistencePort;
        private final MediaPersistencePort mediaPersistencePort;
        private final UserEventPersistencePort userEventPersistencePort;
        private final UserGatewayPort userGatewayPort;
        private final NotificationPort notificationPort;
        private final UserPersistencePort userPersistencePort;

        @Override
        public Mono<Void> createEvent(UUID creatorId, UUID branchId, String title, String description,
                        LocalDate eventDate,
                        String location, LocalTime startTime, LocalTime endTime, String[] imagesUrls,
                        String[] videoUrls,
                        String[] filesUrls) {
                EventModel event = new EventModel();
                event.setCreatorId(creatorId);
                event.setBranchId(branchId);
                event.setTitle(title);
                event.setDescription(description);
                event.setDate(eventDate);
                event.setLocation(location);
                event.setCreatedAt(Instant.now());
                event.setUpdatedAt(Instant.now());
                event.setStartTime(startTime);
                event.setEndTime(endTime);

                return eventPersistencePort.save(event)
                                .flatMap(savedEvent -> {
                                        Mono<Void> imagesMono = Mono.empty();
                                        if (imagesUrls != null) {
                                                imagesMono = Flux.fromArray(imagesUrls)
                                                                .flatMap(imageUrl -> mediaPersistencePort
                                                                                .saveEventMedia(imageUrl, "altText",
                                                                                                savedEvent.getId()))
                                                                .then();
                                        }

                                        Mono<Void> videosMono = Mono.empty();
                                        if (videoUrls != null) {
                                                videosMono = Flux.fromArray(videoUrls)
                                                                .flatMap(videoUrl -> mediaPersistencePort
                                                                                .saveVideoMedia(videoUrl, "title",
                                                                                                savedEvent.getId()))
                                                                .then();
                                        }

                                        Mono<Void> filesMono = Mono.empty();
                                        if (filesUrls != null) {
                                                filesMono = Flux.fromArray(filesUrls)
                                                                .flatMap(fileUrl -> mediaPersistencePort.saveAudioMedia(
                                                                                fileUrl, "title",
                                                                                savedEvent.getId()))
                                                                .then();
                                        }

                                        // Send notification to all users in the branch about the new event
                                        return userPersistencePort.findUsersByBranchId(branchId).collectList()
                                                        .flatMap(users -> {
                                                                List<String> emails = users.stream()
                                                                                .map(UserModel::getEmail)
                                                                                .toList();
                                                                return notificationPort.sendNewEventAlert(emails,
                                                                                title);
                                                        })
                                                        .then(Mono.when(imagesMono, videosMono, filesMono));

                                });
        }

        @Override
        public Mono<Void> joinEvent(UUID userId, UUID eventId) {
                // Here you might add logic to check if the event and user exist before creating
                // the link
                UserEventModel userEventModel = new UserEventModel();
                userEventModel.setUserId(userId);
                userEventModel.setEventId(eventId);
                userEventModel.setTimestamp(Instant.now());
                return userEventPersistencePort.save(userEventModel);
        }

        @Override
        public Flux<EventResponseDTO> getEventsByBranch(UUID branchId) {
                return eventPersistencePort.findByBranchId(branchId)
                                .flatMap(eventModel -> {
                                        Mono<Long> participantCountMono = userEventPersistencePort
                                                        .countByEventId(eventModel.getId());
                                        Mono<List<String>> imageUrlsMono = mediaPersistencePort
                                                        .getImagesByEventId(eventModel.getId())
                                                        .map(ImageModel::getUrl)
                                                        .collectList();

                                        return Mono.zip(participantCountMono, imageUrlsMono)
                                                        .map(tuple -> new EventResponseDTO(
                                                                        eventModel.getId(),
                                                                        eventModel.getCreatorId(),
                                                                        eventModel.getBranchId(),
                                                                        eventModel.getTitle(),
                                                                        eventModel.getDescription(),
                                                                        eventModel.getLocation(),
                                                                        eventModel.getDate(),
                                                                        eventModel.getStartTime(),
                                                                        eventModel.getEndTime(),
                                                                        eventModel.getCreatedAt(),
                                                                        eventModel.getUpdatedAt(),
                                                                        tuple.getT1(), // participant count
                                                                        tuple.getT2() // image urls
                                        ));
                                });
        }

        @Override
        public Flux<ParticipantDTO> getParticipants(UUID eventId) {
                return userEventPersistencePort.findByEventId(eventId)
                                .flatMap(userEvent -> userGatewayPort.findById(userEvent.getUserId()))
                                .map(userInfo -> new ParticipantDTO(userInfo.id(),
                                                userInfo.firstName() + " " + userInfo.lastName()));
        }

        @Override
        public Mono<Void> leaveEvent(UUID userId, UUID eventId) {
                return userEventPersistencePort.delete(userId, eventId);
        }
}
```

*Lignes: 155*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\content\FeedService.java

```java
package com.yowyob.ugate_service.application.service.content;

import com.yowyob.ugate_service.domain.model.EventModel;
import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.model.ImageModel;
import com.yowyob.ugate_service.domain.model.MediaInfo;
import com.yowyob.ugate_service.domain.model.PublicationModel;
import com.yowyob.ugate_service.domain.ports.in.content.GetFeedUseCase;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.EventPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserEventPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.dto.PublicationResponseDTO;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.EventResponseDTO;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class FeedService implements GetFeedUseCase {
    private final PublicationPersistencePort publicationModelRepository;
    private final EventPersistencePort eventPersistencePort;
    private final UserGatewayPort userGatewayPort;
    private final MediaPersistencePort mediaPersistencePort;
    private final UserEventPersistencePort userEventPersistencePort;

    @Override
    public Flux<Map<String, Object>> getFeed(int page, int size) {
        Flux<Map<String, Object>> publicationsFlux = publicationModelRepository
                .findAllPaginated(page, size)
                .flatMap(this::toPublicationResponseDTO)
                .map(dto -> Map.of(
                        "type", "publication",
                        "data", dto));

        Flux<Map<String, Object>> eventsFlux = eventPersistencePort
                .findAllPaginated(page, size)
                .flatMap(this::toEventResponseDTO)
                .map(dto -> Map.of(
                        "type", "event",
                        "data", dto));

        return publicationsFlux.mergeWith(eventsFlux)
                .sort((a, b) -> {
                    Instant aCreatedAt = getInstant(a.get("data"));
                    Instant bCreatedAt = getInstant(b.get("data"));

                    if (aCreatedAt == null && bCreatedAt == null) {
                        return 0;
                    }
                    if (aCreatedAt == null) {
                        return 1;
                    }
                    if (bCreatedAt == null) {
                        return -1;
                    }

                    return bCreatedAt.compareTo(aCreatedAt);
                })
                .skip((long) page * size)
                .take(size);
    }

    private Instant getInstant(Object data) {
        if (data instanceof PublicationResponseDTO) {
            return ((PublicationResponseDTO) data).getCreatedAt();
        } else if (data instanceof EventResponseDTO) {
            return ((EventResponseDTO) data).getCreatedAt();
        }
        return null;
    }

    private Mono<PublicationResponseDTO> toPublicationResponseDTO(PublicationModel publicationModel) {
        Mono<ExternalUserInfo> authorMono = userGatewayPort.findById(publicationModel.getAuthorId());
        Mono<List<MediaInfo>> mediaListMono = mediaPersistencePort.getMediaByPublicationId(publicationModel.getId())
                .map(mediaInfo -> {
                    MediaInfo fileDto = new MediaInfo();
                    fileDto.setUrl(mediaInfo.getUrl());
                    fileDto.setType(mediaInfo.getType());
                    return fileDto;
                })
                .collectList();

        return Mono.zip(authorMono, mediaListMono)
                .map(tuple -> {
                    ExternalUserInfo author = tuple.getT1();
                    List<MediaInfo> mediaList = tuple.getT2();

                    PublicationResponseDTO dto = new PublicationResponseDTO();
                    dto.setId(publicationModel.getId());
                    dto.setBranchId(publicationModel.getBranchI());
                    dto.setAuthorFullName(author.firstName() + " " + author.lastName());
                    dto.setContent(publicationModel.getContent());
                    dto.setNLikes(publicationModel.getNLikes());
                    // dto.setNComments(publicationModel.get); // Assuming comments count is not directly in model
                    dto.setCreatedAt(publicationModel.getCreatedAt());
                    dto.setFileUrlAndType(mediaList);
                    return dto;
                });
    }

    private Mono<EventResponseDTO> toEventResponseDTO(EventModel eventModel) {
        Mono<Long> participantCountMono = userEventPersistencePort.countByEventId(eventModel.getId());
        Mono<List<String>> imageUrlsMono = mediaPersistencePort.getImagesByEventId(eventModel.getId())
                .map(ImageModel::getUrl) // Assuming ImageModel has getUrl()
                .collectList();

        return Mono.zip(participantCountMono, imageUrlsMono)
                .map(tuple -> new EventResponseDTO(
                        eventModel.getId(),
                        eventModel.getCreatorId(),
                        eventModel.getBranchId(),
                        eventModel.getTitle(),
                        eventModel.getDescription(),
                        eventModel.getLocation(),
                        eventModel.getDate(),
                        eventModel.getStartTime(),
                        eventModel.getEndTime(),
                        eventModel.getCreatedAt(),
                        eventModel.getUpdatedAt(),
                        tuple.getT1(), // participant count
                        tuple.getT2()  // image urls
                ));
    }
}

```

*Lignes: 131*

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

import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.model.MediaInfo;
import com.yowyob.ugate_service.domain.model.PublicationModel;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.BranchPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.dto.PublicationResponseDTO;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class PublicationService {

        private final PublicationPersistencePort publicationModelRepository;
        private final MediaPersistencePort mediaPersistencePort;
        private final UserGatewayPort userGatewayPort;
        private final NotificationPort notificationPort;
        private final BranchPersistencePort branchPersistencePort;
        private final SyndicatRepository syndicatRepository;

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
                                        Mono<Void> mediaMonos = Mono.when(
                                            (imagesUrls != null ? Flux.fromArray(imagesUrls)
                                                .flatMap(imageUrl -> mediaPersistencePort.saveImageMedia(imageUrl, "image", savedPublication.getId())).then() : Mono.empty()),
                                            (videoUrls != null ? Flux.fromArray(videoUrls)
                                                .flatMap(videoUrl -> mediaPersistencePort.saveVideoMedia(videoUrl, "video", savedPublication.getId())).then() : Mono.empty()),
                                            (filesUrls != null ? Flux.fromArray(filesUrls)
                                                .flatMap(fileUrl -> mediaPersistencePort.saveAudioMedia(fileUrl, "audio", savedPublication.getId())).then() : Mono.empty())
                                        );

                                        Mono<Void> adminNotificationMono = Mono.zip(
                                            branchPersistencePort.findById(branchId),
                                            userGatewayPort.findById(savedPublication.getAuthorId())
                                        )
                                        .flatMap(branchAndAuthorTuple -> {
                                            var branch = branchAndAuthorTuple.getT1();
                                            var authorInfo = branchAndAuthorTuple.getT2();

                                            return syndicatRepository.findById(branch.syndicatId())
                                                .flatMap(syndicat ->
                                                    userGatewayPort.findById(syndicat.creatorId()) // Assuming creatorId is the admin
                                                        .flatMap(adminInfo ->
                                                            notificationPort.sendAdminAlertWhenNewPublication(
                                                                List.of(adminInfo.email()),
                                                                savedPublication.getContent(), // Using content as title
                                                                authorInfo.firstName() + " " + authorInfo.lastName()
                                                            )
                                                        )
                                                );
                                        });

                                        return Mono.when(mediaMonos, adminNotificationMono);
                                });
        }

        public Flux<PublicationResponseDTO> getSyndicatPublication(UUID branchId) {
                // Récupérer toutes les publications d'une branche
                // etenfin pour chaque publication récupérer les infos et retourner.
                return publicationModelRepository.findByBranchId(branchId)
                                .flatMap(publicationModel -> {
                                        Mono<ExternalUserInfo> authorMono = userGatewayPort
                                                        .findById(publicationModel.getAuthorId());
                                        Mono<List<MediaInfo>> mediaListMono = mediaPersistencePort
                                                        .getMediaByPublicationId(publicationModel.getId())
                                                        .map(mediaInfo -> {
                                                                MediaInfo fileDto = new MediaInfo();
                                                                fileDto.setUrl(mediaInfo.getUrl());
                                                                fileDto.setType(mediaInfo.getType());
                                                                return fileDto;
                                                        })
                                                        .collectList(); // Collect Flux to Mono<List>

                                        return Mono.zip(authorMono, mediaListMono)
                                                        .map(tuple -> {
                                                                ExternalUserInfo author = tuple.getT1();
                                                                List<MediaInfo> mediaList = tuple.getT2();

                                                                PublicationResponseDTO dto = new PublicationResponseDTO();
                                                                dto.setId(publicationModel.getId());
                                                                dto.setBranchId(publicationModel.getBranchI());
                                                                dto.setAuthorFullName(author.firstName() + " "
                                                                                + author.lastName());
                                                                dto.setContent(publicationModel.getContent());
                                                                dto.setNLikes(publicationModel.getNLikes());
                                                                // dto.setNComments(publicationModel.get); normalement
                                                                // pour le nombre de
                                                                // commentaire
                                                                dto.setCreatedAt(publicationModel.getCreatedAt());
                                                                dto.setFileUrlAndType(mediaList); // Set the collected
                                                                                                  // List
                                                                return dto;
                                                        });
                                });
        }

        public Mono<Void> incrementLikes(UUID publicationId) {
                return publicationModelRepository.incrementLikes(publicationId)
                        .switchIfEmpty(Mono.empty());
        }
}
```

*Lignes: 121*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\content\PublicationVoteService.java

```java
package com.yowyob.ugate_service.application.service.content;

import com.yowyob.ugate_service.domain.model.PublicationVoteModel;
import com.yowyob.ugate_service.domain.model.VoteModel;
import com.yowyob.ugate_service.domain.ports.in.content.CastVoteUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.CreatePublicationVoteUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.GetPublicationVoteResultsUseCase;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationVotePersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.VotePersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.PublicationVoteWithResultsDTO;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.VoteResultDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublicationVoteService
        implements CreatePublicationVoteUseCase, CastVoteUseCase, GetPublicationVoteResultsUseCase {

    private final PublicationVotePersistencePort publicationVotePersistencePort;
    private final VotePersistencePort votePersistencePort;

    @Override
    public Mono<Void> createPublicationVote(PublicationVoteModel publicationVoteModel) {

        return publicationVotePersistencePort.save(publicationVoteModel).then();
    }

    @Override
    public Mono<Void> castVote(UUID userId, UUID publicationVoteId, String choiceLabel) {
        return publicationVotePersistencePort.findById(publicationVoteId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Poll not found")))
                .flatMap(poll -> {
                    if (poll.getClosingAt() != null && poll.getClosingAt().isBefore(Instant.now())) {
                        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "This poll is closed."));
                    }
                    VoteModel vote = new VoteModel(null, userId, publicationVoteId, choiceLabel);
                    return votePersistencePort.save(vote);
                });
    }

    @Override
    public Mono<PublicationVoteWithResultsDTO> getPublicationVoteResults(UUID publicationVoteId, UUID userId) {
        Mono<PublicationVoteModel> pollMono = publicationVotePersistencePort.findById(publicationVoteId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Poll not found")));

        Mono<List<VoteModel>> votesMono = votePersistencePort.findByPublicationVoteId(publicationVoteId).collectList();

        return Mono.zip(pollMono, votesMono)
                .map(tuple -> {
                    PublicationVoteModel poll = tuple.getT1();
                    List<VoteModel> votes = tuple.getT2();

                    long totalVotes = votes.size();
                    boolean hasVoted = votes.stream().anyMatch(v -> v.getUserId().equals(userId));

                    List<VoteResultDTO> results = votes.stream()
                            .collect(Collectors.groupingBy(VoteModel::getLabel, Collectors.counting()))
                            .entrySet().stream()
                            .map(entry -> new VoteResultDTO(entry.getKey(), entry.getValue()))
                            .collect(Collectors.toList());

                    return PublicationVoteWithResultsDTO.from(poll, totalVotes, results, hasVoted);
                });
    }
}
```

*Lignes: 73*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\content\ReactionService.java

```java
package com.yowyob.ugate_service.application.service.content;

import java.util.UUID;

import com.yowyob.ugate_service.domain.enumeration.ReactionTypeEnum;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.ReactionPersistencePort;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.Loggers;
import reactor.util.Logger;

@AllArgsConstructor
public class ReactionService {

  private static final Logger log = Loggers.getLogger(ReactionService.class);

  // Une methode pour ajouter une reaction à une publication et changer nlikes
  // dans le publication service en appelant la methode prévu
  private final PublicationService publicationService;
  private final ReactionPersistencePort reactionPersistencePort;
  private final PublicationPersistencePort publicationPersistencePort;
  private final UserGatewayPort userGatewayPort;
  private final NotificationPort notificationPort;

  public Mono<Void> addReactionToPublication(UUID publicationId, UUID userId, ReactionTypeEnum reactionType) {
    log.info("addReactionToPublication called for publicationId: {}, userId: {}", publicationId, userId);
    return reactionPersistencePort.saveReaction(publicationId, reactionType, userId)
        .doOnSuccess(v -> log.info("Reaction saved for publicationId: {}", publicationId))
        .then(publicationService.incrementLikes(publicationId)
            .doOnSuccess(v -> log.info("Likes incremented for publicationId: {}", publicationId))
            .doOnError(e -> log.error("Error incrementing likes for publicationId: {}", publicationId, e)))
        .then(Mono.defer(() -> {
            log.info("Mono.defer block entered for publicationId: {}", publicationId);
            return Mono.zip(
                publicationPersistencePort.findById(publicationId)
                    .doOnNext(p -> log.info("Found publication model: {}", p.getId()))
                    .doOnError(e -> log.error("Error finding publication model for id: {}", publicationId, e)),
                userGatewayPort.findById(userId)
                    .doOnNext(u -> log.info("Found reactor user info: {}", u.id()))
                    .doOnError(e -> log.error("Error finding reactor user info for id: {}", userId, e))
            );
        }))
        .flatMap(tuple -> {
            log.info("flatMap after Mono.zip entered for publicationId: {}", publicationId);
            Tuple2<com.yowyob.ugate_service.domain.model.PublicationModel, com.yowyob.ugate_service.domain.model.ExternalUserInfo> typedTuple = tuple;
            var publication = typedTuple.getT1();
            var reactorInfo = typedTuple.getT2();

            return userGatewayPort.findById(publication.getAuthorId())
                .doOnNext(pubAuthorInfo -> log.info("Found publication author info: {}", pubAuthorInfo.id()))
                .doOnError(e -> log.error("Error finding publication author info for id: {}", publication.getAuthorId(), e))
                .flatMap(publicationAuthorInfo ->
                        notificationPort.sendPublicationReactAlert(
                                publicationAuthorInfo.email(),
                                publication.getContent(), // Using content as title for now
                                reactorInfo.firstName()
                        ).doOnSuccess(v -> log.info("Publication reaction alert sent for publicationId: {}", publicationId))
                        .doOnError(e -> log.error("Error sending notification for publicationId: {}", publicationId, e))
                );
        })
        .then() // Ensure the Mono<Void> return type
        .doOnSuccess(v -> log.info("addReactionToPublication completed successfully for publicationId: {}", publicationId))
        .doOnError(e -> log.error("addReactionToPublication encountered an error for publicationId: {}", publicationId, e));
  }
}

```

*Lignes: 71*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\marketplace\ProductManagementService.java

```java
package com.yowyob.ugate_service.application.service.marketplace;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yowyob.ugate_service.domain.exception.InsufficientStockException;
import com.yowyob.ugate_service.domain.exception.ProductNotFoundException;
import com.yowyob.ugate_service.domain.model.Product;
import com.yowyob.ugate_service.domain.ports.in.marketplace.ManageProductUseCase;
import com.yowyob.ugate_service.domain.ports.out.marketplace.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductManagementService implements ManageProductUseCase {
    private final ProductRepositoryPort productRepositoryPort;

    @Override
    @Transactional 
    public Mono<Product> createProduct(Product product) {

        Product productToSave  = new Product(
            product.id() != null ? product.id() : UUID.randomUUID(),
            product.syndicatId(),
            product.name(),
            product.description(),
            product.price(),
            product.sku(),
            product.category(),
            product.stock(),
            product.imageUrl(),
            product.isActive()
        );

        return productRepositoryPort.saveProduct(productToSave);
    }

    @Override
    public Mono<Product> updateStock(UUID id, Integer quantity) {
        return productRepositoryPort.findById(id)
            .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found")))
            .flatMap(existing -> {
                int newStock = existing.stock() + quantity;
                if (newStock < 0) return Mono.error(new InsufficientStockException("Insufficient stock available"));
                Product productToUpdate = new Product(
                    existing.id(),
                    existing.syndicatId(),
                    existing.name(),
                    existing.description(),
                    existing.price(),
                    existing.sku(),
                    existing.category(),
                    newStock,
                    existing.imageUrl(),
                    existing.isActive()
                );
                return productRepositoryPort.updateProduct(productToUpdate);
            });
    }
    
    @Override
    public Mono<Void> deleteProduct(UUID id) {
        return productRepositoryPort.findById(id)
            .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found")))
            .flatMap(existing -> productRepositoryPort.deleteById(id));
    }

    @Override
    public Mono<Product> updateProduct(Product product) {
        return productRepositoryPort.findById(product.id())
            .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found")))
            .flatMap(existing -> {
                
                Product productToUpdate = new Product(
                    existing.id(),
                    product.syndicatId() == null ? existing.syndicatId() : product.syndicatId(),
                    product.name() == null ? existing.name() :  product.name(),
                    product.description() == null ? existing.description() : product.description(),
                    product.price() == null ? existing.price() : product.price(),
                    product.sku() == null ? existing.sku() : product.sku(),
                    product.category() == null ? existing.category() : product.category(),
                    existing.stock(),
                    existing.imageUrl(),
                    existing.isActive()
                );
                return productRepositoryPort.updateProduct(productToUpdate);
            });
    }

    @Override
    public Flux<Product> getSyndicatProducts(UUID syndicatId) {
        return productRepositoryPort.findBySyndicatId(syndicatId);
    }

    @Override
    public Mono<Product> getProductDetails(UUID id) {
       return productRepositoryPort.findById(id)
            .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found")));
    }
}

```

*Lignes: 104*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\marketplace\ServiceOfferingService.java

```java
package com.yowyob.ugate_service.application.service.marketplace;
import java.util.UUID;

import com.yowyob.ugate_service.domain.exception.ServiceNotFoundException;
import com.yowyob.ugate_service.domain.model.SyndicatService;
import com.yowyob.ugate_service.domain.ports.in.marketplace.ManageServiceUseCase;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

import com.yowyob.ugate_service.domain.ports.out.marketplace.ServiceOfferingRepositoryPort;



@Service
@RequiredArgsConstructor
public class ServiceOfferingService implements ManageServiceUseCase {

    public final ServiceOfferingRepositoryPort serviceOfferingRepositoryPort;

    @Override
    public Mono<SyndicatService> createService(SyndicatService service) {
        SyndicatService serviceToCreate = new SyndicatService(
            UUID.randomUUID(),
            service.syndicatId(),
            service.title(),
            service.description(),
            service.price(),
            service.features(),
            service.isActive()
        );

        return serviceOfferingRepositoryPort.save(serviceToCreate);
    }

    @Override
    public Mono<SyndicatService> updateService(SyndicatService service) {
        return serviceOfferingRepositoryPort.findServiceById(service.id())
            .flatMap(existing -> {
                SyndicatService serviceToUpdate = new SyndicatService(
                    existing.id(),
                    existing.syndicatId(),
                    service.title() != null ? service.title() : existing.title(),
                    service.description() != null ? service.description() : existing.description(),
                    service.price() != null ? service.price() : existing.price(),
                    service.features() != null ? service.features() : existing.features(),
                    service.isActive() != null ? service.isActive() : existing.isActive()
                );
                return serviceOfferingRepositoryPort.updateService(serviceToUpdate);
            });
    }

    @Override
    public Mono<Void> deleteService(UUID id) {
        return serviceOfferingRepositoryPort.findServiceById(id)
            .switchIfEmpty(Mono.error(new ServiceNotFoundException("Service not found")))
            .flatMap( serviceOffering -> serviceOfferingRepositoryPort.deleteService(serviceOffering.id()));
        
    }

    @Override
    public Flux<SyndicatService> getAllActiveServices() {
        return serviceOfferingRepositoryPort.findAllActiveServices();
    }

    @Override
    public Mono<SyndicatService> getServiceDetails(UUID id) {
        return serviceOfferingRepositoryPort.findServiceById(id)
            .switchIfEmpty(Mono.error(new ServiceNotFoundException("Service not found")));
    }
}

```

*Lignes: 75*

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

import com.yowyob.ugate_service.domain.ports.out.media.FileStoragePort; // Import du port Média
import com.yowyob.ugate_service.domain.ports.out.syndicate.BranchPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreateBranchRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.UpdateBranchRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.BranchResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Agency;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Branch;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.AgencyRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatRepository;
import com.yowyob.ugate_service.infrastructure.mappers.syndicate.BranchMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class BranchManagementService {

    private final BranchPersistencePort branchPersistencePort;
    private final FileStoragePort fileStoragePort;
    private final BranchMapper branchMapper;
    private final AgencyRepository agencyRepository;
    private final SyndicatRepository syndicatRepository;





    @Transactional
    public Mono<BranchResponse> createBranch(UUID syndicatId, CreateBranchRequest request) {
        return syndicatRepository.findById(syndicatId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Syndicat introuvable : " + syndicatId)))
                .flatMap(syndicat -> {
                    Mono<String> bannerUrlMono;
                    if (request.getBanner() != null && !request.getBanner().filename().isBlank()) {
                        bannerUrlMono = fileStoragePort.uploadFile(request.getBanner(), "branches");
                    } else {
                        bannerUrlMono = Mono.justOrEmpty(null);
                    }

                    return bannerUrlMono.flatMap(bannerUrl -> {
                        UUID sharedId = UUID.randomUUID();


                        Agency newAgency = Agency.createNew(
                                sharedId,
                                syndicat.organizationId(),
                                request.getName()
                        );

                        // 4. Création de la Branch
                        Branch newBranch = Branch.createNew(
                                sharedId,
                                syndicatId,
                                request.getName(),
                                request.getLocation(),
                                request.getContact(),
                                bannerUrl
                        );

                        return agencyRepository.save(newAgency)
                                .then(branchPersistencePort.save(newBranch));
                    });
                })
                .map(branchMapper::toResponse)
                .doOnSuccess(b -> log.info("Branche créée avec succès : {}", b.id()));
    }

    public Flux<BranchResponse> getSyndicateBranches(UUID syndicatId) {
        return branchPersistencePort.findBySyndicatId(syndicatId)
                .map(branchMapper::toResponse);
    }

    @Transactional
    public Mono<BranchResponse> updateBranch(UUID branchId, UpdateBranchRequest request) {

        return branchPersistencePort.findById(branchId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Branche introuvable : " + branchId)))
                .flatMap(existingBranch -> {
                    String newName = request.name() != null ? request.name() : existingBranch.name();
                    String newLocation = request.location() != null ? request.location() : existingBranch.location();
                    String newContact = request.contact() != null ? request.contact() : existingBranch.contact();

                    Branch updatedBranch = existingBranch.withInfo(newName, newLocation, newContact, existingBranch.bannerUrl());
                    return branchPersistencePort.save(updatedBranch);
                })
                .map(branchMapper::toResponse);
    }
}
```

*Lignes: 101*

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

import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.AddUserResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.MemberResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateFullStatsResponse;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyndicateMembershipService {

    private final MembershipRequestRepository requestRepository;
    private final SyndicatMemberRepository memberRepository;
    private final SyndicatRepository syndicatRepository;
    private final BranchRepository branchRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    // Pour les stats (si besoin de compter les publications)
    private final PublicationRepository publicationRepository;

    private final UserGatewayPort userGateway;
    private final NotificationPort notificationPort;

    /**
     * Un utilisateur demande à rejoindre un syndicat via une branche spécifique.
     */
    @Transactional
    public Mono<MembershipRequest> requestToJoin(UUID syndicatId, UUID branchId, String motivation) {
        return getCurrentUserId()
                .flatMap(userId ->
                        memberRepository.existsBySyndicatIdAndUserId(syndicatId, userId)
                                .flatMap(isMember -> {
                                    if (Boolean.TRUE.equals(isMember)) {
                                        return Mono.error(new IllegalStateException("Vous êtes déjà membre de ce syndicat."));
                                    }

                                    return requestRepository.findLastRequest(userId, syndicatId)
                                            .flatMap(lastRequest -> {
                                                if (lastRequest.status() == MembershipRequest.MembershipStatus.PENDING) {
                                                    return Mono.error(new IllegalStateException("Une demande est déjà en cours de traitement."));
                                                }
                                                return createNewRequest(userId, syndicatId, branchId, motivation);
                                            })
                                            .switchIfEmpty(createNewRequest(userId, syndicatId, branchId, motivation));
                                })
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
     * Un admin traite (approuve ou rejette) une demande.
     */
    @Transactional
    public Mono<Void> processRequest(UUID requestId, boolean approve, String rejectionReason) {
        return requestRepository.findById(requestId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Demande introuvable")))
                .flatMap(request -> {
                    if (request.status() != MembershipRequest.MembershipStatus.PENDING) {
                        return Mono.error(new IllegalStateException("Cette demande a déjà été traitée."));
                    }

                    if (approve) {
                        MembershipRequest approvedRequest = new MembershipRequest(
                                request.id(), request.userId(), request.syndicatId(), request.branchId(),
                                MembershipRequest.MembershipStatus.APPROVED, request.motivation(),
                                null, request.createdAt(), Instant.now()
                        );

                        SyndicatMember newMember = SyndicatMember.createLocal(
                                request.syndicatId(),
                                request.branchId(),
                                request.userId(),
                                RoleTypeEnum.CLIENT
                        );

                        return requestRepository.save(approvedRequest)
                                .then(memberRepository.insertMember(
                                        request.syndicatId(),
                                        request.userId(),
                                        request.branchId(),
                                        true,
                                        RoleTypeEnum.CLIENT.name()
                                ));
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

    // --- LECTURE DES DEMANDES ---

    /**
     * Récupérer toutes les demandes en attente pour un syndicat (toutes branches).
     */
    public Flux<MembershipRequest> getRequestsBySyndicate(UUID syndicatId) {
        return requestRepository.findBySyndicatIdAndStatus(syndicatId, MembershipRequest.MembershipStatus.PENDING, null);
    }

    /**
     * Récupérer les demandes en attente pour une branche spécifique.
     */
    public Flux<MembershipRequest> getRequestsByBranch(UUID branchId) {
        return requestRepository.findByBranchIdAndStatus(branchId, MembershipRequest.MembershipStatus.PENDING);
    }

    /**
     * Détails d'une demande spécifique.
     */
    public Mono<MembershipRequest> getRequestDetails(UUID requestId) {
        return requestRepository.findById(requestId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Demande non trouvée")));
    }

    // --- GESTION DES RÔLES ---

    /**
     * Modifier le rôle d'un membre dans une branche spécifique.
     * Protection : Impossible de modifier le rôle du créateur du syndicat.
     */
    @Transactional
    public Mono<Void> updateMemberRole(UUID syndicatId, UUID targetUserId, RoleTypeEnum newRole) {
        return memberRepository.findBySyndicatIdAndUserId(syndicatId, targetUserId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Membre introuvable dans ce syndicat")))
                .flatMap(member ->
                        syndicatRepository.findById(syndicatId)
                                .flatMap(syndicat -> {
                                    if (syndicat.creatorId().equals(targetUserId)) {
                                        return Mono.error(new IllegalStateException("Impossible de modifier le rôle du créateur du syndicat."));
                                    }
                                    SyndicatMember updatedMember = member.withRole(newRole);
                                    return memberRepository.save(updatedMember);
                                })
                )
                .then();
    }


    public Mono<SyndicateFullStatsResponse> getSyndicateStats(UUID syndicatId) {
        return Mono.zip(
                memberRepository.countBySyndicatIdAndIsActiveTrue(syndicatId)
                        .defaultIfEmpty(0L),
                branchRepository.countBySyndicatId(syndicatId)
                        .defaultIfEmpty(0L),
                requestRepository.countBySyndicatIdAndStatus(syndicatId, MembershipRequest.MembershipStatus.PENDING)
                        .defaultIfEmpty(0L),

                // 4. Services (Actuellement 0 car le lien Service <-> Syndicat n'est pas encore défini dans l'entité ServiceEntity)
                Mono.just(0L),


                publicationRepository.countBySyndicatId(syndicatId)
                        .defaultIfEmpty(0L)
        ).map(tuple -> new SyndicateFullStatsResponse(
                tuple.getT1(), // totalMembers
                tuple.getT2(), // totalBranches
                tuple.getT3(), // pendingRequests
                tuple.getT4(), // activeServices
                tuple.getT5()  // totalPublications
        ));
    }



    
    //TODO LES Méthodes qui suivent sont à refactoriser
    @Transactional
    public Mono<AddUserResponse> addMemberByAdmin(UUID syndicatId, UUID branchId,
                                                  String email, String firstName, String lastName) {
        log.info("🔵 Début ajout membre - Syndicat: {}, Branche: {}, Email: {}",
                syndicatId, branchId, email);

        return syndicatRepository.findById(syndicatId)
                .doOnNext(s -> log.debug("✅ Syndicat trouvé: {}", s.name()))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Syndicat introuvable")))
                .zipWith(branchRepository.findById(branchId)
                        .doOnNext(b -> log.debug("✅ Branche trouvée: {}", branchId))
                        .switchIfEmpty(Mono.error(new ResourceNotFoundException("Branche introuvable"))))
                .flatMap(tuple -> {
                    var syndicat = tuple.getT1();
                    log.debug("📋 Vérification existence utilisateur: {}", email);

                    // Mono<Tuple2<User, Boolean>> où Boolean indique si c'est un nouvel user
                    Mono<Tuple2<User, Boolean>> userMono = userRepository.findByEmail(email)
                            .doOnNext(u -> log.info("✅ Utilisateur existant trouvé: {} (ID: {})",
                                    email, u.getId()))
                            .map(user -> Tuples.of(user, false)) // Utilisateur existant
                            .switchIfEmpty(Mono.defer(() -> {
                                log.info("🆕 Utilisateur inexistant, création externe + locale pour: {}", email);

                                return userGateway.registerUser(email, firstName, lastName, "00000000")
                                        .doOnNext(extUser -> log.info(
                                                "✅ Utilisateur créé dans système externe - ID: {}, Email: {}",
                                                extUser.id(), email))
                                        .doOnError(e -> log.error(
                                                "❌ ERREUR création utilisateur externe pour {}: {}",
                                                email, e.getMessage(), e))
                                        .flatMap(extUser -> {
                                            User localUser = new User(
                                                    extUser.id(),
                                                    firstName + " " + lastName,
                                                    null,
                                                    email
                                            );
                                            Profile localProfile = Profile.createNew(
                                                    extUser.id(), firstName, lastName);

                                            log.debug("💾 Sauvegarde utilisateur local + profil - ID: {}", extUser.id());

                                            return userRepository.save(localUser)
                                                    .doOnSuccess(u -> log.info("✅ User local sauvegardé: {} - {}",
                                                            u.getId(), u.email()))
                                                    .doOnError(e -> log.error(
                                                            "❌ ERREUR sauvegarde user local (ID: {}): {} - " +
                                                                    "ATTENTION: User externe créé mais pas en local!",
                                                            extUser.id(), e.getMessage(), e))
                                                    .zipWith(profileRepository.save(localProfile)
                                                            .doOnSuccess(p -> log.debug("✅ Profil sauvegardé: {}", p.userId()))
                                                            .doOnError(e -> log.error(
                                                                    "❌ ERREUR sauvegarde profil (ID: {}): {} - " +
                                                                            "ATTENTION: User externe créé, user local sauvegardé mais pas le profil!",
                                                                    extUser.id(), e.getMessage(), e)))
                                                    .map(userAndProfile -> Tuples.of(userAndProfile.getT1(), true)) // Nouvel utilisateur
                                                    .onErrorResume(e -> {
                                                        log.error(
                                                                "🔴 ROLLBACK NÉCESSAIRE - Échec sauvegarde locale " +
                                                                        "mais user externe créé (ID: {}, Email: {}). " +
                                                                        "Action requise: Nettoyer user externe ou réessayer sync.",
                                                                extUser.id(), email, e);

                                                        return Mono.error(new IllegalStateException(
                                                                "Échec synchronisation user local/externe pour " + email, e));
                                                    });
                                        });
                            }));

                    return userMono.flatMap(userTuple -> {
                        User user = userTuple.getT1();
                        boolean isNewUser = userTuple.getT2();
                        UUID userId = user.getId();

                        log.debug("🔍 Vérification si déjà membre - User: {}, Syndicat: {}",
                                userId, syndicatId);

                        return memberRepository.existsBySyndicatIdAndUserId(syndicatId, userId)
                                .doOnNext(exists -> log.debug("Résultat vérification membre: {}", exists))
                                .flatMap(isMember -> {
                                    if (isMember) {
                                        log.warn("⚠️ Utilisateur {} déjà membre du syndicat {}",
                                                userId, syndicatId);
                                        return Mono.error(new IllegalStateException("Déjà membre"));
                                    }

                                    log.info("➕ Insertion nouveau membre - User: {}, Syndicat: {}, Branche: {}",
                                            userId, syndicatId, branchId);

                                    SyndicatMember member = SyndicatMember.createLocal(
                                            syndicatId, branchId, userId, RoleTypeEnum.CLIENT);

                                    return memberRepository.insertMember(
                                                    syndicatId,
                                                    userId,
                                                    branchId,
                                                    true,
                                                    RoleTypeEnum.CLIENT.name()
                                            )
                                            .doOnSuccess(v -> log.info(
                                                    "✅ Membre inséré avec succès - User: {}, Syndicat: {}",
                                                    userId, syndicatId))
                                            .doOnError(e -> log.error(
                                                    "❌ ERREUR insertion membre - User: {}, Syndicat: {}: {}",
                                                    userId, syndicatId, e.getMessage(), e))
                                            .thenReturn(Tuples.of(user, member, syndicat.name(), isNewUser));
                                });
                    });
                })
                // Construction de la réponse avec notification APRÈS le commit
                .flatMap(resultTuple -> {
                    User user = resultTuple.getT1();
                    SyndicatMember member = resultTuple.getT2();
                    String syndicatName = resultTuple.getT3();
                    boolean isNewUser = resultTuple.getT4();

                    log.info("📧 Tentative envoi notification invitation - Email: {}", email);

                    // Créer l'objet de données pour la réponse
                    Map<String, Object> responseData = Map.of(
                            "user", user,
                            "member", member,
                            "isNewUser", isNewUser
                    );

                    return notificationPort.sendSyndicateInvitation(email, syndicatName, firstName)
                            .doOnSuccess(v -> log.info("✅ Notification envoyée avec succès à {}", email))
                            .doOnError(e -> log.error(
                                    "⚠️ ÉCHEC NOTIFICATION (membre ajouté mais email non envoyé) - " +
                                            "Email: {}, Syndicat: {}, Erreur: {}",
                                    email, syndicatName, e.getMessage()))
                            .onErrorResume(e -> {
                                log.warn("♻️ Erreur notification ignorée (membre déjà en DB)");
                                return Mono.empty();
                            })
                            .thenReturn(new AddUserResponse(
                                    "Membre ajouté avec succès" + (isNewUser ? " (nouvel utilisateur créé)" : ""),
                                    responseData
                            ));
                })
                .doOnSuccess(response -> log.info(
                        "🎉 Ajout membre terminé avec succès - Email: {}, Syndicat: {}, Data: {}",
                        email, syndicatId, response.data()))
                .doOnError(e -> log.error(
                        "❌ ÉCHEC GLOBAL ajout membre - Email: {}, Syndicat: {}, Erreur: {}",
                        email, syndicatId, e.getMessage()));
    }

    public Flux<MemberResponse> getSyndicateMembers(UUID syndicatId) {
        log.info("🔍 Récupération membres du syndicat: {}", syndicatId);

        return memberRepository.findBySyndicatId(syndicatId)
                .doOnNext(member -> log.debug("👤 Membre trouvé - UserID: {}, Role: {}, BranchID: {}",
                        member.userId(), member.role(), member.branchId()))
                .flatMap(member -> {
                    log.debug("🔎 Recherche infos utilisateur - ID: {}", member.userId());

                    return userGateway.findById(member.userId())
                            .doOnNext(extUser -> log.debug(
                                    "✅ User trouvé dans gateway externe: {} {} ({})",
                                    extUser.firstName(), extUser.lastName(), extUser.email()))
                            .doOnError(e -> log.warn(
                                    "⚠️ Erreur gateway externe pour user {}: {}, tentative fallback local",
                                    member.userId(), e.getMessage()))
                            .onErrorResume(e -> {
                                // ✅ Fallback aussi sur erreur, pas seulement sur empty
                                log.debug("♻️ Fallback vers repository local pour user {}", member.userId());
                                return Mono.empty();
                            })
                            .switchIfEmpty(Mono.defer(() -> {
                                log.debug("🔄 Gateway vide, recherche user {} dans repository local",
                                        member.userId());

                                return userRepository.findById(member.userId())
                                        .doOnNext(localUser -> log.debug(
                                                "✅ User trouvé en local: {} ({})",
                                                localUser.name(), localUser.email()))
                                        .map(localUser -> {
                                            // ✅ Parsing robuste avec limit
                                            String[] parts = localUser.name().split(" ", 2);
                                            String firstName = parts[0];
                                            String lastName = parts.length > 1 ? parts[1] : "";

                                            log.debug("📝 Parsing nom local: '{}' → Prénom: '{}', Nom: '{}'",
                                                    localUser.name(), firstName, lastName);

                                            return new ExternalUserInfo(
                                                    localUser.id(),
                                                    firstName,
                                                    lastName,
                                                    localUser.email(),
                                                    localUser.phoneNumber(),
                                                    List.of(),
                                                    List.of()
                                            );
                                        })
                                        .switchIfEmpty(Mono.defer(() -> {
                                            log.error(
                                                    "❌ DONNÉE INCOHÉRENTE - User {} (membre du syndicat {}) " +
                                                            "introuvable ni dans gateway ni dans repo local!",
                                                    member.userId(), syndicatId);
                                            return Mono.empty();
                                        }));
                            }))
                            .map(userInfo -> {
                                log.debug("✅ MemberResponse créé pour user: {} {} ({})",
                                        userInfo.firstName(), userInfo.lastName(), userInfo.email());

                                return new MemberResponse(
                                        userInfo.id(),
                                        userInfo.firstName(),
                                        userInfo.lastName(),
                                        userInfo.email(),
                                        null, // Avatar URL - TODO: récupérer depuis ProfileRepository si nécessaire
                                        member.role(),
                                        member.branchId(),
                                        member.joinedAt()
                                );
                            });
                })
                .doOnComplete(() -> log.info("✅ Récupération membres terminée pour syndicat {}",
                        syndicatId))
                .doOnError(e -> log.error(
                        "❌ Erreur récupération membres syndicat {}: {}",
                        syndicatId, e.getMessage(), e));
    }

    private Mono<UUID> getCurrentUserId() {
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> UUID.fromString(((Jwt) ctx.getAuthentication().getPrincipal()).getSubject()));
    }
}
```

*Lignes: 440*

---

### 📄 src\main\java\com\yowyob\ugate_service\application\service\syndicate\SyndicatManagementService.java

```java
package com.yowyob.ugate_service.application.service.syndicate;

import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.media.FileStoragePort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.UpdateSyndicateFullRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.PaginatedResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateDetailsResponse;
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

import java.time.Instant;
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
    private final BranchRepository branchRepository;

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
                    BusinessActor newActor = BusinessActor.createNew(creatorId, name, null, null);
                    return businessActorRepository.save(newActor);
                }));

        Organization organization = Organization.createNew(orgId, creatorId, name, null);

        Syndicat syndicat = new Syndicat(
                syndicatId, orgId, creatorId, false, name, description, domain, "STANDARD",
                null, urls.logoUrl(), urls.docUrl(), null, null, null, null, true
        );

        SyndicatMember adminMember = SyndicatMember.createGlobalAdmin(
                syndicatId,
                creatorId
        );

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


    @Transactional
    public Mono<SyndicateResponse> updateSyndicateFull(UUID syndicatId, UUID requesterId, UpdateSyndicateFullRequest request) {
        return syndicatRepository.findById(syndicatId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Syndicat introuvable")))
                .flatMap(syndicat -> {
                    // 1. Vérification de sécurité (Ownership)
                    if (!syndicat.creatorId().equals(requesterId)) {
                        return Mono.error(new IllegalStateException("Seul le créateur peut modifier ce syndicat."));
                    }

                    // 2. Gestion des fichiers en parallèle
                    Mono<String> logoMono = (request.logo() != null)
                            ? fileStoragePort.uploadFile(request.logo(), "syndicats/logos")
                            : Mono.justOrEmpty(syndicat.logoUrl());

                    Mono<String> charteMono = (request.charte() != null)
                            ? fileStoragePort.uploadFile(request.charte(), "syndicats/chartes")
                            : Mono.justOrEmpty(syndicat.charteUrl());

                    Mono<String> statusMono = (request.statusDoc() != null)
                            ? fileStoragePort.uploadFile(request.statusDoc(), "syndicats/statuts")
                            : Mono.justOrEmpty(syndicat.statusUrl());

                    return Mono.zip(logoMono.defaultIfEmpty(""), charteMono.defaultIfEmpty(""), statusMono.defaultIfEmpty(""))
                            .flatMap(tuple -> {
                                String newLogo = tuple.getT1().isEmpty() ? syndicat.logoUrl() : tuple.getT1();
                                String newCharte = tuple.getT2().isEmpty() ? syndicat.charteUrl() : tuple.getT2();
                                String newStatus = tuple.getT3().isEmpty() ? syndicat.statusUrl() : tuple.getT3();

                                // 3. Mise à jour de l'entité
                                // Utilisation du constructeur complet ou d'une méthode with... modifiée
                                Syndicat updatedSyndicat = new Syndicat(
                                        syndicat.id(),
                                        syndicat.organizationId(),
                                        syndicat.creatorId(),
                                        syndicat.isApproved(), // On ne change pas l'approbation ici
                                        request.name() != null ? request.name() : syndicat.name(),
                                        request.description() != null ? request.description() : syndicat.description(),
                                        request.domain() != null ? request.domain() : syndicat.domain(),
                                        syndicat.type(),
                                        newCharte,
                                        newLogo,
                                        newStatus,
                                        syndicat.membersListUrl(),
                                        syndicat.commitmentCertificateUrl(),
                                        syndicat.createdAt(),
                                        Instant.now(), // UpdatedAt
                                        syndicat.isActive()
                                );

                                return syndicatRepository.save(updatedSyndicat);
                            });
                })
                .map(syndicateMapper::toResponse)
                .doOnSuccess(s -> log.info("Syndicat {} mis à jour par {}", syndicatId, requesterId));
    }


    public Mono<SyndicateDetailsResponse> getSyndicateDetails(UUID syndicatId) {
        return syndicatRepository.findById(syndicatId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Syndicat introuvable")))
                .flatMap(syndicat -> {

                    // 1. Récupération de l'Organisation
                    Mono<Organization> orgMono = organizationRepository.findById(syndicat.organizationId())
                            // Fallback sur une organisation vide si introuvable (pour éviter de planter tout l'appel)
                            .defaultIfEmpty(new Organization(null, null, null, null, "N/A", null, false, false));

                    // 2. Récupération du Créateur
                    Mono<User> creatorMono = userRepository.findById(syndicat.creatorId())
                            .defaultIfEmpty(new User(syndicat.creatorId(), "Inconnu", "", ""));

                    // 3. Récupération des Stats (Branches)
                    Mono<Long> branchCountMono = branchRepository.countBySyndicatId(syndicatId)
                            .defaultIfEmpty(0L);

                    // 4. Récupération des Stats (Membres actifs)
                    Mono<Long> memberCountMono = syndicatMemberRepository.countBySyndicatIdAndIsActiveTrue(syndicatId)
                            .defaultIfEmpty(0L);

                    // Exécution en parallèle
                    return Mono.zip(orgMono, creatorMono, branchCountMono, memberCountMono)
                            .map(tuple -> {
                                Organization org = tuple.getT1();
                                User creator = tuple.getT2();
                                Long branches = tuple.getT3();
                                Long members = tuple.getT4();

                                return new SyndicateDetailsResponse(
                                        syndicat.id(),
                                        syndicat.name(),
                                        syndicat.description(),
                                        syndicat.domain(),
                                        syndicat.type(),
                                        syndicat.isApproved(),
                                        syndicat.isActive(),
                                        new SyndicateDetailsResponse.SyndicatDocuments(
                                                syndicat.logoUrl(),
                                                syndicat.charteUrl(),
                                                syndicat.statusUrl(),
                                                syndicat.membersListUrl(),
                                                syndicat.commitmentCertificateUrl()
                                        ),
                                        new SyndicateDetailsResponse.OrganizationInfo(
                                                "N/A",
                                                org.shortName(), // On map ce qu'on peut, adapte selon ton entité Organization réelle
                                                "N/A", // Keywords
                                                org.email(),
                                                org.shortName(),
                                                org.longName()
                                        ),
                                        new SyndicateDetailsResponse.CreatorInfo(
                                                creator.getId(),
                                                creator.name(),
                                                creator.email()
                                        ),
                                        new SyndicateDetailsResponse.SyndicatStats(
                                                members,
                                                branches
                                        ),
                                        syndicat.createdAt(),
                                        syndicat.updatedAt()
                                );
                            });
                });
    }
}
```

*Lignes: 353*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\enumeration\ReactionTypeEnum.java

```java
package com.yowyob.ugate_service.domain.enumeration;

public enum ReactionTypeEnum {
   LIKE, LOVE, HAHA, WOW, SAD, ANGRY
}

```

*Lignes: 6*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\exception\InsufficientStockException.java

```java
package com.yowyob.ugate_service.domain.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}

```

*Lignes: 8*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\exception\ProductNotFoundException.java

```java
package com.yowyob.ugate_service.domain.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    
}
```

*Lignes: 9*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\exception\ServiceNotFoundException.java

```java
package com.yowyob.ugate_service.domain.exception;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException(String message) {
        super(message);
    }
    
}

```

*Lignes: 9*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\model\CommentModel.java

```java
package com.yowyob.ugate_service.domain.model;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class CommentModel {
  UUID id;

  UUID authorId; // FK -> User

  UUID publicationId; // FK -> Publication

  UUID parentId; // FK -> Comment (Nullable : null si commentaire racine)

  UUID imageId; // FK -> Image (Nullable)

  String content;

  Instant createdAt;
}

```

*Lignes: 24*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\model\EventModel.java

```java
package com.yowyob.ugate_service.domain.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import lombok.Data;


@Data
public class EventModel {
  UUID id;
  UUID creatorId;
  UUID branchId;

  String title;
  String description;
  String location;

  LocalDate date;
  LocalTime startTime;
  LocalTime endTime;
  Instant createdAt;
  Instant updatedAt;
}

```

*Lignes: 27*

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

### 📄 src\main\java\com\yowyob\ugate_service\domain\model\ImageModel.java

```java
package com.yowyob.ugate_service.domain.model;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class ImageModel {
  UUID id;

  String url;

  String altText;

  Instant uploadedAt;
}

```

*Lignes: 18*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\model\MediaInfo.java

```java
package com.yowyob.ugate_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MediaInfo {
  private String url;
  private String type;
}
```

*Lignes: 13*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\model\Product.java

```java
package com.yowyob.ugate_service.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record Product(
    UUID id,
    UUID syndicatId,
    String name,
    String description,
    BigDecimal price,
    String sku,
    String category,
    Integer stock,
    String imageUrl,
    Boolean isActive
) {}
```

*Lignes: 17*

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

### 📄 src\main\java\com\yowyob\ugate_service\domain\model\PublicationVoteModel.java

```java
package com.yowyob.ugate_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicationVoteModel {
    private UUID id;
    private UUID branchId;
    private String title;
    private String description;
    private Instant closingAt;
    private String type;
}

```

*Lignes: 21*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\model\ReactionModel.java

```java
package com.yowyob.ugate_service.domain.model;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class ReactionModel {
  UUID id;

  Long publicationId; // FK -> Publication

  UUID userId; // FK -> User

  String type; // "LIKE", "LOVE", etc.

  Instant reactedAt;
}

```

*Lignes: 20*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\model\SyndicatService.java

```java
package com.yowyob.ugate_service.domain.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record SyndicatService(
    UUID id,
    UUID syndicatId,
    String title,
    String description,
    BigDecimal price,
    // String duration, // ex: "1h", "par session"
    List<String> features,
    Boolean isActive
) {}
```

*Lignes: 16*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\model\UserEventModel.java

```java
package com.yowyob.ugate_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEventModel {
    private UUID id;
    private UUID userId;
    private UUID eventId;
    private Instant timestamp;
}

```

*Lignes: 19*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\model\UserModel.java

```java
package com.yowyob.ugate_service.domain.model;

import lombok.Data;

@Data
public class UserModel {
  private String id;
  private String email;
  private String name;
  private String phoneNumber;
}

```

*Lignes: 12*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\model\VoteModel.java

```java
package com.yowyob.ugate_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteModel {
    private UUID id;
    private UUID userId;
    private UUID publicationVoteId;
    private String label;
}

```

*Lignes: 18*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\in\content\CastVoteUseCase.java

```java
package com.yowyob.ugate_service.domain.ports.in.content;

import reactor.core.publisher.Mono;
import java.util.UUID;

public interface CastVoteUseCase {
    Mono<Void> castVote(UUID userId, UUID publicationVoteId, String choiceLabel);
}

```

*Lignes: 9*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\in\content\CreateEventUseCase.java

```java
package com.yowyob.ugate_service.domain.ports.in.content;

import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public interface CreateEventUseCase {
    Mono<Void> createEvent(UUID creatorId, UUID branchId, String title, String description, LocalDate eventDate,
                                 String location, LocalTime startTime, LocalTime endTime, String[] imagesUrls, String[] videoUrls,
                                 String[] filesUrls);
}

```

*Lignes: 14*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\in\content\CreatePublicationVoteUseCase.java

```java
package com.yowyob.ugate_service.domain.ports.in.content;

import com.yowyob.ugate_service.domain.model.PublicationVoteModel;
import reactor.core.publisher.Mono;

public interface CreatePublicationVoteUseCase {
    Mono<Void> createPublicationVote(PublicationVoteModel publicationVoteModel);
}

```

*Lignes: 9*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\in\content\GetEventParticipantsUseCase.java

```java
package com.yowyob.ugate_service.domain.ports.in.content;

import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ParticipantDTO;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface GetEventParticipantsUseCase {
    Flux<ParticipantDTO> getParticipants(UUID eventId);
}

```

*Lignes: 11*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\in\content\GetEventsByBranchUseCase.java

```java
package com.yowyob.ugate_service.domain.ports.in.content;

import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.EventResponseDTO;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface GetEventsByBranchUseCase {
    Flux<EventResponseDTO> getEventsByBranch(UUID branchId);
}

```

*Lignes: 11*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\in\content\GetFeedUseCase.java

```java
package com.yowyob.ugate_service.domain.ports.in.content;

import java.util.Map;
import reactor.core.publisher.Flux;

public interface GetFeedUseCase {
    Flux<Map<String, Object>> getFeed(int page, int size);
}

```

*Lignes: 9*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\in\content\GetPublicationVoteResultsUseCase.java

```java
package com.yowyob.ugate_service.domain.ports.in.content;

import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.PublicationVoteWithResultsDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface GetPublicationVoteResultsUseCase {
    Mono<PublicationVoteWithResultsDTO> getPublicationVoteResults(UUID publicationVoteId, UUID userId);
}

```

*Lignes: 11*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\in\content\JoinEventUseCase.java

```java
package com.yowyob.ugate_service.domain.ports.in.content;

import reactor.core.publisher.Mono;
import java.util.UUID;

public interface JoinEventUseCase {
    Mono<Void> joinEvent(UUID userId, UUID eventId);
}

```

*Lignes: 9*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\in\content\LeaveEventUseCase.java

```java
package com.yowyob.ugate_service.domain.ports.in.content;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface LeaveEventUseCase {
    Mono<Void> leaveEvent(UUID userId, UUID eventId);
}

```

*Lignes: 10*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\in\marketplace\ManageProductUseCase.java

```java
package com.yowyob.ugate_service.domain.ports.in.marketplace;
import java.util.UUID;

import com.yowyob.ugate_service.domain.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ManageProductUseCase {
    Mono<Product> createProduct(Product product);
    Mono<Product> updateProduct(Product product);
    Mono<Product> updateStock(UUID id, Integer quantity);
    Mono<Void> deleteProduct(UUID id);
    Flux<Product> getSyndicatProducts(UUID syndicatId);
    Mono<Product> getProductDetails(UUID id);
}
```

*Lignes: 17*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\in\marketplace\ManageServiceUseCase.java

```java
package com.yowyob.ugate_service.domain.ports.in.marketplace;


import java.util.UUID;
import com.yowyob.ugate_service.domain.model.SyndicatService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ManageServiceUseCase {
    Mono<SyndicatService> createService(SyndicatService service);
    Mono<SyndicatService> updateService(SyndicatService service);
    Mono<Void> deleteService(UUID id);
    // Flux<ServiceOffering> getSyndicatServices(UUID syndicatId);
    Flux<SyndicatService> getAllActiveServices();
    Mono<SyndicatService> getServiceDetails(UUID id);
}
```

*Lignes: 16*

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

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\marketplace\ProductRepositoryPort.java

```java
package com.yowyob.ugate_service.domain.ports.out.marketplace;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.UUID;

import com.yowyob.ugate_service.domain.model.Product;

public interface ProductRepositoryPort {
    Mono<Product> saveProduct(Product product);
    Mono<Product> findById(UUID id);
    Mono<Void> deleteById( UUID id);
    Mono<Product> updateProduct(Product product);
    Flux<Product> findBySyndicatId(UUID syndicatId);
    
} 
```

*Lignes: 17*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\marketplace\ServiceOfferingRepositoryPort.java

```java
package com.yowyob.ugate_service.domain.ports.out.marketplace;

import com.yowyob.ugate_service.domain.model.SyndicatService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface ServiceOfferingRepositoryPort {
    Mono<SyndicatService> save(SyndicatService serviceOffering);
    Mono<SyndicatService> findServiceById(UUID id);
    Mono<SyndicatService> updateService(SyndicatService serviceOffering);
    Mono<Void> deleteService(UUID id);
    Flux<SyndicatService> findAllActiveServices();


}

```

*Lignes: 18*

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

import java.util.List;

import reactor.core.publisher.Mono;

public interface NotificationPort {
    Mono<Void> sendSyndicateInvitation(String email, String syndicateName, String firstName);

    Mono<Void> sendNewEventAlert(List<String> emails, String eventName);

    Mono<Void> sendPublicationCommentAlert(String authorEmail, String publicationTitle, String firstName);

    Mono<Void> sendPublicationReactAlert(String authorEmail, String publicationTitle, String firstName);

    Mono<Void> sendAdminAlertWhenNewPublication(List<String> emails, String publicationTitle, String authorName);

    Mono<Void> sendAdminAlertAcceptEvent(List<String> emails, String eventName, String organizerName);
}
```

*Lignes: 19*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\syndicate\BranchPersistencePort.java

```java
package com.yowyob.ugate_service.domain.ports.out.syndicate;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Branch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BranchPersistencePort {
    Mono<Branch> save(Branch branch);
    Mono<Branch> findById(UUID id);
    Flux<Branch> findBySyndicatId(UUID syndicatId);
}
```

*Lignes: 13*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\syndicate\CommentPersistencePort.java

```java
package com.yowyob.ugate_service.domain.ports.out.syndicate;

import java.util.UUID;

import com.yowyob.ugate_service.domain.model.CommentModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentPersistencePort {
  Mono<Void> saveComment(CommentModel commentModel);

  Flux<CommentModel> findCommentsByPublicationId(UUID publicationId);
}

```

*Lignes: 15*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\syndicate\dto\CommentResponseDto.java

```java
package com.yowyob.ugate_service.domain.ports.out.syndicate.dto;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class CommentResponseDto {
  UUID id;

  UUID authorId;

  String authorFullName;

  UUID publicationId;

  UUID parentId;

  String imageUrl;

  String content;

  Instant createdAt;
}

```

*Lignes: 26*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\syndicate\dto\PublicationResponseDTO.java

```java
package com.yowyob.ugate_service.domain.ports.out.syndicate.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.yowyob.ugate_service.domain.model.MediaInfo;

import lombok.Data;
import reactor.core.publisher.Flux;

@Data
public class PublicationResponseDTO {
  UUID id;

  UUID branchId; // FK -> Branch

  String AuthorFullName;

  String content;

  Long nLikes; // Compteur de likes

  Long nComments; // Compteur de comments

  Instant createdAt;

  List<MediaInfo> FileUrlAndType;

}

```

*Lignes: 31*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\syndicate\EventPersistencePort.java

```java
package com.yowyob.ugate_service.domain.ports.out.syndicate;

import com.yowyob.ugate_service.domain.model.EventModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

public interface EventPersistencePort {
  Mono<EventModel> save(EventModel event);

  Flux<EventModel> findByBranchId(UUID branchId);

  Flux<EventModel> findAllPaginated(int page, int size);
}

```

*Lignes: 18*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\syndicate\MediaPersistencePort.java

```java
package com.yowyob.ugate_service.domain.ports.out.syndicate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import com.yowyob.ugate_service.domain.model.ImageModel;
import com.yowyob.ugate_service.domain.model.MediaInfo;

public interface MediaPersistencePort {
  Mono<Void> saveImageMedia(String imageUrl, String altText, UUID publicationId);

  Mono<Void> saveVideoMedia(String videoUrl, String title, UUID publicationId);

  Mono<Void> saveAudioMedia(String audioUrl, String title, UUID publicationId);

  Flux<MediaInfo> getMediaByPublicationId(UUID publicationId);

  Mono<ImageModel> saveImage(String audioUrl, String altext);

  Mono<ImageModel> getImageById(UUID imageId);

  Mono<Void> saveEventMedia(String imageUrl, String altText, UUID eventId);

  Flux<ImageModel> getImagesByEventId(UUID eventId);
}

```

*Lignes: 28*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\syndicate\PublicationPersistencePort.java

```java
package com.yowyob.ugate_service.domain.ports.out.syndicate;

import java.util.Map;
import java.util.UUID;

import com.yowyob.ugate_service.domain.model.PublicationModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PublicationPersistencePort {

  Mono<PublicationModel> save(PublicationModel publicationModel);

  Mono<PublicationModel> findById(UUID id); // Added method

  Flux<PublicationModel> findByBranchId(UUID branchId);

  Mono<Void> incrementLikes(UUID publicationId);

  Flux<PublicationModel> findAllPaginated(int page, int size);
}

```

*Lignes: 23*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\syndicate\PublicationVotePersistencePort.java

```java
package com.yowyob.ugate_service.domain.ports.out.syndicate;

import com.yowyob.ugate_service.domain.model.PublicationVoteModel;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PublicationVotePersistencePort {
    Mono<PublicationVoteModel> save(PublicationVoteModel model);
    Mono<PublicationVoteModel> findById(UUID id);
}

```

*Lignes: 12*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\syndicate\ReactionPersistencePort.java

```java
package com.yowyob.ugate_service.domain.ports.out.syndicate;

import java.util.UUID;

import com.yowyob.ugate_service.domain.enumeration.ReactionTypeEnum;

import reactor.core.publisher.Mono;

public interface ReactionPersistencePort {
  Mono<Void> saveReaction(UUID publicationId, ReactionTypeEnum reactionType, UUID userId);
}

```

*Lignes: 12*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\syndicate\SyndicatePersistencePort.java

```java
package com.yowyob.ugate_service.domain.ports.out.syndicate;

public class SyndicatePersistencePort {
}

```

*Lignes: 5*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\syndicate\UserEventPersistencePort.java

```java
package com.yowyob.ugate_service.domain.ports.out.syndicate;

import com.yowyob.ugate_service.domain.model.UserEventModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface UserEventPersistencePort {
    Mono<Void> save(UserEventModel userEventModel);
    Mono<Long> countByEventId(UUID eventId);
    Flux<UserEventModel> findByEventId(UUID eventId);
    Mono<Void> delete(UUID userId, UUID eventId);
}

```

*Lignes: 14*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\syndicate\UserPersistencePort.java

```java
package com.yowyob.ugate_service.domain.ports.out.syndicate;

import java.util.UUID;

import com.yowyob.ugate_service.domain.model.UserModel;

import reactor.core.publisher.Flux;

public interface UserPersistencePort {
  public Flux<UserModel> findUsersByBranchId(UUID branchId);
}

```

*Lignes: 12*

---

### 📄 src\main\java\com\yowyob\ugate_service\domain\ports\out\syndicate\VotePersistencePort.java

```java
package com.yowyob.ugate_service.domain.ports.out.syndicate;

import com.yowyob.ugate_service.domain.model.VoteModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface VotePersistencePort {
    Mono<Void> save(VoteModel model);
    Flux<VoteModel> findByPublicationVoteId(UUID publicationVoteId);
}

```

*Lignes: 13*

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

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\content\CommentController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.content;

import com.yowyob.ugate_service.application.service.content.CommentService;
import com.yowyob.ugate_service.domain.ports.out.syndicate.dto.CommentResponseDto;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreateCommentRequest;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.media.MediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

import org.springframework.security.oauth2.jwt.Jwt;

@RestController
@RequestMapping("/publications/{publicationId}/comments")
@AllArgsConstructor
@Tag(name = "Comments", description = "API for managing comments on publications")
public class CommentController {

        private final CommentService commentService;
        private final MediaService mediaService;

        @Operation(summary = "Create a new comment", description = "Add a comment to a specific publication.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Comment created successfully"),
                        @ApiResponse(responseCode = "400", description = "Invalid input provided"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized access")
        })
        @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public Mono<ResponseEntity<Void>> createComment(
                        @Parameter(description = "UUID of the publication") @PathVariable UUID publicationId,
                        @Parameter(description = "Content of the comment") @RequestPart(name = "content") Mono<String> content,
                        @Parameter(description = "Image file for the comment") @RequestPart(name = "image", required = false) Mono<FilePart> image,
                        @Parameter(description = "Parent comment ID") @RequestPart(name = "parentId", required = false) Mono<String> parentId) {

                Mono<String> imageUrlMono = mediaService.uploadImage(image == null ? Flux.empty() : image.flux())
                                .map(list -> list.isEmpty() ? "" : list.get(0));

                return ReactiveSecurityContextHolder.getContext()
                                .map(SecurityContext::getAuthentication)
                                .map(authentication -> {
                                        Jwt jwt = (Jwt) authentication.getPrincipal();
                                        return UUID.fromString(jwt.getSubject());
                                })
                                .flatMap(authorId -> Mono.zip(content, imageUrlMono, parentId.defaultIfEmpty(""))
                                                .flatMap(tuple -> {
                                                        String contentVal = tuple.getT1();
                                                        String imageUrlVal = tuple.getT2().isEmpty() ? null
                                                                        : tuple.getT2();
                                                        String parentIdStr = tuple.getT3();
                                                        UUID parentUuid = (parentIdStr == null || parentIdStr.isEmpty())
                                                                        ? null
                                                                        : UUID.fromString(parentIdStr);

                                                        return commentService.createComment(
                                                                        authorId,
                                                                        publicationId,
                                                                        parentUuid,
                                                                        imageUrlVal,
                                                                        contentVal);
                                                }))
                                .map(v -> ResponseEntity.ok().build());
        }

        @Operation(summary = "Get comments for a publication", description = "Retrieve a list of comments associated with a publication.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successfully retrieved comments", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentResponseDto.class))),
                        @ApiResponse(responseCode = "404", description = "Publication not found")
        })
        @GetMapping
        public Flux<CommentResponseDto> getComments(
                        @Parameter(description = "UUID of the publication") @PathVariable UUID publicationId) {
                return commentService.getCommentsByPublicationId(publicationId);
        }
}

```

*Lignes: 91*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\content\EventController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.content;

import com.yowyob.ugate_service.domain.ports.in.content.*;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.EventResponseDTO;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ParticipantDTO;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.codec.multipart.FilePart; // Added import

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Validated
@Tag(name = "Events", description = "API for managing events")
public class EventController {

        private final CreateEventUseCase createEventUseCase;
        private final JoinEventUseCase joinEventUseCase;
        private final GetEventsByBranchUseCase getEventsByBranchUseCase;
        private final GetEventParticipantsUseCase getEventParticipantsUseCase;
        private final LeaveEventUseCase leaveEventUseCase;
        private final MediaService mediaService;

        @Operation(summary = "Create a new event", description = "Creates a new event with optional image, video, and file attachments.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Event created successfully", content = @Content(schema = @Schema(hidden = true))),
                        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(hidden = true)))
        })
        @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public Mono<ResponseEntity<Void>> createEvent(
                        @Parameter(description = "Creator ID") @RequestPart("creatorId") Mono<String> creatorId,
                        @Parameter(description = "Branch ID") @RequestPart("branchId") Mono<String> branchId,
                        @Parameter(description = "Title") @RequestPart("title") Mono<String> title,
                        @Parameter(description = "Description") @RequestPart("description") Mono<String> description,
                        @Parameter(description = "Event Date (YYYY-MM-DD)") @RequestPart("eventDate") Mono<String> eventDate,
                        @Parameter(description = "Location") @RequestPart("location") Mono<String> location,
                        @Parameter(description = "Start Time (HH:MM)") @RequestPart("startTime") Mono<String> startTime,
                        @Parameter(description = "End Time (HH:MM)") @RequestPart("endTime") Mono<String> endTime,
                        @Parameter(description = "Optional image files to be attached") @RequestPart(name = "images", required = false) Flux<FilePart> images,
                        @Parameter(description = "Optional video files to be attached") @RequestPart(name = "videos", required = false) Flux<FilePart> videos,
                        @Parameter(description = "Optional general files to be attached") @RequestPart(name = "files", required = false) Flux<FilePart> files) {

                Mono<List<String>> imagesUrlsMono = mediaService.uploadImage(images == null ? Flux.empty() : images);
                Mono<List<String>> videosUrlsMono = mediaService.uploadVideo(videos == null ? Flux.empty() : videos);
                Mono<List<String>> filesUrlsMono = mediaService.uploadFiles(files == null ? Flux.empty() : files);

                return Mono.zip(creatorId, branchId, title, description, eventDate, location, startTime, endTime)
                                .zipWith(Mono.zip(imagesUrlsMono, videosUrlsMono, filesUrlsMono))
                                .flatMap(tuple -> {
                                        var fields = tuple.getT1();
                                        var media = tuple.getT2();

                                        UUID creatorIdVal = UUID.fromString(fields.getT1());
                                        UUID branchIdVal = UUID.fromString(fields.getT2());
                                        String titleVal = fields.getT3();
                                        String descriptionVal = fields.getT4();
                                        LocalDate eventDateVal = LocalDate.parse(fields.getT5());
                                        String locationVal = fields.getT6();
                                        LocalTime startTimeVal = LocalTime.parse(fields.getT7());
                                        LocalTime endTimeVal = LocalTime.parse(fields.getT8());

                                        List<String> imageUrls = media.getT1();
                                        List<String> videoUrls = media.getT2();
                                        List<String> fileUrls = media.getT3();

                                        return createEventUseCase.createEvent(
                                                        creatorIdVal,
                                                        branchIdVal,
                                                        titleVal,
                                                        descriptionVal,
                                                        eventDateVal,
                                                        locationVal,
                                                        startTimeVal,
                                                        endTimeVal,
                                                        imageUrls.toArray(new String[0]),
                                                        videoUrls.toArray(new String[0]),
                                                        fileUrls.toArray(new String[0]));
                                })
                                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
        }

        @Operation(summary = "Join an event", description = "Allows an authenticated user to join an existing event.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successfully joined the event"),
                        @ApiResponse(responseCode = "404", description = "Event not found")
        })
        @PostMapping("/{eventId}/join")
        public Mono<ResponseEntity<Void>> joinEvent(
                        @AuthenticationPrincipal Jwt jwt,
                        @Parameter(description = "ID of the event to join") @PathVariable UUID eventId) {

                UUID userId = UUID.fromString(jwt.getSubject());
                return joinEventUseCase.joinEvent(userId, eventId)
                                .then(Mono.just(ResponseEntity.ok().build()));
        }

        @Operation(summary = "Get events by branch", description = "Retrieves a list of events for a specific branch, including participant counts.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Events retrieved successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EventResponseDTO.class))),
                        @ApiResponse(responseCode = "404", description = "Branch not found", content = @Content(schema = @Schema(hidden = true)))
        })
        @GetMapping("/branch/{branchId}")
        public Flux<EventResponseDTO> getEventsByBranch(
                        @Parameter(description = "ID of the branch to retrieve events from") @PathVariable UUID branchId) {
                return getEventsByBranchUseCase.getEventsByBranch(branchId);
        }

        @Operation(summary = "Get participants for an event", description = "Retrieves a list of participants for a specific event.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Participants retrieved successfully"),
                        @ApiResponse(responseCode = "404", description = "Event not found")
        })
        @GetMapping("/{eventId}/participants")
        public Flux<ParticipantDTO> getParticipants(
                        @Parameter(description = "ID of the event to retrieve participants from") @PathVariable UUID eventId) {
                return getEventParticipantsUseCase.getParticipants(eventId);
        }

        @Operation(summary = "Leave an event", description = "Allows an authenticated user to leave an event they have joined.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Successfully left the event"),
                        @ApiResponse(responseCode = "404", description = "Event or participation not found")
        })
        @DeleteMapping("/{eventId}/leave")
        public Mono<ResponseEntity<Void>> leaveEvent(
                        @AuthenticationPrincipal Jwt jwt,
                        @Parameter(description = "ID of the event to leave") @PathVariable UUID eventId) {

                UUID userId = UUID.fromString(jwt.getSubject());
                return leaveEventUseCase.leaveEvent(userId, eventId)
                                .then(Mono.just(ResponseEntity.noContent().build()));
        }
}

```

*Lignes: 156*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\content\FeedController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.content;

import com.yowyob.ugate_service.domain.ports.out.syndicate.dto.PublicationResponseDTO;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.EventResponseDTO;
import com.yowyob.ugate_service.domain.ports.in.content.GetFeedUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/feed")
@Tag(name = "Feed", description = "API for retrieving a combined feed of publications and events")
public class FeedController {

    private final GetFeedUseCase getFeedUseCase;

    @Operation(summary = "Get a paginated feed of publications and events",
               description = "Retrieves a combined and sorted feed of recent publications and events. Each item in the feed is a map containing a 'type' (publication or event) and 'data' (either a PublicationResponseDTO or an EventResponseDTO).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved feed items",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(
                                                description = "A list of feed items, where each item is a map with 'type' and 'data'. 'data' can be PublicationResponseDTO or EventResponseDTO.",
                                                example = """
                                                [
                                                  {
                                                    "type": "publication",
                                                    "data": {
                                                      "id": "123e4567-e89b-12d3-a456-426614174000",
                                                      "branchId": "123e4567-e89b-12d3-a456-426614174001",
                                                      "authorFullName": "John Doe",
                                                      "content": "My latest thoughts.",
                                                      "nLikes": 10,
                                                      "nComments": 2,
                                                      "createdAt": "2023-01-01T12:00:00Z",
                                                      "fileUrlAndType": [
                                                        {"url": "http://example.com/pub_img.jpg", "type": "IMAGE"}
                                                      ]
                                                    }
                                                  },
                                                  {
                                                    "type": "event",
                                                    "data": {
                                                      "id": "123e4567-e89b-12d3-a456-426614174002",
                                                      "creatorId": "123e4567-e89b-12d3-a456-426614174003",
                                                      "branchId": "123e4567-e89b-12d3-a456-426614174001",
                                                      "title": "Community Meetup",
                                                      "description": "A gathering for local developers.",
                                                      "location": "Community Hall",
                                                      "date": "2023-01-15",
                                                      "startTime": "18:00:00",
                                                      "endTime": "20:00:00",
                                                      "createdAt": "2023-01-05T10:00:00Z",
                                                      "updatedAt": "2023-01-05T10:00:00Z",
                                                      "participantCount": 50,
                                                      "imageUrls": ["http://example.com/event_banner.jpg"]
                                                    }
                                                  }
                                                ]
                                                """,
                                                oneOf = { PublicationResponseDTO.class, EventResponseDTO.class }
                                            )))
            ,
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters supplied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(produces = "application/json")
    public Flux<Map<String, Object>> getFeed(
            @Parameter(description = "Page number (0-indexed)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page", example = "20")
            @RequestParam(defaultValue = "20") int size
    ) {
        return getFeedUseCase.getFeed(page, size);
    }
}

```

*Lignes: 87*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\content\PublicationController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.content;

import com.yowyob.ugate_service.application.service.content.PublicationService;
import com.yowyob.ugate_service.domain.ports.out.syndicate.dto.PublicationResponseDTO;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.media.MediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema; // Import important
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/publications")
@AllArgsConstructor
@Validated
@Tag(name = "Publications", description = "API for managing publications")
public class PublicationController {

    private final PublicationService publicationService;
    private final MediaService mediaService;

    @Operation(summary = "Create a new publication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Publication created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<Void>> createPublication(
            @Parameter(description = "Content") @RequestPart("content") Mono<String> content,
            @Parameter(description = "Author ID") @RequestPart("authorId") Mono<String> authorId,
            @Parameter(description = "Branch ID") @RequestPart("branchId") Mono<String> branchId,

            // --- CORRECTION SWAGGER ICI ---
            // On dit explicitement à Swagger : "C'est un tableau de fichiers binaires", même si le code Java utilise 'Part'
            @Parameter(
                    description = "Optional image files",
                    array = @ArraySchema(schema = @Schema(type = "string", format = "binary"))
            )
            @RequestPart(name = "images", required = false) Flux<Part> images,

            @Parameter(
                    description = "Optional video files",
                    array = @ArraySchema(schema = @Schema(type = "string", format = "binary"))
            )
            @RequestPart(name = "videos", required = false) Flux<Part> videos,

            @Parameter(
                    description = "Optional general files",
                    array = @ArraySchema(schema = @Schema(type = "string", format = "binary"))
            )
            @RequestPart(name = "files", required = false) Flux<Part> files) {

        // Conversion et nettoyage (Le code backend reste robuste)
        Flux<FilePart> imageFiles = convertParts(images);
        Flux<FilePart> videoFiles = convertParts(videos);
        Flux<FilePart> genericFiles = convertParts(files);

        Mono<List<String>> imagesUrlsMono = mediaService.uploadImage(imageFiles);
        Mono<List<String>> videosUrlsMono = mediaService.uploadVideo(videoFiles);
        Mono<List<String>> filesUrlsMono = mediaService.uploadFiles(genericFiles);

        return Mono.zip(content, authorId, branchId)
                .zipWith(Mono.zip(imagesUrlsMono, videosUrlsMono, filesUrlsMono))
                .flatMap(tuple -> {
                    var textData = tuple.getT1();
                    var mediaData = tuple.getT2();

                    String contentValue = textData.getT1();
                    UUID authorIdValue = UUID.fromString(textData.getT2());
                    UUID branchIdValue = UUID.fromString(textData.getT3());

                    List<String> imageUrls = mediaData.getT1();
                    List<String> videoUrls = mediaData.getT2();
                    List<String> fileUrls = mediaData.getT3();

                    return publicationService.createPublication(
                            authorIdValue,
                            branchIdValue,
                            contentValue,
                            imageUrls.toArray(new String[0]),
                            videoUrls.toArray(new String[0]),
                            fileUrls.toArray(new String[0]));
                })
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
    }

    private Flux<FilePart> convertParts(Flux<Part> parts) {
        if (parts == null) {
            return Flux.empty();
        }
        return parts
                .filter(part -> part instanceof FilePart)
                .cast(FilePart.class);
    }

    @GetMapping("/branch/{branchId}")
    public Flux<PublicationResponseDTO> getPublicationsByBranch(@PathVariable UUID branchId) {
        return publicationService.getSyndicatPublication(branchId);
    }
}
```

*Lignes: 116*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\content\PublicationVoteController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.content;

import com.yowyob.ugate_service.domain.model.PublicationVoteModel;
import com.yowyob.ugate_service.domain.ports.in.content.CastVoteUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.CreatePublicationVoteUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.GetPublicationVoteResultsUseCase;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CastVoteRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreatePublicationVoteRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.PublicationVoteWithResultsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/publication-votes")
@RequiredArgsConstructor
@Tag(name = "Publication Votes", description = "API for managing publication votes (polls)")
public class PublicationVoteController {

        private final CreatePublicationVoteUseCase createPublicationVoteUseCase;
        private final CastVoteUseCase castVoteUseCase;
        private final GetPublicationVoteResultsUseCase getPublicationVoteResultsUseCase;

        @Operation(summary = "Create a new publication vote (poll)", description = "Creates a new poll that can be associated with a publication.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Poll created successfully"),
                        @ApiResponse(responseCode = "400", description = "Invalid input")
        })
        @PostMapping
        public Mono<ResponseEntity<Void>> createPublicationVote(
                        @Valid @RequestBody CreatePublicationVoteRequest request) {
                PublicationVoteModel model = new PublicationVoteModel();
                model.setTitle(request.getTitle());
                model.setDescription(request.getDescription());
                model.setClosingAt(request.getClosingAt());
                model.setType(request.getType());
                model.setBranchId(request.getBranchId());

                return createPublicationVoteUseCase.createPublicationVote(model)
                                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
        }

        @Operation(summary = "Cast a vote on a poll", description = "Allows an authenticated user to cast a vote on a specific poll.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Vote cast successfully"),
                        @ApiResponse(responseCode = "400", description = "Invalid input"),
                        @ApiResponse(responseCode = "404", description = "Poll not found"),
                        @ApiResponse(responseCode = "409", description = "Poll is closed")
        })
        @PostMapping("/{publicationVoteId}/cast")
        public Mono<ResponseEntity<Void>> castVote(
                        @AuthenticationPrincipal Jwt jwt,
                        @PathVariable UUID publicationVoteId,
                        @Valid @RequestBody CastVoteRequest request) {

                UUID userId = UUID.fromString(jwt.getSubject());
                return castVoteUseCase.castVote(userId, publicationVoteId, request.getChoiceLabel())
                                .then(Mono.just(ResponseEntity.ok().build()));
        }

        @Operation(summary = "Get results for a poll", description = "Retrieves the results for a specific poll, including total votes, vote distribution, and whether the current user has voted.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Poll results retrieved successfully"),
                        @ApiResponse(responseCode = "404", description = "Poll not found")
        })
        @GetMapping("/{publicationVoteId}/results")
        public Mono<PublicationVoteWithResultsDTO> getPublicationVoteResults(
                        @AuthenticationPrincipal Jwt jwt,
                        @PathVariable UUID publicationVoteId) {

                UUID userId = UUID.fromString(jwt.getSubject());
                return getPublicationVoteResultsUseCase.getPublicationVoteResults(publicationVoteId, userId);
        }
}
```

*Lignes: 85*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\content\ReactionController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.content;

import com.yowyob.ugate_service.application.service.content.ReactionService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreateReactionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/publications/{publicationId}/reactions")
@RequiredArgsConstructor
@Tag(name = "Reactions", description = "API for managing reactions on publications")
public class ReactionController {

    private final ReactionService reactionService;

    @PostMapping
    @Operation(summary = "Add a reaction to a publication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reaction added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @ApiResponse(responseCode = "404", description = "Publication not found")
    })
    public Mono<ResponseEntity<Void>> addReaction(
            @Parameter(description = "ID of the publication to react to", required = true)
            @PathVariable UUID publicationId,
            @RequestBody CreateReactionRequest request) {
        return reactionService.addReactionToPublication(
                publicationId,
                request.getUserId(),
                request.getReactionType()
        ).then(Mono.just(ResponseEntity.ok().build()));
    }
}

```

*Lignes: 43*

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

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\CastVoteRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CastVoteRequest {
    @NotBlank(message = "Choice label cannot be blank")
    private String choiceLabel;
}

```

*Lignes: 11*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\CreateBranchRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.http.codec.multipart.FilePart;

@Data
public class CreateBranchRequest {

        @Schema(description = "Nom de la branche", example = "Agence Centrale")
        @NotBlank(message = "Le nom est obligatoire")
        private String name;

        @Schema(description = "Ville ou quartier", example = "Douala")
        @NotBlank(message = "La localisation est obligatoire")
        private String location;

        @Schema(description = "Numéro de téléphone ou email", example = "+237 699999999")
        private String contact;


        @Schema(description = "Image de la bannière (JPG/PNG)", type = "string", format = "binary")
        private FilePart banner;
}
```

*Lignes: 25*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\CreateCommentRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateCommentRequest {
    private String content;
    private String imageUrl;
    private UUID parentId;
}

```

*Lignes: 13*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\CreatePublicationVoteRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Schema(name = "CreatePublicationVoteRequest", description = "Request DTO for creating a new publication vote (poll)")
public class CreatePublicationVoteRequest {

    @NotBlank(message = "Title cannot be blank")
    @Schema(description = "The title of the poll.", example = "What is the best programming language?")
    private String title;

    @Schema(description = "A short description of the poll.", example = "Let us know your favorite language.")
    private String description;

    @Future(message = "Closing date must be in the future")
    @Schema(description = "The date and time when the poll will close.")
    private Instant closingAt;

    @NotBlank(message = "Type cannot be blank")
    @Schema(description = "The type of poll.", example = "SINGLE_CHOICE", allowableValues = { "SINGLE_CHOICE",
            "MULTIPLE_CHOICE" })
    private String type;

    @NotNull(message = "Branch ID cannot be null")
    @Schema(description = "The branch ID associated with the poll.", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID branchId;
}

```

*Lignes: 36*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\CreateReactionRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import java.util.UUID;

import com.yowyob.ugate_service.domain.enumeration.ReactionTypeEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Request object for creating a reaction")
public class CreateReactionRequest {

    @Schema(description = "The UUID of the user creating the reaction", example = "a1b2c3d4-e5f6-7890-1234-567890abcdef")
    private UUID userId;

    @Schema(description = "The type of the reaction", example = "LIKE")
    private ReactionTypeEnum reactionType;
}

```

*Lignes: 20*

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

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\ProductRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(
    UUID syndicatId,
    String name,
    String description,
    BigDecimal price,
    String sku,
    String category,
    Integer stock,
    Boolean isActive
) {

}

```

*Lignes: 21*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\ServiceOfferingRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ServiceOfferingRequest(
   
    UUID syndicatId,
    String title,
    String description,
    BigDecimal price,
    // String duration, // ex: "1h", "par session"
    List<String> features,
    Boolean isActive
) {

}

```

*Lignes: 19*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\UpdateBranchRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;


public record UpdateBranchRequest(
        String name,
        String location,
        String contact
) {}
```

*Lignes: 8*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\UpdateFullProfileRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;



import java.time.Instant;
import org.springframework.http.codec.multipart.FilePart;

public record UpdateFullProfileRequest(
        String firstName,
        String lastName,
        String phoneNumber,
        String nationality,
        String gender,
        String language,
        Instant birthDate,
        FilePart profileImage // Optionnel : pour mettre à jour la photo
) {}
```

*Lignes: 17*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\UpdateMemberRoleRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.enumeration.RoleTypeEnum;

public record UpdateMemberRoleRequest(
        RoleTypeEnum newRole
) {}
```

*Lignes: 7*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\request\UpdateSyndicateFullRequest.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request;

import org.springframework.http.codec.multipart.FilePart;

public record UpdateSyndicateFullRequest(
        String name,
        String description,
        String domain,
        // Fichiers optionnels (s'ils sont null, on garde l'ancien)
        FilePart logo,
        FilePart charte,
        FilePart statusDoc
) {}
```

*Lignes: 13*

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

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\response\AddUserResponse.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

public record AddUserResponse(
        String message,
        Object data
) {
}

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

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\response\BranchResponse.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import java.time.Instant;
import java.util.UUID;

public record BranchResponse(
        UUID id,
        UUID syndicatId,
        String name,
        String location,
        String contact,
        String bannerUrl,
        Instant createdAt,
        Instant updatedAt
) {}
```

*Lignes: 15*

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

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\response\EventResponseDTO.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDTO {
    private UUID id;
    private UUID creatorId;
    private UUID branchId;
    private String title;
    private String description;
    private String location;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Instant createdAt;
    private Instant updatedAt;
    private long participantCount;
    private List<String> imageUrls;
}

```

*Lignes: 31*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\response\MemberResponse.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.enumeration.RoleTypeEnum;
import java.time.Instant;
import java.util.UUID;

public record MemberResponse(
        UUID userId,
        String firstName,
        String lastName,
        String email,
        String profileImageUrl,
        RoleTypeEnum role,
        UUID branchId,
        Instant joinedAt
) {}
```

*Lignes: 16*

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

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\response\ParticipantDTO.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDTO {
    private UUID userId;
    private String fullName;
}

```

*Lignes: 16*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\response\ProductResponse.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import com.yowyob.ugate_service.domain.model.MediaInfo;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
    UUID id,
    UUID syndicatId,
    String name,
    String description,
    BigDecimal price,
    String sku,
    String category,
    Integer stock,
    String image,
    Boolean isActive
) {

}

```

*Lignes: 22*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\response\PublicationVoteWithResultsDTO.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import com.yowyob.ugate_service.domain.model.PublicationVoteModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicationVoteWithResultsDTO {
    private UUID id;
    private String title;
    private String description;
    private Instant closingAt;
    private String type;
    private long totalVotes;
    private List<VoteResultDTO> results;
    private boolean hasVoted;

    public static PublicationVoteWithResultsDTO from(PublicationVoteModel model, long totalVotes, List<VoteResultDTO> results, boolean hasVoted) {
        return new PublicationVoteWithResultsDTO(
            model.getId(),
            model.getTitle(),
            model.getDescription(),
            model.getClosingAt(),
            model.getType(),
            totalVotes,
            results,
            hasVoted
        );
    }
}

```

*Lignes: 38*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\response\ServiceOfferingResponse.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ServiceOfferingResponse(
    UUID id,
    // UUID syndicatId,
    String title,
    String description,
    BigDecimal price,
    // String duration, // ex: "1h", "par session"
    List<String> features,
    Boolean isActive
) {

}

```

*Lignes: 19*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\response\StatsResponse.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

public record StatsResponse(
        long totalSyndicats,
        long activeSyndicats,
        long pendingSyndicats,
        long totalMembers,
        long activeMembers,
        double totalRevenue

) {}
```

*Lignes: 11*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\response\SyndicateDetailsResponse.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import java.time.Instant;
import java.util.UUID;

public record SyndicateDetailsResponse(
        // Infos de base
        UUID id,
        String name,
        String description,
        String domain,
        String type,
        Boolean isApproved,
        Boolean isActive,

        // Documents et URLs
        SyndicatDocuments documents,

        // Organisation liée
        OrganizationInfo organization,

        // Créateur
        CreatorInfo creator,

        // Statistiques en temps réel
        SyndicatStats stats,

        // Audit
        Instant createdAt,
        Instant updatedAt
) {

    public record SyndicatDocuments(
            String logoUrl,
            String charteUrl,
            String statusUrl,
            String membersListUrl,
            String commitmentCertificateUrl
    ) {}

    public record OrganizationInfo(
            String legalForm,
            String socialNetwork, // JSON ou String brut
            String keywords,
            String email,
            String shortName,
            String longName
    ) {}

    public record CreatorInfo(
            UUID id,
            String fullName,
            String email
    ) {}

    public record SyndicatStats(
            Long totalMembers,
            Long totalBranches
    ) {}
}
```

*Lignes: 60*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\response\SyndicateFullStatsResponse.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;



public record SyndicateFullStatsResponse(
        long totalMembers,
        long totalBranches,
        long pendingRequests,
        long activeServices,
        long totalPublications
) {}

```

*Lignes: 12*

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
        Instant createdAt,

        Boolean isActive
) {}
```

*Lignes: 18*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\dto\response\VoteResultDTO.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteResultDTO {
    private String choiceLabel;
    private long count;
}

```

*Lignes: 14*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\product\ProductController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.product;

import java.util.List;
import java.util.UUID;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.media.MediaService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;


import com.yowyob.ugate_service.domain.model.Product;
import com.yowyob.ugate_service.domain.ports.in.marketplace.ManageProductUseCase;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.ProductRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ProductResponse;
import com.yowyob.ugate_service.infrastructure.mappers.products.ProductMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Gestion des Produits", description = "API pour la gestion des produits dans le marketplace.")
public class ProductController {
    private final ManageProductUseCase productUseCase;
    private final ProductMapper mapper;
    private final MediaService mediaService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un nouveau produit", description = "Permet de créer un nouveau produit avec ses champs individuels.")
    public Mono<ProductResponse> createProduct(
            @Parameter(description = "ID du syndicat") @RequestPart("syndicatId") Mono<String> syndicatId,
            @Parameter(description = "Nom du produit") @RequestPart("name") Mono<String> name,
            @Parameter(description = "Description") @RequestPart("description") Mono<String> description,
            @Parameter(description = "Prix") @RequestPart("price") Mono<String> price,
            @Parameter(description = "SKU") @RequestPart("sku") Mono<String> sku,
            @Parameter(description = "Catégorie") @RequestPart("category") Mono<String> category,
            @Parameter(description = "Stock initial") @RequestPart("stock") Mono<String> stock,
            @Parameter(description = "Est actif") @RequestPart("isActive") Mono<String> isActive,
            @Parameter(description = "Fichier image") @RequestPart(name = "image", required = false) Flux<FilePart> imageFile
    ) {
        // 1. Gérer l'upload de l'image de manière asynchrone
        Mono<List<String>> imagesUrlsMono = mediaService.uploadImage(imageFile != null ? imageFile : Flux.empty());

        // 2. Combiner tous les champs Mono
        return Mono.zip(syndicatId, name, description, price, sku, category, stock, isActive)
                .zipWith(imagesUrlsMono)
                .flatMap(tuple -> {
                    var fields = tuple.getT1(); // Contient les 8 premiers champs
                    List<String> imageUrls = tuple.getT2(); // Contient l'URL de l'image

                    String uploadedUrl = imageUrls.isEmpty() ? null : imageUrls.get(0);

                    // 3. Conversion manuelle des types (String -> UUID, BigDecimal, Integer, Boolean)
                    Product productDomain = new Product(
                            UUID.randomUUID(), // On génère l'ID ici pour l'insertion
                            UUID.fromString(fields.getT1()),      // syndicatId
                            fields.getT2(),                       // name
                            fields.getT3(),                       // description
                            new java.math.BigDecimal(fields.getT4()), // price
                            fields.getT5(),                       // sku
                            fields.getT6(),                       // category
                            Integer.parseInt(fields.getT7()),     // stock
                            uploadedUrl,
                            Boolean.parseBoolean(fields.getT8())  // isActive
                    );

                    return productUseCase.createProduct(productDomain);
                })
                .map(this::mapToResponse);
    }

    @PatchMapping("/{id}/stock")
    @Operation(
        summary = "Mettre à jour le stock d'un produit", 
        description = "Permet de mettre à jour la quantité en stock d'un produit spécifique.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Stock mis à jour avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "404", description = "Produit non trouvé")
    })
    public Mono<ProductResponse> updateStock(@PathVariable UUID id, @RequestBody int quantity) {
        return productUseCase.updateStock(id, quantity)
            .map(this::mapToResponse);
    }


    @PatchMapping("/{id}")
    @Operation(
        summary = "Mettre à jour les détails d'un produit", 
        description = "Permet de mettre à jour les informations d'un produit spécifique.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Produit mis à jour avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "404", description = "Produit non trouvé")
    })
    public Mono<ProductResponse> updateProduct(@PathVariable UUID id, @RequestBody @Valid ProductRequest dto) {
        Product product = new Product(
            id,
            dto.syndicatId(),
            dto.name(),
            dto.description(),
            dto.price(),
            dto.sku(),
            dto.category(),
            dto.stock(),
            null,
            dto.isActive()
        );
        return productUseCase.updateProduct(product)
            .map(this::mapToResponse);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Supprimer un produit", 
        description = "Permet de supprimer un produit spécifique du marketplace.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Produit supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Produit non trouvé")
    })
    public Mono<Void> deleteProduct(@PathVariable UUID id) {
        return productUseCase.deleteProduct(id);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Récupérer les détails d'un produit", 
        description = "Permet de récupérer les informations détaillées d'un produit spécifique.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Détails du produit récupérés avec succès"),
        @ApiResponse(responseCode = "404", description = "Produit non trouvé")
    })
    public Mono<ProductResponse> getProductDetails(@PathVariable UUID id) {
        return productUseCase.getProductDetails(id)
            .map(this::mapToResponse);
    }

    @GetMapping("/syndicates/{syndicatId}")
    @Operation(
        summary = "Récupérer les produits d'un syndicat", 
        description = "Permet de récupérer tous les produits associés à un syndicat spécifique.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Produits récupérés avec succès"),
        @ApiResponse(responseCode = "404", description = "Syndicat non trouvé")
    })
    public Flux<ProductResponse> getSyndicatProducts(@PathVariable UUID syndicatId) {
       return productUseCase.getSyndicatProducts(syndicatId)
            .map(this::mapToResponse);
    }

    public ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.id(),
                product.syndicatId(),
                product.name(),
                product.description(),
                product.price(),
                product.sku(),
                product.category(),
                product.stock(),
                product.imageUrl(),
                product.isActive()
        );
    }
}

```

*Lignes: 189*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\serviceOffering\ServiceOfferingController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.serviceOffering;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.yowyob.ugate_service.domain.model.SyndicatService;
import com.yowyob.ugate_service.domain.ports.in.marketplace.ManageServiceUseCase;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.ServiceOfferingRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ServiceOfferingResponse;
import com.yowyob.ugate_service.infrastructure.mappers.serviceOffering.ServiceOfferingMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/service-offerings")
@RequiredArgsConstructor
@Tag(name = "Gestion des services", description = "API pour la gestion des services dans le marketplace.")
public class ServiceOfferingController {

    private final ManageServiceUseCase serviceOfferingUseCase;
    private final ServiceOfferingMapper mapper;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Créer un nouveau service", 
        description = "Permet de créer un nouveau service dans le marketplace.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Service créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public Mono<ServiceOfferingResponse> createServiceOffering(@RequestBody @Valid Mono<ServiceOfferingRequest> dto) {
        return dto
            .map(mapper::mapToDomain)
            .flatMap(serviceOfferingUseCase::createService)
            .map(mapper::mapToResponse);
    }

    @PatchMapping("/{id}")
    @Operation(
        summary = "Mettre à jour un service existant", 
        description = "Permet de mettre à jour les détails d'un service existant dans le marketplace.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Service mis à jour avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "404", description = "Service non trouvé")
    })
    public Mono<ServiceOfferingResponse> updateServiceOffering(@PathVariable UUID id, @RequestBody @Valid ServiceOfferingRequest dto) {
        SyndicatService serviceToUpdate = new SyndicatService(
            id,
            null,
            dto.title(),
            dto.description(),
            dto.price(),
            dto.features(),
            dto.isActive()
        );
        return serviceOfferingUseCase.updateService(serviceToUpdate)
            .map(mapper::mapToResponse);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Supprimer un service", 
        description = "Permet de supprimer un service spécifique du marketplace.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Service supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Service non trouvé")
    })
    public Mono<Void> deleteServiceOffering(@PathVariable UUID id) {
        return serviceOfferingUseCase.deleteService(id);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtenir les détails d'un service", 
        description = "Permet de récupérer les détails d'un service spécifique dans le marketplace.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Détails du service récupérés avec succès"),
        @ApiResponse(responseCode = "404", description = "Service non trouvé")
    })
    public Mono<ServiceOfferingResponse> getServiceOfferingDetails(@PathVariable UUID id) {
        return serviceOfferingUseCase.getServiceDetails(id)
            .map(mapper::mapToResponse);
    }

    @GetMapping("/active")
    @Operation(
        summary = "Lister tous les services actifs", 
        description = "Permet de récupérer une liste de tous les services actifs dans le marketplace.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Liste des services actifs récupérée avec succès")
    })
    public Flux<ServiceOfferingResponse> getAllActiveServiceOfferings() {
        return serviceOfferingUseCase.getAllActiveServices()
            .map(mapper::mapToResponse);
    }
}

```

*Lignes: 127*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\superadmin\SuperAdminAnalyticsController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.superadmin;

import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.StatsResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatMemberRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/super-admin/analytics")
@RequiredArgsConstructor
@Tag(name = "SuperAdmin Analytics", description = "Statistiques globales de la plateforme")
public class SuperAdminAnalyticsController {

    private final SyndicatRepository syndicatRepository;
    private final SyndicatMemberRepository memberRepository;

    @Operation(summary = "Obtenir les KPIs globaux", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/dashboard")
    public Mono<StatsResponse> getDashboardStats() {
        return Mono.zip(
                syndicatRepository.count(),                 // T1: Total Syndicats
                syndicatRepository.countByIsApprovedTrue(), // T2: Approuvés
                syndicatRepository.countByIsApprovedFalse(),// T3: En attente
                memberRepository.count(),                   // T4: Total Membres
                memberRepository.countByIsActiveTrue()      // T5: Membres Actifs
        ).map(tuple -> new StatsResponse(
                tuple.getT1(),
                tuple.getT2(),
                tuple.getT3(),
                tuple.getT4(),
                tuple.getT5(),
                45230.00 // Mock revenu pour l'instant
        ));
    }
}
```

*Lignes: 42*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\superadmin\SyndicateSuperAdminController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.superadmin;

import com.yowyob.ugate_service.application.service.syndicate.SyndicatManagementService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.StatsResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatMemberRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/super-admin/syndicates")
@RequiredArgsConstructor
@Tag(name = "SuperAdmin Management", description = "Gestion de l'état d'un Syndicat ( l'approuver, le désactiver, etc)")
public class SyndicateSuperAdminController {

    private final SyndicatManagementService syndicateService;
    private final SyndicatRepository syndicatRepository;
    private final SyndicatMemberRepository memberRepository;



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


    @GetMapping("/dashboard")
    @Operation(summary = "Obtenir les statistiques globales")
    public Mono<StatsResponse> getGlobalStats() {
        return Mono.zip(
                syndicatRepository.count(),
                syndicatRepository.countByIsActiveTrue(),
                syndicatRepository.countByIsApprovedFalse(),
                memberRepository.count(),
                memberRepository.countByIsActiveTrue()
        ).map(tuple -> new StatsResponse(
                tuple.getT1(),
                tuple.getT2(),
                tuple.getT3(),
                tuple.getT4(),
                tuple.getT5(),
                0.0
        ));
    }


}

```

*Lignes: 90*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\syndicate\BranchController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.syndicate;

import com.yowyob.ugate_service.application.service.syndicate.BranchManagementService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreateBranchRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.UpdateBranchRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.BranchResponse;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "Branches", description = "Gestion des branches (agences) des syndicats")
public class BranchController {

    private final BranchManagementService branchService;

    @Operation(
            summary = "Créer une branche pour un syndicat",
            description = "Permet de créer une branche avec une bannière optionnelle.",
            security = @SecurityRequirement(name = "bearerAuth"),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = CreateBranchRequest.class)
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Branche créée"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping(
            value = "/syndicates/{syndicatId}/branches",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BranchResponse> createBranch(
            @Parameter(description = "ID du syndicat", required = true)
            @PathVariable UUID syndicatId,
            @Parameter(hidden = true)
            @Valid @ModelAttribute CreateBranchRequest request) {

        return branchService.createBranch(syndicatId, request);
    }

    @Operation(summary = "Lister les branches d'un syndicat", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/syndicates/{syndicatId}/branches")
    public Flux<BranchResponse> getBranchesBySyndicate(@PathVariable UUID syndicatId) {
        return branchService.getSyndicateBranches(syndicatId);
    }

    @Operation(summary = "Mettre à jour une branche", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping("/branches/{branchId}")
    public Mono<BranchResponse> updateBranch(
            @PathVariable UUID branchId,
            @RequestBody UpdateBranchRequest request) {
        return branchService.updateBranch(branchId, request);
    }
}
```

*Lignes: 74*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\syndicate\SyndicateController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.syndicate;


import com.yowyob.ugate_service.application.service.syndicate.SyndicatManagementService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.UpdateSyndicateFullRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.PaginatedResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateDetailsResponse;
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


    @Operation(
            summary = "Mise à jour complète d'un syndicat (Créateur uniquement)",
            description = "Permet de modifier nom, description, domaine et fichiers. Les champs non envoyés restent inchangés.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<SyndicateResponse> updateSyndicate(
            @Parameter(description = "ID du syndicat") @PathVariable UUID id,

            @RequestPart(value = "name", required = false) String name,
            @RequestPart(value = "description", required = false) String description,
            @RequestPart(value = "domain", required = false) String domain,
            @RequestPart(value = "logo", required = false) FilePart logo,
            @RequestPart(value = "charte", required = false) FilePart charte,
            @RequestPart(value = "statusDoc", required = false) FilePart statusDoc
    ) {
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> UUID.fromString(((Jwt) ctx.getAuthentication().getPrincipal()).getSubject()))
                .flatMap(requesterId -> {
                    UpdateSyndicateFullRequest request = new UpdateSyndicateFullRequest(
                            name, description, domain, logo, charte, statusDoc
                    );
                    return syndicateService.updateSyndicateFull(id, requesterId, request);
                });
    }


    @Operation(
            summary = "Obtenir les détails complets d'un syndicat",
            description = "Retourne les informations du syndicat, de l'organisation liée, du créateur, les documents et les statistiques.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Détails récupérés avec succès"),
            @ApiResponse(responseCode = "404", description = "Syndicat introuvable")
    })
    @GetMapping("/{id}/details")
    public Mono<SyndicateDetailsResponse> getSyndicateDetails(
            @Parameter(description = "UUID du syndicat") @PathVariable UUID id
    ) {
        return syndicateService.getSyndicateDetails(id);
    }


}
```

*Lignes: 162*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\syndicate\SyndicateMangementController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.syndicate;


import com.yowyob.ugate_service.application.service.syndicate.MembershipService;
import com.yowyob.ugate_service.application.service.syndicate.SyndicateMembershipService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.MembershipApprovalRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.UpdateMemberRoleRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.AddUserResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.BasicResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.MemberResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateFullStatsResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.MembershipRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/admin/syndicates")
@RequiredArgsConstructor
@Tag(name = "Syndicat Admin Management", description = "Gestion de l'état d'un Syndicat ( l'approuver, le désactiver, etc)")
public class SyndicateMangementController {
    private final SyndicateMembershipService membershipService;
    @Operation(
            summary = "Ajouter un membre manuellement (Admin)",
            description = "Permet à un administrateur d'ajouter directement un utilisateur dans une branche spécifique du syndicat via son email.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Membre ajouté et invitation envoyée"),
            @ApiResponse(responseCode = "404", description = "Syndicat ou Branche introuvable"),
            @ApiResponse(responseCode = "400", description = "Données manquantes")
    })

    @PostMapping("/{syndicatId}/branches/{branchId}/members/add")
    public Mono<ResponseEntity<AddUserResponse>> addMember(
            @Parameter(description = "ID du syndicat", required = true)
            @PathVariable UUID syndicatId,

            @Parameter(description = "ID de la branche cible", required = true)
            @PathVariable UUID branchId,

            @RequestBody AddMemberRequest request) {

        log.info("🔵 Admin ajoute membre {} dans la branche {} du syndicat {}",
                request.email(), branchId, syndicatId);

        return membershipService.addMemberByAdmin(
                syndicatId,
                branchId,
                request.email(),
                request.firstName(),
                request.lastName()
        ).map(ResponseEntity::ok);
    }

    @Operation(summary = "Lister les membres d'un syndicat", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{syndicatId}/members")
    public Flux<MemberResponse> getMembers(@PathVariable UUID syndicatId) {
        return membershipService.getSyndicateMembers(syndicatId);
    }

    @Operation(summary = "Lister les demandes d'une branche", description = "Retourne les demandes en attente pour une branche spécifique.", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{syndicatId}/branches/{branchId}/requests")
    public Flux<MembershipRequest> getBranchRequests(
            @PathVariable UUID syndicatId, // Gardé pour la cohérence de l'URL, même si non utilisé directement
            @PathVariable UUID branchId) {
        return membershipService.getRequestsByBranch(branchId);
    }

    @Operation(summary = "Obtenir les détails d'une demande", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/requests/{requestId}")
    public Mono<MembershipRequest> getRequestDetails(@PathVariable UUID requestId) {
        return membershipService.getRequestDetails(requestId);
    }

    @Operation(summary = "Traiter une demande (Approuver/Rejeter)", description = "Permet à un admin de valider ou refuser une adhésion.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Traitement effectué"),
            @ApiResponse(responseCode = "404", description = "Demande introuvable")
    })
    @PostMapping("/requests/{requestId}/process")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> processRequest(
            @PathVariable UUID requestId,
            @Valid @RequestBody MembershipApprovalRequest request) {
        return membershipService.processRequest(requestId, request.approve(), request.rejectionReason());
    }

    @Operation(
            summary = "Changer le rôle d'un membre",
            description = "Permet de modifier le rôle d'un membre du syndicat (ex: passer en MODERATOR).",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PatchMapping("/{syndicatId}/members/{userId}/role")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> updateMemberRole(
            @PathVariable UUID syndicatId,
            @PathVariable UUID userId,
            @Valid @RequestBody UpdateMemberRoleRequest request) {
        return membershipService.updateMemberRole(syndicatId, userId, request.newRole());
    }

    @Operation(
            summary = "Obtenir les statistiques du syndicat",
            description = "Renvoie les compteurs globaux (Membres, Branches, Demandes, Publications) pour le tableau de bord.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/{syndicatId}/stats")
    public Mono<SyndicateFullStatsResponse> getSyndicateStats(@PathVariable UUID syndicatId) {
        return membershipService.getSyndicateStats(syndicatId);
    }

    public record AddMemberRequest(String email, String firstName, String lastName) {}
}

```

*Lignes: 129*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\inbound\rest\syndicate\SyndicateMembershipController.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.syndicate;

import com.yowyob.ugate_service.application.service.auth.UserManagementService;
import com.yowyob.ugate_service.application.service.syndicate.SyndicateMembershipService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.MembershipApprovalRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.MembershipRequestRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.UpdateFullProfileRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.UpdateMemberRoleRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.MemberResponse;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.SyndicateFullStatsResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.MembershipRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/syndicates")
@RequiredArgsConstructor
@Tag(name = "Gestion Syndicat & Membres", description = "Endpoints pour gérer les adhésions, les rôles et visualiser les statistiques.")
public class SyndicateMembershipController {

    private final SyndicateMembershipService membershipService;
    private final UserManagementService userManagementService;


    @Operation(summary = "Demander à rejoindre un syndicat", description = "Crée une demande d'adhésion pour une branche spécifique.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Demande créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides ou demande déjà existante")
    })
    @PostMapping("/{syndicatId}/memberships/request")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MembershipRequest> requestToJoin(
            @PathVariable UUID syndicatId,
            @Valid @RequestBody MembershipRequestRequest request) {
        return membershipService.requestToJoin(syndicatId, request.branchId(), request.motivation());
    }


    @Operation(summary = "Mise à jour complète du profil de l'utilisateur connecté", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/user")
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<MemberResponse> updateMyProfile(
            @AuthenticationPrincipal Jwt jwt,
            @RequestPart(value = "firstName", required = false) String firstName,
            @RequestPart(value = "lastName", required = false) String lastName,
            @RequestPart(value = "phoneNumber", required = false) String phoneNumber,
            @RequestPart(value = "nationality", required = false) String nationality,
            @RequestPart(value = "gender", required = false) String gender,
            @RequestPart(value = "language", required = false) String language,
            @RequestPart(value = "birthDate", required = false) String birthDateStr, // Format ISO
            @RequestPart(value = "image", required = false) FilePart image
    ) {
        UUID userId = UUID.fromString(jwt.getSubject());

        Instant birthDate = (birthDateStr != null) ? Instant.parse(birthDateStr) : null;

        UpdateFullProfileRequest request = new UpdateFullProfileRequest(
                firstName, lastName, phoneNumber, nationality, gender, language, birthDate, image
        );

        return userManagementService.updateFullProfile(userId, request);
    }


}
```

*Lignes: 82*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\external\client\authentication\TraMaSysUserAdapter.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.authentication;

import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpStatusCode;
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
        var registerData = new RegisterRequest(
                email,
                password,
                email,
                null,
                firstName,
                lastName,
                "SYNDICAT",
                List.of("CLIENT")
        );


        org.springframework.http.client.MultipartBodyBuilder builder = new org.springframework.http.client.MultipartBodyBuilder();
        builder.part("data", registerData, org.springframework.http.MediaType.APPLICATION_JSON);

        return webClient.post()
                .uri("/api/auth/register")
                .body(org.springframework.web.reactive.function.BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class).flatMap(body -> {
                            log.error("Détail erreur TraMaSys : {}", body);
                            return Mono.error(new RuntimeException("Erreur Auth-Service: " + body));
                        })
                )
                .bodyToMono(AuthResponse.class)
                .map(response -> mapToDomain(response.user()))
                .doOnSuccess(user -> log.info("Utilisateur créé avec succès sur TraMaSys : {}", user.id()));
    }

    @Override
    public Mono<ExternalUserInfo> findById(UUID id) {
        String cacheKey = "v2_user:" + id;
        return userRedisTemplate.opsForValue().get(cacheKey)
                .cast(ExternalUserInfo.class)
                .switchIfEmpty(webClient.get()
                        .uri("/api/users/{id}", id)
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, response ->
                                response.bodyToMono(String.class).flatMap(body -> {
                                    log.error("Error fetching user from TraMaSys, id: {}, response: {}", id, body);
                                    return Mono.empty(); // Return empty instead of an error
                                })
                        )
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

*Lignes: 125*

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

        return baseUrl + "/media/" + dto.id();
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
```

*Lignes: 93*

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

        private final Integer eventAlertTemplateId;
        private final Integer publicationCommentAlertTemplateId;
        private final Integer publicationReactAlertTemplateId;
        private final Integer adminAlertWhenNewPublicationTemplateId;
        private final Integer adminAlertAcceptEventTemplateId;

        public HttpNotificationAdapter(WebClient.Builder builder,
                        @Value("${application.external.notification-service-url}") String notificationUrl,
                        @Value("${application.external.notification-service-token}") String serviceToken,
                        @Value("${application.external.notification-invite-template-id}") Integer inviteTemplateId,
                        @Value("${application.external.notification-new-event-alert-templatet-id}") Integer eventAlertTemplateId,
                        @Value("${application.external.notification-publication-comment-alert-template-id}") Integer publicationCommentAlertTemplate,
                        @Value("${application.external.notification-publication-react-alert-template-id}") Integer publicationReactAlertTemplateId,
                        @Value("${application.external.notification-admin-alert-when-new-publication-template-id}") Integer adminAlertWhenNewPublicationTemplateId,
                        @Value("${application.external.notification-admin-alert-accept-event-template-id}") Integer adminAlertAcceptEventTemplateId) {
                this.webClient = builder.baseUrl(notificationUrl).build();
                this.serviceToken = serviceToken;
                this.inviteTemplateId = inviteTemplateId;
                this.eventAlertTemplateId = eventAlertTemplateId;
                this.publicationCommentAlertTemplateId = publicationCommentAlertTemplate;
                this.publicationReactAlertTemplateId = publicationReactAlertTemplateId;
                this.adminAlertWhenNewPublicationTemplateId = adminAlertWhenNewPublicationTemplateId;
                this.adminAlertAcceptEventTemplateId = adminAlertAcceptEventTemplateId;
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
                                                "loginUrl", "https://ugate.yowyob.com/reset-password"));

                return webClient.post()
                                .uri("/api/v1/notifications/send")
                                .header("X-Service-Token", serviceToken)
                                .bodyValue(requestBody)
                                .retrieve()
                                .onStatus(status -> status.isError(), response -> response.bodyToMono(String.class)
                                                .flatMap(error -> Mono.error(
                                                                new RuntimeException("Notification Error: " + error))))
                                .toBodilessEntity()
                                .doOnSuccess(v -> log.info("Invitation envoyée avec succès à {}", email))
                                .doOnError(e -> log.error("Échec de l'envoi de l'invitation à {}: {}", email,
                                                e.getMessage()))
                                .then();
        }

        private Mono<Void> sendEmailNotification(Integer templateId, List<String> recipients,
                        Map<String, Object> data) {
                Map<String, Object> requestBody = Map.of(
                                "notificationType", "EMAIL",
                                "templateId", templateId,
                                "to", recipients,
                                "data", data);

                return webClient.post()
                                .uri("/api/v1/notifications/send")
                                .header("X-Service-Token", serviceToken)
                                .bodyValue(requestBody)
                                .retrieve()
                                .onStatus(status -> status.isError(), response -> response.bodyToMono(String.class)
                                                .flatMap(error -> Mono.error(
                                                                new RuntimeException("Notification Error: " + error))))
                                .toBodilessEntity()
                                .doOnSuccess(v -> log.info("Notification envoyée avec succès à {}", recipients))
                                .doOnError(e -> log.error("Échec de l'envoi de la notification à {}: {}", recipients,
                                                e.getMessage()))
                                .then();
        }

        @Override
        public Mono<Void> sendNewEventAlert(List<String> emails, String eventName) {
                Map<String, Object> data = Map.of(
                                "eventName", eventName,
                                "eventUrl",
                                "https://ugate.yowyob.com/events/" + eventName.replaceAll(" ", "-").toLowerCase());

                return sendEmailNotification(eventAlertTemplateId, emails, data);
        }

        @Override
        public Mono<Void> sendPublicationCommentAlert(String authorEmail, String publicationTitle, String firstName) {
                Map<String, Object> data = Map.of(
                                "publicationTitle", publicationTitle,
                                "firstName", firstName,
                                "publicationUrl", "https://ugate.yowyob.com/publications/"
                                                + publicationTitle.replaceAll(" ", "-").toLowerCase());

                return sendEmailNotification(publicationCommentAlertTemplateId, List.of(authorEmail), data);
        }

        @Override
        public Mono<Void> sendPublicationReactAlert(String authorEmail, String publicationTitle, String firstName) {
                Map<String, Object> data = Map.of(
                                "publicationTitle", publicationTitle,
                                "firstName", firstName,
                                "publicationUrl", "https://ugate.yowyob.com/publications/"
                                                + publicationTitle.replaceAll(" ", "-").toLowerCase());

                return sendEmailNotification(publicationReactAlertTemplateId, List.of(authorEmail), data);
        }

        @Override
        public Mono<Void> sendAdminAlertWhenNewPublication(List<String> emails, String publicationTitle,
                        String authorName) {
                Map<String, Object> data = Map.of(
                                "publicationTitle", publicationTitle,
                                "authorName", authorName,
                                "publicationUrl", "https://ugate.yowyob.com/publications/"
                                                + publicationTitle.replaceAll(" ", "-").toLowerCase());

                return sendEmailNotification(adminAlertWhenNewPublicationTemplateId, emails, data);
        }

        @Override
        public Mono<Void> sendAdminAlertAcceptEvent(List<String> emails, String eventName, String organizerName) {
                Map<String, Object> data = Map.of(
                                "eventName", eventName,
                                "organizerName", organizerName,
                                "eventUrl",
                                "https://ugate.yowyob.com/events/" + eventName.replaceAll(" ", "-").toLowerCase());

                return sendEmailNotification(adminAlertAcceptEventTemplateId, emails, data);
        }

}
```

*Lignes: 153*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\adapters\BranchPersistenceAdapter.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.ports.out.syndicate.BranchPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Branch;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component; // Très important !
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BranchPersistenceAdapter implements BranchPersistencePort {

    private final BranchRepository branchRepository;

    @Override
    public Mono<Branch> save(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public Mono<Branch> findById(UUID id) {
        return branchRepository.findById(id);
    }

    @Override
    public Flux<Branch> findBySyndicatId(UUID syndicatId) {
        return branchRepository.findBySyndicatId(syndicatId);
    }
}
```

*Lignes: 33*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\adapters\CommentPersistenceAdapter.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.model.CommentModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.CommentPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.CommentRepository;
import com.yowyob.ugate_service.infrastructure.mappers.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CommentPersistenceAdapter implements CommentPersistencePort {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public Mono<Void> saveComment(CommentModel commentModel) {
        return Mono.just(commentMapper.toEntity(commentModel))
                .flatMap(commentRepository::save)
                .then();
    }

    @Override
    public Flux<CommentModel> findCommentsByPublicationId(UUID publicationId) {
        return commentRepository.findByPublicationId(publicationId)
                .map(commentMapper::toModel);
    }
}

```

*Lignes: 34*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\adapters\EventPersistenceAdapter.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import reactor.core.publisher.Flux;
import com.yowyob.ugate_service.domain.model.EventModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.EventPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Event;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.EventRepository;
import com.yowyob.ugate_service.infrastructure.mappers.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EventPersistenceAdapter implements EventPersistencePort {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public Mono<EventModel> save(EventModel eventModel) {
        Event eventEntity = eventMapper.toEntity(eventModel);
        return eventRepository.save(eventEntity)
                .map(eventMapper::toModel);
    }

    @Override
    public Flux<EventModel> findByBranchId(UUID branchId) {
        return eventRepository.findByBranchId(branchId)
                .map(eventMapper::toModel);
    }

    @Override
    public Flux<EventModel> findAllPaginated(int page, int size) {
        // The FeedService will handle the actual pagination after merging and sorting.
        // This method should return all events for now.
        return eventRepository.findAll()
            .map(eventMapper::toModel);
    }
}

```

*Lignes: 43*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\adapters\MediaPersistenceAdapter.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.model.ImageModel;
import com.yowyob.ugate_service.domain.model.MediaInfo;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.EventImages;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Image;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.PublicationImage;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.EventImagesRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ImageRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationImageRepository;
import com.yowyob.ugate_service.infrastructure.mappers.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MediaPersistenceAdapter implements MediaPersistencePort {

    private final ImageRepository imageRepository;
    private final PublicationImageRepository publicationImageRepository;
    private final EventImagesRepository eventImagesRepository;
    private final ImageMapper imageMapper;

    @Override
    public Mono<Void> saveImageMedia(String imageUrl, String altText, UUID publicationId) {
        Image image = new Image(null, imageUrl, altText, Instant.now());

        return imageRepository.save(image)
                .flatMap(savedImage -> {
                    PublicationImage publicationImage = new PublicationImage(publicationId, savedImage.id(),
                            Instant.now(), Instant.now());
                    return publicationImageRepository.save(publicationImage);
                }).then();
    }

    @Override
    public Mono<Void> saveEventMedia(String imageUrl, String altText, UUID eventId) {
        Image image = new Image(null, imageUrl, altText, Instant.now());

        return imageRepository.save(image)
                .flatMap(savedImage -> {
                    EventImages eventImage = new EventImages(eventId, savedImage.id(),
                            Instant.now(), Instant.now());
                    return eventImagesRepository.save(eventImage);
                }).then();
    }

    @Override
    public Mono<Void> saveVideoMedia(String videoUrl, String title, UUID publicationId) {
        Image image = new Image(null, videoUrl, title, Instant.now());

        return imageRepository.save(image)
                .flatMap(savedImage -> {
                    PublicationImage publicationImage = new PublicationImage(publicationId, savedImage.id(),
                            Instant.now(), Instant.now());
                    return publicationImageRepository.save(publicationImage);
                }).then();
    }

    @Override
    public Mono<Void> saveAudioMedia(String audioUrl, String title, UUID publicationId) {
        Image image = new Image(null, audioUrl, title, Instant.now());

        return imageRepository.save(image)
                .flatMap(savedImage -> {
                    PublicationImage publicationImage = new PublicationImage(publicationId, savedImage.id(),
                            Instant.now(), Instant.now());
                    return publicationImageRepository.save(publicationImage);
                }).then();
    }

    @Override
    public Flux<MediaInfo> getMediaByPublicationId(UUID publicationId) {
        return imageRepository.findByPublicationId(publicationId)
                .map(image -> new MediaInfo(
                        image.url(),
                        image.altText().toUpperCase()));
    }

    @Override
    public Mono<ImageModel> saveImage(String imageUrl, String altText) {
        ImageModel imageModel = new ImageModel();
        imageModel.setUrl(imageUrl);
        imageModel.setAltText(altText);
        imageModel.setUploadedAt(Instant.now());
        Image image = imageMapper.toEntity(imageModel);
        return imageRepository.save(image).map(imageMapper::toModel);
    }

    @Override
    public Mono<ImageModel> getImageById(UUID imageId) {
        if (imageId == null) {
            return Mono.empty();
        }
        return imageRepository.findById(imageId)
                .map(imageMapper::toModel);
    }

    @Override
    public Flux<ImageModel> getImagesByEventId(UUID eventId) {
        return eventImagesRepository.findByEventId(eventId)
                .flatMap(eventImage -> imageRepository.findById(eventImage.imageId()))
                .map(imageMapper::toModel);
    }
}
```

*Lignes: 111*

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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

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
                publicationModel.getCreatedAt());

        return publicationRepository.save(publication)
                .map(savedPublication -> {
                    publicationModel.setId(savedPublication.id());
                    return publicationModel;
                });
    }

    @Override
    public Mono<PublicationModel> findById(UUID id) { // Added this method
        return publicationRepository.findById(id)
                .map(publication -> {
                    PublicationModel model = new PublicationModel();
                    model.setId(publication.id());
                    model.setBranchI(publication.branchId());
                    model.setAuthorId(publication.authorId());
                    model.setContent(publication.content());
                    model.setNLikes(publication.nLikes());
                    model.setCreatedAt(publication.createdAt());
                    return model;
                });
    }

    @Override
    public Flux<PublicationModel> findByBranchId(UUID branchId) {
        return publicationRepository.findByBranchId(branchId)
                .map(publication -> {
                    PublicationModel model = new PublicationModel();
                    model.setId(publication.id());
                    model.setBranchI(publication.branchId());
                    model.setAuthorId(publication.authorId());
                    model.setContent(publication.content());
                    model.setNLikes(publication.nLikes());
                    model.setCreatedAt(publication.createdAt());
                    return model;
                });
    }

    @Override
    public Mono<Void> incrementLikes(UUID publicationId) {
        return publicationRepository.findById(publicationId)
                .flatMap(publication -> {
                    Publication updatedPublication = new Publication(
                            publication.id(),
                            publication.branchId(),
                            publication.authorId(),
                            publication.content(),
                            publication.nLikes() + 1,
                            publication.createdAt());
                    return publicationRepository.save(updatedPublication);
                })
                .then();
    }

    @Override
    public Flux<PublicationModel> findAllPaginated(int page, int size) {
        // The FeedService will handle the actual pagination after merging and sorting.
        // This method should return all publications for now.
        return publicationRepository.findAll()
                .map(publication -> {
                    PublicationModel model = new PublicationModel();
                    model.setId(publication.id());
                    model.setBranchI(publication.branchId());
                    model.setAuthorId(publication.authorId());
                    model.setContent(publication.content());
                    model.setNLikes(publication.nLikes());
                    model.setCreatedAt(publication.createdAt());
                    return model;
                });
    }
}
```

*Lignes: 98*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\adapters\PublicationVotePersistenceAdapter.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.model.PublicationVoteModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationVotePersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.PublicationVote;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationVoteRepository;
import com.yowyob.ugate_service.infrastructure.mappers.PublicationVoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PublicationVotePersistenceAdapter implements PublicationVotePersistencePort {

    private final PublicationVoteRepository publicationVoteRepository;
    private final PublicationVoteMapper publicationVoteMapper;

    @Override
    public Mono<PublicationVoteModel> save(PublicationVoteModel model) {
        PublicationVote entity = publicationVoteMapper.toEntity(model);
        return publicationVoteRepository.save(entity)
                .map(publicationVoteMapper::toModel);
    }

    @Override
    public Mono<PublicationVoteModel> findById(UUID id) {
        return publicationVoteRepository.findById(id)
                .map(publicationVoteMapper::toModel);
    }
}

```

*Lignes: 34*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\adapters\ReactionPersistenceAdapter.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.enumeration.ReactionTypeEnum;
import com.yowyob.ugate_service.domain.ports.out.syndicate.ReactionPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Reaction;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ReactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@AllArgsConstructor
public class ReactionPersistenceAdapter implements ReactionPersistencePort {

    private final ReactionRepository reactionRepository;

    @Override
    public Mono<Void> saveReaction(UUID publicationId, ReactionTypeEnum reactionType, UUID userId) {
        Reaction reaction = new Reaction(publicationId, userId, reactionType);
        return reactionRepository.insertReaction(
                reaction.publicationId(),
                reaction.userId(),
                reaction.type().name(),
                reaction.reactedAt()).then();
    }
}

```

*Lignes: 29*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\adapters\UserEventPersistenceAdapter.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.model.UserEventModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserEventPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.UserEvent;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.UserEventRepository;
import com.yowyob.ugate_service.infrastructure.mappers.UserEventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserEventPersistenceAdapter implements UserEventPersistencePort {

    private final UserEventRepository userEventRepository;
    private final UserEventMapper userEventMapper;

    @Override
    public Mono<Void> save(UserEventModel userEventModel) {
        UserEvent userEventEntity = userEventMapper.toEntity(userEventModel);
        return userEventRepository.save(userEventEntity).then();
    }

    @Override
    public Mono<Long> countByEventId(UUID eventId) {
        return userEventRepository.countByEventId(eventId.toString());
    }

    @Override
    public Flux<UserEventModel> findByEventId(UUID eventId) {
        return userEventRepository.findByEventId(eventId.toString())
                .map(userEventMapper::toModel);
    }

    @Override
    public Mono<Void> delete(UUID userId, UUID eventId) {
        return userEventRepository.deleteByUserIdAndEventId(userId.toString(), eventId.toString());
    }
}
```

*Lignes: 43*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\adapters\UserPersistenceAdapterPort.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.yowyob.ugate_service.domain.model.UserModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatMemberRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.UserRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@Component
@AllArgsConstructor
public class UserPersistenceAdapterPort implements UserPersistencePort {

  private final UserRepository userRepository;
  private final SyndicatMemberRepository syndicatMemberRepository;

  @Override
  public Flux<UserModel> findUsersByBranchId(UUID branchId) {
    return syndicatMemberRepository.findByBranchId(branchId)
        .flatMap(member -> userRepository.findById(member.userId()))
        .map(userEntity -> {
          UserModel userModel = new UserModel();
          userModel.setId(userEntity.getId().toString());
          userModel.setEmail(userEntity.email());
          userModel.setName(userEntity.name());
          userModel.setPhoneNumber(userEntity.phoneNumber());
          return userModel;
        });

  }

}

```

*Lignes: 38*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\adapters\VotePersistenceAdapter.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import com.yowyob.ugate_service.domain.model.VoteModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.VotePersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.VoteRepository;
import com.yowyob.ugate_service.infrastructure.mappers.VoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VotePersistenceAdapter implements VotePersistencePort {

    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;

    @Override
    public Mono<Void> save(VoteModel model) {
        return voteRepository.save(voteMapper.toEntity(model)).then();
    }

    @Override
    public Flux<VoteModel> findByPublicationVoteId(UUID publicationVoteId) {
        return voteRepository.findByPublicationVoteId(publicationVoteId)
                .map(voteMapper::toModel);
    }
}
```

*Lignes: 30*

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

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\Agency.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("agencies")
public record Agency(
        @Id
        UUID id,

        @Column("organization_id")
        UUID organizationId,

        String name,

        @CreatedDate
        @Column("created_at")
        Instant createdAt,

        @LastModifiedDate
        @Column("updated_at")
        Instant updatedAt,

        @Transient
        boolean isNewRecord
) implements Persistable<UUID> {

    @PersistenceCreator
    public Agency(UUID id, UUID organizationId, String name, Instant createdAt, Instant updatedAt) {
        this(id, organizationId, name, createdAt, updatedAt, false);
    }


    public static Agency createNew(UUID id, UUID organizationId, String name) {
        return new Agency(id, organizationId, name, Instant.now(), Instant.now(), true);
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

*Lignes: 57*

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

import java.util.UUID;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceCreator; // Import
import org.springframework.data.annotation.Transient;         // Import
import org.springframework.data.domain.Persistable;           // Import
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;

@Table("branches")
public record Branch(
        @Id
        UUID id,

        @Column("syndicat_id")
        UUID syndicatId,

        String name,
        String location,
        String contact,

        @Column("banner_url")
        String bannerUrl,

        @CreatedDate
        @Column("created_at")
        Instant createdAt,

        @LastModifiedDate
        @Column("updated_at")
        Instant updatedAt,

        @Transient
        boolean isNewRecord

) implements Persistable<UUID> {


    @PersistenceCreator
    public Branch(UUID id, UUID syndicatId, String name, String location, String contact, String bannerUrl, Instant createdAt, Instant updatedAt) {
        this(id, syndicatId, name, location, contact, bannerUrl, createdAt, updatedAt, false);
    }


    public static Branch createNew(UUID id, UUID syndicatId, String name, String location, String contact, String bannerUrl) {
        return new Branch(
                id,
                syndicatId,
                name,
                location,
                contact,
                bannerUrl,
                Instant.now(),
                Instant.now(),
                true
        );
    }

    public Branch withInfo(String name, String location, String contact, String bannerUrl) {
        return new Branch(
                this.id, this.syndicatId, name, location, contact, bannerUrl,
                this.createdAt, null,
                false
        );
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

*Lignes: 81*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\BusinessActor.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator; // Import Important
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("business_actors")
public record BusinessActor(
        @Id
        UUID id,

        String name,

        @Column("phone_number")
        String phoneNumber,

        @Column("email_address")
        String emailAddress,

        @Transient
        @org.springframework.data.annotation.ReadOnlyProperty
        boolean isNewRecord

) implements Persistable<UUID> {

    @PersistenceCreator
    public BusinessActor(UUID id, String name, String phoneNumber, String emailAddress) {
        this(id, name, phoneNumber, emailAddress, false);
    }

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

*Lignes: 54*

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

@Table("comments")
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
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Table("event")
public record Event(
        @Id UUID id,
        @Column("creator_id") UUID creatorId,
        @Column("branch_id") UUID branchId,
        String title,
        String description,
        String location,
        @Column("event_date") LocalDate date,
        @Column("start_time") LocalTime startTime,
        @Column("end_time") LocalTime endTime,
        @CreatedDate @Column("created_at") Instant createdAt,
        @Column("updated_at") Instant updatedAt) {

    public Event(UUID creatorId, UUID branchId, String title, String description, String location,
            LocalDate date, LocalTime startTime, LocalTime endTime, Instant createdAt, Instant updatedAt) {
        this(null, creatorId, branchId, title, description, location, date, startTime, endTime, createdAt, updatedAt);
    }
}

```

*Lignes: 32*

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

import org.springframework.data.annotation.*;
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

    @PersistenceCreator
    public Organization(UUID id, UUID businessActorId, String code, String shortName,
                        String longName, String email, Boolean isActive) {
        this(id, businessActorId, code, shortName, longName, email, isActive, false);
    }

    public Organization(UUID id, UUID businessActorId, String code, String email, String name) {
        this(id, businessActorId, code, name, name, email, true, true);
    }

    public static Organization createNew(UUID id, UUID businessActorId, String name, String email) {
        return new Organization(
                id,
                businessActorId,
                name.toUpperCase().replaceAll("\\s+", "_"),
                name,
                name,
                email,
                true,
                true // isNewRecord = true pour forcer l'INSERT
        );
    }

    @Override public UUID getId() { return id; }
    @Override @Transient public boolean isNew() { return isNewRecord; }
}

```

*Lignes: 52*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\ProductEntity.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Transient;
import java.math.BigDecimal;
import java.util.UUID;

@Table("syndicat_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity implements Persistable<UUID> {

                
                @Id
                private UUID id;
        
                private UUID syndicatId;

                private String name;

                private String description;

                private BigDecimal price;

                private String sku;

                private String category;

                private Integer stock;

                private String imageUrl;

                private Boolean isActive;

                @Transient
                private boolean isNew = false;

                
                @Override
                public boolean isNew() {
                    // Si isNew est true OU si l'id est null, Spring fera un INSERT
                    return isNew || id == null;
                }

} 
```

*Lignes: 58*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\Profile.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("profiles")
public record Profile (
        @Id UUID id,
        @Column("user_id")
        UUID userId,
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
        Instant updatedAt,

        @Transient
        boolean isNewRecord

) implements Persistable<UUID> {

        @PersistenceCreator
        public Profile(UUID id, UUID userId, String profilImageUrl, String firstName, String lastName,
                       Instant birth_date, String nationality, String gender, String language,
                       Instant createdAt, Instant updatedAt) {
                this(id, userId, profilImageUrl, firstName, lastName, birth_date, nationality, gender, language, createdAt, updatedAt, false);
        }

        public static Profile createNew(UUID userId, String firstName, String lastName) {
                return new Profile(
                        UUID.randomUUID(),
                        userId,
                        null,
                        firstName,
                        lastName,
                        null, null, null, null,
                        Instant.now(),
                        Instant.now(),
                        true
                );
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

*Lignes: 74*

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

@Table("publications")
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

*Lignes: 29*

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

@Table("publication_images")
public record PublicationImage(
                // Pas de @Id unique ici car c'est une clé composite (publication_id + image_id)

                @Column("publication_id") UUID publicationId,

                @Column("image_id") UUID imageId,

                @CreatedDate @Column("created_at") Instant createdAt,

                @LastModifiedDate @Column("updated_at") Instant updatedAt) {
}
```

*Lignes: 21*

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
                @Id UUID id,

                UUID branchId, // FK -> Branch

                String title,
                String description,

                // "closing_at" suggère un timestamp précis
                @Column("closing_at") Instant closingAt,

                String type) {
}

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

import com.yowyob.ugate_service.domain.enumeration.ReactionTypeEnum;

import java.time.Instant;
import java.util.UUID;

@Table("reactions")
public record Reaction(
        @Id UUID id,

        @Column("publication_id") UUID publicationId, // FK -> Publication

        @Column("user_id") UUID userId, // FK -> User

        ReactionTypeEnum type, // "LIKE", "LOVE", etc.

        @CreatedDate @Column("reacted_at") Instant reactedAt) {
    public Reaction(UUID publicationId, UUID userId, ReactionTypeEnum type) {
        this(null, publicationId, userId, type, Instant.now());
    }
}
```

*Lignes: 27*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\ServiceEntity.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import java.util.List;
import java.util.UUID;


@Table("syndicat_services") 
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceEntity implements Persistable<UUID>{
    @Id
    private UUID id;

    @Column("syndicat_id")
    private UUID syndicatId;
    
    private String title;

    private String description;

    private BigDecimal price;
   
    private String[] features;

    @Column("is_active")
    private Boolean isActive;

    @Transient
    private boolean isNew = false;



    // 4. C'est ici que la magie opère
    @Override
    public boolean isNew() {
        // Si isNew est true OU si l'id est null, Spring fera un INSERT
        return isNew || id == null;
    }
}
```

*Lignes: 57*

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

        @Column("branch_id")
        UUID branchId,

        @CreatedDate     // Rempli automatiquement si l'auditing est activé
        @Column("joined_at")
        Instant joinedAt,

        @Column("is_active")
        Boolean isActive,

        @Column("role") RoleTypeEnum role   // MEMBER, MODERATOR, ADMIN
) {

    public static SyndicatMember createLocal(UUID syndicatId, UUID branchId, UUID userId, RoleTypeEnum role) {
        if (branchId == null) {
            throw new IllegalArgumentException("Un membre local doit avoir une branche (branchId ne peut pas être null)");
        }
        return new SyndicatMember(syndicatId, userId, branchId, Instant.now(), true, role);
    }


    public static SyndicatMember createGlobalAdmin(UUID syndicatId, UUID userId) {
        return new SyndicatMember(syndicatId, userId, null, Instant.now(), true, RoleTypeEnum.ADMIN);
    }

    public SyndicatMember withRole(RoleTypeEnum newRole) {
        return new SyndicatMember(syndicatId, userId, branchId, joinedAt, isActive, newRole);
    }

    public boolean isGlobalAdmin() {
        return RoleTypeEnum.ADMIN.equals(this.role) && this.branchId == null;
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

*Lignes: 84*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\User.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
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

        @Column("phone_number") String phoneNumber,

        @Column("email_address") String email,
        @Transient boolean isNewRecord) implements Persistable<UUID> {
    public User(UUID id, String name, String phoneNumber, String email) {
        this(id, name, phoneNumber, email, true);
    }

    @PersistenceCreator
    public static User create(UUID id, String name, String phoneNumber, String email) {
        return new User(id, name, phoneNumber, email, false);
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

*Lignes: 43*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\entity\UserEvent.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("user_events")
public record UserEvent(

    @Column("user_event_id") //
    UUID id,

    @Column("user_id") //
    String userId,

    @Column("event_id") //
    String eventId,

    @Column("timestamp") //
    Instant timestamp) {

  public UserEvent(String userId, String eventId, Instant timestamp) {
    this(null, userId, eventId, timestamp);
  }
}

```

*Lignes: 28*

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
        UUID publicationVoteId, // FK -> PublicationVote

        String label // Le choix voté
) {}
```

*Lignes: 21*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\PostgresSyndicatProductAdapter.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence;

import java.util.Optional;
import java.util.UUID;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.yowyob.ugate_service.domain.model.Product;
import com.yowyob.ugate_service.domain.ports.out.marketplace.ProductRepositoryPort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.ProductEntity;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PostgresSyndicatProductAdapter implements ProductRepositoryPort {

    private final ProductRepository syndicatProductRepository;

    @Override
    public Mono<Product> findById(UUID id) {
        return syndicatProductRepository.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public Flux<Product> findBySyndicatId(UUID syndicatId) {
        return syndicatProductRepository.findBySyndicatId(syndicatId)
                .map(this::mapToDomain);
    }

    @Override
    public Mono<Product> saveProduct(Product product) {
       
        ProductEntity entity = new ProductEntity(
            product.id(),
            product.syndicatId(),
            product.name(),
            product.description(),
            product.price(),
            product.sku(),
            product.category(),
            product.stock(),
            product.imageUrl(),
            product.isActive(),
            true
        );
        return syndicatProductRepository.save(entity)
                .map(this::mapToDomain);
    }

    @Override
    public Mono<Product> updateProduct(Product product) {
        return syndicatProductRepository.findById(product.id())
        .switchIfEmpty(Mono.error(new RuntimeException("product not found")))

        .map(entity -> {

            Optional.ofNullable(product.syndicatId()).ifPresent(entity::setSyndicatId);
            Optional.ofNullable(product.name()).ifPresent(entity::setName);
            Optional.ofNullable(product.description()).ifPresent(entity::setDescription);
            Optional.ofNullable(product.price()).ifPresent(entity::setPrice);
            Optional.ofNullable(product.sku()).ifPresent(entity::setSku);
            Optional.ofNullable(product.category()).ifPresent(entity::setCategory);
            Optional.ofNullable(product.stock()).ifPresent(entity::setStock);
            Optional.ofNullable(product.imageUrl()).ifPresent(entity::setImageUrl);
            Optional.ofNullable(product.isActive()).ifPresent(entity::setIsActive);
            entity.setNew(false);
        
            return entity;
        })
        
        .flatMap(syndicatProductRepository::save)
        .map(this::mapToDomain);
        

    }

    @Override
    @Transactional
    public Mono<Void> deleteById(UUID id) {

        syndicatProductRepository.findById(id)
        .switchIfEmpty(Mono.error(new RuntimeException("Product not found")));

        return syndicatProductRepository.deleteById(id);
    }

    // --- MAPPER INTERNE (PROPRE À L'INFRA) ---
    private Product mapToDomain(ProductEntity row) {
        return new Product(
            row.getId(),
            row.getSyndicatId(),
            row.getName(),
            row.getDescription(),
            row.getPrice(),
            row.getSku(),
            row.getCategory(),
            row.getStock(),
            row.getImageUrl(),
            row.getIsActive()
        );
    }
}
```

*Lignes: 107*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\PostgresSyndicatServiceAdapter.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yowyob.ugate_service.domain.model.SyndicatService;
import com.yowyob.ugate_service.domain.ports.out.marketplace.ServiceOfferingRepositoryPort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.ServiceEntity;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PostgresSyndicatServiceAdapter implements ServiceOfferingRepositoryPort {

    private final ServiceRepository syndicatServiceRepository;

    @Override
    public Mono<SyndicatService> findServiceById(UUID id) {
        return syndicatServiceRepository.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public Flux<SyndicatService> findAllActiveServices() {
        return syndicatServiceRepository.findAll()
                .map(this::mapToDomain);
    }

    @Override
    @Transactional
    public Mono<SyndicatService> save(SyndicatService service) {
        ServiceEntity entity = ServiceEntity.builder()
                .id(service.id())
                .syndicatId(service.syndicatId())
                .title(service.title())
                .description(service.description())
                .price(service.price())
                // Conversion List -> Array
                .features(service.features() != null ? service.features().toArray(new String[0]) : null)
                .isActive(service.isActive())
                .isNew(true) // FORCE L'INSERTION
                .build();

        System.out.println("Insertion Service pour Syndicat: " + entity.getSyndicatId());

        return syndicatServiceRepository.save(entity)
               .map(this::mapToDomain);
    }

    @Override
    public Mono<SyndicatService> updateService(SyndicatService service) {
        return syndicatServiceRepository.findById(service.id())
        .switchIfEmpty(Mono.error(new RuntimeException("Service not found")))
        .map(entity -> {

            Optional.ofNullable(service.title()).ifPresent(entity::setTitle);
            Optional.ofNullable(service.description()).ifPresent(entity::setDescription);
            Optional.ofNullable(service.price()).ifPresent(entity::setPrice);
            if (service.features() != null) {
                entity.setFeatures(service.features().toArray(new String[0]));
            }
            Optional.ofNullable(service.isActive()).ifPresent(entity::setIsActive);
            entity.setNew(false);
        
            return entity;
        })
        
        .flatMap(syndicatServiceRepository::save)
        .map(this::mapToDomain);
    }

    @Override
    public Mono<Void> deleteService(UUID id) {
        syndicatServiceRepository.findById(id)
        .switchIfEmpty(Mono.error(new RuntimeException("Service not found")));


        return syndicatServiceRepository.deleteById(id);
    }

    // --- MAPPER ---
    private SyndicatService mapToDomain(ServiceEntity row) {
        List<String> featuresList = (row.getFeatures() != null)
                ? List.of(row.getFeatures())
                : List.of();

        return new SyndicatService(
            row.getId(),
            row.getSyndicatId(),
            row.getTitle(),
            row.getDescription(),
            row.getPrice(),
            featuresList,
            row.getIsActive()
        );
    }
}
```

*Lignes: 106*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\AgencyRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Agency;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import java.util.UUID;

public interface AgencyRepository extends R2dbcRepository<Agency, UUID> {
}
```

*Lignes: 8*

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
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BranchRepository extends R2dbcRepository<Branch, UUID> {
    Flux<Branch> findBySyndicatId(UUID syndicatId);
    Mono<Long> countBySyndicatId(UUID syndicatId);
}
```

*Lignes: 13*

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

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\CommentRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Comment;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface CommentRepository extends R2dbcRepository<Comment, UUID> {
    Flux<Comment> findByPublicationId(UUID publicationId);
}

```

*Lignes: 12*

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

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\EventImagesRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.EventImages;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EventImagesRepository extends R2dbcRepository<EventImages, UUID> {
    // Note: EventImages does not have a single @Id, so we might need a custom approach or composite key setup
    // For now, extending R2dbcRepository<EventImages, UUID> assumes a singular ID if one were to be defined
    // or relies on Spring Data R2DBC's ability to handle entities without a direct @Id (e.g., using all fields as key)
    // If a composite key is explicitly needed, this interface might need adjustment (e.g., extend ReactiveCrudRepository instead)
    // or use specific methods that take eventId and imageId.

    Mono<EventImages> findByEventIdAndImageId(UUID eventId, UUID imageId);

    Flux<EventImages> findByEventId(UUID eventId);
}

```

*Lignes: 21*

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

    // Requête JOIN pratique : Récupérer toutes les images d'une publication
    // directement
    @Query("""
                SELECT i.* FROM images i
                JOIN publication_images pi ON i.id = pi.image_id
                WHERE pi.publication_id = :pubId
            """)
    Flux<Image> findByPublicationId(UUID pubId); // TODO a modifier

    // Même chose pour les événements
    @Query("""
                SELECT i.* FROM images i
                JOIN event_images ei ON i.id = ei.image_id
                WHERE ei.event_id = :eventId
            """)
    Flux<Image> findByEventId(UUID eventId); // TODO a modifier
}
```

*Lignes: 27*

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


    Flux<MembershipRequest> findBySyndicatIdAndStatus(UUID syndicatId, MembershipRequest.MembershipStatus status);


    Flux<MembershipRequest> findByBranchIdAndStatus(UUID branchId, MembershipRequest.MembershipStatus status);


    Mono<Long> countBySyndicatIdAndStatus(UUID syndicatId, MembershipRequest.MembershipStatus status);
}

```

*Lignes: 29*

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

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.ProductEntity;

import reactor.core.publisher.Flux;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import java.util.UUID;

public interface ProductRepository extends R2dbcRepository<ProductEntity, UUID> {
    Flux<ProductEntity> findBySyndicatId(UUID syndicatId);

}

```

*Lignes: 14*

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
import reactor.core.publisher.Flux; // Import Flux
import java.util.UUID;

public interface PublicationImageRepository extends R2dbcRepository<PublicationImage, UUID> {
    Flux<PublicationImage> findByPublicationId(UUID publicationId);
}

```

*Lignes: 11*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\PublicationRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Publication;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PublicationRepository extends R2dbcRepository<Publication, UUID> {
    Flux<Publication> findByBranchId(UUID branchId);
    @Query("""
        SELECT COUNT(p.id) 
        FROM publications p 
        INNER JOIN branches b ON p.branch_id = b.id 
        WHERE b.syndicat_id = :syndicatId
    """)
    Mono<Long> countBySyndicatId(UUID syndicatId);

    Mono<Long> countByBranchId(UUID branchId);
}

```

*Lignes: 23*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\PublicationVoteRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.PublicationVote;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import java.util.UUID;

public interface PublicationVoteRepository extends R2dbcRepository<PublicationVote, UUID> {
}

```

*Lignes: 9*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\ReactionRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Reaction;

import reactor.core.publisher.Mono;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

@Repository
public interface ReactionRepository extends ReactiveCrudRepository<Reaction, UUID> {

  @Query("""
      INSERT INTO reactions (publication_id, user_id, type, reacted_at)
      VALUES (:publicationId, :userId, CAST(:type AS reaction_type_enum), :reactedAt)
      """)
  Mono<Void> insertReaction(
      UUID publicationId,
      UUID userId,
      String type,
      Instant reactedAt);

}

```

*Lignes: 28*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\ServiceRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import java.util.UUID;


import org.springframework.data.r2dbc.repository.R2dbcRepository;


import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.ServiceEntity;
import reactor.core.publisher.Mono;


public interface ServiceRepository extends R2dbcRepository<ServiceEntity, UUID> {

    Mono<Long> countByIsActiveTrue();
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

```

*Lignes: 67*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\SyndicatRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Syndicat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


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

    Mono<Long> countByIsActiveTrue();

    Mono<Long> countByIsApprovedFalse();

    Mono<Long> countByIsApprovedTrue();
}

```

*Lignes: 36*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\UserEventRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.UserEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserEventRepository extends R2dbcRepository<UserEvent, UUID> {
    Mono<Long> countByEventId(String eventId);
    Flux<UserEvent> findByEventId(String eventId);
    Mono<Void> deleteByUserIdAndEventId(String userId, String eventId);
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
    Mono<User> findByEmail(String email);
}
```

*Lignes: 10*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\adapters\outbound\persistence\repository\VoteRepository.java

```java
package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Vote;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface VoteRepository extends R2dbcRepository<Vote, UUID> {
    Flux<Vote> findByPublicationVoteId(UUID publicationVoteId);
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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtTimestampValidator;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.security.oauth2.jwt.*;

import javax.crypto.spec.SecretKeySpec;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, UserSyncWebFilter userSyncWebFilter) {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/webjars/**",
                                "/actuator/**",
                                "/syndicates",
                                "/compliance/**",
                                "/syndicates/*/details",
                                "/syndicates/*/branches",
                                "/products/syndicates/*",
                                "/products/*"
                        ).permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtDecoder(jwtDecoder()))
                )
                .addFilterAfter(userSyncWebFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        byte[] secretKeyBytes = jwtSecret.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        SecretKeySpec secretKey = new SecretKeySpec(secretKeyBytes, "HmacSHA256");

        NimbusReactiveJwtDecoder jwtDecoder = NimbusReactiveJwtDecoder.withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();

        List<OAuth2TokenValidator<Jwt>> validators = new ArrayList<>();
        validators.add(new JwtTimestampValidator(Duration.ofSeconds(60)));

        validators.add(token -> {
            String issuer = token.getClaimAsString("iss");
            if ("auth-service".equals(issuer)) {
                return OAuth2TokenValidatorResult.success();
            }
            return OAuth2TokenValidatorResult.failure(
                    new OAuth2Error("invalid_issuer", "L'émetteur " + issuer + " n'est pas valide", null)
            );
        });

        jwtDecoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>(validators));
        return jwtDecoder;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
```

*Lignes: 100*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\config\ServiceConfig.java

```java
package com.yowyob.ugate_service.infrastructure.config;

import com.yowyob.ugate_service.application.service.content.CommentService;
import com.yowyob.ugate_service.application.service.content.PublicationService;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.CommentPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationPersistencePort;
import com.yowyob.ugate_service.application.service.content.ReactionService;
import com.yowyob.ugate_service.domain.ports.out.syndicate.ReactionPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.VotePersistencePort;
import com.yowyob.ugate_service.application.service.content.PublicationVoteService;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationVotePersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserEventPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.BranchPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatRepository;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserPersistencePort;
//... (keep existing imports)
import com.yowyob.ugate_service.application.service.content.EventService;
import com.yowyob.ugate_service.domain.ports.out.syndicate.EventPersistencePort;
import com.yowyob.ugate_service.application.service.content.FeedService;
import com.yowyob.ugate_service.domain.ports.out.syndicate.EventPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public PublicationService publicationService(PublicationPersistencePort publicationPersistencePort, MediaPersistencePort mediaPersistencePort, UserGatewayPort userGatewayPort, NotificationPort notificationPort, BranchPersistencePort branchPersistencePort, SyndicatRepository syndicatRepository) {
        return new PublicationService(publicationPersistencePort, mediaPersistencePort, userGatewayPort, notificationPort, branchPersistencePort, syndicatRepository);
    }

    @Bean
    public CommentService commentService(MediaPersistencePort mediaPersistencePort, CommentPersistencePort commentPersistencePort, UserGatewayPort userGatewayPort, NotificationPort notificationPort, PublicationPersistencePort publicationPersistencePort) {
        return new CommentService(mediaPersistencePort, commentPersistencePort, userGatewayPort, notificationPort, publicationPersistencePort);
    }

    @Bean
    public ReactionService reactionService(PublicationService publicationService, ReactionPersistencePort reactionPersistencePort, PublicationPersistencePort publicationPersistencePort, UserGatewayPort userGatewayPort, NotificationPort notificationPort) {
        return new ReactionService(publicationService, reactionPersistencePort, publicationPersistencePort, userGatewayPort, notificationPort);
    }

    @Bean
    public PublicationVoteService publicationVoteService(PublicationVotePersistencePort publicationVotePersistencePort, VotePersistencePort votePersistencePort) {
        return new PublicationVoteService(publicationVotePersistencePort, votePersistencePort);
    }

    @Bean
    public EventService eventService(EventPersistencePort eventPersistencePort, MediaPersistencePort mediaPersistencePort, UserEventPersistencePort userEventPersistencePort, UserGatewayPort userGatewayPort, NotificationPort notificationPort, UserPersistencePort userPersistencePort) {
        return new EventService(eventPersistencePort, mediaPersistencePort, userEventPersistencePort, userGatewayPort, notificationPort, userPersistencePort);
    }

    @Bean
    public FeedService feedService(PublicationPersistencePort publicationPersistencePort, EventPersistencePort eventPersistencePort, UserGatewayPort userGatewayPort, MediaPersistencePort mediaPersistencePort, UserEventPersistencePort userEventPersistencePort) {
        return new FeedService(publicationPersistencePort, eventPersistencePort, userGatewayPort, mediaPersistencePort, userEventPersistencePort);
    }
}

```

*Lignes: 60*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\config\UserSyncWebFilter.java

```java
package com.yowyob.ugate_service.infrastructure.config;

import com.yowyob.ugate_service.application.service.auth.UserManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserSyncWebFilter implements WebFilter {

    private final UserManagementService userManagementService;

    @Override
    @NonNull
    public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {

        return ReactiveSecurityContextHolder.getContext()
                .map(securityContext -> securityContext.getAuthentication().getPrincipal())
                .filter(principal -> principal instanceof Jwt)
                .cast(Jwt.class)
                .flatMap(jwt -> {
                    try {
                        // Extraction ID (sub)
                        String sub = jwt.getSubject();
                        UUID userId = UUID.fromString(sub);

                        String username = jwt.getClaimAsString("username");
                        if (username == null) {
                            username = "unknown_user";
                        }

                        return userManagementService.synchronizeUser(userId, username);

                    } catch (IllegalArgumentException e) {
                        log.warn("UUID malformé dans le token : {}", e.getMessage());
                        return Mono.empty();
                    } catch (Exception e) {
                        log.error("Erreur synchro user", e);
                        return Mono.empty();
                    }
                })
                // On continue toujours la chaîne, même si pas de token ou erreur
                .then(chain.filter(exchange));
    }
}
```

*Lignes: 56*

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

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\mappers\CommentMapper.java

```java
package com.yowyob.ugate_service.infrastructure.mappers;

import com.yowyob.ugate_service.domain.model.CommentModel;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toEntity(CommentModel model);
    CommentModel toModel(Comment entity);
}

```

*Lignes: 13*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\mappers\EventMapper.java

```java
package com.yowyob.ugate_service.infrastructure.mappers;

import com.yowyob.ugate_service.domain.model.EventModel;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event toEntity(EventModel model);
    EventModel toModel(Event entity);
}

```

*Lignes: 12*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\mappers\ImageMapper.java

```java
package com.yowyob.ugate_service.infrastructure.mappers;

import com.yowyob.ugate_service.domain.model.ImageModel;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    Image toEntity(ImageModel model);
    ImageModel toModel(Image entity);
}

```

*Lignes: 12*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\mappers\products\ProductMapper.java

```java
package com.yowyob.ugate_service.infrastructure.mappers.products;

import org.mapstruct.Mapper;

import com.yowyob.ugate_service.domain.model.Product;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.ProductRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ProductResponse;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product mapToDomain(ProductRequest dto);
    ProductResponse mapToResponse(Product product);
    
}



```

*Lignes: 17*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\mappers\PublicationVoteMapper.java

```java
package com.yowyob.ugate_service.infrastructure.mappers;

import com.yowyob.ugate_service.domain.model.PublicationVoteModel;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.PublicationVote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublicationVoteMapper {
    PublicationVote toEntity(PublicationVoteModel model);
    PublicationVoteModel toModel(PublicationVote entity);
}

```

*Lignes: 12*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\mappers\serviceOffering\ServiceOfferingMapper.java

```java
package com.yowyob.ugate_service.infrastructure.mappers.serviceOffering;

import org.mapstruct.Mapper;

import com.yowyob.ugate_service.domain.model.SyndicatService;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.ServiceOfferingRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.ServiceOfferingResponse;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceOfferingMapper {

    @Mapping(target = "syndicatId", source = "syndicatId")
    SyndicatService mapToDomain( ServiceOfferingRequest dto );
    ServiceOfferingResponse mapToResponse( SyndicatService serviceOffering );
    

}

```

*Lignes: 19*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\mappers\syndicate\BranchMapper.java

```java
package com.yowyob.ugate_service.infrastructure.mappers.syndicate;

import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.BranchResponse;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Branch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    // MapStruct mappe automatiquement id, name, bannerUrl, etc.
    BranchResponse toResponse(Branch entity);
}
```

*Lignes: 12*

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
                entity.createdAt(),
                entity.isActive()
        );
    }
}

```

*Lignes: 27*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\mappers\UserEventMapper.java

```java
package com.yowyob.ugate_service.infrastructure.mappers;

import com.yowyob.ugate_service.domain.model.UserEventModel;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.UserEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEventMapper {

    @Mapping(target = "userId", expression = "java(model.getUserId().toString())")
    @Mapping(target = "eventId", expression = "java(model.getEventId().toString())")
    UserEvent toEntity(UserEventModel model);

    @Mapping(target = "userId", expression = "java(java.util.UUID.fromString(entity.userId()))")
    @Mapping(target = "eventId", expression = "java(java.util.UUID.fromString(entity.eventId()))")
    UserEventModel toModel(UserEvent entity);
}

```

*Lignes: 19*

---

### 📄 src\main\java\com\yowyob\ugate_service\infrastructure\mappers\VoteMapper.java

```java
package com.yowyob.ugate_service.infrastructure.mappers;

import com.yowyob.ugate_service.domain.model.VoteModel;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Vote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoteMapper {
    Vote toEntity(VoteModel model);
    VoteModel toModel(Vote entity);
}

```

*Lignes: 12*

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
server.forward-headers-strategy=framework
logging.level.org.springframework.security=DEBUG
logging.level.org.springframework.security.oauth2=DEBUG

# POSTGRESQL (R2DBC) 
spring.r2dbc.url=r2dbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5432}/${DB_NAME:newdb2}
spring.r2dbc.username=${DB_USERNAME:postgres}
spring.r2dbc.password=${DB_PASSWORD:postgres}
# spring.r2dbc.username=master
# spring.r2dbc.password=Azerty1234
# Nombre de connexions ouvertes au démarrage
spring.r2dbc.pool.initial-size=1
# MAX connexions simultanées vers PostgreSQL
spring.r2dbc.pool.max-size=5
# Temps max d’attente pour obtenir une connexion
spring.r2dbc.pool.max-acquire-time=30s
# Libère les connexions inutilisées
spring.r2dbc.pool.max-idle-time=30m
# Recycle les connexions (évite les leaks)
spring.r2dbc.pool.max-life-time=10m
# Retry si le pool est saturé
spring.r2dbc.pool.acquire-retry=3
# Vérification de la connexion
spring.r2dbc.pool.validation-query=SELECT 1

# SQL INIT
spring.sql.init.mode=always

# Configuration Liquibase
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml
spring.liquibase.url=jdbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5432}/${DB_NAME:newdb2}
spring.liquibase.user=${DB_USERNAME:postgres}
spring.liquibase.password=${DB_PASSWORD:postgres}
#  spring.liquibase.user=master
#  spring.liquibase.password=Azerty1234

# REDIS
spring.data.redis.host=${REDIS_HOST:168.119.122.86}
spring.data.redis.port=${REDIS_PORT:7000}
spring.data.redis.password=${REDIS_PASSWORD:Azerty1234*}
spring.data.redis.cluster.enabled=false

# KAFKA
spring.kafka.bootstrap-servers=${KAFKA_HOST:168.119.122.86}:${KAFKA_PORT:9092}

# KAFKA CONSUMER
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties.spring.json.trusted.packages=*

# KAFKA PRODUCER
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer

# CUSTOM CONFIG
application.external.stock-service-url= http://${EXTERNAL_HOST:168.119.122.86}:8081
application.kafka.topics.product-events=test-topic

management.endpoints.web.exposure.include=health,info,prometheus
management.endpoint.health.probes.enabled=true
management.metrics.export.prometheus.enabled=true

# RESILIENCE4J
resilience4j.circuitbreaker.instances.stock-service.failureRateThreshold=50
resilience4j.circuitbreaker.instances.stock-service.waitDurationInOpenState=5s
resilience4j.circuitbreaker.instances.stock-service.slidingWindowSize=5

# API Externe

#Media Service
application.external.media-service-url=https://media-service.pynfi.com

# Authentication Service
application.external.auth-service-url=https://auth-service.pynfi.com/
jwt.secret=404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970

#Notification Service
application.external.notification-service-url=https://notification-service.pynfi.com
application.external.notification-service-token=0a98858a-a4ab-40c0-9d44-6239a71daed4
application.external.notification-invite-template-id=39
application.external.notification-new-event-alert-templatet-id=40
application.external.notification-publication-comment-alert-template-id=41
application.external.notification-publication-react-alert-template-id=42
application.external.notification-admin-alert-when-new-publication-template-id=43
application.external.notification-admin-alert-accept-event-template-id=44
```

*Lignes: 95*

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
        <validCheckSum>ANY</validCheckSum> 

        <addColumn tableName="syndicats">
            <column name="creator_id"
                    type="UUID"
                    defaultValueComputed="'00000000-0000-0000-0000-000000000000'">
                <constraints nullable="false"/>
            </column>
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

    <changeSet id="v1.1-add-syndicat-logo-url" author="ERIC KOGHENE">
        <preConditions onFail="MARK_RAN">
            <not><columnExists tableName="syndicats" columnName="logo_url"/></not>
        </preConditions>
        <addColumn tableName="syndicats">
            <column name="logo_url" type="text"/>
        </addColumn>
    </changeSet>

    <changeSet id="v1.1-fix-enum-cast-role" author="ugate-service">
        <sql>
            CREATE CAST (character varying as role_type_enum) WITH INOUT AS IMPLICIT;
        </sql>
    </changeSet>

</databaseChangeLog>
```

*Lignes: 138*

---

### 📄 src\main\resources\db\changelog\changes\v1.10-make-branch-nullable.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">

    <changeSet id="v1.10-add-branch-to-member" author="yowyob">
        <comment>Migration vers le modèle hiérarchique : branch_id nullable hors PK</comment>

        <addColumn tableName="syndicat_members">
            <column name="branch_id" type="UUID">
                <constraints foreignKeyName="fk_member_branch"
                             referencedTableName="branches"
                             referencedColumnNames="id"/>
            </column>
        </addColumn>
    </changeSet>

</databaseChangeLog>
```

*Lignes: 20*

---

### 📄 src\main\resources\db\changelog\changes\v1.11-create-membership-request-table.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">

    <changeSet id="v1.12-create-membership-request" author="yowyob">
        <comment>Création de la table des demandes d'adhésion pour PostgreSQL</comment>

        <createTable tableName="membership_request">
            <!-- ID de la demande -->
            <column name="id" type="UUID" defaultValueComputed="gen_random_uuid()">
                <constraints primaryKey="true" nullable="false"/>
            </column>

            <!-- ID de l'utilisateur qui demande -->
            <column name="user_id" type="UUID">
                <constraints nullable="false"/>
            </column>

            <!-- ID du syndicat concerné -->
            <column name="syndicat_id" type="UUID">
                <constraints nullable="false"
                             foreignKeyName="fk_req_syndicat"
                             referencedTableName="syndicats"
                             referencedColumnNames="id"/>
            </column>

            <!-- ID de la branche -->
            <column name="branch_id" type="UUID">
                <constraints nullable="false"
                             foreignKeyName="fk_req_branch"
                             referencedTableName="branches"
                             referencedColumnNames="id"/>
            </column>

            <!-- Statut (PENDING, APPROVED, REJECTED) -->
            <column name="status" type="VARCHAR(50)">
                <constraints nullable="false"/>
            </column>
            <column name="motivation" type="TEXT">
                <constraints nullable="true"/>
            </column>
            <column name="rejection_reason" type="TEXT">
                <constraints nullable="true"/>
            </column>

            <!-- Dates automatiques -->
            <column name="created_at" type="TIMESTAMP" defaultValueComputed="now()">
                <constraints nullable="false"/>
            </column>
            <column name="updated_at" type="TIMESTAMP" defaultValueComputed="now()">
                <constraints nullable="false"/>
            </column>
        </createTable>
    </changeSet>

</databaseChangeLog>
```

*Lignes: 59*

---

### 📄 src\main\resources\db\changelog\changes\v1.2-create-event-table.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">

    <changeSet id="v1.2-1" author="gemini-agent">
        <createTable tableName="event">
            <column name="id" type="UUID">
                <constraints primaryKey="true" nullable="false"/>
            </column>
            <column name="creator_id" type="UUID"/>
            <column name="branch_id" type="UUID"/>
            <column name="title" type="VARCHAR(255)"/>
            <column name="description" type="TEXT"/>
            <column name="location" type="VARCHAR(255)"/>
            <column name="event_date" type="DATE"/>
            <column name="start_time" type="TIME"/>
            <column name="end_time" type="TIME"/>
            <column name="created_at" type="TIMESTAMP"/>
            <column name="updated_at" type="TIMESTAMP"/>
        </createTable>
    </changeSet>

    <!--<changeSet id="v1.2-2" author="gemini-agent">
        <createTable tableName="event_images">
            <column name="event_id" type="UUID">
                <constraints nullable="false"/>
            </column>
            <column name="image_id" type="UUID">
                <constraints nullable="false"/>
            </column>
            <column name="created_at" type="TIMESTAMP"/>
            <column name="updated_at" type="TIMESTAMP"/>
        </createTable>
        <addPrimaryKey tableName="event_images" columnNames="event_id, image_id"/>
    </changeSet>-->

</databaseChangeLog>

```

*Lignes: 41*

---

### 📄 src\main\resources\db\changelog\changes\v1.3-create-publication-vote-table.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">

    <changeSet id="v1.3-1" author="gemini-agent">
        <createTable tableName="publication_vote">
            <column name="id" type="UUID">
                <constraints primaryKey="true" nullable="false"/>
            </column>
            <column name="title" type="VARCHAR(255)"/>
            <column name="description" type="VARCHAR(255)"/>
            <column name="closing_at" type="TIMESTAMP"/>
            <column name="type" type="VARCHAR(255)"/>
        </createTable>
    </changeSet>

</databaseChangeLog>

```

*Lignes: 21*

---

### 📄 src\main\resources\db\changelog\changes\v1.4-create-vote-table.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">

    <changeSet id="v1.5-add-branch-banner" author="yowyob">
        <addColumn tableName="branches">
            <column name="banner_url" type="VARCHAR(1024)"/>
        </addColumn>
    </changeSet>

</databaseChangeLog>

```

*Lignes: 15*

---

### 📄 src\main\resources\db\changelog\changes\v1.5-add-branch-banner.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog

    xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
    http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">


    <changeSet id="v1.5-add-branch-banner" author="yowyob">
        <preConditions onFail="MARK_RAN">
            <not>
                <columnExists tableName="branches" columnName="banner_url"/>
            </not>
        </preConditions>
        <addColumn tableName="branches">
            <column name="banner_url" type="VARCHAR(1024)"/>
        </addColumn>
    </changeSet>

</databaseChangeLog>
```

*Lignes: 21*

---

### 📄 src\main\resources\db\changelog\changes\v1.6-auto-uuid-comments.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
    xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
        http://www.liquibase.org/xml/ns/dbchangelog
        http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.20.xsd">

    <!-- 1. S’assurer que l’extension pgcrypto est présente -->
    <changeSet id="001-enable-pgcrypto" author="daniel">
        <sql>CREATE EXTENSION IF NOT EXISTS "pgcrypto";</sql>
    </changeSet>

    <!-- 2. Définir UUID automatique pour la colonne id -->
    <changeSet id="002-auto-uuid-event" author="daniel">
        <addDefaultValue
            tableName="event"
            columnName="id"
            defaultValueComputed="gen_random_uuid()"/>
    </changeSet>
</databaseChangeLog>

```

*Lignes: 22*

---

### 📄 src\main\resources\db\changelog\changes\v1.6-setup-products.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">

    <changeSet id="v1.6-1" author="nathan">
        <preConditions onFail="MARK_RAN">
            <not><tableExists tableName="syndicat_products"/></not>
        </preConditions>
        <createTable tableName="syndicat_products">
            <column name="id" type="UUID">
                <constraints primaryKey="true" nullable="false"/>
            </column>
            <column name="syndicat_id" type="UUID"/>
            <column name="name" type="VARCHAR(255)"/>
            <column name="description" type="TEXT"/>
            <column name="price" type="DECIMAL(19,2)"/>
            <column name="sku" type="VARCHAR(100)"/>
            <column name="category" type="VARCHAR(100)"/>
            <column name="stock" type="INTEGER"/>
            <column name="image_url" type="VARCHAR(1024)"/>
            <column name="is_active" type="BOOLEAN" defaultValueBoolean="true"/>
        </createTable>
    </changeSet>
</databaseChangeLog>
```

*Lignes: 27*

---

### 📄 src\main\resources\db\changelog\changes\v1.7-create-event-images-table.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">

    <changeSet id="v1.7-2-fix-event-fk" author="gemini-agent-fix">
        <preConditions onFail="MARK_RAN">
            <tableExists tableName="event_images"/>
        </preConditions>
        <comment>Modify event_images table to correct the foreign key reference from 'events' to 'event' table</comment>
        
        <sql>
            ALTER TABLE event_images DROP CONSTRAINT IF EXISTS event_images_event_id_fkey;
            ALTER TABLE event_images DROP CONSTRAINT IF EXISTS fk_event_images_event;
        </sql>

        <addForeignKeyConstraint baseTableName="event_images"
                                 baseColumnNames="event_id"
                                 constraintName="fk_event_images_event"
                                 referencedTableName="event"
                                 referencedColumnNames="id"
                                 onDelete="CASCADE"/>
    </changeSet>

</databaseChangeLog>
```

*Lignes: 27*

---

### 📄 src\main\resources\db\changelog\changes\v1.7-setup-services.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">

    <changeSet id="v1.7-1" author="nathan">
        <preConditions onFail="MARK_RAN">
            <not><tableExists tableName="syndicat_services"/></not>
        </preConditions>
        <createTable tableName="syndicat_services">
            <column name="id" type="UUID">
                <constraints primaryKey="true" nullable="false"/>
            </column>
            <column name="syndicat_id" type="UUID">
                <constraints nullable="false"/>
            </column>
            <column name="title" type="VARCHAR(255)">
                <constraints nullable="false"/>
            </column>
            <column name="description" type="TEXT"/>
            <column name="price" type="DECIMAL(19,2)"/>
            <!-- Le type TEXT[] est spécifique à Postgres pour les List<String> -->
            <column name="features" type="TEXT[]"/>
            <column name="is_active" type="BOOLEAN" defaultValueBoolean="true"/>
        </createTable>
    </changeSet>
</databaseChangeLog>
```

*Lignes: 29*

---

### 📄 src\main\resources\db\changelog\changes\v1.8-create-user-events-table.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">

    <changeSet id="v1.8-1-create-user-events" author="gemini-agent">
        <preConditions onFail="MARK_RAN">
            <not>
                <tableExists tableName="user_events"/>
            </not>
        </preConditions>
        <createTable tableName="user_events">
            <column name="user_event_id" type="UUID" defaultValueComputed="gen_random_uuid()">
                <constraints primaryKey="true" nullable="false"/>
            </column>
            <column name="user_id" type="VARCHAR(255)">
                <constraints nullable="false"/>
            </column>
            <column name="event_id" type="UUID">
                <constraints nullable="false"/>
            </column>
            <column name="timestamp" type="TIMESTAMP"/>
        </createTable>
    </changeSet>

    <changeSet id="v1.8-2-add-fk-user-events-to-event" author="gemini-agent">
        <addForeignKeyConstraint baseTableName="user_events"
                                 baseColumnNames="event_id"
                                 constraintName="fk_user_events_event"
                                 referencedTableName="event"
                                 referencedColumnNames="id"
                                 onDelete="CASCADE"/>
    </changeSet>

</databaseChangeLog>

```

*Lignes: 38*

---

### 📄 src\main\resources\db\changelog\changes\v1.9-alter-user-events-table.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
         http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">

    <changeSet id="v1.9-1-drop-fk-user-events-event" author="gemini-agent">
        <preConditions onFail="MARK_RAN">
            <foreignKeyConstraintExists foreignKeyTableName="user_events" foreignKeyName="fk_user_events_event"/>
        </preConditions>
        <dropForeignKeyConstraint baseTableName="user_events"
                                 constraintName="fk_user_events_event"/>
    </changeSet>
    
    <changeSet id="v1.9-2-alter-user-events-event_id-type" author="gemini-agent">
        <preConditions onFail="MARK_RAN">
            <columnExists tableName="user_events" columnName="event_id"/>
        </preConditions>
        <modifyDataType tableName="user_events"
                        columnName="event_id"
                        newDataType="VARCHAR(36)"/>
    </changeSet>

</databaseChangeLog>
```

*Lignes: 25*

---

### 📄 src\main\resources\db\changelog\db.changelog-master.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
    http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.3.xsd">

    <include file="classpath:db/changelog/changes/v1.1-sync-java-entities.xml"/>
    <include file="classpath:db/changelog/changes/v1.2-create-event-table.xml"/>
    <include file="classpath:db/changelog/changes/v1.3-create-publication-vote-table.xml"/>
    <include file="classpath:db/changelog/changes/v1.4-create-vote-table.xml"/>
    <include file="classpath:db/changelog/changes/v1.5-add-branch-banner.xml"/>
    <include file="classpath:db/changelog/changes/v1.6-setup-products.xml"/>
    <include file="classpath:db/changelog/changes/v1.7-setup-services.xml"/>
    <include file="classpath:db/changelog/changes/v1.6-auto-uuid-comments.xml"/>
    <include file="classpath:db/changelog/changes/v1.7-create-event-images-table.xml"/>
    <include file="classpath:db/changelog/changes/v1.8-create-user-events-table.xml"/>
    <include file="classpath:db/changelog/changes/v1.9-alter-user-events-table.xml"/>
    <include file="classpath:db/changelog/changes/v1.10-make-branch-nullable.xml"/>
    <include file="classpath:db/changelog/changes/v1.11-create-membership-request-table.xml"/>

</databaseChangeLog>
```

*Lignes: 22*

---

### 📄 src\test\java\com\yowyob\ugate_service\CommentControllerTests.java

```java
package com.yowyob.ugate_service;

import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.model.ImageModel;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreateCommentRequest;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.media.MediaService;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Branch;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Comment;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Image;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Publication;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Syndicat;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.User;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.BranchRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.CommentRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ImageRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import org.springframework.http.client.MultipartBodyBuilder; // NEW IMPORT
import org.springframework.web.reactive.function.BodyInserters; // NEW IMPORT
import org.springframework.core.io.ClassPathResource; // NEW IMPORT
import java.io.IOException; // NEW IMPORT
import java.util.List; // NEW IMPORT

import java.time.Instant;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CommentControllerTests {

        @Autowired
        private ApplicationContext context;

        private WebTestClient webTestClient;

        @Autowired
        private PublicationRepository publicationRepository;

        @Autowired
        private CommentRepository commentRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private BranchRepository branchRepository;

        @Autowired
        private SyndicatRepository syndicatRepository;

        @Autowired
        private ImageRepository imageRepository;

        @MockBean
        private UserGatewayPort userGatewayPort;

        @MockBean
        private MediaService mediaService;

        @MockBean
        private NotificationPort notificationPort;

        private User testUser;
        private Publication testPublication;
        private Branch testBranch;
        private Syndicat testSyndicat;
        private Image testImage;

        @BeforeEach
        public void setUp() throws IOException {
                webTestClient = WebTestClient
                                .bindToApplicationContext(context)
                                .apply(SecurityMockServerConfigurers.springSecurity())
                                .configureClient()
                                .build();

                commentRepository.deleteAll().block();
                publicationRepository.deleteAll().block();
                branchRepository.deleteAll().block();
                syndicatRepository.deleteAll().block();
                userRepository.deleteAll().block();
                imageRepository.deleteAll().block();

                // Setup test data
                testUser = userRepository.save(new User(null, "Test user", "6587895423", "test@mail.co", true)).block();
                testSyndicat = syndicatRepository.save(new Syndicat(null, testUser.id(), "Test Syndicat", "description",
                                "domain", "logo", "status"))
                                .block();
                testBranch = branchRepository
                                .save(Branch.createNew(UUID.randomUUID(), testSyndicat.id(), "Test Branch", "location",
                                                "contact", "bannerUrl"))
                                .block();
                testPublication = publicationRepository
                                .save(new Publication(testBranch.id(), testUser.id(), "Test Content", 0L,
                                                Instant.now()))
                                .block();
                testImage = imageRepository.save(
                                new Image("http://example.com/mock-image.png", "Mock Image Alt Text", Instant.now()))
                                .block();

                // Mock external dependencies
                when(userGatewayPort.findById(any(UUID.class))).thenReturn(Mono.just(new ExternalUserInfo(testUser.id(),
                                "Test", "User", "test@example.com", "1234567890", null, null)));

                // NEW Mock for MediaService (used by controller)
                when(mediaService.uploadImage(any()))
                                .thenReturn(Mono.just(List.of("http://example.com/new-comment-image.png")));

                // Mock NotificationPort
                when(notificationPort.sendPublicationCommentAlert(anyString(), anyString(), anyString()))
                                .thenReturn(Mono.empty());
        }

        @Test
        public void createComment_shouldSucceed() {
                MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
                bodyBuilder.part("content", "This is a test comment");
                bodyBuilder.part("image", new ClassPathResource("test-image.png")).contentType(MediaType.IMAGE_PNG);
                bodyBuilder.part("parentId", "");

                webTestClient
                                .mutateWith(mockJwt().jwt(jwt -> jwt.subject(testUser.id().toString())))
                                .post()
                                .uri("/publications/" + testPublication.id() + "/comments")
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                                .exchange()
                                .expectStatus().isOk();

                StepVerifier.create(commentRepository.findByPublicationId(testPublication.id()))
                                .expectNextMatches(comment -> comment.content().equals("This is a test comment"))
                                .verifyComplete();
        }

        @Test
        public void getComments_shouldReturnComments() {
                Comment comment1 = this.commentRepository
                                .save(new Comment(null, testUser.id(), testPublication.id(),
                                                null,
                                                testImage.id(),
                                                "First comment", Instant.now()))
                                .block();
                Comment comment2 = this.commentRepository
                                .save(new Comment(null, testUser.id(), testPublication.id(),
                                                null,
                                                testImage.id(),
                                                "Second comment", Instant.now()))
                                .block();

                webTestClient.get()
                                .uri("/publications/{publicationId}/comments", testPublication.id().toString())
                                .exchange()
                                .expectStatus().isOk()
                                .expectBody()
                                .jsonPath("$[0].content").isEqualTo(comment1.content())
                                .jsonPath("$[0].authorId").isEqualTo(testUser.id().toString())
                                .jsonPath("$[1].content").isEqualTo(comment2.content())
                                .jsonPath("$[1].authorId").isEqualTo(testUser.id().toString());
        }
}

```

*Lignes: 184*

---

### 📄 src\test\java\com\yowyob\ugate_service\EventControllerTests.java

```java

```

*Lignes: 1*

---

### 📄 src\test\java\com\yowyob\ugate_service\FeedControllerTests.java

```java
package com.yowyob.ugate_service;

import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Event;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Publication;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.EventRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.UUID;

import com.yowyob.ugate_service.domain.model.ImageModel;
import reactor.core.publisher.Flux;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.model.MediaInfo;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.MediaPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserEventPersistencePort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class FeedControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private EventRepository eventRepository;

    @MockBean
    private UserGatewayPort userGatewayPort;
    @MockBean
    private MediaPersistencePort mediaPersistencePort;
    @MockBean
    private UserEventPersistencePort userEventPersistencePort;


    @BeforeEach
    void setUp() {
        publicationRepository.deleteAll().block();
        eventRepository.deleteAll().block();

        // Mock external dependencies for DTO enrichment
        when(userGatewayPort.findById(any(UUID.class)))
                .thenReturn(Mono.just(new ExternalUserInfo(UUID.randomUUID(), "Test", "User", "test@example.com", "123", Collections.emptyList(), Collections.emptyList())));
        when(mediaPersistencePort.getMediaByPublicationId(any(UUID.class)))
                .thenReturn(Flux.just(new MediaInfo("http://pub.img/1", "IMAGE")));
        ImageModel mockImageModel = new ImageModel();
        mockImageModel.setId(UUID.randomUUID());
        mockImageModel.setUrl("http://event.img/1");
        mockImageModel.setAltText("event image alt");
        mockImageModel.setUploadedAt(Instant.now());
        when(mediaPersistencePort.getImagesByEventId(any(UUID.class)))
                .thenReturn(Flux.just(mockImageModel));
        when(userEventPersistencePort.countByEventId(any(UUID.class)))
                .thenReturn(Mono.just(5L));
    }

    @Test
    void testGetFeed() {
        // Arrange
        UUID branchId = UUID.randomUUID();
        UUID authorId = UUID.randomUUID();

        // Create some publications and events with different timestamps
        Publication pub1 = new Publication(branchId, authorId, "Publication 1", 0L, Instant.now().minus(1, ChronoUnit.DAYS));
        Event event1 = new Event(authorId, branchId, "Event 1", "Description 1", "Location 1", LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1), Instant.now().minus(2, ChronoUnit.DAYS), null);
        Publication pub2 = new Publication(branchId, authorId, "Publication 2", 0L, Instant.now());
        Event event2 = new Event(authorId, branchId, "Event 2", "Description 2", "Location 2", LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1), Instant.now().minus(5, ChronoUnit.HOURS), null);

        publicationRepository.save(pub1).block();
        eventRepository.save(event1).block();
        publicationRepository.save(pub2).block();
        eventRepository.save(event2).block();

        // Act & Assert
        webTestClient.get()
                .uri("/api/v1/feed?page=0&size=4")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith("application/json")
                .expectBody()
                .jsonPath("$.length()").isEqualTo(4)
                .jsonPath("$[0].type").isEqualTo("publication")
                .jsonPath("$[0].data.content").isEqualTo("Publication 2") // Most recent
                .jsonPath("$[0].data.authorFullName").isEqualTo("Test User")
                .jsonPath("$[0].data.fileUrlAndType[0].url").isEqualTo("http://pub.img/1")
                .jsonPath("$[1].type").isEqualTo("event")
                .jsonPath("$[1].data.title").isEqualTo("Event 2")
                .jsonPath("$[1].data.participantCount").isEqualTo(5L)
                .jsonPath("$[1].data.imageUrls[0]").isEqualTo("http://event.img/1")
                .jsonPath("$[2].type").isEqualTo("publication")
                .jsonPath("$[2].data.content").isEqualTo("Publication 1")
                .jsonPath("$[3].type").isEqualTo("event")
                .jsonPath("$[3].data.title").isEqualTo("Event 1"); // Oldest
    }

     @Test
    void testGetFeedPagination() {
        // Arrange
        UUID branchId = UUID.randomUUID();
        UUID authorId = UUID.randomUUID();

        for (int i = 0; i < 10; i++) {
            publicationRepository.save(new Publication(branchId, authorId, "Pub " + i, 0L, Instant.now().minus(i, ChronoUnit.MINUTES))).block();
        }

        // Act & Assert
        webTestClient.get()
                .uri("/api/v1/feed?page=1&size=5")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith("application/json")
                .expectBody()
                .jsonPath("$.length()").isEqualTo(5)
                .jsonPath("$[0].type").isEqualTo("publication")
                .jsonPath("$[0].data.content").isEqualTo("Pub 5")
                .jsonPath("$[0].data.authorFullName").isEqualTo("Test User");
    }
}

```

*Lignes: 142*

---

### 📄 src\test\java\com\yowyob\ugate_service\PublicationControllerTests.java

```java
package com.yowyob.ugate_service;


import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.BranchPersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.dto.PublicationResponseDTO;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.external.client.media.MediaService;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Branch;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Image;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Publication;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.PublicationImage;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Syndicat;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ImageRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationImageRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class PublicationControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private PublicationImageRepository publicationImageRepository;

    @MockBean
    private MediaService mediaService;

    @MockBean
    private UserGatewayPort userGatewayPort;

    @MockBean
    private NotificationPort notificationPort;

    @MockBean
    private BranchPersistencePort branchPersistencePort;

    @MockBean
    private SyndicatRepository syndicatRepository;

    // --- New fields for consistent UUIDs ---
    private UUID testAuthorId;
    private UUID testBranchId;
    private UUID testSyndicateId;
    private UUID testSyndicateAdminId;
    // --- End new fields ---

    @BeforeEach
    void setUp() {
        publicationRepository.deleteAll().block();
        imageRepository.deleteAll().block();
        publicationImageRepository.deleteAll().block();

        // --- Initialize consistent UUIDs ---
        testAuthorId = UUID.randomUUID();
        testBranchId = UUID.randomUUID();
        testSyndicateId = UUID.randomUUID();
        testSyndicateAdminId = UUID.randomUUID();
        // --- End initialize consistent UUIDs ---

        when(mediaService.uploadImage(any())).thenReturn(Mono.just(List.of("http://localhost:8080/media/1")));
        when(mediaService.uploadVideo(any())).thenReturn(Mono.just(List.of()));
        when(mediaService.uploadFiles(any())).thenReturn(Mono.just(List.of()));

        // Mock for PublicationService dependencies
        when(branchPersistencePort.findById(testBranchId)).thenReturn(Mono.just(new Branch(
                testBranchId, testSyndicateId, "Mock Branch", "Mock Location", "Mock Contact", "Mock Banner", Instant.now(), Instant.now()
        )));

        when(syndicatRepository.findById(testSyndicateId)).thenReturn(Mono.just(new Syndicat(
                testSyndicateId, testSyndicateAdminId, "Mock Syndicat", "Mock Description", "Mock Domain", "Mock Logo", "Mock Status"
        )));

        // Mock for author and admin user info
        when(userGatewayPort.findById(testAuthorId)).thenReturn(Mono.just(new ExternalUserInfo(testAuthorId,
                "Test", "Author", "author@example.com", "1234567890", null, null)));
        when(userGatewayPort.findById(testSyndicateAdminId)).thenReturn(Mono.just(new ExternalUserInfo(testSyndicateAdminId,
                "Test", "Admin", "admin@example.com", "0987654321", null, null)));


        when(notificationPort.sendAdminAlertWhenNewPublication(anyList(), anyString(), anyString()))
                .thenReturn(Mono.empty());
    }

    @Test
    void testCreatePublicationWithImage() {
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("content", "Test publication content");
        bodyBuilder.part("authorId", testAuthorId.toString()); // Use consistent ID
        bodyBuilder.part("branchId", testBranchId.toString()); // Use consistent ID
        bodyBuilder.part("images", new ClassPathResource("test-image.png"))
                .contentType(org.springframework.http.MediaType.IMAGE_PNG);

        webTestClient.post()
                .uri("/publications")
                .contentType(org.springframework.http.MediaType.MULTIPART_FORM_DATA)
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
        bodyBuilder.part("authorId", testAuthorId.toString()); // Use consistent ID
        bodyBuilder.part("branchId", testBranchId.toString()); // Use consistent ID

        webTestClient.post()
                .uri("/publications")
                .contentType(org.springframework.http.MediaType.MULTIPART_FORM_DATA)
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
        bodyBuilder.part("authorId", testAuthorId.toString()); // Use consistent ID
        bodyBuilder.part("branchId", testBranchId.toString()); // Use consistent ID

        webTestClient.post()
                .uri("/publications")
                .contentType(org.springframework.http.MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void testGetPublicationsByBranch() {
        // Arrange
        UUID branchId = UUID.randomUUID();
        UUID authorId = UUID.randomUUID();
        String authorFirstName = "John";
        String authorLastName = "Doe";

        Publication publication = new Publication(
                branchId,
                authorId,
                "Content for branch test",
                5L,
                Instant.now());
        Publication savedPublication = publicationRepository.save(publication).block();
        assertNotNull(savedPublication);

        Image image = new Image("http://image.url/test.png", "test image", Instant.now());
        Image savedImage = imageRepository.save(image).block();
        assertNotNull(savedImage);

        PublicationImage publicationImage = new PublicationImage(savedPublication.id(), savedImage.id(), Instant.now(),
                Instant.now());
        publicationImageRepository.save(publicationImage).block();

        ExternalUserInfo authorInfo = new ExternalUserInfo(authorId, authorFirstName, authorLastName, "email@test.com",
                "123456789", Collections.emptyList(), Collections.emptyList());
        when(userGatewayPort.findById(authorId)).thenReturn(Mono.just(authorInfo));

        // Act & Assert
        webTestClient.get()
                .uri("/publications/branch/{branchId}", branchId)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .expectBodyList(PublicationResponseDTO.class)
                .hasSize(1)
                .value(list -> {
                    PublicationResponseDTO dto = list.get(0);
                    assertEquals(savedPublication.id(), dto.getId());
                    assertEquals(savedPublication.branchId(), dto.getBranchId());
                    assertEquals(savedPublication.content(), dto.getContent());
                    assertEquals(authorFirstName + " " + authorLastName, dto.getAuthorFullName());
                    assertEquals(savedPublication.nLikes(), dto.getNLikes());

                    List<com.yowyob.ugate_service.domain.model.MediaInfo> mediaInfos = dto.getFileUrlAndType();
                    assertNotNull(mediaInfos);
                    assertEquals(1, mediaInfos.size());
                    assertEquals(savedImage.url(), mediaInfos.get(0).getUrl());
                    assertEquals("TEST IMAGE", mediaInfos.get(0).getType()); // Changed to "TEST IMAGE"
                });
    }
}
```

*Lignes: 229*

---

### 📄 src\test\java\com\yowyob\ugate_service\PublicationVoteControllerTests.java

```java
package com.yowyob.ugate_service;

import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CastVoteRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreatePublicationVoteRequest;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.PublicationVoteWithResultsDTO;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.VoteResultDTO;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.PublicationVote;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Vote;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationVoteRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class PublicationVoteControllerTests {

        private WebTestClient webTestClient;

        @Autowired
        private ApplicationContext context;

        @Autowired
        private PublicationVoteRepository publicationVoteRepository;

        @Autowired
        private VoteRepository voteRepository;

        @BeforeEach
        void setUp() {
                this.webTestClient = WebTestClient
                                .bindToApplicationContext(this.context)
                                .apply(springSecurity())
                                .configureClient()
                                .build();

                publicationVoteRepository.deleteAll().block();
                voteRepository.deleteAll().block();
        }

        @Test
        void testCreateVote_Success() {
                CreatePublicationVoteRequest request = new CreatePublicationVoteRequest();
                request.setTitle("Best Framework");
                request.setDescription("Vote for the best framework");
                request.setClosingAt(Instant.now().plus(1, ChronoUnit.DAYS));
                request.setType("SINGLE_CHOICE");
                request.setBranchId(UUID.randomUUID());

                webTestClient.post()
                                .uri("/publication-votes")
                                .bodyValue(request)
                                .exchange()
                                .expectStatus().isCreated();

                PublicationVote vote = publicationVoteRepository.findAll().blockFirst();
                assertNotNull(vote);
                assertEquals("Best Framework", vote.title());
                assertEquals("SINGLE_CHOICE", vote.type());
        }

        @Test
        void testCastVote_Success() {
                PublicationVote poll = new PublicationVote(null, UUID.randomUUID(), "Favorite Color", "desc",
                                Instant.now().plus(5, ChronoUnit.DAYS), "SINGLE");
                PublicationVote savedPoll = publicationVoteRepository.save(poll).block();
                assertNotNull(savedPoll);

                UUID testUserId = UUID.randomUUID();
                CastVoteRequest request = new CastVoteRequest();
                request.setChoiceLabel("Blue");

                // 2. Perform authenticated POST request
                webTestClient
                                .mutateWith(mockJwt().jwt(jwt -> jwt.subject(testUserId.toString())))
                                .post()
                                .uri("/publication-votes/{publicationVoteId}/cast", savedPoll.id())
                                .bodyValue(request)
                                .exchange()
                                .expectStatus().isOk();

                // 3. Verify the vote was saved
                Vote vote = voteRepository.findAll().blockFirst();
                assertNotNull(vote);
                assertEquals(testUserId, vote.userId());
                assertEquals(savedPoll.id(), vote.publicationVoteId());
                assertEquals("Blue", vote.label());
        }

        @Test
        void testCastVote_Error_PollClosed() {
                // 1. Create a poll that is already closed
                PublicationVote poll = new PublicationVote(null, UUID.randomUUID(), "Past Poll", "desc",
                                Instant.now().minus(1, ChronoUnit.HOURS),
                                "SINGLE");
                PublicationVote savedPoll = publicationVoteRepository.save(poll).block();
                assertNotNull(savedPoll);

                UUID testUserId = UUID.randomUUID();
                CastVoteRequest request = new CastVoteRequest();
                request.setChoiceLabel("Any");

                // 2. Perform authenticated POST request and expect an error
                webTestClient
                                .mutateWith(mockJwt().jwt(jwt -> jwt.subject(testUserId.toString())))
                                .post()
                                .uri("/publication-votes/{publicationVoteId}/cast", savedPoll.id())
                                .bodyValue(request)
                                .exchange()
                                .expectStatus().is4xxClientError(); // Or a specific 4xx error if you have exception
                                                                    // handling
        }

        @Test
        void testGetVoteResults_Success() {
                // 1. Create a poll
                PublicationVote poll = new PublicationVote(null, UUID.randomUUID(), "Favorite Color", "desc",
                                Instant.now().plus(5, ChronoUnit.DAYS), "SINGLE");
                PublicationVote savedPoll = publicationVoteRepository.save(poll).block();
                assertNotNull(savedPoll);

                // 2. Create votes
                UUID user1 = UUID.randomUUID();
                UUID user2 = UUID.randomUUID();
                UUID user3 = UUID.randomUUID(); // This is the user who will be making the request
                voteRepository.save(new Vote(null, user1, savedPoll.id(), "Blue")).block();
                voteRepository.save(new Vote(null, user2, savedPoll.id(), "Red")).block();
                voteRepository.save(new Vote(null, user3, savedPoll.id(), "Blue")).block();

                // 3. Perform authenticated GET request
                webTestClient
                                .mutateWith(mockJwt().jwt(jwt -> jwt.subject(user3.toString())))
                                .get()
                                .uri("/publication-votes/{publicationVoteId}/results", savedPoll.id())
                                .exchange()
                                .expectStatus().isOk()
                                .expectBody(PublicationVoteWithResultsDTO.class)
                                .value(dto -> {
                                        assertEquals(3, dto.getTotalVotes());
                                        assertEquals(true, dto.isHasVoted());
                                        assertEquals(2, dto.getResults().size());
                                        VoteResultDTO blueResult = dto.getResults().stream()
                                                        .filter(r -> r.getChoiceLabel().equals("Blue"))
                                                        .findFirst().orElseThrow();
                                        assertEquals(2, blueResult.getCount());
                                        VoteResultDTO redResult = dto.getResults().stream()
                                                        .filter(r -> r.getChoiceLabel().equals("Red"))
                                                        .findFirst().orElseThrow();
                                        assertEquals(1, redResult.getCount());
                                });
        }

        @Test
        void testGetVoteResults_UserHasNotVoted() {
                // 1. Create a poll
                PublicationVote poll = new PublicationVote(null, UUID.randomUUID(), "Favorite Animal", "desc",
                                Instant.now().plus(5, ChronoUnit.DAYS), "SINGLE");
                PublicationVote savedPoll = publicationVoteRepository.save(poll).block();
                assertNotNull(savedPoll);

                // 2. Create votes from other users
                voteRepository.save(new Vote(null, UUID.randomUUID(), savedPoll.id(), "Dog")).block();

                UUID nonVoterId = UUID.randomUUID(); // This user has not voted

                // 3. Perform authenticated GET request
                webTestClient
                                .mutateWith(mockJwt().jwt(jwt -> jwt.subject(nonVoterId.toString())))
                                .get()
                                .uri("/publication-votes/{publicationVoteId}/results", savedPoll.id())
                                .exchange()
                                .expectStatus().isOk()
                                .expectBody(PublicationVoteWithResultsDTO.class)
                                .value(dto -> {
                                        assertEquals(1, dto.getTotalVotes());
                                        assertEquals(false, dto.isHasVoted());
                                        assertEquals(1, dto.getResults().size());
                                });
        }
}

```

*Lignes: 199*

---

### 📄 src\test\java\com\yowyob\ugate_service\ReactionControllerTests.java

```java
package com.yowyob.ugate_service;

import com.yowyob.ugate_service.domain.enumeration.ReactionTypeEnum;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.request.CreateReactionRequest;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.entity.Publication;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.PublicationRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.ReactionRepository;
import com.yowyob.ugate_service.domain.model.ExternalUserInfo;
import com.yowyob.ugate_service.domain.ports.out.gateway.UserGatewayPort;
import com.yowyob.ugate_service.domain.ports.out.notification.NotificationPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class ReactionControllerTests {

        @Autowired
        private WebTestClient webTestClient;

        @Autowired
        private PublicationRepository publicationRepository;

        @Autowired
        private ReactionRepository reactionRepository;

        @MockBean
        private NotificationPort notificationPort;

        @MockBean
        private UserGatewayPort userGatewayPort;

        private UUID mockPublicationAuthorId;
        private UUID mockReactorUserId; // New field

        @BeforeEach
        void setUp() {
                publicationRepository.deleteAll().block();
                reactionRepository.deleteAll().block();

                mockPublicationAuthorId = UUID.randomUUID();
                mockReactorUserId = UUID.randomUUID(); // Initialize the reactor's user ID

                // Mock external dependencies for ReactionService
                // Mock for the publication author's ID
                when(userGatewayPort.findById(eq(mockPublicationAuthorId)))
                                .thenReturn(Mono.just(new ExternalUserInfo(mockPublicationAuthorId,
                                                "Publication", "Author", "pubauthor@example.com", "0987654321", null,
                                                null)));
                // Mock for the reactor's ID
                when(userGatewayPort.findById(eq(mockReactorUserId)))
                                .thenReturn(Mono.just(new ExternalUserInfo(mockReactorUserId,
                                                "Reactor", "User", "reactor@example.com", "1122334455", null, null)));

                when(notificationPort.sendPublicationReactAlert(anyString(), anyString(), anyString()))
                                .thenReturn(Mono.empty());
        }

        @Test
        void testAddReactionToPublication() {
                // Arrange: Create and save a publication
                // Use the fixed mockPublicationAuthorId for consistency
                Publication publication = new Publication(UUID.randomUUID(), mockPublicationAuthorId, "Test content",
                                0L, Instant.now());
                Publication savedPublication = publicationRepository.save(publication).block();
                assertNotNull(savedPublication);
                assertEquals(0L, savedPublication.nLikes());

                // Prepare the reaction request
                UUID userId = mockReactorUserId; // Use the fixed reactor user ID
                ReactionTypeEnum reactionType = ReactionTypeEnum.LIKE;
                CreateReactionRequest request = new CreateReactionRequest();
                request.setUserId(userId);
                request.setReactionType(reactionType);

                // Act: Call the endpoint to add a reaction
                webTestClient.post()
                                .uri("/publications/{publicationId}/reactions", savedPublication.id())
                                .bodyValue(request)
                                .exchange()
                                .expectStatus().isOk();

                // Assert: Verify the reaction was created
                StepVerifier.create(reactionRepository.findAll())
                                .assertNext(reaction -> {
                                        assertNotNull(reaction.id());
                                        assertEquals(savedPublication.id(), reaction.publicationId());
                                        assertEquals(userId, reaction.userId());
                                        assertEquals(reactionType, reaction.type());
                                })
                                .verifyComplete();

                // Assert: Verify the publication's like count was incremented
                StepVerifier.create(publicationRepository.findById(savedPublication.id()))
                                .assertNext(updatedPublication -> {
                                        assertEquals(1L, updatedPublication.nLikes());
                                })
                                .verifyComplete();
        }
}

```

*Lignes: 123*

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
spring.application.external.media-service-url=http://localhost:8080

spring.liquibase.enabled=false

spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:schema.sql

```

*Lignes: 10*

---

### 📄 src\test\resources\schema.sql

```sql
CREATE DOMAIN IF NOT EXISTS reaction_type_enum AS VARCHAR(255);
CREATE DOMAIN IF NOT EXISTS role_type_enum AS VARCHAR(255);

CREATE TABLE users (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    name VARCHAR(255),
    phone_number VARCHAR(50),
    email_address VARCHAR(255)
);

CREATE TABLE syndicats (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    organization_id UUID,
    creator_id UUID,
    is_approved BOOLEAN,
    name VARCHAR(255),
    description VARCHAR(255),
    domain VARCHAR(255),
    type VARCHAR(255),
    charte_url VARCHAR(255),
    logo_url VARCHAR(255),
    status_url VARCHAR(255),
    members_list_url VARCHAR(255),
    commitment_certificate_url VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    is_active BOOLEAN
);

CREATE TABLE branches (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    syndicat_id UUID,
    name VARCHAR(255),
    location VARCHAR(255),
    contact VARCHAR(255),
    banner_url VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);


CREATE TABLE publications (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    branch_id UUID,
    author_id UUID,
    content VARCHAR(255),
    n_likes BIGINT,
    created_at TIMESTAMP
);

CREATE TABLE images (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    url VARCHAR(255),
    alt_text VARCHAR(255),
    uploaded_at TIMESTAMP
);

CREATE TABLE publication_images (
    publication_id UUID,
    image_id UUID,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    PRIMARY KEY (publication_id, image_id)
);

CREATE TABLE comments (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    author_id UUID,
    publication_id UUID,
    parent_id UUID,
    image_id UUID,
    content VARCHAR(255),
    created_at TIMESTAMP
);

CREATE TABLE reactions (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    publication_id UUID,
    user_id UUID,
    type VARCHAR(255),
    reacted_at TIMESTAMP
);

CREATE TABLE event (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    creator_id UUID,
    branch_id UUID,
    title VARCHAR(255),
    description TEXT,
    location VARCHAR(255),
    event_date DATE,
    start_time TIME,
    end_time TIME,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE event_images (
    event_id UUID,
    image_id UUID,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    PRIMARY KEY (event_id, image_id)
);

CREATE TABLE user_events (
    user_event_id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    user_id VARCHAR(255),
    event_id VARCHAR(255),
    timestamp TIMESTAMP
);

CREATE TABLE publication_vote (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    branch_id UUID,
    title VARCHAR(255),
    description VARCHAR(255),
    closing_at TIMESTAMP,
    type VARCHAR(255)
);

CREATE TABLE vote (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    user_id UUID,
    publication_vote_id UUID,
    label VARCHAR(255)
);

CREATE TABLE profiles (
    id UUID PRIMARY KEY,
    user_id UUID,
    profile_image_url VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    birth_date TIMESTAMP,
    nationality VARCHAR(255),
    gender VARCHAR(255),
    language VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
```

*Lignes: 141*

---

## Statistiques

- **Total de fichiers analysés:** 230
- **Total de lignes de code:** 10 475
- **Moyenne de lignes par fichier:** 46

### Répartition par type de fichier

- **.java:** 209 fichiers
- **.xml:** 15 fichiers
- **.properties:** 2 fichiers
- **.yml:** 1 fichier
- **.yaml:** 1 fichier
- **.md:** 1 fichier
- **.sql:** 1 fichier

---

*Contexte généré automatiquement pour analyse par IA*
