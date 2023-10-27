package com.example.mybatis.demomybatis.proxy;

import com.example.mybatis.demomybatis.handler.CommonProxyHandler;
import com.example.mybatis.demomybatis.service.ProxyService;
import com.example.mybatis.demomybatis.service.impl.ProxyServiceImpl;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * 测试生成 类的 代理类.
 *
 * <p><也可以直接参考根目录下的$Proxy1.class文件<p/>
 */
public class GenerateProxyClassTest {

    @SneakyThrows
    @Test
    public  void assertGenerateProxyClass(){
        String path = "D:\\$Proxy1.class";
        ProxyService proxyService = new ProxyServiceImpl();
        CommonProxyHandler commonProxyHandler = new CommonProxyHandler(proxyService,path);
        ProxyService proxyInstance = (ProxyService) Proxy.newProxyInstance(ProxyServiceImpl.class.getClassLoader(), ProxyServiceImpl.class.getInterfaces(), commonProxyHandler);
        Class cl = Class.forName("java.lang.reflect.ProxyGenerator");
        Method m =cl.getDeclaredMethod("generateProxyClass",String.class,Class[].class);
        m.setAccessible(true);
        byte[] proxies = (byte[]) m.invoke("$Proxy1", ProxyServiceImpl.class.getInterfaces());
//        byte[] proxies = ProxyGenerator.generateProxyClass("$Proxy1", ProxyServiceImpl.class.getInterfaces());
        FileOutputStream outputStream = null;
        outputStream = new FileOutputStream(path);
        outputStream.write(proxies);
        outputStream.flush();
        outputStream.close();
    }
}
