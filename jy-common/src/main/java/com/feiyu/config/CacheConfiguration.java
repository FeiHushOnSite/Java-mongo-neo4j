package com.feiyu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

@Configuration
@EnableCaching
public class CacheConfiguration {
    @Bean
    @Profile("!cache")
    public CacheManager cacheManagerNoOps() {
        return new NoOpCacheManager();
    }

    @Configuration
    @Profile("cache")
    @ConditionalOnProperty(name = "spring.cache.type", havingValue = "ehcache")
    static class EhCacheConfiguration extends CachingConfigurerSupport {

        @Value("${spring.cache.ehcache.config}")
        private Resource ehcacheResource;

        @Bean
        @NotNull
        @Override
        public CacheManager cacheManager() {
            return new EhCacheCacheManager(ehCacheCacheManager().getObject());
        }

        @Bean
        @NotNull
        @Override
        public KeyGenerator keyGenerator() {
            return (target, method, params) -> {
                Object[] objects = Arrays.stream(params)
                        .map(String::valueOf)
                        .toArray();
                return new SimpleKey(objects);
            };
        }

        @Bean
        EhCacheManagerFactoryBean ehCacheCacheManager() {
            EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
            cmfb.setConfigLocation(ehcacheResource);
            cmfb.setShared(true);
            return cmfb;
        }
    }
}
