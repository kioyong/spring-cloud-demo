package com.yong.security.controller;

import com.yong.security.model.ResponseVo;
import com.yong.security.model.UserEntity;
import com.yong.security.repository.UserDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.Function;

/**
 * @author  LiangYong
 * @createdDate 2017/10/1.
 */
@RestController
@CrossOrigin
//@AllArgsConstructor
@Slf4j
public class IndexController {

    /**
     * 无任何权限校验接口，测试用 -> localhost:8081/index
     * 此controller所有接口都是测试用
     * **/

    private static String enable;
    private final UserDao userDao;

    IndexController(@Value("${yong.oauth.enable}") String enable, UserDao userDao){
        this.enable = enable;
        this.userDao = userDao;
    }


    @GetMapping("/index")
    public ResponseVo getHelloWorld(){
        log.debug("start index test");
        return ResponseVo.success("test success!");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/mono")
    public Mono getMono(){
        log.debug("start mono test");
        return Mono.just("hello world");
    }

    @GetMapping("/mono1")
    public Mono getPauseMono(){
        log.debug("start mono1 test");
        return Mono.just("hello pause world").delaySubscription(Duration.ofMillis(3000));
    }

    @GetMapping("/getProperties")
    public Mono getPropertiesTest(){
        return Mono.just("enable_auth = "+ enable);
    }

}
