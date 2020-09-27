package com.arch.greeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ArchGreetingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchGreetingServiceApplication.class, args);
    }

}

