package com.yong.orders.api.controller;

import com.yong.orders.api.client.OrdersClient;
import com.yong.orders.api.config.UnprotectedUrlsConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * @author LiangYong
 * @createdDate 2017/11/4
 */
@RestController
@AllArgsConstructor
public class IndexController {

	private final UnprotectedUrlsConfiguration urls;
	private final OrdersClient client;

	@GetMapping("/index")
	public String index(){
		return "security Test";
	}

	@GetMapping("/me")
	public Principal me(@AuthenticationPrincipal Principal principal){
		return principal;
	}

	@GetMapping("/urls")
	public List<String> getProtectedUrls(){
		return urls.getUrls();
	}

}
