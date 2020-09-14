package com.example.mybatis.demomybatis.handler;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理的handler.
 *
 * <p>RequiredArgsConstructor 注解会对当前类的final属性，或者@NonNull 注解标识的属性 生成构造方法.
 * AllArgsConstructor 注解 则会生成一个包含所有属性的构造方法.
 * 具体生成的之后类的样子可以编译当前文件则可以看到结果</p>
 */
@RequiredArgsConstructor
public class CommonProxyHandler implements InvocationHandler {

    private final Object target;
    private final String name;
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("已经进入代理方法");
        String s = StringUtils.upperCase(name);
        System.out.println(s);
        // 如果这里入参写的是proxy而不是target,则变成了自己invoke再invoke里调用自己
        // 调用invoke方法基本都要有一个target，除非是static，这个target是在handler构造方法初始化的
        Object invoke = method.invoke(target, args);
        System.out.println("目标方法执行完毕");
        return invoke;
    }
}
