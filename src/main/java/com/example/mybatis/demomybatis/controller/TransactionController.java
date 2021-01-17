package com.example.mybatis.demomybatis.controller;

import com.example.mybatis.demomybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异步线程中事务控制.
 * @author jacksparrow414
 * @date 2021/1/13 11:39
 */
@RestController
@RequestMapping(value = "transaction")
public class TransactionController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/async")
    public void AsyncThreadTransaction() {
        userService.asyncThreadTransaction();
    }
    
    @RequestMapping(value = "/asyncTemplate")
    public void asyncThreadTransactionTemplate() {
        userService.asyncThreadTransactionTemplate();
    }
}
