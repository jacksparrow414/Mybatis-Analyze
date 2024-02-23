package com.example.mybatis.demomybatis.thread.fixture;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

/**
 * @author jacksparrow414
 * @date 2020/10/16 11:00
 */
@AllArgsConstructor
public class CountDownLatchFixture implements Runnable{
    
    private CountDownLatch countDownLatch;

    @SneakyThrows
    @Override
    public void run() {
//        case1: 所有线程一起执行，对应com.example.mybatis.demomybatis.thread.CountDownLatchTest.assertAllThreadsStartAtTheSameTime
//        countDownLatch.await();
        System.out.println("countDownLatch Thread" + Thread.currentThread().getName()+ " arrived "+ LocalDateTime.now());
//        case2: 主线程等待所有线程执行完毕之后，对应com.example.mybatis.demomybatis.thread.CountDownLatchTest.assertThreadsFinishedWithCountDownLatch
//        countDownLatch.countDown();
    }
}
