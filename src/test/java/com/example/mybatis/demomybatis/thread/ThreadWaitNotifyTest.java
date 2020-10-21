package com.example.mybatis.demomybatis.thread;

import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * 线程的wait/notify机制.
 *
 * 对象.wait()、对象.notify()等操作前提是已经获得了锁。也就是这些方法要在【同步体里执行】
 *
 * synchronized(object) {
 *     object.wait();/object.wait(long timeout)/object.notify();/object.notifyAll();
 * }
 *
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
                    lock.notifyAll();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
            // 唤醒等待线程的操作要放到同步体里，否则会报错
            // lock.notifyAll();
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " acquire again");
            }
        }
        
    }
}
