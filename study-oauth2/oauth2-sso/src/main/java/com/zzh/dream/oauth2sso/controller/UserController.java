package com.zzh.dream.oauth2sso.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 14/05/2022
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication) {
        return authentication;
    }
}
