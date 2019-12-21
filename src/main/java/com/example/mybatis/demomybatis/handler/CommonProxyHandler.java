package com.example.mybatis.demomybatis.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author jacksparrow414
 * @Date 2019-12-21
 * @Description: TODO
 */
public class CommonProxyHandler implements InvocationHandler {

    private Object target;

    public CommonProxyHandler(Object tarject) {
        this.target = tarject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target,args);
    }
}
