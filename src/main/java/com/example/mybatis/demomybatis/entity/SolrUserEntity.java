package com.example.mybatis.demomybatis.entity;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 用来对应solr中的实体
 * @author jacksparrow414
 * @date 2020-05-03
 * @description: TODO
 */
public class SolrUserEntity {
    @Field
    private String userId;
    @Field
    private String name;
    @Field
    private Long age;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SolrUserEntity{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
