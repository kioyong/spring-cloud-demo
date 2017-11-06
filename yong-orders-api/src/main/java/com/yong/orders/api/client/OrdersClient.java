package com.yong.orders.api.client;

import com.yong.orders.model.OrdersInfo;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author LiangYong
 * @createdDate 2017/11/4
 * Fiegn Clietn Test Demo
 */
@FeignClient(name = "yong-orders", path = "/orders")
public interface OrdersClient extends BaseClient<OrdersInfo>{
}
