package com.obtain.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author obtain_y
 * @date 2020/11/22 21:22
 * @description com.obtain.oauth.config 令牌存储器
 */
@Configuration
public class TokenStoreConfig {
    /**
     * Jwt密钥
     */
    private String secrecy = "yj.obtain.com";

    @Bean("jwtTokenStore")
    public TokenStore getTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * jwt访问token转换器
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(secrecy); //资源服务器需要配置此选项方能解密jwt的token
        return converter;
    }
}
