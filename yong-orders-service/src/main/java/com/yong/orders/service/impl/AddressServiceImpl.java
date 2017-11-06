package com.yong.orders.service.impl;

import com.yong.orders.dao.AddressDao;
import com.yong.orders.model.Address;
import com.yong.orders.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LiangYong on 2017/9/14.
 */
@Service
public class AddressServiceImpl extends BaseServiceImpl<Address> implements AddressService {

    public final AddressDao dao;

    @Autowired
    public AddressServiceImpl(AddressDao dao) {
        super(dao);
        this.dao=dao;
    }


    @Override
    public List<Address> findByLocation(String locationId) {
        return dao.findByLocation(locationId);
    }
}
