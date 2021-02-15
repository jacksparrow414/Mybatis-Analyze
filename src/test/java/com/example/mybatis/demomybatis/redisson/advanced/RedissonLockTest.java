package com.example.mybatis.demomybatis.redisson.advanced;

import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jacksparrow414
 * @date 2021/2/14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RedissonLockTest {
    /**
     * 复现多线程竞争问题，使用一个变量.
     */
    private static int num = 0;
    
    private static final RedissonClient redissonClient = Redisson.create();
    
    @Test
    @SneakyThrows
    public void assertNoRedissonLock() {
        IncrementAge incrementAge = new IncrementAge();
        // 同一个对象的不同的线程
        Thread thread = new Thread(incrementAge);
        Thread thread1 = new Thread(incrementAge);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        log.info("num值为{}", num);
    }
    
    @Test
    @SneakyThrows
    public void assertRedissonLock() {
        IncrementAgeWithLock incrementAgeWithLock = new IncrementAgeWithLock();
        Thread thread = new Thread(incrementAgeWithLock);
        Thread thread1 = new Thread(incrementAgeWithLock);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        log.info("num值为{}", num);
    }
    
    /**
     * 内部类应该为static的.
     */
    static class IncrementAge implements Runnable {
        @Override
        @SneakyThrows
        public void run() {
            for (int i= 0;i<1000;i++) {
                num++;
            }
            log.info("当前执行线程ID{}", Thread.currentThread().getName());
        }
    }
    
    /**
     * 使用分布式锁模拟线程并发情况.
     */
    static class IncrementAgeWithLock implements Runnable {
    
        @Override
        @SneakyThrows
        public void run() {
            RLock redisLock = redissonClient.getLock("redisLock");
            boolean tryLock = redisLock.tryLock(10, 10, TimeUnit.SECONDS);
            if (!tryLock) {
                log.info("未获取到锁");
            }
            if (tryLock) {
                try {
//                    Thread.sleep(11000);
                    for (int i = 0; i < 1000; i++) {
                        num++;
                    }
                }finally {
                    redisLock.unlock();
                }
            }
        }
    }
}