package com.example.mybatis.demomybatis.config;

import cn.hutool.core.collection.CollectionUtil;
import com.example.mybatis.demomybatis.jdbc.datasource.MyDataSource;
import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 自定义数据源设置.
 *
 * <p>模仿ShardingSphere配置数据源的实现思路</p>
 *
 * <p>使用{@link org.springframework.boot.context.properties.bind.Binder}
 * 获取【配置文件具体属性】下的集合的值,需要注入Environment，通过实现{@link org.springframework.context.EnvironmentAware}接口，
 * 在具体方法里处理，这个方法会有environment。【可以获取具体的属性】</p>
 *
 * <p>Binder和{@link org.springframework.boot.context.properties.ConfigurationProperties}很类似，
 * 不过binder用的情况基本是我们自己手动处理【配置文件】的参数绑定</p>
 */
//@Configuration
//@AutoConfigureBefore(value = {DataSourceAutoConfiguration.class})
public class CustomDataSourceConfiguration implements EnvironmentAware {
    
    private Environment environment;
    
    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }
    
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
        // 利用Binder获取配置文件的信息
        Binder binder = Binder.get(environment);
        BindResult<Map> bind = binder.bind(prefix, Bindable.of(Map.class));
        return bind.get();
    }
}
