package com.example.mybatis.demomybatis.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * Lock结合Condition实现等待通知，记得释放锁。所有等待、通知机制在lock和unlock之间
 *
 * Lock lock = new ReentrantLock();
 * Condition condition = lock.newCondition();
 * lock.lock;/lock.tryLock();/lock.tryLock(long nanos)
 * try{
 *     condition.await();/condition.await(long nanos);/condition.signal();/condition.signalAll();
 * } finally {
 *     lock.unlock();
 * }
 *
 * @author jacksparrow414
 * @date 2020/10/21
 */
public final class ThreadLockConditionTest {
    
    // 可重入锁，默认是非公平锁
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static boolean flag = true;
    
    @Test
    @SneakyThrows
    public void assertLockCondition() {
        Thread threadN = new Thread(new ThreadLockConditionTest.threadN(), "threadN");
        Thread threadM = new Thread(new ThreadLockConditionTest.threadM(), "threadM");
        threadN.start();
        TimeUnit.SECONDS.sleep(1);
        threadM.start();
        TimeUnit.SECONDS.sleep(10);
    }
    
    static class threadN implements Runnable {
        
        @Override
        public void run() {
            // 不要将获取锁的过程写在try块中，因为如果在获取锁（自定义锁的实现）时发生了异常，
            //异常抛出的同时，也会导致锁无故释放。
            lock.lock();
            while (flag) {
                System.out.println(Thread.currentThread().getName() + " flag is true wait");
                try {
                    condition.await();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                } finally {
                    // 可重入锁要记得手动释放锁
                    lock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + "falg is false running");
            }
        }
    }
    
    static class threadM implements  Runnable {
        
        @Override
        public void run() {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " acquire lock");
            flag = false;
            // 唤醒所有等待的线程
            try {
                TimeUnit.SECONDS.sleep(3);
                condition.signalAll();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }finally {
                lock.unlock();
            }
            // 唤醒等待线程的操作要放到同步体里，否则会报错
            // condition.signalAll();
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " acquire again");
            condition.signalAll();
            lock.unlock();
        }
        
    }
}
