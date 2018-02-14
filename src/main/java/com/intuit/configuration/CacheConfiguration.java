package com.intuit.configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;


@Configuration
@EnableCaching
public class CacheConfiguration {

    @Value("${bidsCacheExpireTime:12}")
    Long bidsCacheExpireTime;

    @Value("${bidsCacheMaxSize:100}")
    Long bidsCacheMaxSize;

    @Bean
    public Cache<String, Integer> bidsCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(bidsCacheExpireTime, TimeUnit.HOURS)
                .maximumSize(bidsCacheMaxSize)
                .build();

    }
}
