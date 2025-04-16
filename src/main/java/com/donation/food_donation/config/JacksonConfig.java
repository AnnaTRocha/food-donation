package com.donation.food_donation.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .failOnEmptyBeans(false)
                .failOnUnknownProperties(false)
                .serializationInclusion(JsonInclude.Include.NON_NULL);
    }
}