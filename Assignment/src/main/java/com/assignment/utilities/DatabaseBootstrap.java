package com.assignment.utilities;

import com.assignment.utilities.DbConnection;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebListener
public class DatabaseBootstrap implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        DbConnection dbConnection = null;
        try {
            dbConnection = new DbConnection("jdbc:mysql://localhost:3306/Student","root",
                    "1234");


        System.out.println("INFO: Creating database if it does not exist....");

        Statement statement = null;
        Statement statement2 = null;

        try {
            statement = dbConnection.getConnection().createStatement();
            statement.execute("CREATE DATABASE IF NOT EXISTS Student");



            System.out.println("INFO: Connection to database just created or existing");
            DbConnection dbConnection2 = new DbConnection("jdbc:mysql://localhost:3306/Student","root",
                    "1234");
            statement2 = dbConnection2.getConnection().createStatement();

//creating your table
            System.out.println("INFO: Creating tables");
            statement2.execute("create table if not exists user(name varchar(255), age varchar(25)");

            sce.getServletContext().setAttribute("dbConnection", dbConnection2.getConnection());
            sce.getServletContext().setAttribute("dbConnectionClass", dbConnection2);


        }catch (SQLException sqEx){
            sqEx.printStackTrace();
        }finally {
            try {
                if (statement != null)
                    statement.close();

                if (statement2 != null)
                    statement2.close();

            }catch (SQLException sqlEx2){
                sqlEx2.printStackTrace();
            }
        }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        Connection connection = (Connection) sce.getServletContext().getAttribute("dbConnection");

        if (connection != null){
            try{
                connection.close();
            }catch (SQLException sqlEx){
                sqlEx.printStackTrace();
            }
        }

    }
}
