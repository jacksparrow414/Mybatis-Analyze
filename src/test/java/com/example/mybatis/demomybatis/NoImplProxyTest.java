package com.example.mybatis.demomybatis;

import com.example.mybatis.demomybatis.handler.ProxyHandler;
import com.example.mybatis.demomybatis.service.SchoolService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Proxy;

/**
 * @author jacksparrow414
 * @date 2020-03-10
 * @description: TODO
 */
@SpringBootTest
public class NoImplProxyTest {

    @Test
    public static void main(String[] args) {
        ProxyHandler proxyHandler = new ProxyHandler(SchoolService.class);
        SchoolService proxyInstance = (SchoolService) Proxy
                .newProxyInstance(SchoolService.class.getClassLoader(),
                        new Class[]{SchoolService.class}, proxyHandler);
        proxyInstance.getString("测试接口没有实现类的代理");
    }
}
