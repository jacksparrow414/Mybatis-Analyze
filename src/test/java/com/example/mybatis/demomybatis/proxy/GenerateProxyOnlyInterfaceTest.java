package com.example.mybatis.demomybatis.proxy;

import com.example.mybatis.demomybatis.handler.ProxyHandler;
import com.example.mybatis.demomybatis.service.SchoolService;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * 测试生成 接口的 代理类.
 */
public final class GenerateProxyOnlyInterfaceTest {

    @SneakyThrows
    @Test
    public void assertInterfaceProxy() {
        String path = "D:\\$Proxy2.class";
        ProxyHandler proxyHandler = new ProxyHandler(SchoolService.class);
        SchoolService proxyInstance = (SchoolService) Proxy
                .newProxyInstance(SchoolService.class.getClassLoader(),
                        new Class[]{SchoolService.class}, proxyHandler);
        Class cl = Class.forName("java.lang.reflect.ProxyGenerator");
        Method m =cl.getDeclaredMethod("generateProxyClass",String.class,Class[].class);
        m.setAccessible(true);
        byte[] proxies = (byte[]) m.invoke("$Proxy2",new Class[]{SchoolService.class});
    
//        byte[] proxies = ProxyGenerator.generateProxyClass("$Proxy2",new Class[]{SchoolService.class});
        FileOutputStream outputStream;
        outputStream = new FileOutputStream(path);
        outputStream.write(proxies);
        outputStream.flush();
        outputStream.close();
    }
}
