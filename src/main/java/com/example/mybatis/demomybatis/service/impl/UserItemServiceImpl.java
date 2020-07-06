package com.example.mybatis.demomybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatis.demomybatis.dao.UserItemMapper;
import com.example.mybatis.demomybatis.entity.UserItem;
import com.example.mybatis.demomybatis.service.UserItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * serviceImpl.
 * @author jacksparrow414
 * @date 2020-05-24
 * @description: TODO
 */
@Service
public class UserItemServiceImpl extends ServiceImpl<UserItemMapper, UserItem> implements UserItemService {
    
    @Autowired
    private UserItemMapper userItemMapper;
    
    @Override
    public List<UserItem> listByCondition(Integer age) {
        return userItemMapper.listByCondition(age);
    }
}
