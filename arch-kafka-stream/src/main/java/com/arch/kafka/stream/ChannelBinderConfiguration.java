package com.arch.kafka.stream;

import java.time.Instant;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import lombok.extern.log4j.Log4j2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class ChannelBinderConfiguration {


    /**
     * solution 1
     * **/
    @Bean
    public Consumer<Greeting> greeting() {
        return input -> {
            if (input.getName().equals("error")) {
                log.error("topic2 greeting: error intentional");
                throw new RuntimeException("intentional");
            }
            log.info("topic2: {}", input);
        };
    }

    /**
     * solution 1
     * **/
    @Bean
    public Consumer<Greeting> echo() {
        return input -> log.info("topic2 echo: {}", input);
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
     * 每秒执行一次
     * **/
//    @Bean
//    public Supplier<Greeting> newgreeting() {
//        return () -> new Greeting("new Greeting from Supplier", Instant.now().toString());
//    }


}
