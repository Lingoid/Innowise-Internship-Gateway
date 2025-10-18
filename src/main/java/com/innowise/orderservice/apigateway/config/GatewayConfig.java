package com.innowise.orderservice.apigateway.config;

import com.innowise.orderservice.apigateway.util.JwtAuthFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("auth_service_route", p -> p
                        .path("/auth/**")
                        .uri("http://localhost:8080")
                )
                .route("user_service_route_users", p -> p
                        .path("/users/**")
                        .uri("http://localhost:8081")
                )
                .route("user_service_route_cards", p -> p
                        .path("/cards/**")
                        .uri("http://localhost:8081"))
                .build();
    }

    @Bean
    public GlobalFilter customFilter(WebClient.Builder builder) {
        return new JwtAuthFilter(builder);
    }

}
