package com.example.mybatis.demomybatis.proxy;

import com.example.mybatis.demomybatis.handler.ProxyHandler;
import com.example.mybatis.demomybatis.service.ProxyService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Proxy;

/**
 * @Author jacksparrow414
 * @Date 2019-11-30
 * @Description: 代理模式的测试
 */
@SpringBootTest
public class ProxyTests {

    @Test
    public void testProxy(){
        // 接口不能被new，new出来的都是接口的实现类，也可以说接口可以new，
        // 只不过new出来的这个对象不是接口的自身，而是它的实现类，
        // 这就是多态
        ProxyService proxy = new ProxyService() {
            @Override
            public String print(String message) {
                System.out.println("实现类");
                return message;
            }
        };
        ProxyService proxyService =  getProxyService(ProxyService.class);
        System.out.println(proxyService.print("他的"));
    }

    /**
     * 获取代理
     * @param proxyService
     * @return
     */
    public  <T> T getProxyService(Class<T> proxyService){
        return (T) Proxy.newProxyInstance(
                proxyService.getClassLoader(),
                new Class[]{ proxyService },
                new ProxyHandler(proxyService)
        );
    }
}
