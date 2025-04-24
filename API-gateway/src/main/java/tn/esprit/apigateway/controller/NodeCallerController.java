package tn.esprit.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/call-node")
public class NodeCallerController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping
    public Mono<String> callNodeService() {
        return webClientBuilder.build()
                .get()
                .uri("http://MICROSERVICE5/managers/all") // Node.js microservice endpoint
                .retrieve()
                .bodyToMono(String.class);
    }
}