package com.example.mybatis.demomybatis.reflect;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

@Log
public class LogInterceptor {

    /**
     * 参考 https://www.cnblogs.com/yuarvin/p/16847117.html
     * 切面的另一种实现，可以替换cglib的实现{@link CglibProxy#intercept(Object, Method, Object[], MethodProxy)}
     * @param proxySelf 代理对象本身
     * @param target 被代理类 Class
     * @param method 执行的的方法
     * @param arguments 方法参数
     * @param callable 被代理类的原始方法
     * @return
     */
    @SneakyThrows
    @RuntimeType
    public Object intercept(@This Object proxySelf, @Origin Class target, @Origin Method method, @AllArguments Object[] arguments, @SuperCall Callable<?> callable) {
         log.info(proxySelf.getClass().getName());
         log.info(method.getName());
         log.info(target.getName());
        if (ArrayUtils.isNotEmpty(arguments)) {
            log.info(arguments[0].toString());
        }
         log.info("before--------------->");
         Object result = callable.call();
         log.info("after---------------->");
        System.out.println(1);
         return result;
    }
}
