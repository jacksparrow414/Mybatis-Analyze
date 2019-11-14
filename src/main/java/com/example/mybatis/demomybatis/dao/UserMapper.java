package com.example.mybatis.demomybatis.dao;

import com.example.mybatis.demomybatis.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author dhb
 * @date 2019/11/14 9:43
 */
@Mapper
public interface UserMapper {

    void addUser(UserEntity entity);
}
