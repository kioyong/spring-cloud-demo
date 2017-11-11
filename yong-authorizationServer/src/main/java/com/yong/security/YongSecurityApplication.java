package com.yong.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author  LiangYong
 * @createdDate 2017/10/1.
 * **/
@SpringBootApplication
@EnableWebFlux
@EnableAutoConfiguration(
		exclude = {
				org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class
		}
)
public class YongSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(YongSecurityApplication.class, args);
	}
}
