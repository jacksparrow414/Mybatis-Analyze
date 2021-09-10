package com.example.mybatis.demomybatis.thread;

import lombok.SneakyThrows;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 线程并发测试, 用来模拟多线程并发
 *
 * 同一个对象的不同线程的并发请求
 *
 * @author jacksparrow414
 * @date 2020/10/3
 */
public final class ThreadConcurrencyTest {
    
    @Test
    @SneakyThrows
    public void assertMuchThreadsAccessThreadSynchronized() {
        ThreadEntity threadEntity = new ThreadEntity();
        for (int i = 0; i < 500; i++) {
            new CommonThreadExtThread(threadEntity).start();
        }
        Thread.sleep(5000);
        assertThat(threadEntity.getNum(), is(501));
    }
    
    public static void main(String[] args) {
        CommonThreadImplRunnable commonThreadImplRunnable = new CommonThreadImplRunnable();
        // 三个线程用的对象都是同一个
        new Thread(commonThreadImplRunnable).start();
        new Thread(commonThreadImplRunnable).start();
        new Thread(commonThreadImplRunnable).start();
        // 关于Integer.valueOf(1) 会造成死锁的问题
        // https://wiki.sei.cmu.edu/confluence/display/java/LCK01-J.+Do+not+synchronize+on+objects+that+may+be+reused
        Integer lock = Integer.valueOf(1);
        Integer lock2 = Integer.valueOf(1);
//      Integer lock = new Integer(1);
//      Integer lock2 = new Integer(1);
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("ThreadA获得锁");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "ThreadA").start();
        new Thread(() -> {
            synchronized (lock2) {
                System.out.println("ThreadB获得锁");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "ThreadB").start();
    }
}
