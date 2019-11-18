package com.example.mybatis.demomybatis;

import com.example.mybatis.demomybatis.dao.UserMapper;
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
        entity.setAge(77);
        entity.setName("op7");
        entity.setId(7);
        // 第一种方式、获取对应的mapper，然后调用对应的方法即可,这里获取的UserMapper 的动态代理类MapperProxy对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 执行的时候调用MapperProxy.invoke方法执行
        mapper.addUser(entity);
        // 第二种方式、直接调用sqlsession的内置方法,两个参数，第一个参数是，mapper里的方法的完整路径，第二个参数是方法的入参
//        sqlSession.insert("com.example.mybatis.demomybatis.dao.UserMapper.addUser",entity);
        sqlSession.commit();
        sqlSession.close();
    }
}
