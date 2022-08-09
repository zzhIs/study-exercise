package com.zzh.dream.oauth2base.base.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 11/05/2022
 **/
@RestController
@RequestMapping("/user")
public class AuthController {

    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication) {
        return authentication.getPrincipal();
    }

    @GetMapping("/test")
    public Object getCurrentUser() {
        return "我通过了权限验证来了来了....";
    }
}
