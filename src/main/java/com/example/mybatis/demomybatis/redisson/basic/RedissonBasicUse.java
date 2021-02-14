package com.example.mybatis.demomybatis.redisson.basic;

import java.util.concurrent.TimeUnit;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 更多基本使用{@link com.example.mybatis.demomybatis.redisson.basic.RedissonBasicUseTest}
 * @author jacksparrow414
 * @date 2021/2/14
 */
@Component
public class RedissonBasicUse {
     private static final String BASIC_BUCKET = "basic";
     
    @Autowired
    private RedissonClient redissonClient;
    
    public void basicSetKey() {
        RBucket<Object> bucket = redissonClient.getBucket(BASIC_BUCKET);
        bucket.set("redisson basic");
        bucket.getAndSet("get value after set value");
        bucket.set("expire value", 5000, TimeUnit.MILLISECONDS);
    }
}
