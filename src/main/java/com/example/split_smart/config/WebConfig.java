package com.example.split_smart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(@NonNull CorsRegistry registry) {
    registry.addMapping("/**") // You can restrict the path and set it to the specific path you want to enable
                               // CORS for.
        .allowedOrigins("*") // You can restrict origins for better security.
        .allowedMethods("GET", "POST", "OPTIONS")
        .allowedHeaders("*");
  }
}