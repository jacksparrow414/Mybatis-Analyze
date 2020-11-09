package com.example.mybatis.demomybatis.jedis;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author jacksparrow414
 * @date 2020/5/14 10:03
 */
@Configuration
public class JedisPoolCustom {

    private static JedisPool jedisPool = null;

    @Bean
    public JedisPool customJedisPool(){
        if (ObjectUtil.isNull(jedisPool)){
            this.initJedisPool(jedisPool);
        }
        return jedisPool;
    }

    /**
     * 初始化JedisPool
     * @author jacksparrow414
     * @param jedisPool
     * @return void
     */
    private void initJedisPool(JedisPool jedisPool){
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            // 总对象 最大数量-默认值8-负数为没有限制
            poolConfig.setMaxTotal(8);
            // 空闲对象 最大数量-默认值8-负数为没有限制
            poolConfig.setMaxIdle(8);
            //  空闲对象 最小数量-默认值为0-负数则为没有限制
            poolConfig.setMinIdle(0);
            // 执行空闲对象的使用 顺序 两种策略： FIFO(先进先出) LIFO(后进先出)
            poolConfig.setLifo(true);
            // 获取对象时是否使用公平锁机制 默认为false
            poolConfig.setFairness(false);
            // 驱逐线程的运行周期，驱逐线程用来清除池中空闲的对象，默认为-1，即没有驱逐线程，此项不为-1，下面的setMinEvictableIdleTimeMillis回收设置才能生效
            poolConfig.setTimeBetweenEvictionRunsMillis(1800000L);
            // 对象最小空闲时间，超过这个时间会被回收
            poolConfig.setMinEvictableIdleTimeMillis(1800000L);
            // 当池中没有可用连接时，是否阻塞等待
            poolConfig.setBlockWhenExhausted(true);
            // 最大等待时间，如果是-1，则一直等待，直到池中有可用 连接为止
            poolConfig.setMaxWaitMillis(6000);
            JedisPoolCustom.jedisPool = new JedisPool(poolConfig,"localhost");
    }
}
