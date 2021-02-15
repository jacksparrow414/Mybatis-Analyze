package com.example.mybatis.demomybatis.thread;

import lombok.SneakyThrows;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 线程并发测试, 用来模拟多线程并发
 *
 * 同一个对象的不同线程的并发请求
 *
 * @author jacksparrow414
 * @date 2020/10/3
 */
public final class ThreadConcurrencyTest {
    
    @Test
    @SneakyThrows
    public void assertMuchThreadsAccessThreadSynchronized() {
        ThreadEntity threadEntity = new ThreadEntity();
        for (int i = 0; i < 500; i++) {
            new CommonThreadExtThread(threadEntity).start();
        }
        Thread.sleep(5000);
        assertThat(threadEntity.getNum(), is(501));
    }
    
    public static void main(String[] args) {
        CommonThreadImplRunnable commonThreadImplRunnable = new CommonThreadImplRunnable();
        // 三个线程用的对象都是同一个
        new Thread(commonThreadImplRunnable).start();
        new Thread(commonThreadImplRunnable).start();
        new Thread(commonThreadImplRunnable).start();
    }
}
