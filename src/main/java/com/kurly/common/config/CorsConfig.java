package com.kurly.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedMethods(
                        "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE"
                )
                .allowedOrigins(
                        "http://localhost:3000",
                        "https://kurly-lover.web.app"
                );
    }
}
