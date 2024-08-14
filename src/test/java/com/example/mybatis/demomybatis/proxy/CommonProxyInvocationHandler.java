package com.example.mybatis.demomybatis.proxy;

import lombok.extern.java.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Log
public class CommonProxyInvocationHandler implements InvocationHandler {

    private Object target;

    public CommonProxyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("before-------------->");
        Object result = method.invoke(target, args);
        log.info("after--------------->");
        return result;
    }
}
