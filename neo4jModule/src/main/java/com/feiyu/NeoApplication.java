package com.feiyu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableNeo4jRepositories
public class NeoApplication {

    @Autowired
    private RestTemplateBuilder builder;

    public static void main(String[] args) {
        SpringApplication.run(NeoApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return builder.build();
    }
}