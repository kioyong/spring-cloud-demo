package com.arch.kafka.stream;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class ArchKafkaStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchKafkaStreamApplication.class, args);
    }

    @Autowired
    private StreamBridge streamBridge;

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        var greeting = new Greeting(name, Instant.now().toString());
        streamBridge.send("touppercase-out-0", greeting);
        return name;
    }
}
