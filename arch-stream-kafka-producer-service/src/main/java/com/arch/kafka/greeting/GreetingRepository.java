package com.arch.kafka.greeting;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GreetingRepository extends MongoRepository<Greeting, String> {
    String VIP_TYPE = "vip";
    String NORMAL_TYPE = "normal";

}
