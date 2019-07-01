package com.feiyu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

/**
 *  configure  keypair :  C:\Users\> keytool -genkey -keystore keystore.jks  -alias tycoonclient -keyalg RSA
 */

@Configuration
@EnableAuthorizationServer    //启用OAuth2的认证服务器功能
public class OAuthConfigurer extends AuthorizationServerConfigurerAdapter {

    /**
     * JwtAccessTokenConverter 方法中使用数字证书:keystore.jks 设置密码别名参数
     * @return
     */

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"),"tc123456".toCharArray()).getKeyPair("tycoonclient");
        converter.setKeyPair(keyPair);
        return converter;
    }

    /**
     * autoApprove (true)  这行设定了自动确认授权, 用户登陆后 不需要再次授权确认操作
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.inMemory().withClient("ssoclient").secret("ssosecret")
                .autoApprove(true)
                .authorizedGrantTypes("authorization_code", "refresh_token").scopes("openid");
    }
}
