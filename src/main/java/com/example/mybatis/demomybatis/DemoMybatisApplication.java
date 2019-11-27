package com.example.mybatis.demomybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 如果测试是事务的方法，com.example.mybatis.demomybatis.controller.UserController#testTransactional()，
 * 直接把exclude去掉就可以，否则可能启动不起来
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
 //@MapperScan(basePackages = "com.example.mybatis.demomybatis.dao")
public class DemoMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMybatisApplication.class, args);
    }

}
