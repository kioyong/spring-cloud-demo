package com.yong.orders.service.impl;

import com.yong.orders.dao.OrdersDao;
import com.yong.orders.model.OrdersInfo;
import com.yong.orders.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LiangYong on 2017/8/14.
 */
@Service
public class OrdersServiceImpl extends BaseServiceImpl<OrdersInfo> implements OrdersService {

    private OrdersDao ordersDao;

    @Autowired
    public OrdersServiceImpl(OrdersDao dao) {
        super(dao);
    }
}
