package com.example.mybatis.demomybatis.thread;

import lombok.Getter;

/**
 *
 * 作为一个线程中的对象
 * @author jacksparrow414
 * @date 2020/10/3
 */
public class ThreadEntity {
    
    @Getter
    private Integer num = 1;
    
    /**
     * 同步方法
     *
     */
    public synchronized int getNumValue() {
        return num++;
    }
    
    /**
     * 同步代码块
     * ()里放的是要锁的东西
     */
    public int getNumVal() {
        synchronized (num) {
            num++;
        }
        return num;
    }
}
