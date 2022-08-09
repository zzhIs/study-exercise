package com.zzh.dream.oauth2sso;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 有时候我们需要扩展JWT中存储的内容，这里我们在JWT中扩展一个 key为enhance，value为enhance info 的数据。
 * 继承TokenEnhancer实现一个JWT内容增强器
 * @author: zhangzihao
 * @date: 14/05/2022
 **/
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> info = new HashMap<>();
        info.put("enhance", "enhance info");
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;    }
}
