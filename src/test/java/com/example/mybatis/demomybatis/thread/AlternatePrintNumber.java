package com.example.mybatis.demomybatis.thread;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 利用线程的等待、通知机制交替打印奇偶数
 *
 * @author jacksparrow414
 * @date 2021/3/6
 */
@Slf4j
public class AlternatePrintNumber {
    
    static int num = 0;
    
    static final Object lock = new Object();
    
    @SneakyThrows
    @Test
    public void assertAlternatePrintNumber() {
        Thread oddNumberThread = new Thread(new OddNumberThread(), "oddNumberThread");
        Thread evenNumberThread = new Thread(new EvenNumberThread(), "evenNumberThread");
        oddNumberThread.start();
        evenNumberThread.start();
        
        Thread.sleep(30000);
    }
    
    static class OddNumberThread implements Runnable {
        
        @Override
        public void run() {
            while (num <= 10) {
                synchronized (lock) {
                    if (num % 2 == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        log.info("num为{}", num);
                        num++;
                        lock.notifyAll();
                    }
                }
            }
        }
    }
    
    static class EvenNumberThread implements Runnable {
        
        @Override
        public void run() {
            while (num <= 10) {
                synchronized (lock) {
                    if (num % 2 == 0) {
                        log.info("num为{}", num);
                        num++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
