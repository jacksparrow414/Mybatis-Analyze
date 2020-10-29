package com.example.mybatis.demomybatis.service;

/**
 * @author jacksparrow414
 * @date 2020/10/29
 */
public interface UserTransactionService {
    
    void saveUserThrowException();
    
    void saveUserCatchException();
}
