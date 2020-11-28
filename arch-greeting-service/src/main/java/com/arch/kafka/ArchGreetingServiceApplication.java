package com.arch.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@SpringBootApplication
public class ArchGreetingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchGreetingServiceApplication.class, args);
    }

}

