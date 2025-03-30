package com.example.VkBot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        // Создаем ObjectMapper с поддержкой Kotlin
        return new ObjectMapper().registerModule(new KotlinModule());
    }
}