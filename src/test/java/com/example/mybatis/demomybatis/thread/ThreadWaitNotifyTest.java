package com.example.mybatis.demomybatis.thread;

import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * @author jacksparrow414
 * @date 2020/10/20
 */
public final class ThreadWaitNotifyTest {
    static boolean flag = true;
    static Object lock = new Object();
    
    @Test
    @SneakyThrows
    public void assertThreadWaitNotify() {
        Thread threadN = new Thread(new threadN(), "threadN");
        Thread threadM = new Thread(new threadM(), "threadM");
        threadN.start();
        TimeUnit.SECONDS.sleep(1);
        threadM.start();
        TimeUnit.SECONDS.sleep(10);
    }
    
    static class threadN implements Runnable {
    
        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    System.out.println(Thread.currentThread().getName() + " flag is true wait");
                    try {
                        lock.wait();
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
    
                System.out.println(Thread.currentThread().getName() + "falg is false running");
            }
        }
    }
    
    static class threadM implements  Runnable {
    
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " acquire lock");
                flag = false;
                // 唤醒所有等待的线程
                
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
            lock.notifyAll();
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " acquire again");
            }
        }
        
    }
}
