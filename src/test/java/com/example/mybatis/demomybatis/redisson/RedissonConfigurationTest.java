package com.example.mybatis.demomybatis.redisson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jacksparrow414
 * @date 2021/2/14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedissonConfigurationTest {
    
    @Autowired
    private RedissonClient redissonClient;
    
    @Test
    public void assertRedisConfig() {
        redissonClient.getKeys().getKeys().forEach(System.out::println);
    }
}