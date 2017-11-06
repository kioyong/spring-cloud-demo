package com.yong.orders.dao.custom;

/**
 * Created by yong.a.liang on 7/27/2017.
 */
public interface SequenceCustomDao {
    Long getNextSequenceId(String key);
}
