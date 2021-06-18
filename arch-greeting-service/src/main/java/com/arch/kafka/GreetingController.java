package com.arch.kafka;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RefreshScope
@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Value("${instance.name:}")
    private String applicationName;

    @Value("${author.password:}")
    private String password;

    @Value("${author.name:}")
    private String name;

    private final AtomicLong atomicLong = new AtomicLong();

    @GetMapping("/count")
    public Long count() {
        return atomicLong.incrementAndGet();
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(required = false, name = "name", defaultValue = "world") String name) {
        return "hello ".concat(name).concat("Hello Microservice, Message from service: ").concat(applicationName);
    }

    @GetMapping("/hostname")
    public String hostname() {
        return "host name is " + System.getenv("HOSTNAME");
    }

    @GetMapping("/greeting")
    public Greeting greeting() {
        return new Greeting("123", new Date(), new Date());
    }

    @GetMapping("/SecretConfig")
    public String getSecretConfig() {
        return "name is " + name + " secret password is " + password;
    }

}
