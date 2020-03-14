package com.example.mybatis.demomybatis.handler;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author jacksparrow414
 * @Date 2019-12-21
 * @Description: TODO
 */
public class CommonProxyHandler implements InvocationHandler {

    private Object target;
    private String name;

    public CommonProxyHandler(Object target, String name) {
        this.target = target;
        this.name = name;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // return "hhhhh";
        String s = StringUtils.upperCase(name);
        System.out.println(s);
        Object invoke = method.invoke(target, args);
        System.out.println("目标方法执行完毕");
        return invoke;
    }
}
