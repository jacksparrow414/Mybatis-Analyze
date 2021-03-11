package com.example.mybatis.demomybatis.reflect;

/**
 * @author jacksparrow414
 * @date 2021/3/11 14:24
 */
public class CglibUseExample {

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy(new CglibService());
        CglibService proxy = (CglibService) cglibProxy.getProxy();
        proxy.sendMessage("this is cglib");
    }
}
