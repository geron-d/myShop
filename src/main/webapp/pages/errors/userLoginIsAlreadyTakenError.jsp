<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 22.04.2022
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<ul>
    <li><p2>This login is already taken. Please choose another login.</p2></li>
    <form action="${pageContext.request.contextPath}/user/create" method="post">
        <li><p5>Login </p5><input type="text" name="login" placeholder="login"></li>
        <li><p5>Password</p5><input type="text" name="password" placeholder="password"></li>
        <li><input type="submit" value="Register"></li>
    </form>
    <li>If you want LogIn click <a href="${pageContext.request.contextPath}/index.jsp">LogIn</a> </li>
</ul>
</body>
</html>
