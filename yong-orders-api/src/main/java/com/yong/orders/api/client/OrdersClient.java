package com.yong.orders.api.client;

import com.yong.orders.common.Result;
import com.yong.orders.model.OrdersInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author LiangYong
 * @createdDate 2017/11/4
 * Fiegn Clietn Test Demo
 *
 * Q: fallback 在FeignClient层没效果不知道原因.
 * A: 需要添加这个配置 feign.hystrix.enabled=true
 */
@FeignClient(name = "yong-orders", path = "/orders",fallback = OrdersClientFallback.class)
public interface OrdersClient{

    @GetMapping
    Result findAll();

    @GetMapping("/test")
    String ribbonTest();
}
