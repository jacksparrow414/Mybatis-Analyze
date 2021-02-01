package com.example.mybatis.demomybatis.service.impl;

import com.example.mybatis.demomybatis.dao.UserItemMapper;
import com.example.mybatis.demomybatis.entity.UserItem;
import com.example.mybatis.demomybatis.service.UserItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * serviceImpl.
 * @author jacksparrow414
 * @date 2020-05-24
 */
@Service
public class UserItemServiceImpl  implements UserItemService {
    
    @Autowired
    private UserItemMapper userItemMapper;
    
    @Override
    public List<UserItem> listByCondition(Integer age) {
        return userItemMapper.listByCondition(age);
    }
}
