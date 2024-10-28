package com.programmingtechie.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient; // Import the correct HttpClient

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced  // Ensure that the load balancer is active
    public WebClient.Builder webClient() {
        HttpClient httpClient = HttpClient.create(); // Create Reactor Netty HttpClient
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient)); // Use ReactorClientHttpConnector
    }
}