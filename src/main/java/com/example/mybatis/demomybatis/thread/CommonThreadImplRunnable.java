package com.example.mybatis.demomybatis.thread;

import cn.hutool.core.util.RandomUtil;
import lombok.Getter;
import lombok.SneakyThrows;

/**
 * 创建线程的两种方式
 * 1、实现Runnable接口-启动方式- (new Thread(new CommonThreadImplRunnable())).start();
 * 2、继承Thread类-启动方式- (new CommonThreadExtThread()).start();
 *
 *
 * @author jacksparrow414
 * @date 2020/10/3
 */
public class CommonThreadImplRunnable implements Runnable{
    
    /**
     * tmp是线程不安全的
     */
    @Getter
    private int tmp = 0;
    
    @Override
    public void run() {
        count();
    }
    
    @SneakyThrows
    private void count() {
        while (true) {
            // 线程休眠,模拟在加入同步方法的时候,CPU进行切换，否则该方法将由一个线程执行完毕
             Thread.sleep(RandomUtil.randomInt(0, 50));
            synchronized (this) {
                if (tmp < 10) {
                    // 线程休眠，模拟CPU切换造成的异常
                    Thread.sleep(RandomUtil.randomInt(0, 50));
                    System.out.println(Thread.currentThread().getName() + ":" + tmp++);
                }
            }
        }
    }
}
