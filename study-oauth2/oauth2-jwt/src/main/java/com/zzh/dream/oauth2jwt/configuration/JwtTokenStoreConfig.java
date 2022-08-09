package com.zzh.dream.oauth2jwt.configuration;

import com.zzh.dream.oauth2jwt.enhance.JwtTokenEnhancer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 14/05/2022
 **/
@Configuration
public class JwtTokenStoreConfig {
    @Bean
    public TokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter accessTokenConverter = new
                JwtAccessTokenConverter();
        //配置JWT使用的秘钥
        accessTokenConverter.setSigningKey(signature);
        return accessTokenConverter;
    }


    /**
     * 创建一个JwtTokenEnhancer实例
     * @Bean
     * @return
     */
    @Bean
    public JwtTokenEnhancer jwtTokenEnhancer() {
        return new JwtTokenEnhancer();
    }

    @Value("${zzh.signature}")
    private String signature;
}
