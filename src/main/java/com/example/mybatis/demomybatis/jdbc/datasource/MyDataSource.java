package com.example.mybatis.demomybatis.jdbc.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 * @author jacksparrow414
 * @date 2020/9/6
 */
public class MyDataSource implements DataSource {
    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }
    
    @Override
    public Connection getConnection(final String username, final String password) throws SQLException {
        return null;
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
