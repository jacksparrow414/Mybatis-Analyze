package com.example.mybatis.demomybatis.thread;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 线程并发测试, 用来模拟多线程并发
 *
 * @author jacksparrow414
 * @date 2020/10/3
 */
public final class ThreadConcurrencyTest {
    
    private ThreadEntity threadEntity;
    
    @Before
    public void setUp() {
        threadEntity = new ThreadEntity();
    }
    
    @Test
    public void assertMuchThreadsAccessThreadEntity() {
        System.out.println(LocalDateTime.now());
        for (int i = 0; i < 10000; i++) {
            (new Thread(new CommonThreadImplRunnable(threadEntity))).start();
        }
        System.out.println(LocalDateTime.now());
        assertThat(threadEntity.getNum(), is(10001));
    }
    
    @Test
    public void assertMuchThreadsAccessThreadSynchronized() {
        for (int i = 0; i < 10000; i++) {
            new CommonThreadExtThread(threadEntity).start();
        }
        assertThat(threadEntity.getNum(), is(10000));
    }
}
