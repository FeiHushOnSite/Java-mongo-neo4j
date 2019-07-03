package com.feiyu.config;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.security.KeyStore;

@Configuration
public class ConnectionConfiguration {

    @Autowired
    //private ConnectionReportingInterceptor connectionReportingInterceptor;
    @Bean
    public RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory customHttpRequestFactory) {
        RestTemplate restTemplate = new RestTemplate(customHttpRequestFactory);
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        //    restTemplate.getInterceptors().add(new RequestIdHttpRequestInterceptor());
        //    restTemplate.getInterceptors().add(connectionReportingInterceptor);
        return restTemplate;
    }

    @Bean
    HttpComponentsClientHttpRequestFactory customHttpRequestFactoryClientCertificate() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        //   keyStore.load(resourceLoader.getResource(clientCertPath).getInputStream(), clientCertPassword);

        SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
        //      if (acceptSelfSignedHttps) {
        sslContextBuilder = sslContextBuilder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        //     }
        //     if (signRequestWithCertificate) {
        //         sslContextBuilder = sslContextBuilder.loadKeyMaterial(keyStore, clientCertPassword);
        //      }

        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                sslContextBuilder.build());

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
                //             getHttpClientBuilder()
//                        .setSSLSocketFactory(socketFactory)
                //                       .build());
                //       setTimeouts(factory);
        );
        return factory;

    }
}
