<%@ page import ="com.assignment.model.Student"%>
<%@ page import ="com.assignment.model.Teacher"%>
<%@ page isELIgnored = "false" %>

<html>
<body>
<h2>Hello there NEWTON. WELCOME TO World!</h2>


 <%-- JSP actions <%= com.assignment.model.Student.msg()%> --%>

  <%-- Like saying User user = new User();--%>

  <h1> Using useBean </h1>

<jsp:useBean id ="user" class="com.assignment.model.Teacher" scope="request"/>
     <jsp:setProperty name ="user" property="*"/>


      My name is  :  <jsp:getProperty name ="user" property="name" /> <br/>
      I live at   :  <jsp:getProperty name ="user" property="address" /> <br/>
            mail  :  <jsp:getProperty name ="user" property="email" /> <br/>
          Password:  <jsp:getProperty name ="user" property="password" /> <br/>


   <hr/>
   <h1> Using expression Language </h1>
   Name:    ${param.name} <br>
   Address: ${param.address} <br>
   Email: ${param.email} <br>
   Password:  ${param.password} <br>

  <hr/>

  <form action="#" method="post">
            Name    :  <input type="text" name="name"/> </br/>
            Address :  <input type="text" name="address"/> </br/>
            Email   :  <input type="text" name="email"/> </br/>
            Password:  <input type="password" name="password"/>  </br/>
                       <input type="submit" value="Submit"> </br/>
        </form>


<%
        String fromNairobi = "";
        if(user.getName() !=null && user.getName().equals("Mike"))
        fromNairobi = "Nai Town";
        else
        fromNairobi = "Hapatikani";

%>
<%= fromNairobi %> <br>

<%= user.calculateAge()%> <br>




</body>
</html>