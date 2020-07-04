package com.example.mybatis.demomybatis.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.mybatis.demomybatis.entity.UserItem;
import com.example.mybatis.demomybatis.service.UserItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<UserItem> get(@RequestParam(required = false) Long id,
                        @RequestParam(required = false) Integer age,
                        @RequestParam(required = false) String name){
       return itemService.list(new LambdaQueryWrapper<UserItem>()
               .eq(id != null,UserItem::getId,id)
               .eq(age != null,UserItem::getAge,age)
               .eq(StringUtils.isNotEmpty(name),UserItem::getName,name));
    }
}
