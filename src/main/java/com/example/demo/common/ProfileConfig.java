package com.example.demo.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfig {

    @Profile("dev")
    @Bean
    public String getDevConfig() {
        return "this is dev";
    }

    @Profile("prod")
    @Bean
    public String getProdConfig() {
        return "this is prod";
    }

}
