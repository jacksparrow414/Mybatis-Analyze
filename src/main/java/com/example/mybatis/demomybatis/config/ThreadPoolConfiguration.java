package com.example.mybatis.demomybatis.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 线程池配置.
 * @author jacksparrow414
 * @date 2020/11/9 10:10
 */
@Configuration
public class ThreadPoolConfiguration {
    
    @Bean
    public ExecutorService threadPoolExecutor() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("thread-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(20), namedThreadFactory, new DiscardPolicy());
        // 预热线程池,使核心新出全部启动
         threadPoolExecutor.prestartAllCoreThreads();
         // 默认情况下核心线程是不会被回收的，如果要回收核心线程，使用下列方法
//         threadPoolExecutor.allowsCoreThreadTimeOut();
        return threadPoolExecutor;
    }
}
