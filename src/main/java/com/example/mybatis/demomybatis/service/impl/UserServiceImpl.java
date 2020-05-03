package com.example.mybatis.demomybatis.service.impl;

import com.example.mybatis.demomybatis.dao.UserMapper;
import com.example.mybatis.demomybatis.entity.UserEntity;
import com.example.mybatis.demomybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author jacksparrow414
 * @Date 2019-11-27
 * @Description: TODO
 */
@Service
public class UserServiceImpl implements UserService {

   // @Autowired
   // UserMapper userMapper;
    @Override
    /**
     * 这种方式,最外层的方法加上事务注解，即使下面的里面的嵌套方法不加注解，异常出现，两个事务都可以回滚成功
     * @Transactional(rollbackFor = Exception.class)
     */
    public void addUser(UserEntity entity) {
        System.out.println(entity.getName());
       // userMapper.addUser(entity);
       // this.addTwo();
    }

    /**
     * 这种方式，两个事务都回滚不了
     */
    @Transactional(rollbackFor = Exception.class)
    public void addTwo() {
        UserEntity entity = new UserEntity();
        entity.setAge(12);
        entity.setName("jk");
        entity.setId(12);
      //  userMapper.addUser(entity);
        int a = 1/0;
    }
}
