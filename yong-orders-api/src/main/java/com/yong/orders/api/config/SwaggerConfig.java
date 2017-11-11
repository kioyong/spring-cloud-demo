package com.yong.orders.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author LiangYong
 * @createdDate 2017/11/11
 */


@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig {

    @Bean
    public Docket api() {
        log.info("start init swagger2");
        /**
         * 为所有swagger UI 上面的请求默认添加一个 authorization 参数，方便测试
         * **/
        Parameter param = new ParameterBuilder()
                .parameterType("header")
                .name("Authorization")
                .description("Used for oauth authentication")
                .modelRef(new ModelRef("string"))
                .required(false)
                .build();
        List<Parameter> params = new ArrayList<>();
        params.add(param);
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yong.orders.api.controller"))
                .paths(regex(".*"))
                .build()
                .globalOperationParameters(params);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("yong-orders Service API")
                .description("Follow APIs are for yong-orders oprations")
                .version("0.0.1-SNAPSHOT").build();
    }
}

