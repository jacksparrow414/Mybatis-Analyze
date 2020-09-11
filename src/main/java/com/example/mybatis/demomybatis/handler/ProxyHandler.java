package com.example.mybatis.demomybatis.handler;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 可以用来接收接口的的Handler.
 */
@AllArgsConstructor
public class ProxyHandler<T> implements InvocationHandler {

    private Class<T> proxyInterface;
    
    @Override
    @SneakyThrows
    public Object invoke(Object proxy, Method method, Object[] args) {
        String s = null;
        System.out.println(proxyInterface.getName());
        if (proxyInterface.isInterface()) {
            System.out.println("我是接口产生的代理，我不会执行method.invoke方法");
        }else {
            method.invoke(args);
        }
        if ("getString".equals(method.getName())) {
            s = args[0].toString();
            System.out.println(s);
        }
        return s;
    }
}
