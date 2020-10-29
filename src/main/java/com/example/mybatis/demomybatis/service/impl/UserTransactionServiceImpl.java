package com.example.mybatis.demomybatis.service.impl;

import com.example.mybatis.demomybatis.dao.UserMapper;
import com.example.mybatis.demomybatis.entity.UserEntity;
import com.example.mybatis.demomybatis.service.UserTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jacksparrow414
 * @date 2020/10/29
 */
@Component
@Slf4j
public class UserTransactionServiceImpl implements UserTransactionService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUserThrowException() {
        UserEntity entity = UserEntity.builder().id(100).age(100).name("userTransaction").build();
        userMapper.addUser(entity);
        int tmp = 1/0;
    }
    
    /**
     * 如果A调用该方法，由于该方法会抛出异常，并有事务注解，所以，当异常抛出时，整体事务被标记为rollback
     *
     * 但是A却try-catch了该方法的执行过程，导致A没有收到异常信息，错误地认为整个过程没有问题，最后A提交事务
     *
     * 在提交事务的时候却发现事务被标记为回滚了.这就造成了
     * Transaction rolled back because it has been marked as rollback-only 异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserCatchException() {
        UserEntity entity = UserEntity.builder().id(100).age(100).name("userTransaction").build();
        userMapper.addUser(entity);
        int tmp = 1/0;
    }
}
