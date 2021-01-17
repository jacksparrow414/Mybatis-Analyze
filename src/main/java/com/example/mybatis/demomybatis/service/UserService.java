package com.example.mybatis.demomybatis.service;

import com.example.mybatis.demomybatis.entity.UserEntity;

/**
 * @author jacksparrow414
 */
public interface UserService {

    /**
     * 添加用户.
     * @param entity user实体
     */
    void addUser(UserEntity entity);

    /**
     * 在异步线程中使用事务.
     */
    boolean asyncThreadTransaction();
    
    /**
     * 在异步线程中使用TransactionTemplate.
     */
    boolean asyncThreadTransactionTemplate();
}
