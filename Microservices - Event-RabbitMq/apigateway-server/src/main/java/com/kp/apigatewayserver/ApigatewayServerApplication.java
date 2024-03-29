package com.kp.apigatewayserver;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import reactor.netty.http.client.HttpClient;

@SpringBootApplication
public class ApigatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApigatewayServerApplication.class, args);
    }



}