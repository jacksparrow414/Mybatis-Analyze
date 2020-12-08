package com.example.mybatis.demomybatis.lambda;

import com.example.mybatis.demomybatis.entity.Gener;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jacksparrow414
 * @date 2020/12/8 13:49
 */
public class FunctionExampleTest {

    private Gener gener;

    private FunctionExample functionExample;

    @Before
    public void setUp() {
        gener = new Gener();
        gener.setGener(15);
        functionExample = new FunctionExample();
    }

    @Test
    public void assertGetValue() {
        int actual = functionExample.getApplyValue(gener, a -> a.getGener() + 7);
        assertEquals(22, actual);
    }

    @Test
    public void assertGetComposeApplyValue() {
        int actual = functionExample.getComposeApplyValue(gener, a -> a.getGener() + 7);
        assertEquals(27, actual);
    }
}