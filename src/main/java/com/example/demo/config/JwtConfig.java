package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    public JwtTokenProvider jwtTokenProvider() {

        // 🔐 Same values used in portal / default
        String secret = "my-secret-key";
        long validity = 86400000; // 1 day (24 hours)

        return new JwtTokenProvider(secret, validity);
    }
}
