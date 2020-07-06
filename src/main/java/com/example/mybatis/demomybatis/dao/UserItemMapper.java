package com.example.mybatis.demomybatis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatis.demomybatis.entity.UserItem;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author jacksparrow414
 * @date 2020-05-24
 * @description: TODO
 */
@Mapper
public interface UserItemMapper extends BaseMapper<UserItem> {
    
    /**
     * 根据年龄获取List
     *
     * @param age 年龄
     * @return 实体List
     */
    List<UserItem> listByCondition(@Param("age") Integer age);
}
