<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main Page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
          integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="css/registerStyle.css">
</head>
<body>

<div class="main-block">
    <h1>Login</h1>
    <form action="/login" method="post">

        <label id="icon" for="name"><i class="fas fa-envelope"></i></label>
        <input type="text" name="email" id="name" placeholder="Email" required/>

        <label id="icon" for="name"><i class="fas fa-unlock-alt"></i></label>
        <input type="password" name="password" id="name" placeholder="Password" required/>

        <div class="btn-block">
            <button type="submit">Submit</button>
            <a href="/register.jsp">
                <button type="button">Register</button>
            </a>
        </div>
    </form>
    <%
        if (session.getAttribute("user") != null) {
            response.sendRedirect("/home");
        }
        String message = (String) session.getAttribute("message");

        if (message != null) {
    %>
    <span style="color: red"><%=message%></span>
    <%
            session.removeAttribute("message");
        }
    %>

</div>
</body>
</html>