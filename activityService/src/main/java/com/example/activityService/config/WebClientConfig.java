package com.example.activityService.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

//    @Bean
//    @LoadBalanced
//    public WebClient.Builder webClientBuilder() {
//        return WebClient.builder();
//    }
//    @Bean
//    public WebClient userServiceWebClient(WebClient.Builder builder) {
//        return builder.baseUrl("http://localhost:9089").build();
//    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
