package com.tushar.springBoot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
    public config(){
        System.out.println("config bean created");
    }

    @Bean
    public db dbConfig(){
        db d = new db("mongodb");
        return d;
    }

}
