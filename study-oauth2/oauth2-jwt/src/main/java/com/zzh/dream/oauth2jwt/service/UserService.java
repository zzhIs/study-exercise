package com.zzh.dream.oauth2jwt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 11/05/2022
 **/
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 调用接口没有权限就会转发到登录界面，
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("我执行了...loadUserByUsername:"+username);
        String password = passwordEncoder.encode("123123");
        System.out.println(password);
        return new User("zzh",password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
