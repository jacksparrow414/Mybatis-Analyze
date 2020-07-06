package com.example.mybatis.demomybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatis.demomybatis.entity.UserItem;
import java.util.List;

/**
 * service.
 * @author jacksparrow414
 * @date 2020-05-24
 * @description: TODO
 */
public interface UserItemService extends IService<UserItem> {
    
    /**
     * 根据名字查询数据.
     *
     * @param age 年龄
     * @return 实体List
     */
    List<UserItem> listByCondition(Integer age);
}
