package com.yong.orders.dao;

import com.yong.orders.model.ErrorLog;

import java.util.List;

/**
 * Created by yong.a.liang on 9/26/2017.
 */
public interface ErrorLogDao extends BaseDao<ErrorLog>{
	List<ErrorLog> findByMessage(String message);
}
