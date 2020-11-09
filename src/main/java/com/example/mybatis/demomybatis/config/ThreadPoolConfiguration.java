package com.example.mybatis.demomybatis.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
        return new ThreadPoolExecutor(5, 10,
                30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(20), namedThreadFactory);
    }
}
