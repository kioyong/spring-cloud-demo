package com.yong.security.controller;

import com.yong.security.model.AuthenticationVo;
import com.yong.security.model.ResponseVo;
import com.yong.security.model.UserEntity;
import com.yong.security.service.impl.UserDetailServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;


/**
 * @author yong.a.liang
 * createdDate 2017/10/1.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private final UserDetailServiceImpl userDetailService;

    /**
     * 查看用户状态等信息，该接口只能为tonken owner或者具有ADMIN权限的人访问
     * ADMIN可以查看所有人的权限，自己只能查看本人状态
     * **/
    @GetMapping("/{username}")
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public Mono<ResponseVo> getUserDetail(@PathVariable("username")String username){
        return this.userDetailService.findUserByUsername(username)
            .map(ResponseVo::success)
            .switchIfEmpty(Mono.just(ResponseVo.error("user not found!")));
    }

    /**
     * 看到当前使用token的一些信息
     * */
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ApiOperation(value = "")
    public Mono<Principal> me(@AuthenticationPrincipal Principal principal){
        return Mono.just(principal);
    }

    /**
     * 注册功能，Body上需要输入用户名和密码，
     * 当前只添加了用户名密码不能为空，且用户名没有被注册过
     * restController Post request 默认的RequestBody也是 application/json格式的
     * **/
    @PostMapping(path = "/register",consumes = "application/json")
    public Mono<ResponseVo> registerUser(@RequestBody AuthenticationVo auth){
        return this.userDetailService.findUserByUsername(auth.getUsername())
            .map(t -> ResponseVo.error("user " + t.getUsername() + " already exists!"))
            .switchIfEmpty(
                this.userDetailService.registerUser(
                    UserEntity.builder()
                        .username(auth.getUsername())
                        .password(auth.getPassword())
                        .build()
                )
            .map(ResponseVo::success));
    }

//    @RequestMapping({ "/user", "/me" })
//    public Map<String, String> user(Principal principal) {
//        Map<String, String> map = new LinkedHashMap<>();
//        map.put("name", principal.getName());
//        return map;
//    }
}
