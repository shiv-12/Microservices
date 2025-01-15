package com.in28minutes.microservices.api_gateway;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/get")
                        .filters(f -> f
                                .addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("RequestParams", "RequestVal"))
                        .uri("http://httpbin.org:80")
                )
                .route(p -> p.path("/currency-exchange/**")  // Request Starting From
                        .uri("lb://CURRENCY-EXCHANGE")                              // Eureka Application Name
                )
                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://CURRENCY-CONVERSION")
                )
                .route(p -> p.path("/currency-conversion-new/**") // Redirection Logic
                        .filters(f -> f
                                .rewritePath("/currency-conversion-new/(?<segment>.*)",
                                        "/currency-conversion/${segment}")
                        )
                        .uri("lb://CURRENCY-CONVERSION")
                )
                .build();
    }
}
