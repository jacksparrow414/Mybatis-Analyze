package com.example.mybatis.demomybatis.lambda;

import com.example.mybatis.demomybatis.entity.Gener;

import java.util.function.BiConsumer;

/**
 * @author jacksparrow414
 * @date 2020/12/8 11:30
 */
public class ConsumerExample {

    public  void setValueToClass(Gener gener,BiConsumer<Gener, Integer> biConsumer) {
        biConsumer.accept(gener, 51);
    }
}
