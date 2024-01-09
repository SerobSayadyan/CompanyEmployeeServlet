<%@ page import="java.util.List" %>
<%@ page import="com.example.company_employee_servlet.model.Company" %><%--
  Created by IntelliJ IDEA.
  User: Serob
  Date: 13-Nov-23
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Employee</title>
</head>
<body>
<h2>Create Employee</h2>
<%
    List<Company> companies = (List<Company>) request.getAttribute("companies");
%>
<form action="/createEmployee" method="post" enctype="multipart/form-data">
    Name: <input type="text" name="name"><br>
    Surname: <input type="text" name="surname"><br>
    Email: <input type="text" name="email"><br>
    Company ID:
    <select name="companyID">
        <%for (Company company : companies) {%>
        <option value="<%=company.getId()%>"><%=company.getName()%> (<%=company.getCountry()%>)<br>
        </option>
        <%}%>
    </select>
    image:
    <input type="file" name="profilePic"> <br>
    <input type="submit" name="create">
</form>

</body>
</html>
