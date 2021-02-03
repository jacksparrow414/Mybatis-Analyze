package com.example.mybatis.demomybatis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TestTransaction;

/**
 * 在Spring的测试方法中使用事务支持.
 * <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#testcontext-tx">test中使用事务管理文档</a>
 * @author jacksparrow414
 * @date 2021/2/3 9:53
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class SpringTransactionTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 由于这里数据库事务是自动提交事务的，所以当错误出现时，也是能够入到数据库.
     *
     * 1.如果仅仅想要测试，但是测试之后不对数据库数据产生影响，只需要加@Transactional注解,注意：事务的回滚不能通过rollback属性来指定回滚。加了注解，会自动回滚数据
     * <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#testcontext-tx-enabling-transactions">文档地址</a>
     * 2.如果想要管理事务，可以使用TestTransaction静态类，并且当前测试类要继承AbstractTransactionalJUnit4SpringContextTests.<a href="https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#testcontext-tx-programmatic-tx-mgt">TestTransaction文档</a>
     * 3.还可以使用@Commit和@Rollback注解来控制事务是否提交、回滚
     */
    @Test
    public void assertTransactionInTest() {
        try {
            jdbcTemplate.execute("insert into user(id, name, age) VALUES (18, 'woshi', 74)");
            int a = 1/0;
            TestTransaction.flagForCommit();
            TestTransaction.end();
        }catch (Exception exception) {
            logger.error("出现错误,事务回滚", exception);
            TestTransaction.flagForRollback();
        }
    }
}
