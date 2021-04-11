package com.example.mybatis.demomybatis.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * https://zhuanlan.zhihu.com/p/47948392
 *
 * 使用lock condition 来交替打印数字
 *
 * 一定要记住lock的使用格式为:
 * lock.lock()
 * try{
 *     // do something
 * }finally{
 *     lock.unlock();
 * }
 * @author jacksparrow414
 * @date 2021/3/6
 */
@Slf4j
public final class LockConditionPrintNumber {
   
   static int num = 0;
   static Lock lock = new ReentrantLock();
   static Condition oddCondition = lock.newCondition();
   static Condition evenCondition = lock.newCondition();
   
   @Test
   @SneakyThrows
   public void assertPrintNumber() {
       new Thread(new oddThread(), "odd").start();
       new Thread(new evenThread(), "even").start();
       Thread.sleep(2000);
   }
   
   class oddThread implements Runnable {
       @Override
       public void run() {
           while (num <= 10) {
               lock.tryLock();
               try {
                   if (num % 2 == 1) {
                       log.info("num值为{}", num++);
                       evenCondition.signalAll();
                   }else {
                       oddCondition.await();
                   }
               } catch (InterruptedException e) {
                   log.error("错误", e);
               } finally {
                   lock.unlock();
               }
           }
       }
   }
    
    class evenThread implements Runnable {
        @Override
        public void run() {
            while (num <= 10) {
                lock.lock();
                try {
                    if (num % 2 == 0) {
                        log.info("num值为{}", num++);
                        oddCondition.signalAll();
                    }else {
                        evenCondition.await();
                    }
                } catch (InterruptedException e) {
                    log.error("错误", e);
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
