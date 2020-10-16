package com.example.mybatis.demomybatis.thread.fixture;

import lombok.AllArgsConstructor;

import java.util.concurrent.CountDownLatch;

/**
 * @author jacksparrow414
 * @date 2020/10/16 11:00
 */
@AllArgsConstructor
public class CountDownLatchFixture implements Runnable{
    
    private CountDownLatch countDownLatch;
    
    @Override
    public void run() {
        System.out.println("countDownLatch Thread" + Thread.currentThread().getName()+ " arrived");
        countDownLatch.countDown();
    }
}
