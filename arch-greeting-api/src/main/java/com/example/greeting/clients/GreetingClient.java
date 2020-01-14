package com.example.greeting.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "arch-greeting-service", path = "/api/greeting")
public interface GreetingClient {

    @GetMapping
    String hello();
}
