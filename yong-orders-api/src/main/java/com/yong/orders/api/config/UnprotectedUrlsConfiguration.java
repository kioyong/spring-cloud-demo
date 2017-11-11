package com.yong.orders.api.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiangYong
 * @createdDate 2017/11/11
 */
@Configuration
@ConfigurationProperties(prefix = "yong.security")
@Getter
public class UnprotectedUrlsConfiguration {

    private List<String> urls = new ArrayList<>();



}
