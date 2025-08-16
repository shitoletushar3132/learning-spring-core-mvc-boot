package com.tushar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tushar.services.Password;

@Configuration
public class Config {

    public Config() {
        System.out.println("Config bean created");
    }

    @Bean
    public Password createPass() {
        return new Password("sha-");
    }

}
