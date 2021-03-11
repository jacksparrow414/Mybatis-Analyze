package com.example.mybatis.demomybatis.reflect;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGlib动态代理实现.
 * <a href="https://github.com/Snailclimb/JavaGuide/blob/master/docs/java/basis/%E4%BB%A3%E7%90%86%E6%A8%A1%E5%BC%8F%E8%AF%A6%E8%A7%A3.md">具体实现</a>
 * @author jacksparrow414
 * @date 2021/3/11 14:17
 */
public class CglibProxy implements MethodInterceptor {

    private Object target;

    public CglibProxy(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        // 设置这个相当于设置拦截对象
        enhancer.setCallback(this);
        // 创建cglib代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        // cglib是通过继承被代理类实现的
        methodProxy.invokeSuper(o, objects);
        System.out.println("after");
        return null;
    }
}
