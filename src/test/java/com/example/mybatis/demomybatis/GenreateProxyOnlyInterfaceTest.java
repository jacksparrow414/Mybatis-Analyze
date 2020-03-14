package com.example.mybatis.demomybatis;

import com.example.mybatis.demomybatis.handler.ProxyHandler;
import com.example.mybatis.demomybatis.service.SchoolService;
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
public class GenreateProxyOnlyInterfaceTest {

    @Test
    public static void main(String[] args) throws Exception{
        String path = "/Users/jacksparrow414/Downloads/$Proxy2.class";
        ProxyHandler proxyHandler = new ProxyHandler(SchoolService.class);
        SchoolService proxyInstance = (SchoolService) Proxy
                .newProxyInstance(SchoolService.class.getClassLoader(),
                        new Class[]{SchoolService.class}, proxyHandler);
        byte[] proxies = ProxyGenerator.generateProxyClass("$Proxy2",new Class[]{SchoolService.class});
        FileOutputStream outputStream;
        outputStream = new FileOutputStream(path);
        outputStream.write(proxies);
        outputStream.flush();
        outputStream.close();
    }
}
