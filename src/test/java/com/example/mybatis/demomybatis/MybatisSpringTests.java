package com.example.mybatis.demomybatis;

import com.example.mybatis.demomybatis.dao.UserMapper;
import com.example.mybatis.demomybatis.entity.UserEntity;
import java.util.HashMap;
import java.util.Map;
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
    
    @Test
    public void testMybatisUpdate(){
        Long id = 1L;
        // sql 语句默认的update语句返回的是match的值，只有0，1 如果要获得受影响行数，需要使用useAffectedRows
        System.out.println(userMapper.updateUserById(id));
    }
    
    @Test
    public void hh() {
        final Map<String,Integer> map = new HashMap<>();
        map.put("aa",1);
        System.out.println(map.size());
    }
}
