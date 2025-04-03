package com.fatihkarakus.user_service.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Microservice API").version("1.0").description("Microservice API Documentation"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }

    @Bean
    public GroupedOpenApi userServiceApi() {
        return GroupedOpenApi.builder()
                .group("User Service")
                .packagesToScan("com.fatihkarakus.user_service")
                .build();
    }

    @Bean
    public GroupedOpenApi contentServiceApi() {
        return GroupedOpenApi.builder()
                .group("Content Service")
                .packagesToScan("com.fatihkarakus.content_service")
                .build();
    }

    @Bean
    public GroupedOpenApi commentServiceApi() {
        return GroupedOpenApi.builder()
                .group("Comment Service")
                .packagesToScan("com.fatihkarakus.comment_service")
                .build();
    }

    @Bean
    public GroupedOpenApi analyticsServiceApi() {
        return GroupedOpenApi.builder()
                .group("Analytics Service")
                .packagesToScan("com.fatihkarakus.analytics_service")
                .build();
    }
}
