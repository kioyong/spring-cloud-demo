package com.yong.orders.api;

import com.yong.resource.config.EnableYongResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//@EnableEurekaClient
@EnableCircuitBreaker
//@SpringCloudApplication
@EnableFeignClients
@EnableZuulProxy
@RibbonClients
@EnableYongResourceServer
public class YongOrdersAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(YongOrdersAPIApplication.class, args);
	}
}
