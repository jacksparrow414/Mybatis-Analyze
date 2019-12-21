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
        // 读取配置文件,将所有配置好的Mapper接口，在XMLConfigBuilder中parseConfiguration方法里的mapperElement子方法里加进去，
        // 在这期间会走configurationElement方法，将获得的Mapper.xml文件转为MapperStatement对象保存至configuration中,
        // 最终的执行方法在XMLMapperBuilder.parse()里的bindMapperForNamespace()调用，
        // Configuration.addMapper()方法里通过MapperRegistry.addMapper()方法，
        // 放到一个Map里面去,
        // 该Map的Key是Mapper接口的完整路径(仅仅是接口路径而不是接口下面具体方法的路径,要注意，接口方法的具体路径会在下面动态代理那里拿到),
        // 该Map的value是构造一个新的MapperProxyFactory,
        // MapperProxyFactory以便于后面调用sqlSession.getMapper(Mapper接口.class)可以通过该MapperProxyFactory生成当前Mapper接口的动态代理类MapperProxy，
        // 具体可参考MapperRegistry.getMapper()方法即可，里面就是一个new了一个Mapper接口的动态代理对象 MapperProxy
        SqlSessionFactory factory = builder.build(inputStream);
        SqlSession sqlSession = factory.openSession();
        UserEntity entity = new UserEntity();
        entity.setAge(77);
        entity.setName("op7");
        entity.setId(7);
        // 第一种方式、获取对应的mapper，然后调用对应的方法即可,这里获取的UserMapper 的动态代理类MapperProxy对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 执行的时候调用MapperProxy.invoke方法执行,里面最后会执行 mapperMethod.execute(sqlSession, args)，
        // 然后MapperMethod.execute(),根据不同的SQLCommand(insert,update,delete,select)执行sqlSession.insert等不同的方法，
        // 接下来的执行就和第二种方式一毛一样了
        mapper.addUser(entity);
        // 第二种方式、直接调用sqlsession的内置方法,两个参数，第一个参数是，mapper里的具体方法的完整路径，第二个参数是方法的入参
        sqlSession.insert("com.example.mybatis.demomybatis.dao.UserMapper.addUser",entity);
        sqlSession.commit();
        sqlSession.close();
    }
}
