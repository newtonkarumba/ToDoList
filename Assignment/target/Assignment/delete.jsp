<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*,java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <script src="./js/app.js"></script>
</head>
<body>
<h1>===Welcome User To your To-Do-List. What would you like to do? ===</h1>
<hr/>
<div class="navbar">
    <ul>
        <li><a class="active" href="home.jsp">Home</a></li>
        <li><a href="add.html">Add Student</a></li>
        <li><a href="view.html">View All Students</a></li>
        <li><a href="delete.jsp">Delete Students</a></li>

    </ul>
</div>

<div id="studentContent"></div>
    <script>
        AssignmentJsLib.view();
    </script>

    <hr/>

    <% out.println("Please enter the name of the student you want to delete....."); %>

    <hr/>

    <form>
      <label for="fname"> Name</label>
      <input type="text" id="fname" name="fname">
      <input type="submit" value="Delete User"
                     onclick="deleteUser()"/>
    </form>
    <p id="deleted"> Delete: </p>

<script>
function deleteUser()
{
    var deletePrompt = "are you sure you want to delete "
    van deletedName = document.getElementById('fname').value;
    var x = document.getElementById("deleted").value;
     if(name=='')
            alert('You cannot delete nothing. Please choose a name');
            return;

    else
            x= deletePrompt + fname;
            return x;

}
</script>
      <hr/>


    <%
            String name=request.getParameter("name");
            try
            {
            DbConnection dbConnection2 = new DbConnection("jdbc:mysql://localhost:3306/Student","root","1234");
            String query = "Select * from user";
            Statement statement = dbConnection2.connect().createStatement();
            int i=statement.executeUpdate("DELETE FROM user WHERE name="+fname);
            out.println("Data Deleted Successfully!");
            }
            catch(Exception e)
            {
            System.out.print(e);
            e.printStackTrace();
            }
    %>



</body>
</html>