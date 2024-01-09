<%@ page import="com.example.company_employee_servlet.model.Company" %><%--
  Created by IntelliJ IDEA.
  User: Serob
  Date: 09-Nov-23
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Company</title>
</head>
<body>
<% Company company = (Company) request.getAttribute("company"); %>

<h2>Update Company</h2>
<form action="/updateCompany" method="post">
    <input name="id" type="hidden" value="<%=company.getId()%>">
    name: <input type="text" name="name" value="<%=company.getName()%>"> <br>
    country: <input type="text" name="country" value="<%=company.getCountry()%>"> <br>
    <input type="submit" value="update">
</form>

</body>
</html>
