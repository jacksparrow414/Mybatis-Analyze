package com.example.mybatis.demomybatis.thread.fixture;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.concurrent.CyclicBarrier;

/**
 * @author jacksparrow414
 * @date 2020/10/16 11:37
 */
@AllArgsConstructor
public class CyclicBarrierFixture implements Runnable{
    
    private CyclicBarrier barrier;
    
    
    @Override
    @SneakyThrows
    public void run() {
        System.out.println("线程到达"+ Thread.currentThread().getId());
        barrier.await();
        System.out.println("开始执行" + Thread.currentThread().getId() +" "+ LocalDateTime.now());
    }
}
