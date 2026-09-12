package com.auditservice.audit_notification_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Event-Driven Audit & Notification Service API")
                        .version("1.0")
                        .description("Decoupled asynchronous auditing and automated email notification service built with Spring Boot 3"));
    }
}