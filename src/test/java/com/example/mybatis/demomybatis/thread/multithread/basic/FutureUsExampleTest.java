package com.example.mybatis.demomybatis.thread.multithread.basic;

import com.example.mybatis.demomybatis.config.ThreadPoolConfiguration;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 在一个大的异步任务中，线程1、2执行完毕之后再执行线程3
 *
 * 然而还有一种更常见的场景是
 * 客户端请求服务端接口，该接口需要调用其他N个微服务的接口
 *
 * 譬如 请求我的购物车，那么就需要去调用用户的rpc、商品详情的rpc、库存rpc、优惠券等等好多个服务。
 * 同时，这些服务还有相互依赖关系，譬如必须先拿到商品id后，才能去库存rpc服务请求库存信息。 最
 * 终全部获取完毕后，或超时了，就汇总结果，返回给客户端
 *
 * 如果使用Future则请求线程要阻塞，等待其他线程执行完毕之后，再组装数据返回给客户端
 * @author jacksparrow414
 * @date 2020/11/9 11:11
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ThreadPoolConfiguration.class)
public class FutureUsExampleTest {
    
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;
    
    @Test
    @SneakyThrows
    public void assertFuture() {
        Future<String> future1 = threadPoolExecutor.submit(() -> Thread.currentThread().getName());
        Future<String> future2 = threadPoolExecutor.submit(() -> Thread.currentThread().getName());
        // 死循环,直到future1、future2都执行完毕再执行future3
        for (;;) {
            if (future1.isDone() && future2.isDone()) {
                System.out.println(future1.get());
                System.out.println(future2.get());
                Future<String> future3 = threadPoolExecutor.submit(() -> Thread.currentThread().getName());
                // get方法会阻塞当前线程，直到有返回
                future3.get();
                break;
            }
        }
    }
}