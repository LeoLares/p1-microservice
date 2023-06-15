package com.microservice.items;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTempleteConfig {
    @Bean("clientRest")
    public RestTemplate registerRestemplete(){
        return new RestTemplate();
    }
}
