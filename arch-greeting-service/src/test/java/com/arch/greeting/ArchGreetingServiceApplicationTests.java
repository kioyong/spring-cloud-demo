package com.arch.greeting;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "eureka.client.enabled=false"
)
class ArchGreetingServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
