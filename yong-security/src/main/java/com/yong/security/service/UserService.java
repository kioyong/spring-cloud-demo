package com.yong.security.service;

import com.yong.security.model.UserEntity;
import reactor.core.publisher.Mono;

/**
 * Created by LiangYong on 2017/10/1.
 */
public interface UserService {
    Mono<UserEntity> registerUser(UserEntity user);
    Mono<UserEntity> findUserByUsername(String username);

}
