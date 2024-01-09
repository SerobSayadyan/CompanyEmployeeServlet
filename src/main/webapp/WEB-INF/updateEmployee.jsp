<%@ page import="java.util.List" %>
<%@ page import="com.example.company_employee_servlet.model.Employee" %>
<%@ page import="com.example.company_employee_servlet.model.Company" %><%--
  Created by IntelliJ IDEA.
  User: Serob
  Date: 13-Nov-23
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Employee</title>
</head>
<body>
<%
    Employee employee = (Employee) request.getAttribute("employee");
    @SuppressWarnings("all")
    List<Company> companies = (List<Company>) request.getAttribute("companies");
%>
<h2>Update Employee</h2>
<form action="/updateEmployee" method="post">
    <input name="id" type="hidden" value="<%=employee.getId()%>">
    Name: <input type="text" name="name" value="<%=employee.getName()%>"><br>
    Surname: <input type="text" name="surname" value="<%=employee.getSurname()%>"><br>
    Email: <input type="text" name="email" value="<%=employee.getEmail()%>"><br>
    Company ID:
    <select name="companyID">
        <%for (Company company : companies) {%>
        <option value="<%=company.getId()%>"><%=company.getName()%> (<%=company.getCountry()%>)
        </option>
        <%}%>
    </select>

    <input type="submit" value="update">
</form>
</body>
</html>
