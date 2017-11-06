package com.yong.orders.controller;

import com.yong.orders.model.OrdersInfo;
import com.yong.orders.service.OrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LiangYong on 2017/8/14.
 */
@RestController
@RequestMapping("/orders")
public class OrdersController extends BaseController<OrdersInfo> {
    private final static Logger LOG = LoggerFactory.getLogger(OrdersController.class);

    @Autowired
    private OrdersService service;


    public OrdersController(OrdersService service) {
        super(service);
        this.service = service;
    }
}
