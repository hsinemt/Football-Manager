package tn.esprit.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("matches-service", r -> r.path("/matches/**")
                        .uri("lb://microservice"))
                .route("player-service", r -> r.path("/players/**")
                        .uri("lb://player-service"))
                .route("competition-service", r -> r.path("/api/competitions/**")
                        .uri("lb://competition-service"))
                .route("football-service", r -> r.path("/users/**")
                        .uri("lb://Football"))
                .route("match", r -> r.path("/equipe/**")
                        .uri("lb://MATCH"))
                .route("microservice5-nodejs", r -> r.path("/managers/**")
                        .uri("lb://MICROSERVICE5"))
                .build();
    }

}
