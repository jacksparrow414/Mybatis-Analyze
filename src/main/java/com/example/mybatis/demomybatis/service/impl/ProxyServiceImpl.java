package com.example.mybatis.demomybatis.service.impl;

import com.example.mybatis.demomybatis.service.ProxyService;

/**
 * @Author jacksparrow414
 * @Date 2019-12-21
 * @Description: TODO
 */
public class ProxyServiceImpl implements ProxyService {
    @Override
    public String print(String message) {
        return "我不是代理";
    }
    
    public static String message(String message) {
        return message;
    }
    
}
