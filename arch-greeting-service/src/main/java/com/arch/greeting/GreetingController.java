package com.arch.greeting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Value("${instance.name:}")
    private String applicationName;

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
        return System.getenv("HOSTNAME");
    }

}
