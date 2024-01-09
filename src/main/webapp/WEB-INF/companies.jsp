<%@ page import="java.util.List" %>
<%@ page import="com.example.company_employee_servlet.model.Company" %>
<%@ page import="com.example.company_employee_servlet.model.User" %>
<%@ page import="com.example.company_employee_servlet.model.UserType" %><%--
  Created by IntelliJ IDEA.
  User: Serob
  Date: 09-Nov-23
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Companies</title>
</head>

<% @SuppressWarnings("unchecked")
List<Company> companies = (List<Company>) request.getAttribute("companies");
    User user = (User) session.getAttribute("user");
%>
<body>
<h2>Companies</h2> <a href="/createCompany">Create company</a>
<table border="1px">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Country</th>
        <%
            if (user.getType() == UserType.ADMIN) {
        %>
        <th>Action</th>
        <%
            }
        %>
    </tr>
    <%
        if (companies != null && !companies.isEmpty()) {
            for (Company company : companies) {%>
    <tr>
        <td><%= company.getId()%>
        </td>
        <td><%= company.getName()%>
        </td>
        <td><%= company.getCountry()%>
        </td>
        <%
            if (user.getType() == UserType.ADMIN) {
        %>
        <td><a href="/removeCompany?id=<%=company.getId()%>">delete</a> /
            <a href="/updateCompany?id=<%=company.getId()%>">update</a>
        </td>
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
