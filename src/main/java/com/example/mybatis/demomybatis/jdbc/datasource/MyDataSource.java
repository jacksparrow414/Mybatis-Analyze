package com.example.mybatis.demomybatis.jdbc.datasource;

import com.example.mybatis.demomybatis.jdbc.connection.MyConnection;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author jacksparrow414
 * @date 2020/9/6
 */
public class MyDataSource implements DataSource {
    
    /**
     * 自定义dataSourceMap,里面放置了各种DataSource,如Druid,C3P0.
     */
    private final Map<String, DataSource> dataSourceMap;
    
    public MyDataSource(final Map<String, DataSource> dataSourceMap) {
        this.dataSourceMap = dataSourceMap;
    }
    
    /**
     * 获取连接,仅仅返回一个MyConnection对象.
     */
    @Override
    public Connection getConnection() throws SQLException {
        return new MyConnection(dataSourceMap);
    }
    
    /**
     * 获取连接,仅仅返回一个MyConnection对象.
     */
    @Override
    public Connection getConnection(final String username, final String password) throws SQLException {
        return getConnection();
    }
    
    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        return null;
    }
    
    @Override
    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return false;
    }
    
    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }
    
    @Override
    public void setLogWriter(final PrintWriter out) throws SQLException {
    
    }
    
    @Override
    public void setLoginTimeout(final int seconds) throws SQLException {
    
    }
    
    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }
    
    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
