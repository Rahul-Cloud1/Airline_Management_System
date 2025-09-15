package com.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return  builder.routes()
            .route("flightservice", r -> r.path("/flights/**")
                .uri("lb://flightservice"))
            .route("passengerservice", r -> r.path("/passengers/**")
                .uri("lb://passengerservice"))
            .route("bookingservice", r -> r.path("/bookings/**")
                .uri("lb://bookingservice"))
            .build();
    }
}
