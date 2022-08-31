package org.liu.common.cache.config;

import org.liu.common.cache.RedisHelper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @Author lzs
 * @Date 2022/8/30 9:39
 **/
@Configuration
public class CacheConfig {

    @Bean("jacksonRedisTemplate")
    public RedisTemplate<String, Object> jacksonRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(RedisSerializer.json());
        template.setHashKeySerializer(RedisSerializer.json());
        template.setHashValueSerializer(RedisSerializer.json());
        template.setConnectionFactory(connectionFactory);
        return template;
    }

    @Bean
    public RedisHelper redisHelper(StringRedisTemplate stringRedisTemplate,
                                   @Qualifier("jacksonRedisTemplate") RedisTemplate<String, Object> jacksonRedisTemplate,
                                   RedisTemplate<Object, Object> redisTemplate) {
        return new RedisHelper(stringRedisTemplate, jacksonRedisTemplate, redisTemplate);
    }

}
