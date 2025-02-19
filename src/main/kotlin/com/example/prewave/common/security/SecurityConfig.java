package com.example.prewave.common.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    private String[] ignoredUrls;

    private final SecretTokenAuthenticationFilter secretTokenAuthenticationFilter;


    public SecurityConfig(@Value("${auth.ignored.urls}") String[] ignoredUrls, SecretTokenAuthenticationFilter secretTokenAuthenticationFilter) {
        this.ignoredUrls = ignoredUrls;
        this.secretTokenAuthenticationFilter = secretTokenAuthenticationFilter;
    }

    @Bean
    @Order(0)
    public SecurityFilterChain ignoredUrlsFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(ignoredUrls)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/**")
                .sessionManagement(
                        management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(secretTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .build();
    }
}

