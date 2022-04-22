<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 22.04.2022
  Time: 17:32
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
    <form action="${pageContext.request.contextPath}//user/login" method="post">
        <li><p5>Login </p5><input type="text" name="login" placeholder="login"></li>
        <li><p5>Password</p5><input type="text" name="password" placeholder="password"></li>
        <li><input type="submit" value="Register"></li>
    </form>
    <li>If you want LogIn click <a href="${pageContext.request.contextPath}/index.jsp">LogIn</a> </li>
</ul>
</body>
</html>
