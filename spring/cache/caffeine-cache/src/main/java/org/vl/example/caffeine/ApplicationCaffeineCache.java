package org.vl.example.caffeine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class ApplicationCaffeineCache {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationCaffeineCache.class, args);
    }

}
