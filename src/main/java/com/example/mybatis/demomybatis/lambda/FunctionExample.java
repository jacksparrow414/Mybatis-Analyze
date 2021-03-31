package com.example.mybatis.demomybatis.lambda;

import cn.hutool.core.util.ObjectUtil;
import com.example.mybatis.demomybatis.entity.Gener;
import com.google.common.base.Preconditions;

import java.util.function.Function;

/**
 * @author jacksparrow414
 * @date 2020/12/8 13:46
 */
public class FunctionExample {

    /**
     * 入参为Gener,出参为数字
     * @author jacksparrow414
     * @param gener gener
     * @param function 用来自定义的函数
     * @return java.java.lang.Integer
     */
    public Integer getApplyValue(Gener gener, Function<Gener, Integer> function) {
        Preconditions.checkNotNull(function, "function必传");
        if (ObjectUtil.isNotNull(function.apply(gener))) {
            // 多次执行apply不会在上一次结果上累加
            gener.setGener(function.apply(gener));
        }
        return gener.getGener();
    }

    /**
     * compose在apply之前执行，执行结果Function传入apply
     * @author jacksparrow414
     * @param gener gener
     * @param function 自定义函数
     * @return java.java.lang.Integer
     */
    public Integer getComposeApplyValue(Gener gener, Function<Gener, Integer> function) {
       return function.compose(u -> {
            gener.setGener(gener.getGener() + 5);
            return gener;
        }).apply(gener);
    }
}
