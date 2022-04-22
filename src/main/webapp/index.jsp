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
        <form action="${pageContext.request.contextPath}/user/login" method="get">
            <li><p5>Login </p5><input type="text" name="login" placeholder="login"></li>
            <li><p5>Password </p5><input type="text" name="password" placeholder="password"></li>
            <li><input type="submit" value="LogIn"></li>
        </form>
    <li>If you don't register click <a href="${pageContext.request.contextPath}/pages/user/Register.jsp">Register</a> </li>
</ul>

</body>

</html>