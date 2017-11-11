package com.yong.orders.api;

import com.yong.resource.config.EnableYongResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableYongResourceServer
public class YongOrdersAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(YongOrdersAPIApplication.class, args);
	}
}
