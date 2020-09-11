package com.example.mybatis.demomybatis.proxy;

import com.example.mybatis.demomybatis.service.impl.ProxyServiceImpl;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * method.invoke调用示例.
 */
public final class InvokeTest {
    
    @SneakyThrows
    @Test
    public void assertMethodInvoke() {
        // 第一种获得类的对象的方式
        ProxyServiceImpl proxyService = ProxyServiceImpl.class.newInstance();
        Method method = proxyService.getClass().getMethod("print", String.class);
        // 反射调用飞静态static方法，必须传入类的实例(对象)
        method.invoke(proxyService,"hahah");
    }
    
    @SneakyThrows(value = {ClassNotFoundException.class, IllegalAccessException.class, InstantiationException.class, NoSuchMethodException.class})
    @Test
    public void assertMethodInvokeNullException() {
        // 第二种获得类的对象的方式
        ProxyServiceImpl proxyService = (ProxyServiceImpl) Class.forName("com.example.mybatis.demomybatis.service.impl.ProxyServiceImpl").newInstance();
        Method method = proxyService.getClass().getMethod("print", String.class);
        Class<? extends Exception> targetException = null;
        try {
            // 反射调用其他方法时，必须通过类的实例也就是对象去调用,第一个参数必须传类的对象，不传或者null,会调用出错
            method.invoke(null, "报错");
        } catch ( InvocationTargetException | NullPointerException ex ) {
            targetException = ex.getClass();
        }
        Assert.assertEquals(NullPointerException.class, targetException);
    }
    
    @SneakyThrows
    @Test
    public void assertStaticMethodInvoke() {
        ProxyServiceImpl proxyService = ProxyServiceImpl.class.newInstance();
        Method method = proxyService.getClass().getMethod("message", String.class);
        // 调用static方法时，obj参数可以传null
        method.invoke(null, "lalalal");
    }
}
