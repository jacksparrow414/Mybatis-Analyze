package com.example.mybatis.demomybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author jacksparrow414
 * @date 2020-05-24
 * @description: TODO
 */
@Data
@TableName(value = "user")
public class UserItem {
    @TableId
    private Long id;
    private String name;
    private Integer age;
    private Integer del;
    private String sameData;
    
    /**
     * 为了测试shardingsphere的逻辑列和密文列的对应关系
     */
    @TableField(value = "other_info")
    private String info;
}
