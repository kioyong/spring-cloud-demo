package com.yong.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by LiangYong on 2017/10/1.
 */
@Configuration
@EnableSwagger2
@Slf4j
@Controller
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
                .apis(RequestHandlerSelectors.basePackage("com.yong.security.controller"))
                .paths(regex(".*"))
                .build()
                .globalOperationParameters(params);
    }

    /**
     * 让"localhost:8081/" 跳转到"localhost:8081/swagger-ui.html
     * **/
    @RequestMapping("/")
    public String home() {
        return "redirect:swagger-ui.html";
    }

}