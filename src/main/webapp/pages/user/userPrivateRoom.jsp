<%--
  Created by IntelliJ IDEA.
  User: geron
  Date: 01.05.2022
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>userPrivateRoom</title>
    <link rel="stylesheet" href="/style.css">
</head>

<body>
<nav class="top-menu">
    <p><a href="${pageContext.request.contextPath}/start"><img src="${pageContext.request.contextPath}/logo.jpg" alt="Logo"></a></p>
    <ul class="menu-main">
        <li><a href="${pageContext.request.contextPath}/start">Start Page</a></li>
        <li>
            <a href="${pageContext.request.contextPath}/products">Products</a>
            <ul>
                <li><a href="${pageContext.request.contextPath}/products">All products</a></li>
                <li><a href="${pageContext.request.contextPath}/headphones">Headphones</a></li>
            </ul>
        </li>
        <li><a href="${pageContext.request.contextPath}/bucket">Bucket</a></li>
        <li><a href="${pageContext.request.contextPath}/user/userPrivateRoom">Private room</a></li>
    </ul>
</nav>

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