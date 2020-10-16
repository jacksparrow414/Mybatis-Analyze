package com.example.mybatis.demomybatis.thread;

import com.example.mybatis.demomybatis.thread.fixture.CyclicBarrierFixture;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CyclicBarrier;

/**
 *
 * <a href="https://www.jianshu.com/p/3e9e6a72d22e"/>
 * @author jacksparrow414
 * @date 2020/10/16 10:57
 */
public final class CyclicBarrierTest {
    
    @Test
    @SneakyThrows
    public void assertCyclicBarrierTest() {
        int count = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count, () -> {
            System.out.println("所有线程到达完毕");
        });
        for (int i = 0; i < count; i++) {
            new Thread(new CyclicBarrierFixture(cyclicBarrier)).start();
        }
    
        Thread.sleep(2000);
        System.out.println("开始执行主线程");
    }
}
