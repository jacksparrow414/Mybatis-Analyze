package com.example.mybatis.demomybatis.controller;

import com.example.mybatis.demomybatis.dao.UserMapper;
import com.example.mybatis.demomybatis.entity.UserEntity;
import com.example.mybatis.demomybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private UserService userService;
    
    @Autowired
    private UserMapper userMapper;
    
    @RequestMapping(value = "testTransactional")
    public void testTransactional(){
        UserEntity entity = UserEntity.builder().id(13).age(13).name("sparrow").build();
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
    
    @RequestMapping(value = "update")
    public void update(@RequestParam int id) {
        userMapper.updateUserById(id);
    }
}
