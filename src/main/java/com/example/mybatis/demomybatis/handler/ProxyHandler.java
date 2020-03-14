package com.example.mybatis.demomybatis.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author jacksparrow414
 * @Date 2019-11-30
 * @Description: TODO
 */
public class ProxyHandler<T> implements InvocationHandler {

    private  Class<T> proxyInterface;

    public ProxyHandler(Class<T> proxyInterface) {
        this.proxyInterface = proxyInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        String s = null;
        System.out.println(proxyInterface.getName());
        if ("getString".equals(method.getName())) {
            s = args[0].toString();
            System.out.println(s);
        }
        return s;
    }
}
