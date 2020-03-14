package com.example.mybatis.demomybatis.entity;

import com.example.mybatis.demomybatis.annotation.AddCheck;
import com.example.mybatis.demomybatis.annotation.UpdateCheck;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author dhb
 * @date 2019/11/14 9:41
 */
public class UserEntity implements Serializable {
    @NotNull(groups = UpdateCheck.class)
    private Integer id;
    @NotNull(groups = AddCheck.class)
    private String name;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
