package com.yong.security;

import com.google.common.base.Throwables;
import com.yong.security.model.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author  LiangYong
 * @createdDate 2017/10/14.
 */
@RestControllerAdvice
@Slf4j
@ConfigurationProperties
public class ExceptionHandlerConfig {

    @Value("${yong.debug.enable}")
    private static boolean dataFlag;

    @ExceptionHandler(Exception.class)
    public ResponseVo handleArgumentException(Exception ex) {
        List<Map<String, String>> list = Arrays.stream(Throwables.getRootCause(ex).getStackTrace())
                .filter(t ->
                        t.getClassName().startsWith("com.yong") &&
                        t.getLineNumber() > 0 &&
                        !"CorsFilter.java".equals(t.getFileName())
                )
                .map(t -> {
                    Map<String, String> map = new HashMap<>(3);
                    map.put("methodName", t.getMethodName());
                    map.put("fileName", t.getFileName());
                    map.put("lineNumber", t.getLineNumber() + "");
                    return map;
                }).collect(Collectors.toList());
        log.error("handle exception {}",ex);
        //是否返回异常信息抛出的信息到ResopnseVo中
        if(dataFlag) {
            return ResponseVo.error(Throwables.getRootCause(ex).getMessage(), list);
        }else {
            return ResponseVo.error(Throwables.getRootCause(ex).getMessage(),null);
        }
    }
}
