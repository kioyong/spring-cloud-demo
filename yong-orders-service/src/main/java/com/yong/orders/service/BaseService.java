package com.yong.orders.service;


import com.yong.orders.common.Result;

import java.util.List;

/**
 * Created by yong.a.liang on 6/22/2017.
 */
public interface BaseService<T> {

    T addOne(T instance);
    void delete(String id);
    T getOne(String id);
    T updateOne(T instance);

    List<T> findAll();
}
