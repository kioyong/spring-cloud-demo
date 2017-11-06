package com.yong.registry.couldDemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@GetMapping("/index/{number}")
	public String testHelloWorld(@PathVariable int number){
		return "" + 12001* number;
	}
}
