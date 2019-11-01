package com.project.fileparser.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer{
    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedMethods("*");
    }*/

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
            .allowedOrigins(
                "http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD").allowedHeaders("Content-Type", "Origin", "Authorization", "Accept")
            .allowCredentials(true)
        ;
    }
}
