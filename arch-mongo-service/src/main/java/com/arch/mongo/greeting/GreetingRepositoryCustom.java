package com.arch.mongo.greeting;

import java.util.List;

public interface GreetingRepositoryCustom {


  List<Greeting> findAllByDistinctType();

  Double getVIPTypeTotalPoint();
}
