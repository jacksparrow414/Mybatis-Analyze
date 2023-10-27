package com.example.mybatis.demomybatis.jdbc.statement;

import com.example.mybatis.demomybatis.jdbc.connection.MyConnection;
import lombok.Getter;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jacksparrow414
 * @date 2020/9/6
 */
@Getter
public class MyPreparedStatement implements PreparedStatement {
    
    private final MyConnection myConnection;
    private final String sql;
    private ResultSet resultSet;
    private final List<Object> parameters = new ArrayList<>();
    
    public MyPreparedStatement(final MyConnection myConnection, final String sql) {
        this.myConnection = myConnection;
        this.sql = sql;
    }
    
    @Override
    public ResultSet executeQuery() throws SQLException {
        return null;
    }
    
    @Override
    public int executeUpdate() throws SQLException {
        return 0;
    }
    
    @Override
    public void setNull(final int parameterIndex, final int sqlType) throws SQLException {
    
    }
    
    @Override
    public void setBoolean(final int parameterIndex, final boolean x) throws SQLException {
    
    }
    
    @Override
    public void setByte(final int parameterIndex, final byte x) throws SQLException {
    
    }
    
    @Override
    public void setShort(final int parameterIndex, final short x) throws SQLException {
    
    }
    
    @Override
    public void setInt(final int parameterIndex, final int x) throws SQLException {
        // Mybatis TypeHandler处理的时候,现将参数存放在list中
        
        // list必须有值，才可以调用set方法
        parameters.add(null);
        parameters.set(parameterIndex - 1, x);
    }
    
    @Override
    public void setLong(final int parameterIndex, final long x) throws SQLException {
    
    }
    
    @Override
    public void setFloat(final int parameterIndex, final float x) throws SQLException {
    
    }
    
    @Override
    public void setDouble(final int parameterIndex, final double x) throws SQLException {
    
    }
    
    @Override
    public void setBigDecimal(final int parameterIndex, final BigDecimal x) throws SQLException {
    
    }
    
    @Override
    public void setString(final int parameterIndex, final String x) throws SQLException {
        parameters.add(null);
        parameters.set(parameterIndex - 1, x);
    }
    
    @Override
    public void setBytes(final int parameterIndex, final byte[] x) throws SQLException {
    
    }
    
    @Override
    public void setDate(final int parameterIndex, final Date x) throws SQLException {
    
    }
    
    @Override
    public void setTime(final int parameterIndex, final Time x) throws SQLException {
    
    }
    
    @Override
    public void setTimestamp(final int parameterIndex, final Timestamp x) throws SQLException {
    
    }
    
    @Override
    public void setAsciiStream(final int parameterIndex, final InputStream x, final int length) throws SQLException {
    
    }
    
    @Override
    public void setUnicodeStream(final int parameterIndex, final InputStream x, final int length) throws SQLException {
    
    }
    
    @Override
    public void setBinaryStream(final int parameterIndex, final InputStream x, final int length) throws SQLException {
    
    }
    
    @Override
    public void clearParameters() throws SQLException {
    
    }
    
    @Override
    public void setObject(final int parameterIndex, final Object x, final int targetSqlType) throws SQLException {
    
    }
    
    @Override
    public void setObject(final int parameterIndex, final Object x) throws SQLException {
    
    }
    
    /**
     * 真正执行获取Connection-连接、获取PreparedStatement并且执行的地方.
     */
    @Override
    public boolean execute() throws SQLException {
        // 获取具体数据源
        DataSource dataSource = this.myConnection.getDataSourceMap().get("master");
        // 获取具体数据源的connection
        Connection connection = dataSource.getConnection();
        // 真正获取preparedStatement的地方
        PreparedStatement preparedStatement = connection.prepareStatement(this.sql);
        // 通过反射 调用 设置属性值
        final AtomicInteger loop = new AtomicInteger(0);
        parameters.forEach(item -> {
            Method method = null;
            try {
                // 第一种方式: 直接通过preparedStatement.setObject 设置参数值
                preparedStatement.setObject(loop.get()+1, item);
                
                // 第二种方式: 通过反射调用,也是调用preparedStatement.setObject
               // method = PreparedStatement.class.getMethod("setObject", int.class, Object.class);
                //method.invoke(preparedStatement, loop.get()+1, item);
                // NoSuchMethodException | IllegalAccessException | InvocationTargetException |
            } catch ( SQLException e) {
                e.printStackTrace();
            }
            loop.getAndIncrement();
        });
        preparedStatement.execute();
        ResultSet statementResultSet = preparedStatement.getResultSet();
        this.resultSet = statementResultSet;
        // 关闭资源
        statementResultSet.close();
        preparedStatement.close();
        connection.close();
        return true;
    }
    
    @Override
    public void addBatch() throws SQLException {
    
    }
    
    @Override
    public void setCharacterStream(final int parameterIndex, final Reader reader, final int length) throws SQLException {
    
    }
    
    @Override
    public void setRef(final int parameterIndex, final Ref x) throws SQLException {
    
    }
    
    @Override
    public void setBlob(final int parameterIndex, final Blob x) throws SQLException {
    
    }
    
    @Override
    public void setClob(final int parameterIndex, final Clob x) throws SQLException {
    
    }
    
    @Override
    public void setArray(final int parameterIndex, final Array x) throws SQLException {
    
    }
    
    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return null;
    }
    
    @Override
    public void setDate(final int parameterIndex, final Date x, final Calendar cal) throws SQLException {
    
    }
    
    @Override
    public void setTime(final int parameterIndex, final Time x, final Calendar cal) throws SQLException {
    
    }
    
    @Override
    public void setTimestamp(final int parameterIndex, final Timestamp x, final Calendar cal) throws SQLException {
    
    }
    
    @Override
    public void setNull(final int parameterIndex, final int sqlType, final String typeName) throws SQLException {
    
    }
    
    @Override
    public void setURL(final int parameterIndex, final URL x) throws SQLException {
    
    }
    
    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return null;
    }
    
    @Override
    public void setRowId(final int parameterIndex, final RowId x) throws SQLException {
    
    }
    
    @Override
    public void setNString(final int parameterIndex, final String value) throws SQLException {
    
    }
    
    @Override
    public void setNCharacterStream(final int parameterIndex, final Reader value, final long length) throws SQLException {
    
    }
    
    @Override
    public void setNClob(final int parameterIndex, final NClob value) throws SQLException {
    
    }
    
    @Override
    public void setClob(final int parameterIndex, final Reader reader, final long length) throws SQLException {
    
    }
    
    @Override
    public void setBlob(final int parameterIndex, final InputStream inputStream, final long length) throws SQLException {
    
    }
    
    @Override
    public void setNClob(final int parameterIndex, final Reader reader, final long length) throws SQLException {
    
    }
    
    @Override
    public void setSQLXML(final int parameterIndex, final SQLXML xmlObject) throws SQLException {
    
    }
    
    @Override
    public void setObject(final int parameterIndex, final Object x, final int targetSqlType, final int scaleOrLength) throws SQLException {
    
    }
    
    @Override
    public void setAsciiStream(final int parameterIndex, final InputStream x, final long length) throws SQLException {
    
    }
    
    @Override
    public void setBinaryStream(final int parameterIndex, final InputStream x, final long length) throws SQLException {
    
    }
    
    @Override
    public void setCharacterStream(final int parameterIndex, final Reader reader, final long length) throws SQLException {
    
    }
    
    @Override
    public void setAsciiStream(final int parameterIndex, final InputStream x) throws SQLException {
    
    }
    
    @Override
    public void setBinaryStream(final int parameterIndex, final InputStream x) throws SQLException {
    
    }
    
    @Override
    public void setCharacterStream(final int parameterIndex, final Reader reader) throws SQLException {
    
    }
    
    @Override
    public void setNCharacterStream(final int parameterIndex, final Reader value) throws SQLException {
    
    }
    
    @Override
    public void setClob(final int parameterIndex, final Reader reader) throws SQLException {
    
    }
    
    @Override
    public void setBlob(final int parameterIndex, final InputStream inputStream) throws SQLException {
    
    }
    
    @Override
    public void setNClob(final int parameterIndex, final Reader reader) throws SQLException {
    
    }
    
    @Override
    public ResultSet executeQuery(final String sql) throws SQLException {
        return null;
    }
    
    @Override
    public int executeUpdate(final String sql) throws SQLException {
        return 0;
    }
    
    @Override
    public void close() throws SQLException {
    
    }
    
    @Override
    public int getMaxFieldSize() throws SQLException {
        return 0;
    }
    
    @Override
    public void setMaxFieldSize(final int max) throws SQLException {
    
    }
    
    @Override
    public int getMaxRows() throws SQLException {
        return 0;
    }
    
    @Override
    public void setMaxRows(final int max) throws SQLException {
    
    }
    
    @Override
    public void setEscapeProcessing(final boolean enable) throws SQLException {
    
    }
    
    @Override
    public int getQueryTimeout() throws SQLException {
        return 0;
    }
    
    @Override
    public void setQueryTimeout(final int seconds) throws SQLException {
    
    }
    
    @Override
    public void cancel() throws SQLException {
    
    }
    
    @Override
    public SQLWarning getWarnings() throws SQLException {
        return null;
    }
    
    @Override
    public void clearWarnings() throws SQLException {
    
    }
    
    @Override
    public void setCursorName(final String name) throws SQLException {
    
    }
    
    @Override
    public boolean execute(final String sql) throws SQLException {
        return false;
    }
    
    @Override
    public ResultSet getResultSet() throws SQLException {
        return this.resultSet;
    }
    
    @Override
    public int getUpdateCount() throws SQLException {
        return 0;
    }
    
    @Override
    public boolean getMoreResults() throws SQLException {
        return false;
    }
    
    @Override
    public void setFetchDirection(final int direction) throws SQLException {
    
    }
    
    @Override
    public int getFetchDirection() throws SQLException {
        return 0;
    }
    
    @Override
    public void setFetchSize(final int rows) throws SQLException {
    
    }
    
    @Override
    public int getFetchSize() throws SQLException {
        return 0;
    }
    
    @Override
    public int getResultSetConcurrency() throws SQLException {
        return 0;
    }
    
    @Override
    public int getResultSetType() throws SQLException {
        return 0;
    }
    
    @Override
    public void addBatch(final String sql) throws SQLException {
    
    }
    
    @Override
    public void clearBatch() throws SQLException {
    
    }
    
    @Override
    public int[] executeBatch() throws SQLException {
        return new int[0];
    }
    
    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }
    
    @Override
    public boolean getMoreResults(final int current) throws SQLException {
        return false;
    }
    
    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return null;
    }
    
    @Override
    public int executeUpdate(final String sql, final int autoGeneratedKeys) throws SQLException {
        return 0;
    }
    
    @Override
    public int executeUpdate(final String sql, final int[] columnIndexes) throws SQLException {
        return 0;
    }
    
    @Override
    public int executeUpdate(final String sql, final String[] columnNames) throws SQLException {
        return 0;
    }
    
    @Override
    public boolean execute(final String sql, final int autoGeneratedKeys) throws SQLException {
        return false;
    }
    
    @Override
    public boolean execute(final String sql, final int[] columnIndexes) throws SQLException {
        return false;
    }
    
    @Override
    public boolean execute(final String sql, final String[] columnNames) throws SQLException {
        return false;
    }
    
    @Override
    public int getResultSetHoldability() throws SQLException {
        return 0;
    }
    
    @Override
    public boolean isClosed() throws SQLException {
        return false;
    }
    
    @Override
    public void setPoolable(final boolean poolable) throws SQLException {
    
    }
    
    @Override
    public boolean isPoolable() throws SQLException {
        return false;
    }
    
    @Override
    public void closeOnCompletion() throws SQLException {
    
    }
    
    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return false;
    }
    
    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        return null;
    }
    
    @Override
    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return false;
    }
}
