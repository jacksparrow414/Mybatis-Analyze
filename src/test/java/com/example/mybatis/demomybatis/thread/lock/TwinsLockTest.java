package com.example.mybatis.demomybatis.thread.lock;

import java.util.concurrent.locks.Lock;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * @author jacksparrow414
 * @date 2020/10/19
 */
public final class TwinsLockTest {
    
    @Test
    @SneakyThrows
    public void testLock() {
        final Lock lock = new TwinsLock();
        
        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }finally {
                        lock.unlock();
                    }
                }
            }
        }
    
        for (int i = 0; i < 10; i++) {
            Worker worker = new Worker();
            worker.setDaemon(true);
            worker.start();
        }
    
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println();
        }
    }
}