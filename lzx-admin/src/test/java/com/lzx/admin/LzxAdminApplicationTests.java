package com.lzx.admin;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
class LzxAdminApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Test
    void contextLoads() {
        log.info("数据源类型：{}", dataSource.getClass());
    }

    @Test
    void testRedis() {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        // 保存
        operations.set("hello", "redis");
        // 获取
        String hello = operations.get("hello");
        System.out.println(hello);
        // 擦好看底层使用的工厂
        System.out.println(redisConnectionFactory.getClass());
    }

}
