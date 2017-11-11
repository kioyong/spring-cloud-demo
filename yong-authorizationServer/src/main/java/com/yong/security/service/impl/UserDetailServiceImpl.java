package com.yong.security.service.impl;

import com.yong.security.model.UserEntity;
import com.yong.security.repository.UserDao;
import com.yong.security.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.google.common.base.Preconditions.checkArgument;


/**
 * Created by LiangYong on 2017/10/1.
 */
@Service
@AllArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService,UserService {

    @Autowired
    private final UserDao dao;

    /**
     * 实现UserDetailsService 的接口，为Spring Security 提供校验方法
     * **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("start load User By Username");
        return dao.findById(username).block();
    }

    /**
     * @Param UserEntity
     *
     * **/
    @Override
    public Mono<UserEntity> registerUser(UserEntity user) {
        registerUserBeforeCheck(user);
        user.init();
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return dao.insert(user);
    }

    @Override
    public Mono<UserEntity> findUserByUsername(String username){
        checkArgument(!username.isEmpty(),"username can't be null");
        return dao.findById(username);
    }

    public void registerUserBeforeCheck(UserEntity user){
        checkArgument(null!= user.getName() && !user.getName().isEmpty()
                ,"username can't be null");
        checkArgument(null!= user.getPassword() && !user.getPassword().isEmpty()
                ,"password can't be null");
        //TODO 密码的其他校验规则实现,如密码不能输入特殊符号,不能小于6位数等

    }
}
