package com.example.authorization.service;

import com.example.authorization.model.UserEntity;

public interface UserService {

    UserEntity registerUser(UserEntity user) throws Exception;
}
