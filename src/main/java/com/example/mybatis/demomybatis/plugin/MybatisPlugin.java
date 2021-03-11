package com.example.mybatis.demomybatis.plugin;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.PreparedStatement;

/**
 * <a href="https://mybatis.org/mybatis-3/zh/configuration.html#plugins">官方插件文档</a>
 *
 * 1、实现mybatis的interceptor接口的intercept方法
 * 2、在该类上是使用@Intercepts注解，Signature的type有四种：ParameterHandler、ResultSetHandler、StatementHandler 、Executor
 * @author duhongbo
 * @date 2021/3/11 16:03
 */
@Intercepts({
        @Signature(
                type = ParameterHandler.class,
                method = "setParameters",
                args = {PreparedStatement.class})})
public class MybatisPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // do something before
        Object returnObject = invocation.proceed();
        // do something after
        return returnObject;
    }
}
