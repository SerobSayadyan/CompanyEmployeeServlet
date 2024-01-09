<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register Page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
          integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="css/registerStyle.css">
</head>
<body>
<%
    if (session.getAttribute("user") != null) {
        response.sendRedirect("/home");
    }
    String msg = (String) request.getAttribute("msg");
%>


<div class="main-block">
    <h1>Registration</h1>
    <form action="/register" method="post">
        <hr>
        <div class="account-type">
            <input type="radio" value="USER" id="radioOne" name="type" checked/>
            <label for="radioOne" class="radio">USER</label>
            <input type="radio" value="ADMIN" id="radioTwo" name="type"/>
            <label for="radioTwo" class="radio">ADMIN</label>
        </div>
        <hr>

        <label id="icon" for="name"><i class="fas fa-user"></i></label>
        <input type="text" name="name" id="name" placeholder="Name" required/>

        <label id="icon" for="name"><i class="fas fa-user"></i></label>
        <input type="text" name="surname" id="name" placeholder="Surname" required/>

        <label id="icon" for="name"><i class="fas fa-envelope"></i></label>
        <input type="text" name="email" id="name" placeholder="Email" required/>

        <label id="icon" for="name"><i class="fas fa-unlock-alt"></i></label>
        <input type="password" name="password" id="name" placeholder="Password" required/>

        <div class="btn-block">
            <button type="submit">Register</button>
            <a href="/">
                <button type="button">Back</button>
            </a>
        </div>

        <%
            if (msg != null) {
                if (!msg.isEmpty()) {
        %>
        <span style="color: red"><%=msg%></span>
        <%
                }
            }
        %></form>
</div>

</body>
</html>