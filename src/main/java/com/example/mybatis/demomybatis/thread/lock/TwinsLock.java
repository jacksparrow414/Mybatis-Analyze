package com.example.mybatis.demomybatis.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 同一时刻内只允许两个线程同时访问，超过两个将被阻塞。
 *
 * 实现自定义的锁，要实现Lock接口
 *
 * 使用者需要继承同步器并重写指定的
 * 方法，随后将同步器Sync组合在自定义同步组件TwinsLock的实现中，
 * 并调用同步器提供的模板方法，而这些
 * 模板方法将会调用使用者重写的方法。
 * @author jacksparrow414
 * @date 2020/10/19
 */
public class TwinsLock implements Lock {
    
    
    /**
     * 所有自定义的同步器都要继承AQS.
     *
     * 根据自定义同步器要实现的功能，选择性的重写以下5个方法
     *
     * tryAcquire-独占式，只有一个线程可以获得锁
     *
     * tryAcquireShared-共享式，多个线程可以获得锁
     *
     * 以上两个方法：返回值大于、等于0，代表锁获得成功；反之则代表获取锁失败
     *
     * tryRelease-独占式，线程释放锁
     *
     * tryReleaseShared-共享式，共享式释放同步状态
     *
     * 以上两个方法：返回true 代表锁释放成功；反之则代表锁释放失败
     *
     * isHeldExclusively 判断当前线程和同步器中的线程是否一致
     */
    private static final class Sync extends AbstractQueuedSynchronizer {
        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must large then zero");
            }
            // 修改、设置、获取同步状态直接使用AQS提供的方法即可
            setState(count);
        }
    
        /**
         * 获取锁.
         * @param reduceCount
         * @return
         */
        @Override
        protected int tryAcquireShared(int reduceCount) {
            for (;;) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }
    
        /**
         * 释放锁.
         * @param returnCount
         * @return
         */
        @Override
        protected boolean tryReleaseShared(int returnCount) {
            for (;;) {
                int current = getState();
                int newCount = current + returnCount;
                // 使用AQS提供的方法CAS设置值，可以保证原子性
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }
    }
    
    private final Sync sync = new Sync(2);
    
    /**
     * 实现Lock的lock、unlock接口.
     *
     * 在这些接口里调用AQS的模板方法acquire、acquireShared、release、releaseShared
     *
     * tryAcquireNanos、tryAcquireSharedNanos 这两个是有超时时间的
     *
     * 这里的方法会最终调用上面重写的方法
     */
    @Override
    public void lock() {
        sync.acquireShared(1);
    }
    
    @Override
    public void unlock() {
        sync.releaseShared(1);
    }
    
    @Override
    public void lockInterruptibly() throws InterruptedException {
    
    }
    
    @Override
    public boolean tryLock() {
        return false;
    }
    
    @Override
    public boolean tryLock(final long time, final TimeUnit unit) throws InterruptedException {
        return false;
    }
    
    @Override
    public Condition newCondition() {
        return null;
    }
    
    
}
