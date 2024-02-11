package com.kp.apigatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customeRouteLocator(RouteLocatorBuilder builder) {
        RouteLocator routes = builder.routes()
                .route("/customer-service/**", p -> p
                        .path("/api/v1/**")
                        .filters(f -> f
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(config -> config.setName("customerServiceBreaker"))
                        )
                        .uri("lb://CUSTOMER-SERVICE"))
                .route("/product-service/**", p -> p
                        .path("/api/v1/**")
                        .uri("lb://PRODUCT-SERVICE"))
                .build();
        routes.getRoutes().log();
        return routes;
    }
}