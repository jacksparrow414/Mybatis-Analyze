package com.example.mybatis.demomybatis.proxy;

import com.example.mybatis.demomybatis.handler.CommonProxyHandler;
import com.example.mybatis.demomybatis.service.ProxyService;
import com.example.mybatis.demomybatis.service.impl.ProxyServiceImpl;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

/**
 * @Author jacksparrow414
 * @Date 2019-12-21
 * @Description: TODO
 */
public final class CommonProxyTest {

    @Test
    public void testCommonProxy(){
        // 利用java的多态,接收其实例化的实例ProxyServiceImpl
        ProxyService proxyService = new ProxyServiceImpl();
        // handler中传入目标接口的实例化的对象ProxyServiceImpl
        CommonProxyHandler commonProxyHandler = new CommonProxyHandler(proxyService,"testParams");
        // 第一个参数:类、接口 加载器
        // 第二个参数:被代理类要实现的目标接口
        // 第三个参数:handler的对象
        // 返回:实现该接口类的代理类
        ProxyService proxyInstance = (ProxyService) Proxy.newProxyInstance(ProxyServiceImpl.class.getClassLoader(),
                proxyService.getClass().getInterfaces(), commonProxyHandler);
        String s = proxyInstance.print("我是一个小代理");
        System.out.println(s);
    }
}


