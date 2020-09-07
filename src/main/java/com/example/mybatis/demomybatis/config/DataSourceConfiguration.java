package com.example.mybatis.demomybatis.config;

import cn.hutool.core.collection.CollectionUtil;
import com.example.mybatis.demomybatis.jdbc.datasource.MyDataSource;
import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 自定义数据源设置.
 *
 * <p>模仿ShardingSphere配置数据源的实现思路</p>
 *
 * <p>使用{@link org.springframework.boot.context.properties.bind.Binder}
 * 获取具体属性下的集合的值,需要注入Environment</p>
 */
@Configuration
@AutoConfigureBefore(value = {DataSourceAutoConfiguration.class})
public class DataSourceConfiguration {
    
    @Autowired
    private Environment environment;
    
    @Bean
    @SneakyThrows
    public DataSource createDataSource() {
        Map<String, Object> property = getDataSourcePropertyByPrefix("spring.datasource");
        Preconditions.checkState(CollectionUtil.isNotEmpty(property));
        Map<String, DataSource> dataSourceMap = new HashMap<>(1);
        DataSource dataSource = (DataSource) Class.forName(property.get("type").toString()).newInstance();
        property.remove("type");
        Iterator<Entry<String, Object>> iterator = property.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, Object> entry = iterator.next();
            // 通过反射调用各个属性的set方法，设置必要属性
            callSetterMethod(dataSource, getSetterMethodName(entry.getKey()), entry.getValue().toString());
        }
        // 将dataSource设置进去
        dataSourceMap.put("master", dataSource);
        return new MyDataSource(dataSourceMap);
    }
    
    @SneakyThrows
    private void callSetterMethod(final DataSource dataSource, final String setterMethodName, final String value) {
        Method method = dataSource.getClass().getMethod(setterMethodName, String.class);
        method.invoke(dataSource, value);
    }
    
    private String getSetterMethodName(final String key) {
        return key.contains("-") ? CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "set-" + key) : "set" + String.valueOf(key.charAt(0)).toUpperCase() + key.substring(1);
    }
    
    private Map getDataSourcePropertyByPrefix(String prefix) {
        Binder binder = Binder.get(environment);
        BindResult<Map> bind = binder.bind(prefix, Map.class);
        return bind.get();
    }
}
