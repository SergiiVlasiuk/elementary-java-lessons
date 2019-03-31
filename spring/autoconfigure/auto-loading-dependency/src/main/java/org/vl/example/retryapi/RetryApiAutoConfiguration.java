package org.vl.example.retryapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "al.retryapi", name = "settings.enabled")
@ComponentScan(basePackages = "org.vl.example.retryapi")
@Slf4j
public class RetryApiAutoConfiguration {

    @Bean
    RetryApiAutoConfiguration retryApiAutoConfiguration() {
        log.info("Auto configuration for RetryApiAutoConfiguration is loaded...");
        return new RetryApiAutoConfiguration();
    }
}
