package com.zzh.dream.oauth2jwt.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 11/05/2022
 **/
@RestController
@RequestMapping("/user")
public class AuthController {

    @Value("${zzh.signature}")
    private String signature;

    /**
     * 修改UserController类，使用jjwt工具类来解析Authorization头中存储的JWT内容
     * @param authentication
     * @return
     */
    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication, HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = null;
        if(header!=null){
            token = header.substring(header.indexOf("bearer") + 7);
        }else {
            token = request.getParameter("access_token");
        }
        return Jwts.parser()
                .setSigningKey(signature.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

    @GetMapping("/test")
    public Object getCurrentUser() {
        return "我通过了权限验证来了来了....";
    }
}
