package com.onehundredthousand.become_professional.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public Docket api() {
        ArrayList<Response> responses = new ArrayList<>();
        responses.add(new ResponseBuilder().code("500").description("500 message").build());
        responses.add( new ResponseBuilder().code("403")
                .description("Forbidden!!!!!").build());
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.onehundredthousand.become_professional.controllers"))
                .paths(PathSelectors.ant("/api/v1/progressbar/**"))
                .build()
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, responses);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey(AUTHORIZATION_HEADER, "JWT", "header");
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference(AUTHORIZATION_HEADER, authorizationScopes));
    }
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "My REST API",
                "This api will help you understand the methods provided for working with Progress Bar app",
                "0.0.1",
                "Term of service",
                new Contact("Pavel", "www.example.com", "kosenkov.paul@gmail.com"),
                "License of APi", "API license URL", Collections.emptyList()
        );
    }
}
