<%@ page import="com.example.company_employee_servlet.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Serob
  Date: 22-Nov-23
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Companies and employees</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
%>
<h3>Welcome <i><%=user.getName()%> <%=user.getSurname()%></i>  <a href="/logout">log out</a></h3>
<a href="/companies">Companies</a> / <a href="/employees">Employees</a>
</body>
</html>
