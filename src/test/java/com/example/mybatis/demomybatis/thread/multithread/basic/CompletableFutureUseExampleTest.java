package com.example.mybatis.demomybatis.thread.multithread.basic;

import cn.hutool.core.util.StrUtil;
import com.example.mybatis.demomybatis.config.ThreadPoolConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 使用CompletableFuture处理{@link FutureUsExampleTest}说的情况
 * @author jacksparrow414
 * @date 2020/11/9 13:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ThreadPoolConfiguration.class)
public final class CompletableFutureUseExampleTest {
    
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;
    
    @Test
    public void assertCompletableFuture() {
        // 传入自定义线程池，如果不传，使用默认的线程池，应该传入自定义的线程池，方便管理
        CompletableFuture<String> completableFuture1 = CompletableFuture
                // 有返回值
                .supplyAsync(() -> Thread.currentThread().getName(), threadPoolExecutor)
                // 拿到上一步的结果之后，再执行
                .whenComplete((r, e) -> System.out.println("第一个异步线程执行完毕 "+r));
        CompletableFuture<String> completableFuture2 = CompletableFuture
                .supplyAsync(() -> Thread.currentThread().getName(), threadPoolExecutor)
                // 使用上一步结果再执行，有返回值
                .thenApply(r -> r+"第二个异步线程执行完毕");
        CompletableFuture<Void> completableFuture4 = CompletableFuture
                .supplyAsync(() -> Thread.currentThread().getName(), threadPoolExecutor)
                // 使用上一步结果再执行，无返回值
                .thenAccept(System.out::println);
        // 组装两个线程的执行结果
        CompletableFuture<String> completableFuture3 = completableFuture1
                .thenCombine(completableFuture2, (str1, str2) -> str1 + str2)
                .whenComplete((r, e) -> System.out.println("整合两个线程结果完毕，结果是：" + r));
        // CompletableFuture.join()方法和get()方法一样，都是阻塞获取，只不过抛出的异常不一样而已
        assertTrue(StrUtil.isNotBlank(completableFuture3.join()));
    }
    
    @Test
    public void assertAllCompletableFutureFinished() {
        // 如果要等待2+个以上的CompletableFuture都执行完毕，再去执行，上面的thenCombine显然不适合.
        // 对于多个CompletableFuture可以使用allOf，只不过没有返回值，并且不能使用前几个CompletableFuture的返回值
        CompletableFuture<String> completableFuture1 = CompletableFuture
                .supplyAsync(() -> Thread.currentThread().getName(), threadPoolExecutor)
                .whenComplete((r, e) -> System.out.println("第一个异步线程执行完毕 "+r));
        CompletableFuture<String> completableFuture2 = CompletableFuture
                .supplyAsync(() -> Thread.currentThread().getName(), threadPoolExecutor)
                .thenApply(r -> r+"第二个异步线程执行完毕");
        CompletableFuture<Void> completableFuture4 = CompletableFuture
                .supplyAsync(() -> Thread.currentThread().getName(), threadPoolExecutor)
                .thenAccept(System.out::println);
        CompletableFuture.allOf(completableFuture1, completableFuture2, completableFuture4)
                .whenComplete((r,e) -> System.out.println(" 前面三个都异步线程都组合完毕"));
    }
}