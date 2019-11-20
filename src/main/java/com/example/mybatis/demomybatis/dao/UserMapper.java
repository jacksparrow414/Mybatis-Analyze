package com.example.mybatis.demomybatis.dao;

import com.example.mybatis.demomybatis.entity.UserEntity;

/**
 * @author dhb
 * 这里目前先不加@Mapper注解,等以后看Spring和Mybatis结合的时候再加，现在是纯原生
 * @date 2019/11/14 9:43
 */

public interface UserMapper {

    void addUser(UserEntity entity);
}
