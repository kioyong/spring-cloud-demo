package com.yong.security.webflux;

import com.yong.security.model.UserEntity;
import com.yong.security.repository.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by LiangYong on 2017/10/15.
 */
//@AllArgsConstructor
public class TestHandler {

    /**
     * HandlerFunction 的实现与测试，等同于controller 层效果
     * pending implement
     * **/
//    private final UserDao dao;
//
//    public Mono<ServerResponse> listUserTest(ServerRequest request){
//        Flux<UserEntity> all = dao.findAll();
//        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(all, UserEntity.class);
//    }

}
