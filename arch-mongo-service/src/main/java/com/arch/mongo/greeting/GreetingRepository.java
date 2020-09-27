package com.arch.mongo.greeting;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GreetingRepository extends MongoRepository<Greeting, String>, GreetingRepositoryCustom {
    String VIP_TYPE = "vip";
    String NORMAL_TYPE = "normal";

    @Aggregation(pipeline = {
            "{ $match : { type : ?0 } }",
            "{ $group : { _id : null , total : { $sum : '$point' } } }"
    })
    Long getNormalTypeTotalPoint(String type);
}
