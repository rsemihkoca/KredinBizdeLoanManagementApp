package com.rsemihkoca.applicationservicemain.configuration;

import com.rsemihkoca.applicationservicemain.client.akbank.AkbankServiceClient;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
public class BeanConfiguration {


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STANDARD);

        return mapper;
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(5))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }
}
