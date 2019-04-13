package org.vl.example.guavacache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApplicationGuavaCache {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationGuavaCache.class, args);
    }

}
