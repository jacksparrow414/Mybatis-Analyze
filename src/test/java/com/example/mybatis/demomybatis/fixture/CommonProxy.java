package com.example.mybatis.demomybatis.fixture;

import lombok.extern.java.Log;

@Log
public class CommonProxy {

    public void test() {
        log.info("execute method");
    }

    public String getName(String name) {
        return name;
    }
}
