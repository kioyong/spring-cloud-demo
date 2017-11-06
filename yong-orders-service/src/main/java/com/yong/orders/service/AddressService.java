package com.yong.orders.service;

import com.yong.orders.model.Address;

import java.util.List;

/**
 * Created by LiangYong on 2017/9/14.
 */
public interface AddressService extends BaseService<Address> {

    List<Address> findByLocation(String locationId);
}
