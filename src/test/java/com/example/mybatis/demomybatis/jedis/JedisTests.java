package com.example.mybatis.demomybatis.jedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.mybatis.demomybatis.entity.SolrSpringUserEntity;
import com.example.mybatis.demomybatis.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author jacksparrow414
 * @date 2020/5/14 11:24
 */
@SpringBootTest
public class JedisTests {

    @Autowired
    JedisPool jedisPool;

    @Test
     void getJedisPoolTest(){
        Jedis jedis = jedisPool.getResource();
        jedis.set("dhb","杜泓波");
        jedisPool.close();
    }

    @Test
     void testSetDb(){
        Jedis jedis = jedisPool.getResource();
        // 切换库
        jedis.select(3);
        jedis.setnx("jacksparrow414","杜泓波");
        jedis.close();
    }

    @Test
    void testSaveUser(){
        SolrSpringUserEntity entity = SolrSpringUserEntity.builder().userId("88").age(89L).name("hahah").build();

        Jedis jedis = jedisPool.getResource();
        jedis.set("user", JSON.toJSONString(entity));
        UserEntity user = JSONObject.parseObject(jedis.get("user"), UserEntity.class);
        System.out.println(user);
        jedis.close();
    }
    
    @Test
    void testDel(){
        Jedis jedis = jedisPool.getResource();
        jedis.select(3);
        Long result = jedis.del("jacksparrow414");
        if (0L == result) {
            System.out.println("如果删除不存在的key,则返回0");
        }
        jedis.close();
    }

    @Test
    void testSaveList(){
        Jedis jedis = jedisPool.getResource();
        jedis.lpush("list","dhb1","dhb2","dhb3");
        jedis.close();
    }
}
