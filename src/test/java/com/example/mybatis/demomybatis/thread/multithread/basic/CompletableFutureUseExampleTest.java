package com.example.mybatis.demomybatis.thread.multithread.basic;

import cn.hutool.core.util.StrUtil;
import com.example.mybatis.demomybatis.config.ThreadPoolConfiguration;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 使用CompletableFuture处理{@link FutureUsExampleTest}说的情况，
 * CompletableFuture还是异步的，如果想要执行完子线程再执行主线程，则可以CompletableFuture.join()
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

    @Test
    public void assertNoResultFuture() {
        CompletableFuture<Void> voidCompletableFutureFirst = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, threadPoolExecutor);
        CompletableFuture<Void> voidCompletableFutureSecond = CompletableFuture.runAsync(() -> System.out.println(Thread.currentThread().getName()), threadPoolExecutor);
        // 子线程执行完毕, 再执行主线程
        CompletableFuture.allOf(voidCompletableFutureSecond, voidCompletableFutureFirst).join();
        System.out.println("主线程");
    }

    /**
     * 对于多个并行的子任务，如果这些子任务都没有返回值，那么当子任务执行过程中出现异常，在最终汇合处可以执行handle进行异常的处理.
     * 但是如果多个子任务都出现异常了,只会打印一个异常.</br>
     *
     * <b>所以,正确的操作是要对每个子任务进行异常的处理，在每个子任务中使用handel或者</b>
     */
    @Test
    public void assertExceptionNoResultFuture() {
        CompletableFuture<Void> voidCompletableFutureFirst = CompletableFuture.runAsync(() -> {
            int e = 1 / 0;
        }, threadPoolExecutor);

        CompletableFuture<Void> voidCompletableFutureSecond = CompletableFuture.runAsync(() -> {
            List<Integer> list = Lists.newArrayList();
            System.out.println(Thread.currentThread().getName());
            list.get(1);
        }, threadPoolExecutor);

        CompletableFuture.allOf(voidCompletableFutureSecond, voidCompletableFutureFirst).handle((r, e) -> {
            System.out.println("最终打印的异常"+e.getMessage());
            return r;
        }).join();

        // 正确使用姿势
        voidCompletableFutureFirst.handle((r, e) -> {
            if (e == null) {
                return r;
            }else {
                return e.getMessage();
            }
        });
    }

    /**
     * 对于有返回值的子任务，可以使用exceptionally或者handle进行异常处理.
     */
    @Test
    public void assertExceptionResultFuture() {
        CompletableFuture<String> completableFutureFirst = CompletableFuture.supplyAsync(() -> {
            return Thread.currentThread().getName();
        }, threadPoolExecutor).exceptionally(e -> {
            if (e == null) {
                return "异常处理完毕";
            }else {
                return "没有异常";
            }
        });

        CompletableFuture<String> completableFutureSecond = CompletableFuture.supplyAsync(() -> {
            int e = 1/0;
            return Thread.currentThread().getName();
        }, threadPoolExecutor).handle((r, e) -> {
            if (e == null) {
                return r;
            }else {
                return "没有异常";
            }
        });

        // 不能在allOf后使用exceptionally， 因为exceptionally是一个Function，要返回值的，而allOf没有返回值
//        CompletableFuture.allOf(completableFutureSecond, completableFutureFirst).exceptionally(e -> {
//            System.out.println("7777");
//            return;
//        });
    }
}