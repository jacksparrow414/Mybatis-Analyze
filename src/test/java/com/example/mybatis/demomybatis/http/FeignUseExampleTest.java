package com.example.mybatis.demomybatis.http;

import org.junit.Test;

/**
 * @author jacksparrow414
 * @date 2021/2/28
 */
public class FeignUseExampleTest {
    
    /**
     * 此处会报错，因为UserEntity上使用了@Builder,
     * jackson序列化的时候需要一个无参构造函数，加了无参构造函数会和@Builder注解冲突，
     * 所以如果想测试，换一个类
     */
    @Test
    public void useFeign() {
        FeignUseExample example = new FeignUseExample();
        example.useFeign();
    }
}