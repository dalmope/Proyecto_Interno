package com.dalmope.persona.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dalmope.persona.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo getApiInfo() {
        return new ApiInfo(
                "Persona API",
                "API for the Persona service",
                "VERSION_1",
                "TERMS OF SERVICE URL",
                new Contact("Dalmope", ":3", "david.mora@pragma.com.co"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
}

