package com.yong.registry.couldDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CouldDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouldDemoApplication.class, args);
	}
}
