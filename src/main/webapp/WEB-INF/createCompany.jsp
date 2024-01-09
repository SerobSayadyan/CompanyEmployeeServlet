<%@ page import="java.util.List" %>
<%@ page import="com.example.company_employee_servlet.model.Employee" %><%--
  Created by IntelliJ IDEA.
  User: Serob
  Date: 09-Nov-23
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Company</title>
</head>
<body>
<h2>Create Company</h2>
<form action="/createCompany" method="post">
    name: <input type="text" name="name"><br>
    country: <input type="text" name="country"><br>
    <input type="submit" value="create">
</form>

</body>
</html>
