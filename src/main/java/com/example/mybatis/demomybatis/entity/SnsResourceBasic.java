package com.example.mybatis.demomybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jacksparrow414
 * @date 2020/8/20
 */
@TableName(value = "sns_resource_basic")
@Getter
@Setter
public class SnsResourceBasic {
    
    @TableId(value = "res_id")
    private String resId;
}
