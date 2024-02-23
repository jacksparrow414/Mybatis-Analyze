package com.example.mybatis.demomybatis.thread;

import com.example.mybatis.demomybatis.thread.fixture.CountDownLatchFixture;
import com.example.mybatis.demomybatis.thread.fixture.ThreadFixture;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 模拟多线程并发的工具类使用,CountDownLatch
 *
 * @author jacksparrow414
 * @date 2020/10/16 10:37
 */
public final class CountDownLatchTest {
    
    @Test
    @SneakyThrows
    public void assertCountDownLatch() {
        CountDownLatch begin = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(new ThreadFixture()).start();
            begin.countDown();
        }
        begin.await(5, TimeUnit.SECONDS);
        System.out.println("主线程开始执行");
    }
    
    @Test
    @SneakyThrows
    public void assertThreadsFinishedWithCountDownLatch() {
        int count = 10;
        CountDownLatch end = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(new CountDownLatchFixture(end)).start();
        }
        end.await();
        System.out.println("主线程开始执行");
    }

    @Test
    @SneakyThrows
    public void assertAllThreadsStartAtTheSameTime() {
        CountDownLatch begin = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(new CountDownLatchFixture(begin)).start();
            begin.countDown();
        }
        Thread.currentThread().join(10000);
        System.out.println("主线程开始执行");
    }
}
