package com.example.prewave.common.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecretTokenAuthenticationFilter extends OncePerRequestFilter {

    private static final String API_KEY_HEADER = "x-api-key";

    private final String internalApiSecretKey;

    public SecretTokenAuthenticationFilter(@Value("${internet.api.secret.key}") String internalApiSecretKey) {
        this.internalApiSecretKey = internalApiSecretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader(API_KEY_HEADER);
        if (authHeader == null || !authHeader.equals(internalApiSecretKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Invalid API token");
            return;
        }
        filterChain.doFilter(request, response);
    }
}