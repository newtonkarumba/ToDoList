package com.assignment.servlets;

import com.assignment.model.Message;
import com.assignment.model.Organization;
import com.assignment.model.Student;
import com.assignment.utilities.DbConnection;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@WebServlet("/Student")
public class StudentServlet extends HttpServlet {

    DbConnection dbConnection;

    @Override
    public void init() throws ServletException {
       dbConnection= (DbConnection) getServletContext().getAttribute("dbConnectionClass");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        ServletContext scx = getServletContext();
//        Connection dbConnection = (Connection) scx.getAttribute("dbConnection");
        resp.setContentType("text/html");

        // saving the users in an arrayList
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<table width='100%'><tr><th>name</th><th>Id</th></tr>");

   //     Collection<Student> students = new ArrayList<Student>();

        try {
            if(dbConnection == null)
                dbConnection= new DbConnection("jdbc:mysql://localhost:3306/Student","root",
                    "1234");//.getConnection();
            PreparedStatement statement = dbConnection.getPreparedStatement("SELECT * FROM user");
          //  statement.execute();
            ResultSet result =dbConnection.executeQuery(statement);

            while (result.next()){
                stringBuilder.append("<tr><td>" + result.getString("name") +"</td><td>" + result.getString("id") + "</td></tr>");


             /*   Student student = new Student();
                student.setName(result.getString("name"));
                student.setId(result.getString("id"));*/



               /* students.add(student);*/
            }

        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        stringBuilder.append("</table>");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println(stringBuilder);

       /* ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().print(mapper.writeValueAsString(students));*/

    }

    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ServletContext scx = getServletContext();
        if(dbConnection==null){
            return;
        }
//        Connection dbConnection = (Connection) scx.getAttribute("dbConnection");

        String name = request.getParameter("name");
        String id = request.getParameter("id");



        try {
            PreparedStatement statement = dbConnection.getPreparedStatement("insert into user(name,id) values(?,?)");
            statement.setString(1, name==null?null: name.toUpperCase());
            statement.setString(2, id==null?null: id.toUpperCase());

            if(dbConnection.execute(statement)){
                response.getWriter().write(new ObjectMapper().writeValueAsString(
                        new Message(true,"student added successfully")
                ));
                return;
            }
        }catch (SQLException sqlEx){
            //
        }

        response.getWriter().write(new ObjectMapper().writeValueAsString(
                new Message(false,"student failed successfully")
        ));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext scx = getServletContext();
        Connection dbConnection = (Connection) scx.getAttribute("dbConnection");
        String name = req.getParameter("name");
        String id = req.getParameter("id");


        try {
            PreparedStatement statement = dbConnection.prepareStatement("DELETE * FROM user ");
            //  preparedStatement.setLong(1, student.getId());
            //return dbConnection.execute(preparedStatement);
            statement.executeUpdate();

        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }

    }
}



