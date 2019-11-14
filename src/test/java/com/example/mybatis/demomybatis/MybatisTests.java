package com.example.mybatis.demomybatis;

import com.example.mybatis.demomybatis.entity.UserEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author dhb
 * @date 2019/11/14 9:34
 */
@SpringBootTest
public class MybatisTests {

    @Test
    public void testConfiguration() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        SqlSession sqlSession = factory.openSession();
        UserEntity entity = new UserEntity();
        entity.setAge(78);
        entity.setName("oop7");
        entity.setId(775);
        sqlSession.insert("com.example.mybatis.demomybatis.dao.UserMapper.addUser",entity);
        sqlSession.commit();
        sqlSession.close();
    }
}
