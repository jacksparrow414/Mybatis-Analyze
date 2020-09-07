package com.example.mybatis.demomybatis.config;

import com.example.mybatis.demomybatis.jdbc.datasource.MyDataSource;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author duhongbo
 * @date 2020/9/7 15:34
 */
@Configuration
@AutoConfigureBefore(value = {DataSourceAutoConfiguration.class})
public class DataSourceConfiguration {
    
    @Bean
    @SneakyThrows
    public DataSource createDataSource() {
        Map<String, DataSource> dataSourceMap = new HashMap<>(1);
        DataSource dataSource = (DataSource) Class.forName("com.alibaba.druid.pool.DruidDataSource").newInstance();
        dataSourceMap.put("master", dataSource);
        return new MyDataSource(dataSourceMap);
    }
}
