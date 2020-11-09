package com.example.mybatis.demomybatis.entity;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author jacksparrow414
 * @date 2020/8/20 15:55
 */
public final class UserItemTest {
    
    @Test
    public void assertUserItemHashcode() {
        UserItem itemFirst = UserItem.builder().id(78L).build();
        UserItem itemSecond = UserItem.builder().id(78L).build();
        assertThat(itemFirst.getId().hashCode(), is(itemSecond.getId().hashCode()));
    }
}
