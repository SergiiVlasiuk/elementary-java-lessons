package org.vl.example.jks.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JksClientApplication {
    static {
        System.setProperty("javax.net.debug", "all");
        System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
        System.setProperty("https.protocols", "TLSv1.2");

        System.setProperty("javax.net.ssl.trustStore", "/home/sergii/Development/projects/my/elementary-java-lessons" +
                "/spring/jks-example/jks-example-client/src/main/resources/MyClient.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");

        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                (hostname, sslSession) -> {
                    return hostname.equals("localhost");
                });
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(JksClientApplication.class, args);
    }
}
