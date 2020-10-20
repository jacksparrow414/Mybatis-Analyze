package com.example.mybatis.demomybatis.thread;

import org.junit.Before;
import org.junit.Test;

/**
 * 同一个实例并发
 * @author jacksparrow414
 * @date 2020/10/3
 */
public final class CommonThreadImplRunnableTest {
    
    private CommonThreadImplRunnable commonThreadImplRunnable;
    
    @Before
    public void setUp() {
        commonThreadImplRunnable = new CommonThreadImplRunnable();
    }
    
    /**
     * 模拟线程并发
     */
    @Test
    public void assertThreadConcurrency() {
        (new Thread(commonThreadImplRunnable)).start();
        (new Thread(commonThreadImplRunnable)).start();
        (new Thread(commonThreadImplRunnable)).start();
    }
}