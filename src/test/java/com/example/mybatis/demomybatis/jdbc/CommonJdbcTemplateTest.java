package com.example.mybatis.demomybatis.jdbc;

import cn.hutool.core.collection.CollUtil;
import com.example.mybatis.demomybatis.entity.UserEntity;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * 使用JdbcTemplate操作数据库示例.
 * @author jacksparrow414
 * @date 2020/9/9 17:27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public final class CommonJdbcTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 使用PreparedStatement进行预处理.
     * PreparedStatementCallback中的类型是里面函数执行完毕的返回值类型.
     */
    @Test
    public void assertJdbcTemplateInsert() {
        Integer actual = jdbcTemplate.execute("insert into user (id, name, age) VALUES (?,?,?)", (PreparedStatementCallback<Integer>) preparedStatement -> {
            preparedStatement.setInt(1, 19);
            preparedStatement.setString(2, "jacku");
            preparedStatement.setInt(3, 78);
            return preparedStatement.executeUpdate();
        });
        assertThat(actual, Is.is(1));
    }

    /**
     * 查询结果集，返回list.不使用jdbcTemplate.queryForList()，这个方法是将一条数据返回一个list，如果结果集是多个，则会报错
     * org.springframework.jdbc.IncorrectResultSetColumnCountException: Incorrect column count: expected 1, actual 4
     * 直接使用query方法,使用RowMapper函数，里面进行设值操作.
     * 不需要在里面进行while(resultSet.next()){...}判断，这里返回的就是每一项的resultSet，直接进行赋值即可
     * 如果结果集为空，则不会进入函数内
     */
    @Test
    public void assertJdbcTemplateQuery() {
        List<UserEntity> userEntities = jdbcTemplate.query("select * from user", (resultSet, i) -> {
                UserEntity entity = UserEntity.builder().build();
                entity.setId(resultSet.getInt(1));
                entity.setName(resultSet.getString(2));
                entity.setAge(resultSet.getInt(3));
                return entity;
        });
        assertTrue(CollUtil.isNotEmpty(userEntities));
    }
}
