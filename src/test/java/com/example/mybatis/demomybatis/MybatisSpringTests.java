package com.example.mybatis.demomybatis;

import com.example.mybatis.demomybatis.dao.UserMapper;
import com.example.mybatis.demomybatis.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author dhb
 * @date 2019/11/22 14:19
 */
@SpringBootTest
public class MybatisSpringTests {

    @Autowired
    UserMapper userMapper;
    @Test
    public void testMybatisSpring(){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(8);
        userEntity.setName("kkl");
        userEntity.setAge(45);
        userMapper.addUser(userEntity);
    }
}
