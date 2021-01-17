package com.example.mybatis.demomybatis.task;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author jacksparrow414
 * @date 2020/11/10 11:16
 */
@Component
@EnableScheduling
public class ScheduledTaskOccurException {
    
    // @Scheduled(fixedDelay = 5000)
    @SneakyThrows
    public void taskOccurException() {
        Thread.sleep(10000);
        int a = 1/0;
    }
}
