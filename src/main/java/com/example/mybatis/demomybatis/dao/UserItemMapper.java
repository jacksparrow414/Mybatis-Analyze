package com.example.mybatis.demomybatis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatis.demomybatis.entity.UserItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jacksparrow414
 * @date 2020-05-24
 * @description: TODO
 */
@Mapper
public interface UserItemMapper extends BaseMapper<UserItem> {
    
}
