package com.zzh.dream.oauth2sso;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 11/05/2022
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Value("${security.oauth2.sso.login-path}")
    private String loginProcessingUrl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().permitAll()
                .and().authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and().logout().permitAll()
                .and().csrf().disable()
                .formLogin().loginProcessingUrl(loginProcessingUrl)
        ;
    }

}
