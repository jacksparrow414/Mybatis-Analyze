package com.example.mybatis.demomybatis.controller;

import com.example.mybatis.demomybatis.entity.UserItem;
import com.example.mybatis.demomybatis.service.UserItemService;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author jacksparrow414
 * @date 2020-05-24
 * @description: TODO
 */
@RestController
@RequestMapping(value = "userItem")
public class UserItemController {

    @Autowired
    private UserItemService itemService;

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "saveItem")
    public String saveItem(@RequestBody UserItem userItem){
        return userItem.getId().toString();

    }

    @GetMapping(value = "getItem")
    public List<UserItem> get(@RequestParam(required = false) Long id,
                        @RequestParam(required = false) Integer age,
                        @RequestParam(required = false) String name){
       return Collections.emptyList();
    }
    
    @GetMapping(value = "listByCondition")
    public List<UserItem> listByCondition(@RequestParam(required = false) Integer age){
        return itemService.listByCondition(age);
    }
    
    public static void main(String[] args) {
        System.out.println(ClassLayout.parseClass(UserItem.class).toPrintable());
    }
}
