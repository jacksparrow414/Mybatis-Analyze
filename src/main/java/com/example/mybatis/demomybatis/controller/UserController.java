package com.example.mybatis.demomybatis.controller;

import com.example.mybatis.demomybatis.dao.UserMapper;
import com.example.mybatis.demomybatis.entity.UserEntity;
import com.example.mybatis.demomybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author jacksparrow414
 * @Date 2019-11-27
 * @Description: TODO
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;
    
    @Autowired
    UserMapper userMapper;
    
    @RequestMapping(value = "testTransactional")
    public void testTransactional(){
        UserEntity entity = new UserEntity();
        entity.setId(13);
        entity.setName("jjhs");
        entity.setAge(13);
        userService.addUser(entity);
    }
    
    @RequestMapping(value = "query")
    public UserEntity query() {
        UserEntity user = userMapper.getUser();
        return user;
    }
    
    @RequestMapping(value = "insert")
    public void insert(@RequestBody UserEntity userEntity) {
        userMapper.addUser(userEntity);
    }
}
