package com.example.mybatis.demomybatis.proxy;

import com.example.mybatis.demomybatis.handler.ProxyHandler;
import com.example.mybatis.demomybatis.service.SchoolService;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

/**
 * 接口 的代理测试.
 */
public final class NoImplProxyTest {

    @Test
    public void assertNoImpl() {
        ProxyHandler proxyHandler = new ProxyHandler(SchoolService.class);
        SchoolService proxyInstance = (SchoolService) Proxy
                .newProxyInstance(SchoolService.class.getClassLoader(),
                        new Class[]{SchoolService.class}, proxyHandler);
        proxyInstance.getString("测试接口没有实现类的代理");
    }
}
