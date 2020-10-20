package com.example.mybatis.demomybatis.thread;

import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * 不是用等待同步/机制，仅仅靠死循环检测，条件不满足则休眠一段时间.
 *
 * @author jacksparrow414
 * @date 2020/10/20
 */
public final class ThreadWaitTest {
    static boolean flag = true;
    static Object lock = new Object();
    
    @Test
    @SneakyThrows
    public void assertThreadWait() {
        Thread threadN = new Thread(new ThreadN(), "threadN");
        Thread threadM = new Thread(new ThreadM(), "threadM");
        threadN.start();
        threadM.start();
        // 为了测试用例能够打印完全，特意休眠10秒
        TimeUnit.SECONDS.sleep(10);
    }
    
    static class ThreadN implements Runnable {
    
        @Override
        public void run() {
            while (flag) {
                try {
                    System.out.println(Thread.currentThread().getName() + "条件不满足，开始休眠，循环获取");
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " acquire lock");
            }
        }
    }
    
    static class ThreadM implements Runnable {
        @Override
        public void run() {
            while (flag) {
                synchronized (lock) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " acquire lcok");
                        // 第二种休眠方法
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                    flag = false;
                }
            }
            
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " acquire again");
            }
        }
    }
}
