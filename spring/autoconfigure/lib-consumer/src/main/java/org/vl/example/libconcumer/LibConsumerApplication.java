package org.vl.example.libconcumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class LibConsumerApplication {

    public static void main(String[] args) {
        log.info("start loading of LibConsumerApplication");
        SpringApplication.run(LibConsumerApplication.class, args);
        log.info("LibConsumerApplication is loaded");
    }
}
