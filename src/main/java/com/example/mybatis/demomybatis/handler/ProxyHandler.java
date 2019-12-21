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
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (!proxyInterface.isInstance(proxyInterface)) {

                // 代理之前处理
               return method.invoke(this,args);
            }
        }catch (Exception e){
            e.getCause();
        }
        return getMessage(args);
    }

    private Object getMessage(Object[] args) {
        String result = (String) args[0];
        return result;
    }

}
