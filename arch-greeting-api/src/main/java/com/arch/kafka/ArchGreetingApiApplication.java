package com.arch.greeting;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.archaius.ConfigurableEnvironmentConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

@AllArgsConstructor
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ArchGreetingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchGreetingApiApplication.class, args);
    }

    /**
     * when lazy-initialization is true, Archaius can't get ribbon.listOfServers configuration,
     * Currently(2.2.1.RELEASE), we need to ensure the configuration have been initialization
     * https://github.com/spring-cloud/spring-cloud-netflix/issues/3692
     **/
    private final ConfigurableEnvironmentConfiguration config;

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
    }
}

