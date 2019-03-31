package org.vl.example.another;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "al.another", name = "settings.enabled")
@ConditionalOnWebApplication
@ComponentScan(basePackages = "org.vl.example.another")
@Slf4j
public class AnotherAutoConfiguration {

    @Bean
    AnotherAutoConfiguration anotherAutoConfiguration() {
        log.info("Auto configuration for AnotherAutoConfiguration is loaded...");
        return new AnotherAutoConfiguration();
    }
}
