package com.yong.orders.api.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yong.orders.api.client.OrdersClient;
import com.yong.orders.common.Result;
import com.yong.orders.common.ResultCode;
import com.yong.orders.model.OrdersInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author LiangYong
 * @createdDate 2017/11/5
 */
@RestController
@CrossOrigin
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController /*extends BaseController<OrdersInfo>*/{

	private final OrdersClient client;

//	public OrdersController(OrdersClient client) {
//		super(client);
//	}

	@GetMapping
//	@HystrixCommand(fallbackMethod = "defaultfindAll")
	public Result findAll() {
		return client.findAll();
	}

	public Result defaultfindAll(){
		return Result.fail(ResultCode.FAIL,"error, Fallback Test in controller layer");
	}

	@HystrixCommand(fallbackMethod = "throwErrorTestFallback")
	@GetMapping("/error")
	public Result throwErrorTest(){
		throw new RuntimeException("throw error test");
	}

	public Result throwErrorTestFallback(){
		/**
		 *   HystrixCommand 会把抛异常的也会handle, 这样如何处理exception?
		 *  应该在越小影响的地方放 HystrixCommand注解, 如 client 层, 或者service层,尽量往前靠
		 * **/
		return Result.fail(ResultCode.FAIL,"handler error Test in controller layer");
	}

	@GetMapping("/test")
	public String ribbonTest(){
		return client.ribbonTest();
	}
}