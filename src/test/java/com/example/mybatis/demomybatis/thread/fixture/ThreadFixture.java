package com.example.mybatis.demomybatis.thread.fixture;

/**
 * @author jacksparrow414
 * @date 2020/10/16 10:39
 */
public class ThreadFixture implements Runnable{
    
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId() + "---------------arrived ");
    }
}
