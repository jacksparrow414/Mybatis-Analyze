package com.example.mybatis.demomybatis.service.impl;

import com.example.mybatis.demomybatis.entity.UserEntity;
import com.example.mybatis.demomybatis.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.UnexpectedRollbackException;

/**
 * @author jacksparrow414
 * @date 2020/10/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    
    private UserEntity entity;
    
    @Autowired
    private UserService userService;
    
    @Before
    public void setUp() {
        entity = UserEntity.builder().id(1).age(12).name("test").build();
    }
    
    @Test(expected = ArithmeticException.class)
    public void assertTransactionNotRollBack() {
        userService.addUser(entity);
    }
    
    @Test
    public void assertChildTransactionCatchException() {
        userService.addUser(entity);
    }
    
    @Test
    public void assertTransactionWithTwoService() {
        userService.addUser(entity);
    }
    
    @Test(expected = UnexpectedRollbackException.class)
    public void assertTransactionExceptionCatchWithTwoService() {
        userService.addUser(entity);
    }
}