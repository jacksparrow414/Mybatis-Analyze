package com.example.mybatis.demomybatis.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author jacksparrow414
 * @date 2020-05-24
 * @description: TODO
 */
@Data
@Builder
public class UserItem {
    private Long id;
    private String name;
    private Integer age;
    private Integer del;
    private String sameData;
    
    /**
     * 为了测试shardingsphere的逻辑列和密文列的对应关系
     */
    private String info;
}
