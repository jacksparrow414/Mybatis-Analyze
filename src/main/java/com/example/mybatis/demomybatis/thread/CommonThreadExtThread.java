package com.example.mybatis.demomybatis.thread;

import lombok.AllArgsConstructor;

/**
 *
 * 继承Thread，需要重写父类的run方法
 * @author jacksparrow414
 * @date 2020/10/3
 */
@AllArgsConstructor
public class CommonThreadExtThread extends Thread{
    
    private ThreadEntity threadEntity;
    
    @Override
    public void run() {
        threadEntity.getNumVal();
    }
}
