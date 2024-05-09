package com.riwi.beautySalon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //CONFIG SPRING SECURITY
public class SecurityConfig {
    // PUBLIC ROUTES
    private final String[] PUBLIC_RESOURCES = {"/services/public/get","/auth/**"};


    // CHANCHING DE DEFAULT METHOD WITH A BEAN
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
            .csrf(csrf -> csrf.disable()) // DISABLE CSRF FOR MONOLITYCS APPS
            .authorizeHttpRequests(authRequest -> authRequest
                .requestMatchers(PUBLIC_RESOURCES).permitAll()
                .anyRequest().authenticated()
            ).build();
    }
}
