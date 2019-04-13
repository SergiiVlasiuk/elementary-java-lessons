package org.vl.example.defaultcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApplicationDefaultCache {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationDefaultCache.class, args);
    }

}
