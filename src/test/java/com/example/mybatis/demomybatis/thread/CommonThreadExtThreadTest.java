package com.example.mybatis.demomybatis.thread;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jacksparrow414
 * @date 2020/10/3
 */
public final class CommonThreadExtThreadTest {
    
    private ThreadEntity threadEntity;
    
    @Before
    public void setUp() {
        threadEntity = new ThreadEntity();
    }
    
    /**
     * 模拟线程并发
     */
    @Test
    public void assertThreadConcurrency() {
        (new CommonThreadExtThread(threadEntity)).start();
        (new CommonThreadExtThread(threadEntity)).start();
        (new CommonThreadExtThread(threadEntity)).start();
    }
}