package com.arch.kafka.greeting;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.arch.kafka.greeting.GreetingRepository.NORMAL_TYPE;
import static com.arch.kafka.greeting.GreetingRepository.VIP_TYPE;


@RestController
@RequestMapping("/greeting")
@AllArgsConstructor
public class GreetingController {

    private final GreetingRepository repository;

    @GetMapping
    public List<Greeting> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public Greeting save(@RequestBody Greeting greeting) {
        return repository.save(greeting);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        repository.deleteAll();
        repository.save(new Greeting(null, "Dave", VIP_TYPE, 1000D, new BigDecimal(10)));
        repository.save(new Greeting(null, "Jack", VIP_TYPE, 3000D, new BigDecimal(20)));
        repository.save(new Greeting(null, "Boris", NORMAL_TYPE, 100D, new BigDecimal(5)));
        repository.save(new Greeting(null, "Allen", NORMAL_TYPE, 500D, new BigDecimal(26)));
    }
}
