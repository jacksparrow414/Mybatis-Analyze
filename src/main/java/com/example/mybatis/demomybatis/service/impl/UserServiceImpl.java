package com.example.mybatis.demomybatis.service.impl;

import com.example.mybatis.demomybatis.config.DataSourceConfiguration;
import com.example.mybatis.demomybatis.dao.UserMapper;
import com.example.mybatis.demomybatis.entity.UserEntity;
import com.example.mybatis.demomybatis.service.UserService;
import com.example.mybatis.demomybatis.service.UserTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试事务注解时把模仿的自定义的ShardingSphereDatasource配置类{@link DataSourceConfiguration}先注释掉
 *
 * @Author jacksparrow414
 * @Date 2019-11-27
 * @Description: UserServiceImpl
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserTransactionService userTransactionService;
    
    /**
     * 这种方式,最外层的方法加上事务注解，即使下面的里面的嵌套方法不加注解，异常出现，两个事务都可以回滚成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserEntity entity) {
        userMapper.addUser(entity);
        try {
            //addAnotherUser();
            userTransactionService.saveUserCatchException();
        }catch (Exception exception){
            // TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            //log.error("同一个类中子方法执行出错", exception);
            log.error("另一个类中有Transactional注解方法执行出错，但是异常被调用处catch了", exception);
        }
        // userTransactionService.saveUserThrowException();
        
    }

    /**
     * 这种方式,即：当同一个service，暴露的接口方法上没有事务注解，只有同一个serviceImpl子方法有事务注解的情况，
     * 一旦是下面这种方法出错，则两个事务都回滚不了。
     * 因为Spring的事务是依赖AOP的
     * 虽然下面的有注解，但是同一个类不会再去动态代理，所以也就不会再有事务
     */
    @Transactional(rollbackFor = Exception.class)
    public void addAnotherUser() {
        UserEntity entity = UserEntity.builder().id(12).age(12).name("jack").build();
        userMapper.addUser(entity);
        int a = 1/0;
    }
}
