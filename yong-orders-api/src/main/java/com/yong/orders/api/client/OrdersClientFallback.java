package com.yong.orders.api.client;

import com.yong.orders.common.Result;
import com.yong.orders.common.ResultCode;
import com.yong.orders.model.OrdersInfo;
import org.springframework.stereotype.Component;

/**
 * @author LiangYong
 * @date 2017/11/24
 */
@Component
public class OrdersClientFallback implements OrdersClient {

    @Override
    public Result findAll() {
        return fallbcakReturn();
    }

    Result fallbcakReturn(){
        return Result.fail(ResultCode.FAIL,"error, Fallback Test");
    }
}
