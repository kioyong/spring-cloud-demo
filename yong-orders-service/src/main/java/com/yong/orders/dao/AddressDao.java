package com.yong.orders.dao;

import com.yong.orders.dao.BaseDao;
import com.yong.orders.model.Address;

import java.util.List;

/**
 * Created by LiangYong on 2017/9/14.
 */
public interface AddressDao extends BaseDao<Address>{
    List<Address> findByLocation(String location);
}

