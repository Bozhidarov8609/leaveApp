package com.example.leaveapp.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ApplicationCloudConfiguration {

    @Value("bozhidar")
    private String cloudName;
    @Value("987196883221311")
    private String apiKey;
    @Value("${CLOUDINARY_SECRET}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(new HashMap<String, Object>(){{
            put("cloud_name", cloudName);
            put("api_key", apiKey);
            put("api_secret", apiSecret);
        }});
    }
}
