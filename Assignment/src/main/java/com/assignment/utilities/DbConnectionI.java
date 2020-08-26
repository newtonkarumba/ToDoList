package com.assignment.utilities;

import java.sql.Connection;
import java.sql.*;

public interface DbConnectionI {
    Connection openConnection() throws SQLException, ClassNotFoundException;
    ResultSet executeQuery(PreparedStatement preparedStatement) throws SQLException;
    ResultSet executeQuery(String sql) throws SQLException;
    PreparedStatement getPreparedStatement(String sql) throws SQLException;
    boolean execute(PreparedStatement preparedStatement) throws SQLException;
    boolean execute(String sql) throws SQLException;
    void close() throws SQLException;
    Connection getConnection();
}