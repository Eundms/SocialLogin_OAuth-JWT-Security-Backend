package com.basic.template.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

   @Bean
   public CorsFilter corsFilter() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowCredentials(true);
      config.setAllowedOrigins(List.of("http://localhost:3000"));
      config.addAllowedHeader("*");  // Access-Control-Request-Headers  
      config.addAllowedMethod("*"); // Access-Control-Request-Method
      
      source.registerCorsConfiguration("/**", config);
      return new CorsFilter(source);
   }
}
