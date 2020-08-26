package com.assignment.utilities;


import java.sql.*;

public class DbConnection implements DbConnectionI {
    private String username;
    private String password;
    private String url;
    private Connection connection;

    public DbConnection() throws SQLException, ClassNotFoundException {
        this.url = "jdbc:mysql://localhost:3306/Student";
        this.username = "root";
        this.password = "1234";
        this.connect();
    }

    public DbConnection(String url,String username, String password ) throws SQLException, ClassNotFoundException {
        this.username = username;
        this.password = password;
        this.url = url;
        this.connect();
    }

    private void connect() throws SQLException, ClassNotFoundException {
        this.connection = this.openConnection();
    }
    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public Connection openConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public ResultSet executeQuery(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeQuery();
    }

    @Override
    public ResultSet executeQuery(String sql)  throws SQLException {
        System.out.println(sql);
        Statement statement = this.connection.createStatement();
        return statement.executeQuery(sql);
    }

    @Override
    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    @Override
    public boolean execute(PreparedStatement preparedStatement)  throws SQLException {
        return preparedStatement.executeUpdate() != -1;
    }

    @Override
    public boolean execute(String sql)  throws SQLException {
        Statement statement = this.connection.createStatement();
        return statement.execute(sql);
    }

    @Override
    public void close() throws SQLException {
        this.connection.close();
    }
}