package org.vl.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("org.vl.example.another")
@SpringBootApplication
public class AnotherApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnotherApplication.class, args);
    }
}
