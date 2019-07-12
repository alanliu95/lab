package com.alan.eventservice.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public ObjectMapper jackson(){
        return new ObjectMapper();
    }
}
