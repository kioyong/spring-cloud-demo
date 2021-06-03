package com.arch.kafka.stream;

import java.time.Instant;
import java.util.function.Consumer;
import java.util.function.Function;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@SpringBootApplication
public class ArchKafkaStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchKafkaStreamApplication.class, args);
    }

    @Autowired
    private StreamBridge streamBridge;

    /**
     * solution 1
     * **/
    @Bean
    public Consumer<Greeting> greeting() {
        return input -> log.info("topic2: {}", input);
    }

    /**
     * not work yet
     * **/

//    @Bean
//    public Consumer<Flux<Greeting>> greeting() {
//        return flux -> flux.map(Greeting::getName)
//                .log().then();
//    }
    @Bean
    public Function<Greeting, Greeting> touppercase() {
        return input -> {
            log.info("topic1: {}", input);
            input.setName(input.getName().toUpperCase());
            return input;
        };
    }


    /**
     * not work yet
     * **/
//    @Bean
//    public Supplier<Flux<Greeting>> newgreeting() {
//        return () -> Flux.just(new Greeting("new Greeting from Supplier", Instant.now().toString()));
//    }
    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        String s = "hello " + name;
        var greeting = new Greeting(s, Instant.now().toString());
        streamBridge.send("touppercase-out-0", greeting);
        return s;
    }
}
