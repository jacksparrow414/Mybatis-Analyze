package com.example.mybatis.demomybatis.thread;

import org.junit.Test;

/**
 * @author jacksparrow414
 * @date 2020/10/3
 */
public final class CommonThreadImplRunnableTest {
    
    @Test
    public void assertThreadConcurrency() {
        (new Thread(new CommonThreadImplRunnable(new ThreadEntity()))).start();
        (new Thread(new CommonThreadImplRunnable(new ThreadEntity()))).start();
        (new Thread(new CommonThreadImplRunnable(new ThreadEntity()))).start();
    }
}