package com.yong.orders.api.controller;

import com.yong.orders.api.client.OrdersClient;
import com.yong.orders.model.OrdersInfo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiangYong
 * @createdDate 2017/11/5
 */
@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrdersController extends BaseController<OrdersInfo>{

	private OrdersClient client;

	public OrdersController(OrdersClient client) {
		super(client);
	}
}