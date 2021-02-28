package com.example.mybatis.demomybatis.http;

import com.example.mybatis.demomybatis.controller.FeignUse;
import com.example.mybatis.demomybatis.entity.UserEntity;
import feign.Feign;
import feign.Request.Options;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jacksparrow414
 * @date 2021/2/28
 */
@Slf4j
public class FeignUseExample {
    
    public void useFeign() {
        FeignUse feignUse = Feign.builder()
            .client(new ApacheHttpClient())
            .encoder(new JacksonEncoder())
            .decoder(new JacksonDecoder())
            .options(new Options(1000, 60000, true))
            .target(FeignUse.class, "http://localhost:8999");
        UserEntity user = feignUse.getUser();
        log.info("user info: {}", user);
    }
}
