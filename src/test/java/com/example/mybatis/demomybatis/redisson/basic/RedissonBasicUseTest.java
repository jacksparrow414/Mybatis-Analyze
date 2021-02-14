package com.example.mybatis.demomybatis.redisson.basic;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.BatchOptions;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBatch;
import org.redisson.api.RBucket;
import org.redisson.api.RBuckets;
import org.redisson.api.RDeque;
import org.redisson.api.RMap;
import org.redisson.api.RQueue;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RSet;
import org.redisson.api.RSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.protocol.ScoredEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * redis命令对应的redisson的相关文档
 * <a href="https://github.com/redisson/redisson/wiki/11.-Redis%E5%91%BD%E4%BB%A4%E5%92%8CRedisson%E5%AF%B9%E8%B1%A1%E5%8C%B9%E9%85%8D%E5%88%97%E8%A1%A8">redisson文档</a>
 * @author jacksparrow414
 * @date 2021/2/14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RedissonBasicUseTest {
    
    private static final String BASIC_BUCKET = "basic";
    
    @Autowired
    private RedissonClient redissonClient;
    
    /**
     * SET和GET.
     */
    @Test
    public void assertBasicSetKey() {
        RBucket<String> bucket = redissonClient.getBucket(BASIC_BUCKET);
        String basic = bucket.getAndSet("redisson basic");
        Assert.assertTrue(StrUtil.isNotBlank(basic));
        log.info("basic的值为{}", basic);
    }
    
    /**
     * MSET和MGET设置多个key的值和获取多个key的value.
     * 执行批量操作使用RBatch
     */
    @Test
    public void assertBasicSetMultiKey() {
        RBatch batch = redissonClient.createBatch(BatchOptions.defaults());
        batch.getBucket(BASIC_BUCKET + "first").setAsync("first");
        batch.getBucket(BASIC_BUCKET + "second").setAsync("second");
        batch.execute();
        RBuckets buckets = redissonClient.getBuckets();
        Map<String, Object> stringObjectMap = buckets.get("basicsecond", "basicfirst");
        Assert.assertNotNull(stringObjectMap);
        stringObjectMap.forEach((k, v) -> {
            log.info("key是{}", k);
            log.info("value是{}", v.toString());
        });
    }
    
    /**
     * INCR、DECR、INCRBY、DECRBY.
     * 数字的自增，自减，增减指定数字，减少指定数字.
     */
    @Test
    public void assertBasicNumberOperation() {
        RAtomicLong myNumber = redissonClient.getAtomicLong("myNumber");
        long incrementAndGet = myNumber.incrementAndGet();
        Assert.assertEquals(incrementAndGet, 1);
        myNumber.getAndAdd(4);
        Assert.assertEquals(myNumber.get(), 5L);
        myNumber.decrementAndGet();
        Assert.assertEquals(myNumber.get(), 4L);
        // redisson没有DECRBY的对应命令，直接输入【负数】即可实现减法
        myNumber.getAndAdd(-4);
        Assert.assertEquals(myNumber.get(), 0L);
    }
    
    /**
     * DEL示例.
     */
    @Test
    public void assertBasicDelete() {
        RBucket<Object> foo = redissonClient.getBucket("foo");
        Assert.assertTrue(foo.delete());
    }
    
    /**
     * hash的使用
     * HMSET、HGETALL使用.
     */
    @Test
    public void assertBasicHash() {
        RMap<String, String> myCar = redissonClient.getMap("myCar");
        // 为map赋值，相当于HMSET key1 value1 key2 value2
        myCar.put("name", "carName");
        myCar.put("price", "carPrice");
        Set<String> keySet = new HashSet<>();
        keySet.add("name");
        keySet.add("price");
        // 相当于HGETALL
        Map<String, String> myCar1 = myCar.getAll(keySet);
        Assert.assertTrue(CollUtil.isNotEmpty(myCar1));
        myCar1.forEach((k,v) -> {
            log.info(k);
            log.info(v);
        });
    }
    
    /**
     * 双端队列Deque和队列Queue.
     * LPUSH、RPUSH、RPOPLPUSH
     */
    @Test
    public void assertBasicList() {
        RDeque<String> myList = redissonClient.getDeque("myList");
        myList.addFirst("lpush");
        myList.addLast("rpush");
        myList.removeFirst();
        RQueue<String> myQueue = redissonClient.getQueue("myQueue");
        myQueue.add("myqueuefirst");
        // RPOPLPUSH
        myList.pollLastAndOfferFirstTo(myQueue.getName());
        Assert.assertTrue(CollUtil.isNotEmpty(myList));
        myQueue.forEach(System.out::println);
    }
    
    /**
     * SADD、SMEMBERS、SISMEMBER使用
     */
    @Test
    public void assertBasicSet() {
        RSet<Integer> myset = redissonClient.getSet("myset");
        myset.add(1);
        Set<Integer> integerSet = new HashSet<>();
        integerSet.add(3);
        integerSet.add(2);
        myset.addAll(integerSet);
        Assert.assertTrue(myset.contains(2));
        myset.readAll().forEach(System.out::println);
        // 有序集合
        RSortedSet<Integer> sortset = redissonClient.getSortedSet("sortset");
        sortset.addAll(myset);
        Assert.assertNotNull(sortset);
        sortset.readAll().forEach(System.out::println);
    }
    
    /**
     * 有序集合ZSET的使用.
     * ZRANGBYSCORE等使用.
     */
    @Test
    public void assertBasicZSet() {
        RScoredSortedSet<String> scoreSet = redissonClient.getScoredSortedSet("scoreSet");
        scoreSet.add(12, "jack");
        scoreSet.add(2, "tom");
        scoreSet.addAndGetRank(23, "jam");
        // 两个boolean值代表是否包含边界
        Collection<String> valueRange = scoreSet.valueRange(12, true, 23, false);
        Assert.assertTrue(CollUtil.isNotEmpty(valueRange));
        valueRange.forEach(System.out::println);
        // 获取value的同时获取score，ZRANGEBYSCORE...WITHSCORES
        Collection<ScoredEntry<String>> scoredEntries = scoreSet.entryRange(1, true, 23, true);
        Assert.assertTrue(CollUtil.isNotEmpty(scoredEntries));
        scoredEntries.forEach(each -> {
            log.info(each.getValue());
            log.info(each.getScore().toString());
        });
    }
}