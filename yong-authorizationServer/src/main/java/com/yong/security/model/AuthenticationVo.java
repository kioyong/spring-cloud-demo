package com.yong.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author  LiangYong
 * @createdDate 2017/10/6.
 */
@Data
@AllArgsConstructor
public class AuthenticationVo {
    private String username;
    private String password;
}