package com.example.prewave.common.config;

import java.time.Duration;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
@EnableCaching
@ConfigurationProperties(prefix = "spring.redis")
public class RedisCacheConfig {

    private static final String KEY_PREFIX = "V1:";
    public static final String TREE_NODES_CACHE_NAME = "treeNodesCache";

    private String host;
    private int port;

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(host, port);
        return new LettuceConnectionFactory(configuration);
    }
    @Primary
    @Bean
    public RedisCacheManager redisCacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisCacheConfiguration baseConfig = RedisCacheConfiguration.defaultCacheConfig()
                .prefixCacheNameWith(KEY_PREFIX)
                .disableCachingNullValues()
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        RedisCacheConfiguration treeNodesCacheConfig = baseConfig.entryTtl(Duration.ofMinutes(3));

        return RedisCacheManager.builder(lettuceConnectionFactory)
                .withInitialCacheConfigurations(Map.of(
                        TREE_NODES_CACHE_NAME, treeNodesCacheConfig
                ))
                .build();
    }
}
