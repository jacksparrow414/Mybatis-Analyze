package com.example.mybatis.demomybatis.thread;

import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * 各种线程锁的例子.
 *
 * @author jacksparrow414
 * @date 2020/10/15
 */
public final class ThreadLock {

    private ThreadEntity entity;
    
    @Before
    public void setUp() {
         entity = new ThreadEntity();
    }
    
    @Test
    public void assertObjectHeaderNoLock() {
        System.out.println(ClassLayout.parseInstance(entity).toPrintable());
    }
    
    @Test
    @SneakyThrows
    public void assertBiasedLock() {
        System.out.println("虚拟机启动");
        TimeUnit.SECONDS.sleep(6);
        ThreadEntity threadEntity = new ThreadEntity();
        ClassLayout layout = ClassLayout.parseInstance(threadEntity);
        System.out.println("虚拟机启动6秒后");
        System.out.println(layout.toPrintable());
        synchronized (threadEntity){
            System.out.println("执行同步代码块");
            System.out.println("线程ID："+Thread.currentThread().getId());
            System.out.println(layout.toPrintable());
        }
        System.out.println("执行同步代码块结束");
        System.out.println(layout.toPrintable());
    }
    
    @Test
    public void assertThinLock() {
        ClassLayout layout = ClassLayout.parseInstance(entity);
        System.out.println(layout.toPrintable());
        synchronized (entity){
            System.out.println("执行同步体");
            System.out.println(layout.toPrintable());
        }
        System.out.println("执行同步体结束");
        System.out.println(layout.toPrintable());
    }
    
    @Test
    @SneakyThrows
    public void assertThinLockToFatLock() {
        ClassLayout layout = ClassLayout.parseInstance(entity);
        System.out.println("初始对象头");
        System.out.println(layout.toPrintable());
        Thread thread = new Thread(() ->{
            synchronized (entity){
                try {
                    TimeUnit.SECONDS.sleep(4);
                }catch (InterruptedException exception){
                
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("锁竞争之前的对象头");
        System.out.println(layout.toPrintable());
        synchronized (entity){
            System.out.println("主线程执行同步体时的对象头");
            System.out.println(layout.toPrintable());
        }
    }
}
