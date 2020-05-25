package com.example.mybatis.demomybatis.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.mybatis.demomybatis.entity.UserItem;
import com.example.mybatis.demomybatis.service.UserItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
        userItem.setId(IdWorker.getId());
        itemService.save(userItem);
        return userItem.getId().toString();

    }

    @GetMapping(value = "getItem")
    public UserItem get(Long id,Integer age){
       return itemService.getOne(new LambdaQueryWrapper<UserItem>()
               .eq(UserItem::getId,id)
               .eq(UserItem::getAge,age));
    }
}
