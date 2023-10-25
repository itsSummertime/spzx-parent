package com.atguigu.spzx.product.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {

    /**
     * CacheManager缓存管理器
     * 管理的是SpringCache缓存注解的使用
     */
    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory connectionFactory) {

        //定义序列化器>>>>>>
        //json序列化
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        //string序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();


        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                //过期时间600秒
                .entryTtl(Duration.ofSeconds(600))
                //key使用string序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
                //value使用json序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer));

        //返回一个Reddis的缓存管理器，数据就会缓存到Redis数据库
        return RedisCacheManager.builder(connectionFactory).cacheDefaults(config).build();
    }
}