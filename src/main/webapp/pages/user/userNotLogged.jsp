<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 03.05.2022
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>userNotLogged</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>

<body>

<ul>
    <li>
        <p>You are not logged. Log in, please</p>
    </li>
    <form action="${pageContext.request.contextPath}/user/login" method="get">
        <li>
            <p>Login</p>
            <label>
                <input type="text" name="login" placeholder="login">
            </label></li>
        <li>
            <p>Password</p>
            <label>
                <input type="text" name="password" placeholder="password">
            </label></li>
        <li><input type="submit" value="LogIn"></li>
    </form>
    <li>If you don't register click <a href="${pageContext.request.contextPath}/pages/user/Register.jsp">Register</a>
    </li>
</ul>

</body>

</html>
