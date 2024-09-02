package com.artisanat_backend.configs;


import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    Cloudinary cloudinary() {
        final Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dniddbder");
        config.put("api_key", "684125745129812");
        config.put("api_secret", "CJLZeQF8yrS9ZtDkmEnJ39esH44");
        return new Cloudinary(config);
    }
}
