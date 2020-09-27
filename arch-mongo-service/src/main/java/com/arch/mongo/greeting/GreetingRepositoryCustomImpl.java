package com.arch.mongo.greeting;

import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static com.arch.mongo.greeting.GreetingRepository.VIP_TYPE;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@AllArgsConstructor
public class GreetingRepositoryCustomImpl implements GreetingRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public Double getVIPTypeTotalPoint() {
        Aggregation agg = newAggregation(Greeting.class,
                match(where("type").is(VIP_TYPE)),
                group("type").sum("point").as("totalPoint"),
                project("totalPoint")
        );
        AggregationResults<Document> greeting = mongoTemplate.aggregate(agg, "greeting", Document.class);
        Double totalPoint = Objects.requireNonNull(greeting.getUniqueMappedResult()).getDouble("totalPoint");
        return totalPoint == null ? 0 : totalPoint;
    }
}
