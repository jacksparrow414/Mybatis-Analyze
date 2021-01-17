package com.example.mybatis.demomybatis.config;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

/**
 *
 * 如果没有特殊要求，其实不需要配置，
 * 因为{@link TransactionAutoConfiguration}和{@link DataSourceTransactionManagerAutoConfiguration}会自动开启配置
 * @author jacksparrow414
 * @date 2021/1/13 11:30
 */
@Configuration
// @AutoConfigureBefore(value = {DataSourceAutoConfiguration.class})
public class CustomTransactionConfiguration {

//    @Value("${spring.datasource.driver-class-name}")
//    private String driverClassName;
//
//    @Value("${spring.datasource.url}")
//    private String url;
//
//    @Value("${spring.datasource.username}")
//    private String username;
//
//    @Value("${spring.datasource.password}")
//    private String password;
//
//    @Bean
//    public DataSource dataSource() {
//        DruidDataSource result = new DruidDataSource();
//        result.setDriverClassName(driverClassName);
//        result.setUrl(url);
//        result.setUsername(username);
//        result.setPassword(password);
//        return result;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }
    
    /**
     *
     * 除了上面的方式，可以直接使用下面这种方式.
     * 直接将DataSource当做属性通过DataSourceTransactionManager的构造方法注入到DataSourceTransactionManager中。
     * 建议使用下面这种方式
     * @param dataSource DataSource具体实例
     * @return org.springframework.transaction.PlatformTransactionManager
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
    /**
     * TransactionTemplate
     * @param transactionManager PlatformTransactionManager具体实例
     * @return org.springframework.transaction.support.TransactionTemplate
     */
    @Bean
    public TransactionTemplate transactionTemplate (PlatformTransactionManager transactionManager) {
        TransactionTemplate result = new TransactionTemplate(transactionManager);
        // 设置事务传播属性
        result.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        return result;
    }
}
