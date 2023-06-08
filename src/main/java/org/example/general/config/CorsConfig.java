package org.example.general.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // Class used to configure CORS settings
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Allowed resource URLs
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
                .allowCredentials(true);
    }
}
