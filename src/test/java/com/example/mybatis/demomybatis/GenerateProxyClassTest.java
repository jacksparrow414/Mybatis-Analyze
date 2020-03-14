package com.example.mybatis.demomybatis;

import com.example.mybatis.demomybatis.handler.CommonProxyHandler;
import com.example.mybatis.demomybatis.service.ProxyService;
import com.example.mybatis.demomybatis.service.impl.ProxyServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

/**
 * @author jacksparrow414
 * @date 2020-03-10
 * @description: TODO
 */
@SpringBootTest
public class GenerateProxyClassTest {

    @Test
    public static void main(String[] args) throws Exception{
        String path = "/Users/jacksparrow414/Downloads/$Proxy1.class";
        ProxyService proxyService = new ProxyServiceImpl();
        CommonProxyHandler commonProxyHandler = new CommonProxyHandler(proxyService,path);
        ProxyService proxyInstance = (ProxyService) Proxy.newProxyInstance(ProxyServiceImpl.class.getClassLoader(), ProxyServiceImpl.class.getInterfaces(), commonProxyHandler);
        byte[] proxies = ProxyGenerator.generateProxyClass("$Proxy1", ProxyServiceImpl.class.getInterfaces());
        FileOutputStream outputStream = null;
        outputStream = new FileOutputStream(path);
        outputStream.write(proxies);
        outputStream.flush();
        outputStream.close();
    }
}
