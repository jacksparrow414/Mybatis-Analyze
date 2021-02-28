package com.example.mybatis.demomybatis.controller;

import com.example.mybatis.demomybatis.entity.UserEntity;
import feign.RequestLine;

/**
 * @author jacksparrow414
 * @date 2021/2/28
 */
public interface FeignUse {
    
    /**
     * 注解里面的写法 <a href="https://tools.ietf.org/html/rfc6570">RFC 6570</a>.
     */
    @RequestLine("GET /user/query")
    UserEntity getUser();
}
