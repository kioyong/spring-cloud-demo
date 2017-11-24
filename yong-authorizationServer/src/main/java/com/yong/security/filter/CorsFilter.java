package com.yong.security.filter;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author  LiangYong
 * @createdDate 2017/10/13.
 */
@Component
public class CorsFilter extends OncePerRequestFilter {

    /**
     * 解决前后端分离跨域请求OPTIONS请求401问题，配合WebSecurityConfig 里面的配置 + @Order(-1) 使用
     * 参考 https://stackoverflow.com/questions/25136532/allow-options-http-method-for-oauth-token-request
     * **/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
//        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "authorization, content-type");
//        }
        filterChain.doFilter(request, response);
    }
}