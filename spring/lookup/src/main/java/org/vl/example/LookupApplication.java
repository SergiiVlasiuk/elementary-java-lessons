package org.vl.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("org.vl.example.lookup")
@SpringBootApplication
public class LookupApplication {

    public static void main(String[] args) {
        SpringApplication.run(LookupApplication.class, args);
    }
}
