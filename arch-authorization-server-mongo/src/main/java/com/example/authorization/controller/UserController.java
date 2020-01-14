package com.example.authorization.controller;

import com.example.authorization.model.UserEntity;
import com.example.authorization.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/user")
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntity user) throws Exception {
        return userService.registerUser(user);
    }

    @GetMapping("/info")
    public Principal updateUserInfo(@AuthenticationPrincipal Principal principal) {
        return principal;
    }

}
