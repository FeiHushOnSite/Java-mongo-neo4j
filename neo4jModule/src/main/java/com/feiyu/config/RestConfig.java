package com.feiyu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Configuration
public class RestConfig {

    @Value("${message.username}")
    private String MassageUsername;
    @Value("${message.password}")
    private String MassagePassword;

    @Bean
    public RestTemplate messageTemplate(){
        RestTemplate messageTemplate = new RestTemplate();
        messageTemplate.getMessageConverters().add(0,new StringHttpMessageConverter(Charset.forName("utf-8")));
        messageTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(MassageUsername,MassagePassword));
        return messageTemplate;
    }
}
