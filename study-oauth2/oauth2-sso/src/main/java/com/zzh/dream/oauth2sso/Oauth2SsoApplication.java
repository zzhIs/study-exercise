package com.zzh.dream.oauth2sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @EnableOAuth2Sso单点登录的原理简单来说就是：
 * 标注有@EnableOAuth2Sso的OAuth2 Client应用在通过某种OAuth2授权流程获取访问令牌后（一般是授权码流程），
 * 通过访问令牌访问userDetails用户明细这个受保护资源服务，获取用户信息后，将用户信息转换为Spring Security上下文中的认证后凭证Authentication，
 * 从而完成标注有@EnableOAuth2Sso的OAuth2 Client应用自身的登录认证的过程。整个过程是基于OAuth2的SSO单点登录
 */
@EnableOAuth2Sso
@SpringBootApplication
public class Oauth2SsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2SsoApplication.class, args);
	}


}
