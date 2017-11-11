package com.yong.resource.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author LiangYong
 * @createdDate 2017/11/11
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ResourceServerConfiguration.class})
public @interface EnableYongResourceServer {
}
