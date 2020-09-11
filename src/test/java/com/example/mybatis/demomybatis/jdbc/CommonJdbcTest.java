package com.example.mybatis.demomybatis.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.mybatis.demomybatis.entity.Gener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * 传统JDBC方式演示.
 */
public final class CommonJdbcTest {
    
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    private final String URL = "jdbc:mysql://localhost:3306/dhb";
    
    private final String USER = "root";
    
    private final String PASSWORD = "12345";
    
    @SneakyThrows
    @Test
    public void assertCommonJdbcInsert() {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        connection.setAutoCommit(true);
        String sql = "insert into gener (id, gener) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 参数下标从1开始
        preparedStatement.setInt(1, 289);
        preparedStatement.setInt(2, 857);
        assertThat(preparedStatement.executeUpdate() , is(1));
        preparedStatement.close();
        connection.close();
    }
    
    @SneakyThrows
    @Test
    public void assertCommonJdbcDelete() {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("delete from gener where id = ?");
        preparedStatement.setInt(1, 289);
        assertThat(preparedStatement.executeUpdate(), is(1));
        preparedStatement.close();
        connection.close();
    }
    
    @SneakyThrows
    @Test
    public void assertCommonJdbcUpdate() {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("update gener set gener = ? where id = ?");
        preparedStatement.setInt(1,233);
        preparedStatement.setInt(2, 289);
        assertThat(preparedStatement.executeUpdate(), is(1));
        preparedStatement.close();
        connection.close();
    }
    
    @SneakyThrows
    @Test
    public void assertCommonJdbcQuery() {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("select * from gener");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Gener> list = new ArrayList<>();
        while (resultSet.next()) {
            Gener gener = new Gener();
            int id = resultSet.getInt(1);
            int generQuery = resultSet.getInt(2);
            gener.setId(id);
            gener.setGener(generQuery);
            list.add(gener);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        System.out.println(list.size());
    }
    
    @SneakyThrows
    @Test
    public void assertOperationByDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into gener(id,gener) values(?,?)");
        preparedStatement.setInt(1, 233);
        preparedStatement.setInt(2, 34);
        assertThat(preparedStatement.executeUpdate(), is(1));
        preparedStatement.close();
        connection.close();
        dataSource.close();
    }
}
