package com.anderson.app.geoapp;

import com.anderson.app.geoapp.service.FeatureService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GeoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeoAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(FeatureService featureService) {
        return args -> featureService.loadFeatures();
    }
}
