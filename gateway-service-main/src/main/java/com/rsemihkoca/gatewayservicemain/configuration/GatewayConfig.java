package com.rsemihkoca.gatewayservicemain.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service-main", r -> r
                        .path("/api/v1/user/**")
                        .uri("lb://USER-SERVICE-MAIN")) //.uri("http://localhost:8081"))
                .route("user-service", r -> r
                        .path("/api/v1/users/**")
                        .uri("lb://USER-SERVICE-MAIN")) //.uri("http://localhost:8081"))
                .route("fallback", r -> r
                        .path("/**")
                        .uri("forward:/error"))
                .build();
    }
}
