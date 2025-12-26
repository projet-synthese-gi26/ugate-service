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