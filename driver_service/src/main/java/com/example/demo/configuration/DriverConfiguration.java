package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DriverConfiguration {
    @Bean
    public RestTemplate restTemplateBean() {
        return new RestTemplate();
    }
}
