package com.example.mybatis.demomybatis.thread;

import lombok.AllArgsConstructor;

/**
 * 创建线程的两种方式
 * 1、实现Runnable接口-启动方式- (new Thread(new CommonThreadImplRunnable())).start();
 * 2、继承Thread类-启动方式- (new CommonThreadExtThread()).start();
 *
 *
 * @author jacksparrow414
 * @date 2020/10/3
 */
@AllArgsConstructor
public class CommonThreadImplRunnable implements Runnable{
    
    private ThreadEntity threadEntity;
    
    @Override
    public void run() {
        System.out.println(threadEntity.getNumValue());
    }
}
