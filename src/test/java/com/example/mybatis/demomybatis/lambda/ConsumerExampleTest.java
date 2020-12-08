package com.example.mybatis.demomybatis.lambda;

import com.example.mybatis.demomybatis.entity.Gener;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jacksparrow414
 * @date 2020/12/8 11:33
 */
public final class ConsumerExampleTest {

    private Gener gener;

    @Before
    public void setUp() {
        gener = new Gener();
    }

    @Test
    public void assertSetValueToClass() {
        ConsumerExample consumerExample = new ConsumerExample();
        consumerExample.setValueToClass(gener, Gener::setGener);
        System.out.println(gener.getGener().intValue());
    }
}