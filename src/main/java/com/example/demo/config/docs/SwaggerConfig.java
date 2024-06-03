package com.example.demo.config.docs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "JWT API 명세",
                description = "TestApp API 명세",
                version = "v1"),
        servers = {
        @Server(url = "127.0.0.1:8080", description = "Local"),
        @Server(url = "49.50.167.39", description = "Server")
}
)
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi OpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("TestApp API v1")
                .pathsToMatch(paths)
                .build();
    }
}