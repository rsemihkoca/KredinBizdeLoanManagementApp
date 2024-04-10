package com.rsemihkoca.bankservicemain.configuration;


import com.rsemihkoca.bankservicemain.dto.response.MergedLoanResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, List<MergedLoanResponse>> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, List<MergedLoanResponse>> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
