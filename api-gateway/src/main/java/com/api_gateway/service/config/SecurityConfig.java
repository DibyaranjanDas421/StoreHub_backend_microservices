package com.api_gateway.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        return serverHttpSecurity
                .csrf(csrf -> csrf.disable())  // Disable CSRF for simplicity
                .authorizeExchange(exchange -> exchange
                    .pathMatchers("/eureka/**").permitAll()  // Allow Eureka access
                    .anyExchange().authenticated()            // All other routes require authentication
                )
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt)  // Use JWT for OAuth2
                .build();
    }
}