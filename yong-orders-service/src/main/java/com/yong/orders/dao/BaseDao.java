package com.yong.orders.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by yong.a.liang on 6/22/2017.
 */
@NoRepositoryBean
public interface BaseDao <T> extends MongoRepository<T, String> {
//    void delete(String id);
}
