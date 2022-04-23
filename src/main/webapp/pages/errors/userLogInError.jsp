<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 22.04.2022
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index</title>
    <link rel="stylesheet" href="/style.css">
</head>

<body>

<ul>
    <li><p2>You input wrong login or password</p2></li>
    <form action="${pageContext.request.contextPath}/user/login" method="get">
        <li><p5>Login </p5><input type="text" name="login" placeholder="login"></li>
        <li><p5>Password </p5><input type="text" name="password" placeholder="password"></li>
        <li><input type="submit" value="LogIn"></li>
    </form>
    <li>If you don't register click <a href="${pageContext.request.contextPath}/pages/user/Register.jsp">Register</a> </li>
</ul>

</body>

</html>
