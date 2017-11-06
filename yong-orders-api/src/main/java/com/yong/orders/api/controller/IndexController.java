package com.yong.orders.api.controller;

import com.yong.orders.api.client.OrdersClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author LiangYong
 * @createdDate 2017/11/4
 */
@RestController
public class IndexController {

	@Autowired
	private OrdersClient client;

	@GetMapping("/index")
	public String index(){
		return "security Test";
	}

	@GetMapping("/me")
	public Principal me(@AuthenticationPrincipal Principal principal){
		return principal;
	}

}
