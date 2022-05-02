<%--@elvariable id="user" type="java"--%>
<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 02.05.2022
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>userPrivateRoom</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>

<body>
<c:import url="/pages/adminHeader.jsp"/>

<ul>
    <li><h1>User Data</h1></li>
    <li><h5>Login: </h5>${user.login}</li>
    <li><h5>AccessLevel:</h5>${user.accessLevel}</li>
</ul>
<form action="${pageContext.request.contextPath}/user/userPrivateRoom" method="post">
    <input type="submit" value="LogOut">
</form>
</body>
</html>
