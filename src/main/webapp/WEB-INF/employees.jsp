<%@ page import="java.util.List" %>
<%@ page import="com.example.company_employee_servlet.model.Employee" %>
<%@ page import="com.example.company_employee_servlet.model.User" %>
<%@ page import="com.example.company_employee_servlet.model.UserType" %><%--
  Created by IntelliJ IDEA.
  User: Serob
  Date: 13-Nov-23
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>
<%
    List<Employee> employees = (List<Employee>) request.getAttribute("employees");
    User user = (User) session.getAttribute("user");
%>
<h2>Employees</h2>

<a href="/createEmployee">Create Employee</a>
<form action="/employees" method="get">
    <%
        String keyword = request.getParameter("keyword");
        keyword = (keyword == null || keyword.equals("null"))
                ? "" : keyword;
    %>
    <input type="text" name="keyword" value="<%=keyword%>">
    <input type="submit" value="search">
</form>
<table border="1px">
    <tr>
        <td>picture</td>
        <td>ID</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Email</td>
        <td>Company name (country)</td>
        <td>Company ID</td>
        <%
            if (user.getType() == UserType.ADMIN) {
        %>
        <td>Action</td>
        <%
            }
        %>
    </tr>
    <%
        if (employees != null && !employees.isEmpty()) {
            for (Employee employee : employees) {
    %>
    <tr>
        <%
            String picName = employee.getPicName();
            if (picName == null || picName.equals("null")) {
        %>
        <th><img src="/img/default_image.png" width="100"></th>
        <%
        } else {
        %>
        <th><img src="/getImage?picName=<%=employee.getPicName()%>" width="100"></th>
        <%
            }
        %>
        <th><%=employee.getId()%>
        </th>
        <th><%=employee.getName()%>
        </th>
        <th><%=employee.getSurname()%>
        </th>
        <th><%=employee.getEmail()%>
        </th>
        <th><%=employee.getCompany().getName()%> (<%=employee.getCompany().getCountry()%>)</th>
        <th><%=employee.getCompany().getId()%>
        </th>
        <%
            if (user.getType() == UserType.ADMIN) {
        %>
        <th>
            <a href="/removeEmployee?id=<%=employee.getId()%>">delete/</a>
            <a href="/updateEmployee?id=<%=employee.getId()%>">update</a>
        </th>
        <%
            }
        %>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
