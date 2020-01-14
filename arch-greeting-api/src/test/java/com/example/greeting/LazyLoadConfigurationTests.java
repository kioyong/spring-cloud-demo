package org.springframework.cloud.netflix.archaius;

import com.netflix.config.DynamicProperty;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Liang Yong
 */

@SpringBootTest(
    classes = LazyLoadConfigurationTests.TestConfig.class,
    properties = {"client.ribbon.listOfServers=foo.com,bar.com", "spring.main.lazy-initialization=true"})
public class LazyLoadConfigurationTests {

    @Test
    public void test() {
        DynamicProperty instance = DynamicProperty.getInstance("client.ribbon.listOfServers");
        assertThat(instance.getString()).isEqualTo("foo.com,bar.com");
    }

    @SpringBootConfiguration
    @EnableAutoConfiguration
    public static class TestConfig {
//		public static void main(String[] args) {
//			SpringApplication.run(TestConfig.class, args);
//		}
    }
}
