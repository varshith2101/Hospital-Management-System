package com.hms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Get allowed origins from environment variable
        String allowedOriginsEnv = System.getenv("ALLOWED_ORIGINS");

        if (allowedOriginsEnv != null && !allowedOriginsEnv.isEmpty()) {
            // Split by comma and add each origin
            List<String> origins = Arrays.asList(allowedOriginsEnv.split(","));
            for (String origin : origins) {
                config.addAllowedOrigin(origin.trim());
            }
        } else {
            // Default for local development
            config.addAllowedOrigin("http://localhost:3000");
        }

        // Allow all methods
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        config.addAllowedMethod("OPTIONS");

        // Allow all headers
        config.addAllowedHeader("*");

        // Allow credentials
        config.setAllowCredentials(true);

        // Apply to all paths
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
