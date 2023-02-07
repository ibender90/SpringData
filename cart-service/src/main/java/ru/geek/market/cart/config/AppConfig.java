package ru.geek.market.cart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:endpoint.properties")
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(){ //бин для передачи запросов по rest
        return new RestTemplate();
    }
}
