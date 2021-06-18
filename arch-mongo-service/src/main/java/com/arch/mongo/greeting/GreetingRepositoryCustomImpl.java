package com.arch.mongo.greeting;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import org.bson.Document;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import static com.arch.mongo.greeting.GreetingRepository.VIP_TYPE;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.replaceRoot;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@AllArgsConstructor
public class GreetingRepositoryCustomImpl implements GreetingRepositoryCustom {

  private final MongoTemplate mongoTemplate;

  @Override
  public List<Greeting> findAllByDistinctType() {
    Aggregation agg = newAggregation(Greeting.class,
        group("type").first("$$ROOT").as("item"),
        replaceRoot("$item")
    );
    AggregationResults<Greeting> greeting = mongoTemplate.aggregate(agg, "greeting", Greeting.class);
    return greeting.getMappedResults();
  }

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
